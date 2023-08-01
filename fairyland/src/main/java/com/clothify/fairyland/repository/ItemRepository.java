package com.clothify.fairyland.repository;

import com.clothify.fairyland.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    Item getItemByItemId(Integer id);
}
