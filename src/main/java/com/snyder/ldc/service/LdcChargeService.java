package com.snyder.ldc.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snyder.ldc.entity.LdcCharge;
import com.snyder.ldc.enums.ChargeType;
import com.snyder.ldc.enums.Status;
import com.snyder.ldc.exception.DateValidationException;
import com.snyder.ldc.exception.DuplicateResourceException;
import com.snyder.ldc.exception.ResourceNotFoundException;
import com.snyder.ldc.model.LdcChargeModel;
import com.snyder.ldc.repository.LdcChargeRepository;

@Service
@Transactional
public class LdcChargeService {
	private static final Logger logger = Logger.getLogger(LdcChargeService.class.getName());

	@Autowired
	private LdcChargeRepository chargeRepository;

	public LdcChargeModel createCharge(LdcChargeModel request) {
		logger.info("Creating LDC Charge: " + request.getChargeCode());

		// Check for duplicates
		if (chargeRepository.findByChargeCode(request.getChargeCode()).isPresent()) {
			throw new DuplicateResourceException("Charge code already exists: " + request.getChargeCode());
		}

		// Validate dates
		if (request.getEffectiveTo() != null && request.getEffectiveFrom().isAfter(request.getEffectiveTo())) {
			throw new DateValidationException("Effective from date must be before effective to date");
		}

		LdcCharge charge = new LdcCharge();
		charge.setChargeCode(request.getChargeCode());
		charge.setChargeName(request.getChargeName());
		charge.setChargeType(ChargeType.valueOf(request.getChargeType()).toString());
		charge.setAmount(request.getAmount());
		charge.setEffectiveFrom(request.getEffectiveFrom());
		charge.setEffectiveTo(request.getEffectiveTo());
		charge.setServiceAreaId(request.getServiceAreaId());
		charge.setStatus(Status.valueOf(request.getStatus() != null ? request.getStatus() : "ACTIVE").toString());

		LdcCharge saved = chargeRepository.save(charge);
		logger.info("LDC Charge created with ID: " + saved.getId());
		return mapToResponse(saved);
	}

	public LdcChargeModel getChargeById(Long id) {
		LdcCharge charge = chargeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("LDC Charge not found with ID: " + id));
		return mapToResponse(charge);
	}

	public List<LdcChargeModel> getAllCharges() {
		return chargeRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	public List<LdcChargeModel> getChargesByServiceArea(Long serviceAreaId) {
		return chargeRepository.findByServiceAreaId(serviceAreaId).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	public LdcChargeModel updateCharge(Long id, LdcChargeModel request) {
		logger.info("Updating LDC Charge with ID: " + id);

		LdcCharge charge = chargeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("LDC Charge not found with ID: " + id));

		// Validate dates
		if (request.getEffectiveTo() != null && request.getEffectiveFrom().isAfter(request.getEffectiveTo())) {
			throw new DateValidationException("Effective from date must be before effective to date");
		}

		charge.setChargeName(request.getChargeName());
		charge.setChargeType(ChargeType.valueOf(request.getChargeType()).toString());
		charge.setAmount(request.getAmount());
		charge.setEffectiveFrom(request.getEffectiveFrom());
		charge.setEffectiveTo(request.getEffectiveTo());
		charge.setServiceAreaId(request.getServiceAreaId());
		if (request.getStatus() != null) {
			charge.setStatus(Status.valueOf(request.getStatus()).toString());
		}

		LdcCharge updated = chargeRepository.save(charge);
		logger.info("LDC Charge updated successfully");
		return mapToResponse(updated);
	}

	public void deleteCharge(Long id) {
		logger.info("Deleting LDC Charge with ID: " + id);

		if (!chargeRepository.existsById(id)) {
			throw new ResourceNotFoundException("LDC Charge not found with ID: " + id);
		}
		chargeRepository.deleteById(id);
		logger.info("LDC Charge deleted successfully");
	}

	private LdcChargeModel mapToResponse(LdcCharge charge) {
		return new LdcChargeModel(charge.getId(), charge.getChargeCode(), charge.getChargeName(),
				charge.getChargeType().toString(), charge.getAmount(), charge.getEffectiveFrom(),
				charge.getEffectiveTo(), charge.getServiceAreaId(), charge.getStatus().toString(),
				charge.getCreatedAt(), charge.getUpdatedAt());
	}
}
