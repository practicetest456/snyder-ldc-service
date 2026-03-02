package com.snyder.ldc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

public class MonthlyTolModel extends RepresentationModel<MonthlyTolModel> {
	private Long id;
	private Long meterId;
	private String month;
	private BigDecimal allowedPercentage;
	private BigDecimal actualVariance;
	private BigDecimal penaltyAmount;
	private Boolean penaltyApplied;
	private String remarks;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public MonthlyTolModel(Long id, Long meterId, String month, BigDecimal allowedPercentage, BigDecimal actualVariance,
			BigDecimal penaltyAmount, Boolean penaltyApplied, String remarks, String status, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.meterId = meterId;
		this.month = month;
		this.allowedPercentage = allowedPercentage;
		this.actualVariance = actualVariance;
		this.penaltyAmount = penaltyAmount;
		this.penaltyApplied = penaltyApplied;
		this.remarks = remarks;
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
}
