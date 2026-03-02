package com.snyder.ldc.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snyder.ldc.entity.MonthlyTol;
import com.snyder.ldc.enums.Status;
import com.snyder.ldc.exception.DuplicateResourceException;
import com.snyder.ldc.exception.ResourceNotFoundException;
import com.snyder.ldc.model.MonthlyTolModel;
import com.snyder.ldc.repository.MonthlyTolRepository;

@Service
@Transactional
public class MonthlyTolService {
	private static final Logger logger = Logger.getLogger(MonthlyTolService.class.getName());

	@Autowired
	private MonthlyTolRepository monthlyTolRepository;

	public MonthlyTolModel createMonthlyTol(MonthlyTolModel request) {
		logger.info("Creating Monthly TOL for meter: " + request.getMeterId() + ", month: " + request.getMonth());

		if (monthlyTolRepository.findByMeterIdAndMonth(request.getMeterId(), request.getMonth()).isPresent()) {
			throw new DuplicateResourceException("Monthly TOL already exists for this meter and month");
		}

		MonthlyTol tol = new MonthlyTol();
		tol.setMeterId(request.getMeterId());
		tol.setMonth(request.getMonth());
		tol.setAllowedPercentage(request.getAllowedPercentage());
		tol.setActualVariance(request.getActualVariance());
		tol.setPenaltyAmount(request.getPenaltyAmount());
		tol.setPenaltyApplied(request.getPenaltyApplied() != null ? request.getPenaltyApplied() : false);
		tol.setRemarks(request.getRemarks());
		tol.setStatus(Status.valueOf(request.getStatus() != null ? request.getStatus() : "ACTIVE").toString());

		MonthlyTol saved = monthlyTolRepository.save(tol);
		logger.info("Monthly TOL created with ID: " + saved.getId());
		return mapToResponse(saved);
	}

	public MonthlyTolModel getMonthlyTolById(Long id) {
		MonthlyTol tol = monthlyTolRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Monthly TOL not found with ID: " + id));
		return mapToResponse(tol);
	}

	public List<MonthlyTolModel> getAllMonthlyTols() {
		return monthlyTolRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	public List<MonthlyTolModel> getMonthlyTolsByMeterId(Long meterId) {
		return monthlyTolRepository.findByMeterId(meterId).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	public MonthlyTolModel updateMonthlyTol(Long id, MonthlyTolModel request) {
		logger.info("Updating Monthly TOL with ID: " + id);

		MonthlyTol tol = monthlyTolRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Monthly TOL not found with ID: " + id));

		tol.setAllowedPercentage(request.getAllowedPercentage());
		tol.setActualVariance(request.getActualVariance());
		tol.setPenaltyAmount(request.getPenaltyAmount());
		tol.setPenaltyApplied(request.getPenaltyApplied());
		tol.setRemarks(request.getRemarks());
		if (request.getStatus() != null) {
			tol.setStatus(Status.valueOf(request.getStatus()).toString());
		}

		MonthlyTol updated = monthlyTolRepository.save(tol);
		logger.info("Monthly TOL updated successfully");
		return mapToResponse(updated);
	}

	public void deleteMonthlyTol(Long id) {
		logger.info("Deleting Monthly TOL with ID: " + id);

		if (!monthlyTolRepository.existsById(id)) {
			throw new ResourceNotFoundException("Monthly TOL not found with ID: " + id);
		}
		monthlyTolRepository.deleteById(id);
		logger.info("Monthly TOL deleted successfully");
	}

	private MonthlyTolModel mapToResponse(MonthlyTol tol) {
		return new MonthlyTolModel(tol.getId(), tol.getMeterId(), tol.getMonth(), tol.getAllowedPercentage(),
				tol.getActualVariance(), tol.getPenaltyAmount(), tol.getPenaltyApplied(), tol.getRemarks(),
				tol.getStatus().toString(), tol.getCreatedAt(), tol.getUpdatedAt());
	}
}
