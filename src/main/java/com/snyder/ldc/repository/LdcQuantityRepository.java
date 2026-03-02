package com.snyder.ldc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.snyder.ldc.entity.LdcQuantity;
import com.snyder.ldc.enums.QuantityType;

@Repository
public interface LdcQuantityRepository extends JpaRepository<LdcQuantity, Long> {
	List<LdcQuantity> findByMeterId(Long meterId);

	List<LdcQuantity> findByQuantityType(QuantityType quantityType);

	@Query("SELECT q FROM LdcQuantity q WHERE q.meterId = :meterId " + "ORDER BY q.readingDate DESC LIMIT 1")
	Optional<LdcQuantity> findLatestByMeterId(@Param("meterId") Long meterId);
}
