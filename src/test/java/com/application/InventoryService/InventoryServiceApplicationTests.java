package com.application.InventoryService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.InventoryService.Entity.InventoryItemEntity;
import com.application.InventoryService.Repository.InventoryRepo;
import com.application.InventoryService.Services.InventoryService;

import lombok.extern.slf4j.Slf4j;


@ExtendWith(MockitoExtension.class)
@Slf4j
class InventoryServiceApplicationTests {

	@InjectMocks
	private InventoryService inventoryService;
	@Mock
	@Autowired
	private InventoryRepo inventoryRepo;

	private InventoryItemEntity addItem;

	@BeforeEach
	void Setup(){
				InventoryItemEntity item = InventoryItemEntity.builder()
		.itemName("item name").quantity(0).price(0)
		.supplier("suplier name").category("category name").location("location name")
		.inStock(false).description("product description")
		.expiryDate(LocalDateTime.now()).manufactureDate(LocalDateTime.now()).build();
        when(inventoryRepo.save(any(InventoryItemEntity.class)))
            .thenAnswer(invocation -> {
                InventoryItemEntity arg = invocation.getArgument(0);
                arg.setId(100L);
                return arg;});
        addItem = inventoryService.addtoInv(item);
	}

	@Test
	public void addItemshouldaddItemtoInventory(){
		log.info(addItem.toString());
		Assertions.assertNotNull(addItem);
	}

	@Test
	public void deleteInvItemshouldDeleteInvItem(){
		log.info(addItem.getId().toString());
		inventoryService.deleteInvItem(addItem.getId());
	}
}