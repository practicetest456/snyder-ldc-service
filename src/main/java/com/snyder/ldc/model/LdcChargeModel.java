package com.snyder.ldc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

public class LdcChargeModel extends RepresentationModel<LdcChargeModel> {
	private Long id;
	private String chargeCode;
	private String chargeName;
	private String chargeType;
	private BigDecimal amount;
	private LocalDateTime effectiveFrom;
	private LocalDateTime effectiveTo;
	private Long serviceAreaId;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public LdcChargeModel(Long id, String chargeCode, String chargeName, String chargeType, BigDecimal amount,
			LocalDateTime effectiveFrom, LocalDateTime effectiveTo, Long serviceAreaId, String status,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.chargeCode = chargeCode;
		this.chargeName = chargeName;
		this.chargeType = chargeType;
		this.amount = amount;
		this.effectiveFrom = effectiveFrom;
		this.effectiveTo = effectiveTo;
		this.serviceAreaId = serviceAreaId;
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

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public Long getServiceAreaId() {
		return serviceAreaId;
	}

	public void setServiceAreaId(Long serviceAreaId) {
		this.serviceAreaId = serviceAreaId;
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
