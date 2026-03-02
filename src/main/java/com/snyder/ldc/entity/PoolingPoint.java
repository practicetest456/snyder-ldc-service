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
@Table(name = "pooling_points", uniqueConstraints = { @UniqueConstraint(columnNames = "pooling_code") })
public class PoolingPoint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "pooling_code", nullable = false, unique = true, length = 50)
	private String poolingCode;

	@Column(name = "location", nullable = false, length = 250)
	private String location;

	@Column(name = "capacity", nullable = false, precision = 15, scale = 2)
	private BigDecimal capacity;

	@Column(name = "current_volume", precision = 15, scale = 2)
	private BigDecimal currentVolume = BigDecimal.ZERO;

	@Column(name = "active_contracts", nullable = false)
	private Integer activeContracts = 0;

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
		if (this.currentVolume == null) {
			this.currentVolume = BigDecimal.ZERO;
		}
		if (this.activeContracts == null) {
			this.activeContracts = 0;
		}
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	// Constructors
	public PoolingPoint() {
	}

	public PoolingPoint(String poolingCode, String location, BigDecimal capacity, Integer activeContracts,
			Long serviceAreaId, String status) {
		this.poolingCode = poolingCode;
		this.location = location;
		this.capacity = capacity;
		this.activeContracts = activeContracts;
		this.serviceAreaId = serviceAreaId;
		this.status = status;
		this.currentVolume = BigDecimal.ZERO;
	}

	// Getters and Setters
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

	@Override
	public String toString() {
		return "PoolingPoint{" + "id=" + id + ", poolingCode='" + poolingCode + '\'' + ", location='" + location + '\''
				+ ", capacity=" + capacity + ", currentVolume=" + currentVolume + ", activeContracts=" + activeContracts
				+ ", serviceAreaId=" + serviceAreaId + ", status=" + status + '}';
	}
}
