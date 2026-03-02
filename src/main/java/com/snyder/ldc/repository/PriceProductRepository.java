package com.snyder.ldc.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.snyder.ldc.entity.PriceProduct;
import com.snyder.ldc.enums.PricingType;

@Repository
public interface PriceProductRepository extends JpaRepository<PriceProduct, Long> {
	Optional<PriceProduct> findByProductCode(String productCode);

	List<PriceProduct> findByPoolingPointId(Long poolingPointId);

	List<PriceProduct> findByPricingType(PricingType pricingType);

	@Query("SELECT pp FROM PriceProduct pp WHERE pp.poolingPointId = :poolingPointId "
			+ "AND pp.effectiveFrom <= :date AND (pp.effectiveTo IS NULL OR pp.effectiveTo >= :date)")
	List<PriceProduct> findActiveProductsByPoolingPointAndDate(@Param("poolingPointId") Long poolingPointId,
			@Param("date") LocalDateTime date);
}
