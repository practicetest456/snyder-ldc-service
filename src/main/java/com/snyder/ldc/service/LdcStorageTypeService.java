package com.snyder.ldc.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snyder.ldc.entity.LdcStorageType;
import com.snyder.ldc.enums.Status;
import com.snyder.ldc.exception.DuplicateResourceException;
import com.snyder.ldc.exception.ResourceNotFoundException;
import com.snyder.ldc.model.LdcStorageTypeModel;
import com.snyder.ldc.repository.LdcStorageTypeRepository;

@Service
@Transactional
public class LdcStorageTypeService {
	private static final Logger logger = Logger.getLogger(LdcStorageTypeService.class.getName());

	@Autowired
	private LdcStorageTypeRepository storageTypeRepository;

	public LdcStorageTypeModel createStorageType(LdcStorageTypeModel request) {
		logger.info("Creating LDC Storage Type: " + request.getStorageTypeName());

		if (storageTypeRepository.findByStorageTypeName(request.getStorageTypeName()).isPresent()) {
			throw new DuplicateResourceException("Storage Type already exists: " + request.getStorageTypeName());
		}

		LdcStorageType storageType = new LdcStorageType();
		storageType.setStorageTypeName(request.getStorageTypeName());
		storageType.setCapacity(request.getCapacity());
		storageType.setInjectionRate(request.getInjectionRate());
		storageType.setWithdrawalRate(request.getWithdrawalRate());
		storageType.setStatus(Status.valueOf(request.getStatus() != null ? request.getStatus() : "ACTIVE").toString());

		LdcStorageType saved = storageTypeRepository.save(storageType);
		logger.info("LDC Storage Type created with ID: " + saved.getId());
		return mapToResponse(saved);
	}

	public LdcStorageTypeModel getStorageTypeById(Long id) {
		LdcStorageType storageType = storageTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("LDC Storage Type not found with ID: " + id));
		return mapToResponse(storageType);
	}

	public List<LdcStorageTypeModel> getAllStorageTypes() {
		return storageTypeRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	public LdcStorageTypeModel updateStorageType(Long id, LdcStorageTypeModel request) {
		logger.info("Updating LDC Storage Type with ID: " + id);

		LdcStorageType storageType = storageTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("LDC Storage Type not found with ID: " + id));

		storageType.setCapacity(request.getCapacity());
		storageType.setInjectionRate(request.getInjectionRate());
		storageType.setWithdrawalRate(request.getWithdrawalRate());
		if (request.getStatus() != null) {
			storageType.setStatus(Status.valueOf(request.getStatus()).toString());
		}

		LdcStorageType updated = storageTypeRepository.save(storageType);
		logger.info("LDC Storage Type updated successfully");
		return mapToResponse(updated);
	}

	public void deleteStorageType(Long id) {
		logger.info("Deleting LDC Storage Type with ID: " + id);

		if (!storageTypeRepository.existsById(id)) {
			throw new ResourceNotFoundException("LDC Storage Type not found with ID: " + id);
		}
		storageTypeRepository.deleteById(id);
		logger.info("LDC Storage Type deleted successfully");
	}

	private LdcStorageTypeModel mapToResponse(LdcStorageType storageType) {
		return new LdcStorageTypeModel(storageType.getId(), storageType.getStorageTypeName(), storageType.getCapacity(),
				storageType.getInjectionRate(), storageType.getWithdrawalRate(), storageType.getCurrentStorageVolume(),
				storageType.getStatus().toString(), storageType.getCreatedAt(), storageType.getUpdatedAt());
	}
}
