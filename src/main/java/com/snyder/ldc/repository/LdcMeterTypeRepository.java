package com.snyder.ldc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snyder.ldc.entity.LdcMeterType;
import com.snyder.ldc.enums.PressureCategory;

@Repository
public interface LdcMeterTypeRepository extends JpaRepository<LdcMeterType, Long> {
	Optional<LdcMeterType> findByMeterTypeCode(String meterTypeCode);

	List<LdcMeterType> findByPressureCategory(PressureCategory pressureCategory);
}