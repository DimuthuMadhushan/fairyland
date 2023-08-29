package com.clothify.fairyland.repository;

import com.clothify.fairyland.entity.Item;
import com.clothify.fairyland.enumbers.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    Item getItemByItemId(Integer id);
    List<Item> getItemsByCategory(Category category);
}
