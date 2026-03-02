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
@Table(name = "ldc_meter_types", uniqueConstraints = { @UniqueConstraint(columnNames = "meter_type_code") })
public class LdcMeterType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "meter_type_code", nullable = false, unique = true, length = 50)
	private String meterTypeCode;

	@Column(name = "description", nullable = false, length = 250)
	private String description;

	@Column(name = "pressure_category", nullable = false)
	private String pressureCategory;

	@Column(name = "max_capacity", nullable = false, precision = 15, scale = 2)
	private BigDecimal maxCapacity;

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
	public LdcMeterType() {
	}

	public LdcMeterType(String meterTypeCode, String description, String pressureCategory, BigDecimal maxCapacity,
			String status) {
		this.meterTypeCode = meterTypeCode;
		this.description = description;
		this.pressureCategory = pressureCategory;
		this.maxCapacity = maxCapacity;
		this.status = status;
	}

	// Getters and Setters
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

	@Override
	public String toString() {
		return "LdcMeterType{" + "id=" + id + ", meterTypeCode='" + meterTypeCode + '\'' + ", description='"
				+ description + '\'' + ", pressureCategory=" + pressureCategory + ", maxCapacity=" + maxCapacity
				+ ", status=" + status + '}';
	}
}
