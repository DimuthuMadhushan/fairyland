package com.clothify.fairyland.controller;

import com.clothify.fairyland.dao.ItemDAO;
import com.clothify.fairyland.entity.Item;
import com.clothify.fairyland.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final String FOLDER_PATH="E:/proimg/";
    @Autowired
    ItemRepository itemRepository;
    @PostMapping("/post")
    public Item addItem(@RequestBody Item item){
        itemRepository.save(item);
        return item;
    }
    @PostMapping("/addimage")
    public boolean saveImage(@ModelAttribute ItemDAO itemDAO) throws IOException {
        itemDAO.getImage().transferTo(new File(FOLDER_PATH + itemDAO.getImage().getOriginalFilename()));
        itemRepository.save(new Item(itemDAO.getCategory(),itemDAO.getxLQuantity(),itemDAO.getlQuantity(),itemDAO.getmQuantity(),itemDAO.getsQuantity()
        ,itemDAO.getUnitPrice(),itemDAO.getImage().getOriginalFilename(),itemDAO.getTittle()));
        return true;

    }
    @GetMapping("/getItem/{id}")
    public Item getItem(@PathVariable (value = "id")Integer id){
        return itemRepository.getItemByItemId(id);
    }
    @GetMapping("/getimage/{filename}")
    public ResponseEntity<byte[]>getImage(@PathVariable(value = "filename") String filename) throws IOException {
        byte[] image = Files.readAllBytes(new File(FOLDER_PATH+filename).toPath());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }
    @GetMapping("/get-all")
    public List<Item> getItems(){
        return itemRepository.findAll();
    }


    @DeleteMapping("/delete/{id}")
    public Boolean deleteItem(@PathVariable(value = "id")Integer itemId){
        String filePath=itemRepository.getItemByItemId(itemId).getImgUrl();
        File file=new File(FOLDER_PATH+filePath);

        if(file.delete()){
            itemRepository.deleteById(itemId);
        }else {
            return false;
        }

        return true;
    }
    @PutMapping("/update/{id}")
    public Boolean updateItem(@PathVariable(value = "id")Integer itemId,@RequestBody Item item){
        Item updateItem=itemRepository.getItemByItemId(itemId);
        updateItem.setCategory(item.getCategory());
        updateItem.setlQuantity(item.getlQuantity());
        updateItem.setmQuantity(item.getmQuantity());
        updateItem.setsQuantity(item.getsQuantity());
        updateItem.setUnitPrice(item.getUnitPrice());
        updateItem.setxLQuantity(item.getxLQuantity());

        itemRepository.save(updateItem);
        return true;
    }

}
