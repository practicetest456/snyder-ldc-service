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
@Table(name = "ldc_storage_types", uniqueConstraints = { @UniqueConstraint(columnNames = "storage_type_name") })
public class LdcStorageType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "storage_type_name", nullable = false, unique = true, length = 100)
	private String storageTypeName;

	@Column(name = "capacity", nullable = false, precision = 15, scale = 2)
	private BigDecimal capacity;

	@Column(name = "injection_rate", nullable = false, precision = 15, scale = 4)
	private BigDecimal injectionRate;

	@Column(name = "withdrawal_rate", nullable = false, precision = 15, scale = 4)
	private BigDecimal withdrawalRate;

	@Column(name = "current_storage_volume", precision = 15, scale = 2)
	private BigDecimal currentStorageVolume = BigDecimal.ZERO;

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
		if (this.currentStorageVolume == null) {
			this.currentStorageVolume = BigDecimal.ZERO;
		}
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	// Constructors
	public LdcStorageType() {
	}

	public LdcStorageType(String storageTypeName, BigDecimal capacity, BigDecimal injectionRate,
			BigDecimal withdrawalRate, String status) {
		this.storageTypeName = storageTypeName;
		this.capacity = capacity;
		this.injectionRate = injectionRate;
		this.withdrawalRate = withdrawalRate;
		this.status = status;
		this.currentStorageVolume = BigDecimal.ZERO;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStorageTypeName() {
		return storageTypeName;
	}

	public void setStorageTypeName(String storageTypeName) {
		this.storageTypeName = storageTypeName;
	}

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public BigDecimal getInjectionRate() {
		return injectionRate;
	}

	public void setInjectionRate(BigDecimal injectionRate) {
		this.injectionRate = injectionRate;
	}

	public BigDecimal getWithdrawalRate() {
		return withdrawalRate;
	}

	public void setWithdrawalRate(BigDecimal withdrawalRate) {
		this.withdrawalRate = withdrawalRate;
	}

	public BigDecimal getCurrentStorageVolume() {
		return currentStorageVolume;
	}

	public void setCurrentStorageVolume(BigDecimal currentStorageVolume) {
		this.currentStorageVolume = currentStorageVolume;
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
		return "LdcStorageType{" + "id=" + id + ", storageTypeName='" + storageTypeName + '\'' + ", capacity="
				+ capacity + ", injectionRate=" + injectionRate + ", withdrawalRate=" + withdrawalRate
				+ ", currentStorageVolume=" + currentStorageVolume + ", status=" + status + '}';
	}
}
