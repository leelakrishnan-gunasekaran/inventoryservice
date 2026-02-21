package com.application.InventoryService.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Inventory")
public class InventoryItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private String itemId;
    private int quantity;
    private double price;
    private String supplier;
    private String category;
    private String location;
    @CurrentTimestamp
    private LocalDateTime lastUpdated;  
    private boolean inStock;
    private String description;
    private LocalDateTime expiryDate;
    private LocalDateTime manufactureDate;
    @CurrentTimestamp
    private LocalDateTime addedDate;

}
