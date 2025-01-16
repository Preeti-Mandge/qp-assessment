package com.groceryapi.grocerybookingapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groceryapi.grocerybookingapi.model.GroceryItem;
import com.groceryapi.grocerybookingapi.service.GroceryService;

@RestController
@RequestMapping("/api")
public class GroceryController {

	@Autowired
    private GroceryService groceryService;

    //Admin endpoints
	
	//Add new grocery items to the system
    @PostMapping("/admin/grocery")
    public ResponseEntity<GroceryItem> addGroceryItem(@RequestBody GroceryItem groceryItem) {
        return ResponseEntity.ok(groceryService.addGroceryItem(groceryItem));
    }

    //View existing grocery items
    @GetMapping("/admin/grocery")
    public ResponseEntity<List<GroceryItem>> viewAllGroceryItems() {
        return ResponseEntity.ok(groceryService.getAllGroceryItems());
    }

    //Update details (e.g., name, price) of existing grocery items
    @PutMapping("/admin/grocery/{id}")
    public ResponseEntity<GroceryItem> updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItem groceryItem) {
        return ResponseEntity.ok(groceryService.updateGroceryItem(id, groceryItem));
    }

    //Manage inventory levels of grocery items
    @PutMapping("/admin/grocery/{id}/inventory")
    public ResponseEntity<GroceryItem> updateInventory(@PathVariable Long id, @RequestParam int quantity) {
        return ResponseEntity.ok(groceryService.updateInventory(id, quantity));
    }

    //Remove grocery items from the system
    @DeleteMapping("/admin/grocery/{id}")
    public ResponseEntity<Void> deleteGroceryItem(@PathVariable Long id) {
        groceryService.deleteGroceryItem(id);
        return ResponseEntity.noContent().build();
    }

    // User endpoints
    
    // View the list of available grocery items
    @GetMapping("/user/grocery")
    public ResponseEntity<List<GroceryItem>> viewAvailableGroceryItems() {
        return ResponseEntity.ok(groceryService.getAllGroceryItems());
    }

    // Ability to book multiple grocery items in a single order
    @PostMapping("/user/order")
    public ResponseEntity<String> bookGroceryItems(@RequestBody List<Long> groceryItemIds) {
        groceryService.bookGroceryItems(groceryItemIds);
        return ResponseEntity.ok("Order placed successfully.");
    }
    
}
