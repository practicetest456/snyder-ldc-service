package com.snyder.ldc.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "price_products", uniqueConstraints = { @UniqueConstraint(columnNames = "product_code") })
public class PriceProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_code", nullable = false, unique = true, length = 50)
	private String productCode;

	@Column(name = "product_name", nullable = false, length = 150)
	private String productName;

	@Column(name = "pricing_type", nullable = false)
	private String pricingType;

	@Column(name = "base_price", nullable = false, precision = 15, scale = 6)
	private BigDecimal basePrice;

	@Column(name = "formula_reference", length = 250)
	private String formulaReference;

	@Column(name = "effective_from", nullable = false)
	private LocalDateTime effectiveFrom;

	@Column(name = "effective_to")
	private LocalDateTime effectiveTo;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "pooling_point_id", nullable = false)
	private Long poolingPointId;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	// Constructors
	public PriceProduct() {
	}

	public PriceProduct(String productCode, String productName, String pricingType, BigDecimal basePrice,
			String formulaReference, LocalDateTime effectiveFrom, LocalDateTime effectiveTo, String description,
			Long poolingPointId, String status) {
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
	}

	// Getters and Setters
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

	@Override
	public String toString() {
		return "PriceProduct{" + "id=" + id + ", productCode='" + productCode + '\'' + ", productName='" + productName
				+ '\'' + ", pricingType=" + pricingType + ", basePrice=" + basePrice + ", effectiveFrom="
				+ effectiveFrom + ", effectiveTo=" + effectiveTo + ", poolingPointId=" + poolingPointId + ", status="
				+ status + '}';
	}
}
