package com.application.InventoryService.Services;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.application.InventoryService.DTO.updateInvItemDTO;
import com.application.InventoryService.Entity.InventoryItemEntity;
import com.application.InventoryService.Repository.InventoryRepo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Transactional
    public InventoryItemEntity addtoInv(InventoryItemEntity request) {

        InventoryItemEntity item = InventoryItemEntity.builder()
                .itemName(request.getItemName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .itemId(UUID.randomUUID().toString().toUpperCase().substring(0,10))
                .supplier(request.getSupplier())
                .category(request.getCategory())
                .location(request.getLocation())
                .lastUpdated(request.getLastUpdated())
                .inStock(request.isInStock())
                .description(request.getDescription())
                .expiryDate(request.getExpiryDate())
                .manufactureDate(request.getManufactureDate())
                .addedDate(request.getAddedDate())
                .build();
        return inventoryRepo.save(item);  
}

    public String updateInv(updateInvItemDTO request) {

        boolean exists = inventoryRepo.existsById(request.getId());

        if (exists) {
            InventoryItemEntity item = InventoryItemEntity.builder()
                    .id(request.getId())
                    .itemName(request.getItemName())
                    .quantity(request.getQuantity())
                    .price(request.getPrice())
                    .supplier(request.getSupplier())
                    .category(request.getCategory())
                    .location(request.getLocation())
                    .lastUpdated(request.getLastUpdated())
                    .inStock(request.isInStock())
                    .description(request.getDescription())
                    .expiryDate(request.getExpiryDate())
                    .manufactureDate(request.getManufactureDate())
                    .addedDate(request.getAddedDate())
                    .build();
            inventoryRepo.save(item);
            return "Item updated successfully";
        } else {
            return "Item with ID " + request.getId() + " does not exist.";
        }
    }

    public String deleteInvItem(Long id) {
        boolean exists = inventoryRepo.existsById(id);
        if (exists) {
            inventoryRepo.deleteById(id);
            return "Item deleted successfully";
        } else {
            return "Item with ID " + id + " does not exist.";
        }
    }

    public InventoryItemEntity getInvById(Long id) {
        return inventoryRepo.findById(id).orElse(null);
    }

    @CircuitBreaker(name = "inventoryservice", fallbackMethod = "fallbackResponse")
    @Cacheable("Inventory")
    public List<InventoryItemEntity> getAllInvItems() {
        return inventoryRepo.findAll();
    }

    public List<InventoryItemEntity> fallbackResponse(Throwable t) {
        return Collections.emptyList();
    }


    public InventoryItemEntity requestItembyId(String id){
        log.info(id);
        if(inventoryRepo.existsByItemId(id)){
            return inventoryRepo.findByItemId(id);
        }
        return null;
    } 
   
}