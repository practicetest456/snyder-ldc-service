package com.snyder.ldc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

public class PriceProductModel extends RepresentationModel<PriceProductModel> {
	private Long id;
	private String productCode;
	private String productName;
	private String pricingType;
	private BigDecimal basePrice;
	private String formulaReference;
	private LocalDateTime effectiveFrom;
	private LocalDateTime effectiveTo;
	private String description;
	private Long poolingPointId;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public PriceProductModel(Long id, String productCode, String productName, String pricingType, BigDecimal basePrice,
			String formulaReference, LocalDateTime effectiveFrom, LocalDateTime effectiveTo, String description,
			Long poolingPointId, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.productCode = productCode;
		this.productName = productName;
		this.pricingType = pricingType;
		this.basePrice = basePrice;
		this.formulaReference = formulaReference;
		this.effectiveFrom = effectiveFrom;
		this.effectiveTo = effectiveTo;
		this.description = description;
		this.poolingPointId = poolingPointId;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPricingType() {
		return pricingType;
	}

	public void setPricingType(String pricingType) {
		this.pricingType = pricingType;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public String getFormulaReference() {
		return formulaReference;
	}

	public void setFormulaReference(String formulaReference) {
		this.formulaReference = formulaReference;
	}

	public LocalDateTime getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(LocalDateTime effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public LocalDateTime getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(LocalDateTime effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPoolingPointId() {
		return poolingPointId;
	}

	public void setPoolingPointId(Long poolingPointId) {
		this.poolingPointId = poolingPointId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
