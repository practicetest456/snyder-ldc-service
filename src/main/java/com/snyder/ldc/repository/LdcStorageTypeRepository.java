package com.snyder.ldc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snyder.ldc.entity.LdcStorageType;
import com.snyder.ldc.enums.Status;

@Repository
public interface LdcStorageTypeRepository extends JpaRepository<LdcStorageType, Long> {
	Optional<LdcStorageType> findByStorageTypeName(String storageTypeName);

	List<LdcStorageType> findByStatus(Status status);
}
