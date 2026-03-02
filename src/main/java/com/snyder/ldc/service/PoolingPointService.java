package com.snyder.ldc.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snyder.ldc.entity.PoolingPoint;
import com.snyder.ldc.enums.Status;
import com.snyder.ldc.exception.DuplicateResourceException;
import com.snyder.ldc.exception.ResourceNotFoundException;
import com.snyder.ldc.model.PoolingPointModel;
import com.snyder.ldc.repository.PoolingPointRepository;

@Service
@Transactional
public class PoolingPointService {
	private static final Logger logger = Logger.getLogger(PoolingPointService.class.getName());

	@Autowired
	private PoolingPointRepository poolingPointRepository;

	public PoolingPointModel createPoolingPoint(PoolingPointModel request) {
		logger.info("Creating Pooling Point: " + request.getPoolingCode());

		if (poolingPointRepository.findByPoolingCode(request.getPoolingCode()).isPresent()) {
			throw new DuplicateResourceException("Pooling Point code already exists: " + request.getPoolingCode());
		}

		PoolingPoint poolingPoint = new PoolingPoint();
		poolingPoint.setPoolingCode(request.getPoolingCode());
		poolingPoint.setLocation(request.getLocation());
		poolingPoint.setCapacity(request.getCapacity());
		poolingPoint.setActiveContracts(request.getActiveContracts() != null ? request.getActiveContracts() : 0);
		poolingPoint.setServiceAreaId(request.getServiceAreaId());
		poolingPoint.setStatus(Status.valueOf(request.getStatus() != null ? request.getStatus() : "ACTIVE").toString());

		PoolingPoint saved = poolingPointRepository.save(poolingPoint);
		logger.info("Pooling Point created with ID: " + saved.getId());
		return mapToResponse(saved);
	}

	public PoolingPointModel getPoolingPointById(Long id) {
		PoolingPoint poolingPoint = poolingPointRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pooling Point not found with ID: " + id));
		return mapToResponse(poolingPoint);
	}

	public List<PoolingPointModel> getAllPoolingPoints() {
		return poolingPointRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	public List<PoolingPointModel> getPoolingPointsByServiceArea(Long serviceAreaId) {
		return poolingPointRepository.findByServiceAreaId(serviceAreaId).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	public PoolingPointModel updatePoolingPoint(Long id, PoolingPointModel request) {
		logger.info("Updating Pooling Point with ID: " + id);

		PoolingPoint poolingPoint = poolingPointRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pooling Point not found with ID: " + id));

		poolingPoint.setLocation(request.getLocation());
		poolingPoint.setCapacity(request.getCapacity());
		poolingPoint.setActiveContracts(request.getActiveContracts());
		poolingPoint.setServiceAreaId(request.getServiceAreaId());
		if (request.getStatus() != null) {
			poolingPoint.setStatus(Status.valueOf(request.getStatus()).toString());
		}

		PoolingPoint updated = poolingPointRepository.save(poolingPoint);
		logger.info("Pooling Point updated successfully");
		return mapToResponse(updated);
	}

	public void deletePoolingPoint(Long id) {
		logger.info("Deleting Pooling Point with ID: " + id);

		if (!poolingPointRepository.existsById(id)) {
			throw new ResourceNotFoundException("Pooling Point not found with ID: " + id);
		}
		poolingPointRepository.deleteById(id);
		logger.info("Pooling Point deleted successfully");
	}

	private PoolingPointModel mapToResponse(PoolingPoint poolingPoint) {
		return new PoolingPointModel(poolingPoint.getId(), poolingPoint.getPoolingCode(), poolingPoint.getLocation(),
				poolingPoint.getCapacity(), poolingPoint.getCurrentVolume(), poolingPoint.getActiveContracts(),
				poolingPoint.getServiceAreaId(), poolingPoint.getStatus().toString(), poolingPoint.getCreatedAt(),
				poolingPoint.getUpdatedAt());
	}
}
