package com.snyder.ldc.service;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snyder.ldc.entity.LdcQuantity;
import com.snyder.ldc.enums.QuantityType;
import com.snyder.ldc.enums.VolumeUnit;
import com.snyder.ldc.exception.ResourceNotFoundException;
import com.snyder.ldc.model.LdcQuantityModel;
import com.snyder.ldc.repository.LdcQuantityRepository;

@Service
@Transactional
public class LdcQuantityService {
	private static final Logger logger = Logger.getLogger(LdcQuantityService.class.getName());

	@Autowired
	private LdcQuantityRepository quantityRepository;

	public LdcQuantityModel recordQuantity(LdcQuantityModel request) {
		logger.info("Recording LDC Quantity for meter: " + request.getMeterId());

		LdcQuantity quantity = new LdcQuantity();
		quantity.setMeterId(request.getMeterId());
		quantity.setQuantityType(QuantityType.valueOf(request.getQuantityType()).toString());
		quantity.setVolume(request.getVolume());
		quantity.setUnit(VolumeUnit.valueOf(request.getUnit()).toString());
		quantity.setReadingDate(LocalDate.parse(request.getReadingDate()));
		quantity.setRemarks(request.getRemarks());

		LdcQuantity saved = quantityRepository.save(quantity);
		logger.info("LDC Quantity recorded with ID: " + saved.getId());
		return mapToResponse(saved);
	}

	public LdcQuantityModel getQuantityById(Long id) {
		LdcQuantity quantity = quantityRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("LDC Quantity not found with ID: " + id));
		return mapToResponse(quantity);
	}

	public List<LdcQuantityModel> getAllQuantities() {
		return quantityRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	public List<LdcQuantityModel> getQuantitiesByMeterId(Long meterId) {
		return quantityRepository.findByMeterId(meterId).stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	public LdcQuantityModel updateQuantity(Long id, LdcQuantityModel request) {
		logger.info("Updating LDC Quantity with ID: " + id);

		LdcQuantity quantity = quantityRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("LDC Quantity not found with ID: " + id));

		quantity.setQuantityType(QuantityType.valueOf(request.getQuantityType()).toString());
		quantity.setVolume(request.getVolume());
		quantity.setUnit(VolumeUnit.valueOf(request.getUnit()).toString());
		quantity.setReadingDate(LocalDate.parse(request.getReadingDate()));
		quantity.setRemarks(request.getRemarks());

		LdcQuantity updated = quantityRepository.save(quantity);
		logger.info("LDC Quantity updated successfully");
		return mapToResponse(updated);
	}

	public void deleteQuantity(Long id) {
		logger.info("Deleting LDC Quantity with ID: " + id);

		if (!quantityRepository.existsById(id)) {
			throw new ResourceNotFoundException("LDC Quantity not found with ID: " + id);
		}
		quantityRepository.deleteById(id);
		logger.info("LDC Quantity deleted successfully");
	}

	private LdcQuantityModel mapToResponse(LdcQuantity quantity) {
		return new LdcQuantityModel(quantity.getId(), quantity.getMeterId(), quantity.getQuantityType().toString(),
				quantity.getVolume(), quantity.getUnit().toString(), quantity.getReadingDate().toString(),
				quantity.getRemarks(), quantity.getCreatedAt(), quantity.getUpdatedAt());
	}
}
