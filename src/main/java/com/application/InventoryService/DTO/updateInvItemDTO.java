package com.application.InventoryService.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class updateInvItemDTO {

    private Long id;    
    private String itemName;
    private int quantity;
    private double price;
    private String supplier;
    private String category;
    private String location;
    private LocalDateTime lastUpdated;  
    private boolean inStock;
    private String description;
    private LocalDateTime expiryDate;
    private LocalDateTime manufactureDate;
    private LocalDateTime addedDate;

}
