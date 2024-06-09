package com.example.service.repo;

import com.example.service.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long>, JpaSpecificationExecutor<ServiceEntity> {

    boolean existsByIdAndDeletedAtNull(Long id);

    @Transactional
    @Modifying
    @Query(value = "update service set deleted_At = now() where id = :id", nativeQuery = true)
    void softDelete(Long id);

    boolean existsByNameAndDeletedAtNull(String name);

    Optional<ServiceEntity> findByIdAndDeletedAtNull(Long id);
}
