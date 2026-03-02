package com.snyder.ldc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snyder.ldc.entity.LdcTariff;
import com.snyder.ldc.enums.Status;
import com.snyder.ldc.exception.DateValidationException;
import com.snyder.ldc.exception.DuplicateResourceException;
import com.snyder.ldc.exception.ResourceNotFoundException;
import com.snyder.ldc.exception.ValidationException;
import com.snyder.ldc.model.LdcTariffModel;
import com.snyder.ldc.repository.LdcTariffRepository;

@Service
@Transactional
public class LdcTariffService {
	private static final Logger logger = Logger.getLogger(LdcTariffService.class.getName());

	@Autowired
	private LdcTariffRepository tariffRepository;

	public LdcTariffModel createTariff(LdcTariffModel request) {
		logger.info("Creating LDC Tariff: " + request.getTariffCode());

		if (tariffRepository.findByTariffCode(request.getTariffCode()).isPresent()) {
			throw new DuplicateResourceException("Tariff code already exists: " + request.getTariffCode());
		}

		LocalDateTime effectiveFrom = request.getEffectiveFrom();
		LocalDateTime effectiveTo = request.getEffectiveTo() != null ? request.getEffectiveTo() : null;

		if (effectiveTo != null && effectiveFrom.isAfter(effectiveTo)) {
			throw new DateValidationException("Effective from date must be before effective to date");
		}

		if (request.getSlabFrom().compareTo(request.getSlabTo()) >= 0) {
			throw new ValidationException("Slab from must be less than slab to");
		}

		LdcTariff tariff = new LdcTariff();
		tariff.setTariffCode(request.getTariffCode());
		tariff.setMeterTypeId(request.getMeterTypeId());
		tariff.setRatePerUnit(request.getRatePerUnit());
		tariff.setSlabFrom(request.getSlabFrom());
		tariff.setSlabTo(request.getSlabTo());
		tariff.setEffectiveFrom(effectiveFrom);
		tariff.setEffectiveTo(effectiveTo);
		tariff.setStatus(Status.valueOf(request.getStatus() != null ? request.getStatus() : "ACTIVE").toString());

		LdcTariff saved = tariffRepository.save(tariff);
		logger.info("LDC Tariff created with ID: " + saved.getId());
		return mapToResponse(saved);
	}

	public LdcTariffModel getTariffById(Long id) {
		LdcTariff tariff = tariffRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("LDC Tariff not found with ID: " + id));
		return mapToResponse(tariff);
	}

	public List<LdcTariffModel> getAllTariffs() {
		return tariffRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	public List<LdcTariffModel> getTariffsByMeterType(Long meterTypeId) {
		return tariffRepository.findByMeterTypeId(meterTypeId).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	public LdcTariffModel updateTariff(Long id, LdcTariffModel request) {
		logger.info("Updating LDC Tariff with ID: " + id);

		LdcTariff tariff = tariffRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("LDC Tariff not found with ID: " + id));

		LocalDateTime effectiveFrom = request.getEffectiveFrom();
		LocalDateTime effectiveTo = request.getEffectiveTo() != null ? request.getEffectiveTo() : null;

		if (effectiveTo != null && effectiveFrom.isAfter(effectiveTo)) {
			throw new DateValidationException("Effective from date must be before effective to date");
		}

		tariff.setMeterTypeId(request.getMeterTypeId());
		tariff.setRatePerUnit(request.getRatePerUnit());
		tariff.setSlabFrom(request.getSlabFrom());
		tariff.setSlabTo(request.getSlabTo());
		tariff.setEffectiveFrom(effectiveFrom);
		tariff.setEffectiveTo(effectiveTo);
		if (request.getStatus() != null) {
			tariff.setStatus(Status.valueOf(request.getStatus()).toString());
		}

		LdcTariff updated = tariffRepository.save(tariff);
		logger.info("LDC Tariff updated successfully");
		return mapToResponse(updated);
	}

	public void deleteTariff(Long id) {
		logger.info("Deleting LDC Tariff with ID: " + id);

		if (!tariffRepository.existsById(id)) {
			throw new ResourceNotFoundException("LDC Tariff not found with ID: " + id);
		}
		tariffRepository.deleteById(id);
		logger.info("LDC Tariff deleted successfully");
	}

	private LdcTariffModel mapToResponse(LdcTariff tariff) {
		return new LdcTariffModel(tariff.getId(), tariff.getTariffCode(), tariff.getMeterTypeId(),
				tariff.getRatePerUnit(), tariff.getSlabFrom(), tariff.getSlabTo(), tariff.getEffectiveFrom(),
				tariff.getEffectiveTo(), tariff.getStatus().toString(), tariff.getCreatedAt(), tariff.getUpdatedAt());
	}
}
