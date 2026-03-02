package com.snyder.ldc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snyder.ldc.entity.PriceProduct;
import com.snyder.ldc.enums.PricingType;
import com.snyder.ldc.enums.Status;
import com.snyder.ldc.exception.DateValidationException;
import com.snyder.ldc.exception.DuplicateResourceException;
import com.snyder.ldc.exception.ResourceNotFoundException;
import com.snyder.ldc.model.PriceProductModel;
import com.snyder.ldc.repository.PriceProductRepository;

@Service
@Transactional
public class PriceProductService {
	private static final Logger logger = Logger.getLogger(PriceProductService.class.getName());

	@Autowired
	private PriceProductRepository priceProductRepository;

	public PriceProductModel createPriceProduct(PriceProductModel request) {
		logger.info("Creating Price Product: " + request.getProductCode());

		if (priceProductRepository.findByProductCode(request.getProductCode()).isPresent()) {
			throw new DuplicateResourceException("Product code already exists: " + request.getProductCode());
		}

		LocalDateTime effectiveFrom = request.getEffectiveFrom();
		LocalDateTime effectiveTo = request.getEffectiveTo() != null ? request.getEffectiveTo() : null;

		if (effectiveTo != null && effectiveFrom.isAfter(effectiveTo)) {
			throw new DateValidationException("Effective from date must be before effective to date");
		}

		PriceProduct product = new PriceProduct();
		product.setProductCode(request.getProductCode());
		product.setProductName(request.getProductName());
		product.setPricingType(PricingType.valueOf(request.getPricingType()).toString());
		product.setBasePrice(request.getBasePrice());
		product.setFormulaReference(request.getFormulaReference());
		product.setEffectiveFrom(effectiveFrom);
		product.setEffectiveTo(effectiveTo);
		product.setDescription(request.getDescription());
		product.setPoolingPointId(request.getPoolingPointId());
		product.setStatus(Status.valueOf(request.getStatus() != null ? request.getStatus() : "ACTIVE").toString());

		PriceProduct saved = priceProductRepository.save(product);
		logger.info("Price Product created with ID: " + saved.getId());
		return mapToResponse(saved);
	}

	public PriceProductModel getPriceProductById(Long id) {
		PriceProduct product = priceProductRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Price Product not found with ID: " + id));
		return mapToResponse(product);
	}

	public List<PriceProductModel> getAllPriceProducts() {
		return priceProductRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	public List<PriceProductModel> getPriceProductsByPoolingPoint(Long poolingPointId) {
		return priceProductRepository.findByPoolingPointId(poolingPointId).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	public PriceProductModel updatePriceProduct(Long id, PriceProductModel request) {
		logger.info("Updating Price Product with ID: " + id);

		PriceProduct product = priceProductRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Price Product not found with ID: " + id));

		LocalDateTime effectiveFrom = request.getEffectiveFrom();
		LocalDateTime effectiveTo = request.getEffectiveTo() != null ? request.getEffectiveTo() : null;

		if (effectiveTo != null && effectiveFrom.isAfter(effectiveTo)) {
			throw new DateValidationException("Effective from date must be before effective to date");
		}

		product.setProductName(request.getProductName());
		product.setPricingType(PricingType.valueOf(request.getPricingType()).toString());
		product.setBasePrice(request.getBasePrice());
		product.setFormulaReference(request.getFormulaReference());
		product.setEffectiveFrom(effectiveFrom);
		product.setEffectiveTo(effectiveTo);
		product.setDescription(request.getDescription());
		product.setPoolingPointId(request.getPoolingPointId());
		if (request.getStatus() != null) {
			product.setStatus(Status.valueOf(request.getStatus()).toString());
		}

		PriceProduct updated = priceProductRepository.save(product);
		logger.info("Price Product updated successfully");
		return mapToResponse(updated);
	}

	public void deletePriceProduct(Long id) {
		logger.info("Deleting Price Product with ID: " + id);

		if (!priceProductRepository.existsById(id)) {
			throw new ResourceNotFoundException("Price Product not found with ID: " + id);
		}
		priceProductRepository.deleteById(id);
		logger.info("Price Product deleted successfully");
	}

	private PriceProductModel mapToResponse(PriceProduct product) {
		return new PriceProductModel(product.getId(), product.getProductCode(), product.getProductName(),
				product.getPricingType().toString(), product.getBasePrice(), product.getFormulaReference(),
				product.getEffectiveFrom(), product.getEffectiveTo(), product.getDescription(),
				product.getPoolingPointId(), product.getStatus().toString(), product.getCreatedAt(),
				product.getUpdatedAt());
	}
}
