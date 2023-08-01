package com.clothify.fairyland.controller;

import com.clothify.fairyland.entity.Item;
import com.clothify.fairyland.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final String FOLDER_PATH="E:/fairyland/fairyland/src/main/resources/images/";
    @Autowired
    ItemRepository itemRepository;
    @PostMapping
    public Item  addItem(@RequestBody Item item){
        itemRepository.save(item);
        return item;
    }
    @PostMapping("/addimage")
    public boolean saveImage(@RequestParam(value = "id")Integer itemId, @RequestParam(value = "image")MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();
        Item item=itemRepository.getItemByItemId(itemId);
        item.setImgUrl(filePath);
        itemRepository.save(item);

        file.transferTo(new File(filePath));
        return true;

    }
    @GetMapping
    public List<Item> getItems(){
        return itemRepository.findAll();
    }
    @DeleteMapping("/{id}")
    public Boolean deleteItem(@PathVariable(value = "id")Integer itemId){
        itemRepository.deleteById(itemId);
        return true;
    }
    @PutMapping("/{id}")
    public Item updateItem(@PathVariable(value = "id")Integer itemId,@RequestBody Item item){
        Item updateItem=itemRepository.getItemByItemId(itemId);
        updateItem.setCategory(item.getCategory());
        updateItem.setDescription(item.getDescription());
        updateItem.setImgUrl(item.getImgUrl());
        updateItem.setlQuantity(item.getlQuantity());
        updateItem.setmQuantity(item.getmQuantity());
        updateItem.setsQuantity(item.getsQuantity());
        updateItem.setUnitPrice(item.getUnitPrice());
        updateItem.setxLQuantity(item.getxLQuantity());

        itemRepository.save(updateItem);
        return updateItem;
    }

}
