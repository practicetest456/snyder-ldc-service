package com.snyder.ldc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

public class PoolingPointModel extends RepresentationModel<PoolingPointModel> {
	private Long id;
	private String poolingCode;
	private String location;
	private BigDecimal capacity;
	private BigDecimal currentVolume;
	private Integer activeContracts;
	private Long serviceAreaId;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public PoolingPointModel(Long id, String poolingCode, String location, BigDecimal capacity,
			BigDecimal currentVolume, Integer activeContracts, Long serviceAreaId, String status,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.poolingCode = poolingCode;
		this.location = location;
		this.capacity = capacity;
		this.currentVolume = currentVolume;
		this.activeContracts = activeContracts;
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

	public String getPoolingCode() {
		return poolingCode;
	}

	public void setPoolingCode(String poolingCode) {
		this.poolingCode = poolingCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public BigDecimal getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(BigDecimal currentVolume) {
		this.currentVolume = currentVolume;
	}

	public Integer getActiveContracts() {
		return activeContracts;
	}

	public void setActiveContracts(Integer activeContracts) {
		this.activeContracts = activeContracts;
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
