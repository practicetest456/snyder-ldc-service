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
@Table(name = "ldc_charges", uniqueConstraints = { @UniqueConstraint(columnNames = "charge_code") })
public class LdcCharge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "charge_code", nullable = false, unique = true, length = 50)
	private String chargeCode;

	@Column(name = "charge_name", nullable = false, length = 100)
	private String chargeName;

	@Column(name = "charge_type", nullable = false)
	private String chargeType;

	@Column(name = "amount", nullable = false, precision = 15, scale = 4)
	private BigDecimal amount;

	@Column(name = "effective_from", nullable = false)
	private LocalDateTime effectiveFrom;

	@Column(name = "effective_to")
	private LocalDateTime effectiveTo;

	@Column(name = "service_area_id", nullable = false)
	private Long serviceAreaId;

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
	public LdcCharge() {
	}

	public LdcCharge(String chargeCode, String chargeName, String chargeType, BigDecimal amount,
			LocalDateTime effectiveFrom, LocalDateTime effectiveTo, Long serviceAreaId, String status) {
		this.chargeCode = chargeCode;
		this.chargeName = chargeName;
		this.chargeType = chargeType;
		this.amount = amount;
		this.effectiveFrom = effectiveFrom;
		this.effectiveTo = effectiveTo;
		this.serviceAreaId = serviceAreaId;
		this.status = status;
	}

	// Getters and Setters
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

	@Override
	public String toString() {
		return "LdcCharge{" + "id=" + id + ", chargeCode='" + chargeCode + '\'' + ", chargeName='" + chargeName + '\''
				+ ", chargeType=" + chargeType + ", amount=" + amount + ", effectiveFrom=" + effectiveFrom
				+ ", effectiveTo=" + effectiveTo + ", serviceAreaId=" + serviceAreaId + ", status=" + status + '}';
	}
}
