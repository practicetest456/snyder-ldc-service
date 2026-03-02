package com.snyder.ldc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

public class LdcStorageTypeModel extends RepresentationModel<LdcStorageTypeModel> {
	private Long id;
	private String storageTypeName;
	private BigDecimal capacity;
	private BigDecimal injectionRate;
	private BigDecimal withdrawalRate;
	private BigDecimal currentStorageVolume;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public LdcStorageTypeModel(Long id, String storageTypeName, BigDecimal capacity, BigDecimal injectionRate,
			BigDecimal withdrawalRate, BigDecimal currentStorageVolume, String status, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.storageTypeName = storageTypeName;
		this.capacity = capacity;
		this.injectionRate = injectionRate;
		this.withdrawalRate = withdrawalRate;
		this.currentStorageVolume = currentStorageVolume;
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
}
