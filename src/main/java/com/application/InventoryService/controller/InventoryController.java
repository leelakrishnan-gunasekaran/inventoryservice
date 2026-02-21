package com.application.InventoryService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.InventoryService.DTO.updateInvItemDTO;
import com.application.InventoryService.Entity.InventoryItemEntity;
import com.application.InventoryService.Services.InventoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/addItem")
    public ResponseEntity<InventoryItemEntity> AddtoInventory(@RequestBody InventoryItemEntity request) {
        return ResponseEntity.ok(inventoryService.addtoInv(request));
    }

    @PostMapping("/updateItem")
    public ResponseEntity<String> UpdateInventory(@RequestBody updateInvItemDTO request) {
        return ResponseEntity.ok(inventoryService.updateInv(request));
    }

    @GetMapping("/getAllItems")
    public ResponseEntity<List<InventoryItemEntity>> getAllItems() {
        return ResponseEntity.ok(inventoryService.getAllInvItems());
    }

    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<String> deleteItem(@org.springframework.web.bind.annotation.PathVariable Long id)
    {
        return ResponseEntity.ok(inventoryService.deleteInvItem(id));
    }

    @GetMapping("/getItem/{id}")
    public ResponseEntity<InventoryItemEntity> getItembyId(@PathVariable String id) {
        return ResponseEntity.ok(inventoryService.requestItembyId(id));
    }

    @GetMapping("/public")
    public String publicresource() {
        return "Inventory Public";
    }
    
}
