package com.snyder.ldc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

public class LdcQuantityModel extends RepresentationModel<LdcQuantityModel> {
	private Long id;
	private Long meterId;
	private String quantityType;
	private BigDecimal volume;
	private String unit;
	private String readingDate;
	private String remarks;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public LdcQuantityModel(Long id, Long meterId, String quantityType, BigDecimal volume, String unit,
			String readingDate, String remarks, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.meterId = meterId;
		this.quantityType = quantityType;
		this.volume = volume;
		this.unit = unit;
		this.readingDate = readingDate;
		this.remarks = remarks;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMeterId() {
		return meterId;
	}

	public void setMeterId(Long meterId) {
		this.meterId = meterId;
	}

	public String getQuantityType() {
		return quantityType;
	}

	public void setQuantityType(String quantityType) {
		this.quantityType = quantityType;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(String readingDate) {
		this.readingDate = readingDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
