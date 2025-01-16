package com.groceryapi.grocerybookingapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceryapi.grocerybookingapi.model.GroceryItem;
import com.groceryapi.grocerybookingapi.repository.GroceryRepository;

@Service
public class GroceryService {
	@Autowired
    private GroceryRepository groceryRepository;

    public GroceryItem addGroceryItem(GroceryItem groceryItem) {
        return groceryRepository.save(groceryItem);
    }
    
    public List<GroceryItem> getAllGroceryItems() {
        return groceryRepository.findAll();
    }

    public GroceryItem updateGroceryItem(Long id, GroceryItem groceryItem) {
        GroceryItem existingItem = groceryRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        existingItem.setName(groceryItem.getName());
        existingItem.setPrice(groceryItem.getPrice());
        existingItem.setInventory(groceryItem.getInventory());
        return groceryRepository.save(existingItem);
    }

    public GroceryItem updateInventory(Long id, int quantity) {
        GroceryItem existingItem = groceryRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        existingItem.setInventory(quantity);
        return groceryRepository.save(existingItem);
    }
    
    public void deleteGroceryItem(Long id) {
        groceryRepository.deleteById(id);
    }

    public void bookGroceryItems(List<Long> groceryItemIds) {
        List<GroceryItem> items = groceryRepository.findAllById(groceryItemIds);
        for (GroceryItem item : items) {
            if (item.getInventory() > 0) {
                item.setInventory(item.getInventory() - 1);
                groceryRepository.save(item);
            } else {
                throw new RuntimeException("Item out of stock: " + item.getName());
            }
        }
    }
    
    
    
}
