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
@Table(name = "ldc_tariffs", uniqueConstraints = { @UniqueConstraint(columnNames = "tariff_code") })
public class LdcTariff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "tariff_code", nullable = false, unique = true, length = 50)
	private String tariffCode;

	@Column(name = "meter_type_id", nullable = false)
	private Long meterTypeId;

	@Column(name = "rate_per_unit", nullable = false, precision = 15, scale = 6)
	private BigDecimal ratePerUnit;

	@Column(name = "slab_from", nullable = false, precision = 15, scale = 2)
	private BigDecimal slabFrom;

	@Column(name = "slab_to", nullable = false, precision = 15, scale = 2)
	private BigDecimal slabTo;

	@Column(name = "effective_from", nullable = false)
	private LocalDateTime effectiveFrom;

	@Column(name = "effective_to")
	private LocalDateTime effectiveTo;

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
	public LdcTariff() {
	}

	public LdcTariff(String tariffCode, Long meterTypeId, BigDecimal ratePerUnit, BigDecimal slabFrom,
			BigDecimal slabTo, LocalDateTime effectiveFrom, LocalDateTime effectiveTo, String status) {
		this.tariffCode = tariffCode;
		this.meterTypeId = meterTypeId;
		this.ratePerUnit = ratePerUnit;
		this.slabFrom = slabFrom;
		this.slabTo = slabTo;
		this.effectiveFrom = effectiveFrom;
		this.effectiveTo = effectiveTo;
		this.status = status;
	}

	// Getters and Setters
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

	@Override
	public String toString() {
		return "LdcTariff{" + "id=" + id + ", tariffCode='" + tariffCode + '\'' + ", meterTypeId=" + meterTypeId
				+ ", ratePerUnit=" + ratePerUnit + ", slabFrom=" + slabFrom + ", slabTo=" + slabTo + ", effectiveFrom="
				+ effectiveFrom + ", effectiveTo=" + effectiveTo + ", status=" + status + '}';
	}
}
