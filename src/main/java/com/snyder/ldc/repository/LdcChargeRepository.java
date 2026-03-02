package com.snyder.ldc.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.snyder.ldc.entity.LdcCharge;
import com.snyder.ldc.enums.ChargeType;

@Repository
public interface LdcChargeRepository extends JpaRepository<LdcCharge, Long> {
	Optional<LdcCharge> findByChargeCode(String chargeCode);

	List<LdcCharge> findByServiceAreaId(Long serviceAreaId);

	List<LdcCharge> findByChargeType(ChargeType chargeType);

	@Query("SELECT c FROM LdcCharge c WHERE c.serviceAreaId = :serviceAreaId "
			+ "AND c.effectiveFrom <= :date AND (c.effectiveTo IS NULL OR c.effectiveTo >= :date)")
	List<LdcCharge> findActiveChargesByServiceAreaAndDate(@Param("serviceAreaId") Long serviceAreaId,
			@Param("date") LocalDateTime date);
}
