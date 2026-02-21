package com.application.InventoryService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.InventoryService.Entity.InventoryItemEntity;


@Repository
public interface InventoryRepo extends JpaRepository<InventoryItemEntity, Long> {

    boolean existsByItemId(String id);

    InventoryItemEntity findByItemId(String id);

}
