package com.snyder.ldc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.snyder.ldc.entity.MonthlyTol;

@Repository
public interface MonthlyTolRepository extends JpaRepository<MonthlyTol, Long> {
	Optional<MonthlyTol> findByMeterIdAndMonth(Long meterId, String month);

	List<MonthlyTol> findByMeterId(Long meterId);

	List<MonthlyTol> findByPenaltyApplied(Boolean penaltyApplied);

	@Query("SELECT mt FROM MonthlyTol mt WHERE mt.penaltyApplied = true "
			+ "AND mt.month = :month ORDER BY mt.penaltyAmount DESC")
	List<MonthlyTol> findPenaltiesForMonth(@Param("month") String month);
}
