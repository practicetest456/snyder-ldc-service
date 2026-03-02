package com.snyder.ldc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

public class LdcTariffModel extends RepresentationModel<LdcTariffModel> {
	private Long id;
	private String tariffCode;
	private Long meterTypeId;
	private BigDecimal ratePerUnit;
	private BigDecimal slabFrom;
	private BigDecimal slabTo;
	private LocalDateTime effectiveFrom;
	private LocalDateTime effectiveTo;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public LdcTariffModel(Long id, String tariffCode, Long meterTypeId, BigDecimal ratePerUnit, BigDecimal slabFrom,
			BigDecimal slabTo, LocalDateTime effectiveFrom, LocalDateTime effectiveTo, String status,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.tariffCode = tariffCode;
		this.meterTypeId = meterTypeId;
		this.ratePerUnit = ratePerUnit;
		this.slabFrom = slabFrom;
		this.slabTo = slabTo;
		this.effectiveFrom = effectiveFrom;
		this.effectiveTo = effectiveTo;
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

	public String getTariffCode() {
		return tariffCode;
	}

	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}

	public Long getMeterTypeId() {
		return meterTypeId;
	}

	public void setMeterTypeId(Long meterTypeId) {
		this.meterTypeId = meterTypeId;
	}

	public BigDecimal getRatePerUnit() {
		return ratePerUnit;
	}

	public void setRatePerUnit(BigDecimal ratePerUnit) {
		this.ratePerUnit = ratePerUnit;
	}

	public BigDecimal getSlabFrom() {
		return slabFrom;
	}

	public void setSlabFrom(BigDecimal slabFrom) {
		this.slabFrom = slabFrom;
	}

	public BigDecimal getSlabTo() {
		return slabTo;
	}

	public void setSlabTo(BigDecimal slabTo) {
		this.slabTo = slabTo;
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
