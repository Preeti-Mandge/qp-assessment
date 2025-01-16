package com.groceryapi.grocerybookingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.groceryapi.grocerybookingapi.model.GroceryItem;

public interface GroceryRepository extends JpaRepository<GroceryItem, Long>{

}
