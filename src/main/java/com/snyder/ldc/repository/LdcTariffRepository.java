package com.snyder.ldc.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.snyder.ldc.entity.LdcTariff;

@Repository
public interface LdcTariffRepository extends JpaRepository<LdcTariff, Long> {
	Optional<LdcTariff> findByTariffCode(String tariffCode);

	List<LdcTariff> findByMeterTypeId(Long meterTypeId);

	@Query("SELECT t FROM LdcTariff t WHERE t.meterTypeId = :meterTypeId "
			+ "AND t.effectiveFrom <= :date AND (t.effectiveTo IS NULL OR t.effectiveTo >= :date)")
	List<LdcTariff> findActiveTariffsByMeterTypeAndDate(@Param("meterTypeId") Long meterTypeId,
			@Param("date") LocalDateTime date);

	@Query("SELECT t FROM LdcTariff t WHERE t.meterTypeId = :meterTypeId "
			+ "AND :volume >= t.slabFrom AND :volume <= t.slabTo "
			+ "AND t.effectiveFrom <= :date AND (t.effectiveTo IS NULL OR t.effectiveTo >= :date)")
	Optional<LdcTariff> findApplicableTariff(@Param("meterTypeId") Long meterTypeId,
			@Param("volume") java.math.BigDecimal volume, @Param("date") LocalDateTime date);
}