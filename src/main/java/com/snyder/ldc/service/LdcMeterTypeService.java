package com.snyder.ldc.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snyder.ldc.entity.LdcMeterType;
import com.snyder.ldc.enums.PressureCategory;
import com.snyder.ldc.enums.Status;
import com.snyder.ldc.exception.DuplicateResourceException;
import com.snyder.ldc.exception.ResourceNotFoundException;
import com.snyder.ldc.model.LdcMeterTypeModel;
import com.snyder.ldc.repository.LdcMeterTypeRepository;

@Service
@Transactional
public class LdcMeterTypeService {
	private static final Logger logger = Logger.getLogger(LdcMeterTypeService.class.getName());

	@Autowired
	private LdcMeterTypeRepository meterTypeRepository;

	public LdcMeterTypeModel createMeterType(LdcMeterTypeModel request) {
		logger.info("Creating LDC Meter Type: " + request.getMeterTypeCode());

		if (meterTypeRepository.findByMeterTypeCode(request.getMeterTypeCode()).isPresent()) {
			throw new DuplicateResourceException("Meter Type code already exists: " + request.getMeterTypeCode());
		}

		LdcMeterType meterType = new LdcMeterType();
		meterType.setMeterTypeCode(request.getMeterTypeCode());
		meterType.setDescription(request.getDescription());
		meterType.setPressureCategory(PressureCategory.valueOf(request.getPressureCategory()).toString());
		meterType.setMaxCapacity(request.getMaxCapacity());
		meterType.setStatus(Status.valueOf(request.getStatus() != null ? request.getStatus() : "ACTIVE").toString());

		LdcMeterType saved = meterTypeRepository.save(meterType);
		logger.info("LDC Meter Type created with ID: " + saved.getId());
		return mapToResponse(saved);
	}

	public LdcMeterTypeModel getMeterTypeById(Long id) {
		LdcMeterType meterType = meterTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("LDC Meter Type not found with ID: " + id));
		return mapToResponse(meterType);
	}

	public List<LdcMeterTypeModel> getAllMeterTypes() {
		return meterTypeRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	public LdcMeterTypeModel updateMeterType(Long id, LdcMeterTypeModel request) {
		logger.info("Updating LDC Meter Type with ID: " + id);

		LdcMeterType meterType = meterTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("LDC Meter Type not found with ID: " + id));

		meterType.setDescription(request.getDescription());
		meterType.setPressureCategory(PressureCategory.valueOf(request.getPressureCategory()).toString());
		meterType.setMaxCapacity(request.getMaxCapacity());
		if (request.getStatus() != null) {
			meterType.setStatus(Status.valueOf(request.getStatus()).toString());
		}

		LdcMeterType updated = meterTypeRepository.save(meterType);
		logger.info("LDC Meter Type updated successfully");
		return mapToResponse(updated);
	}

	public void deleteMeterType(Long id) {
		logger.info("Deleting LDC Meter Type with ID: " + id);

		if (!meterTypeRepository.existsById(id)) {
			throw new ResourceNotFoundException("LDC Meter Type not found with ID: " + id);
		}
		meterTypeRepository.deleteById(id);
		logger.info("LDC Meter Type deleted successfully");
	}

	private LdcMeterTypeModel mapToResponse(LdcMeterType meterType) {
		return new LdcMeterTypeModel(meterType.getId(), meterType.getMeterTypeCode(), meterType.getDescription(),
				meterType.getPressureCategory().toString(), meterType.getMaxCapacity(),
				meterType.getStatus().toString(), meterType.getCreatedAt(), meterType.getUpdatedAt());
	}
}
