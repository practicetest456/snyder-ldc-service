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
@Table(name = "monthly_tol", uniqueConstraints = { @UniqueConstraint(columnNames = { "meter_id", "month_code" }) })
public class MonthlyTol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "meter_id", nullable = false)
	private Long meterId;

	@Column(name = "month_code", nullable = false)
	private String month; // Format: YYYY-MM

	@Column(name = "allowed_percentage", nullable = false, precision = 5, scale = 2)
	private BigDecimal allowedPercentage;

	@Column(name = "actual_variance", precision = 15, scale = 4)
	private BigDecimal actualVariance = BigDecimal.ZERO;

	@Column(name = "penalty_amount", precision = 15, scale = 4)
	private BigDecimal penaltyAmount = BigDecimal.ZERO;

	@Column(name = "penalty_applied", nullable = false)
	private Boolean penaltyApplied = false;

	@Column(name = "remarks", length = 500)
	private String remarks;

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
		if (this.actualVariance == null) {
			this.actualVariance = BigDecimal.ZERO;
		}
		if (this.penaltyAmount == null) {
			this.penaltyAmount = BigDecimal.ZERO;
		}
		if (this.penaltyApplied == null) {
			this.penaltyApplied = false;
		}
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	// Constructors
	public MonthlyTol() {
	}

	public MonthlyTol(Long meterId, String month, BigDecimal allowedPercentage, BigDecimal actualVariance,
			BigDecimal penaltyAmount, Boolean penaltyApplied, String remarks, String status) {
		this.meterId = meterId;
		this.month = month;
		this.allowedPercentage = allowedPercentage;
		this.actualVariance = actualVariance;
		this.penaltyAmount = penaltyAmount;
		this.penaltyApplied = penaltyApplied;
		this.remarks = remarks;
		this.status = status;
	}

	// Getters and Setters
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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getAllowedPercentage() {
		return allowedPercentage;
	}

	public void setAllowedPercentage(BigDecimal allowedPercentage) {
		this.allowedPercentage = allowedPercentage;
	}

	public BigDecimal getActualVariance() {
		return actualVariance;
	}

	public void setActualVariance(BigDecimal actualVariance) {
		this.actualVariance = actualVariance;
	}

	public BigDecimal getPenaltyAmount() {
		return penaltyAmount;
	}

	public void setPenaltyAmount(BigDecimal penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}

	public Boolean getPenaltyApplied() {
		return penaltyApplied;
	}

	public void setPenaltyApplied(Boolean penaltyApplied) {
		this.penaltyApplied = penaltyApplied;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
		return "MonthlyTol{" + "id=" + id + ", meterId=" + meterId + ", month='" + month + '\'' + ", allowedPercentage="
				+ allowedPercentage + ", actualVariance=" + actualVariance + ", penaltyAmount=" + penaltyAmount
				+ ", penaltyApplied=" + penaltyApplied + ", status=" + status + '}';
	}
}
