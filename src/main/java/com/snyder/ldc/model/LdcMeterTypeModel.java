package com.snyder.ldc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

public class LdcMeterTypeModel extends RepresentationModel<LdcMeterTypeModel> {
	private Long id;
	private String meterTypeCode;
	private String description;
	private String pressureCategory;
	private BigDecimal maxCapacity;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public LdcMeterTypeModel(Long id, String meterTypeCode, String description, String pressureCategory,
			BigDecimal maxCapacity, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.meterTypeCode = meterTypeCode;
		this.description = description;
		this.pressureCategory = pressureCategory;
		this.maxCapacity = maxCapacity;
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

	public String getMeterTypeCode() {
		return meterTypeCode;
	}

	public void setMeterTypeCode(String meterTypeCode) {
		this.meterTypeCode = meterTypeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPressureCategory() {
		return pressureCategory;
	}

	public void setPressureCategory(String pressureCategory) {
		this.pressureCategory = pressureCategory;
	}

	public BigDecimal getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(BigDecimal maxCapacity) {
		this.maxCapacity = maxCapacity;
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