package com.snyder.ldc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.snyder.ldc.entity.PoolingPoint;
import com.snyder.ldc.enums.Status;

@Repository
public interface PoolingPointRepository extends JpaRepository<PoolingPoint, Long> {
	Optional<PoolingPoint> findByPoolingCode(String poolingCode);

	List<PoolingPoint> findByServiceAreaId(Long serviceAreaId);

	List<PoolingPoint> findByStatus(Status status);

	@Query("SELECT pp FROM PoolingPoint pp WHERE pp.currentVolume < pp.capacity")
	List<PoolingPoint> findPoolingPointsWithAvailableCapacity();
}