package com.example.toyproject001.repository;

import com.example.toyproject001.Toyproject001ApplicationTests;
import com.example.toyproject001.model.entity.Item;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ItemRepositoryTest extends Toyproject001ApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = new Item();

        item.setName("노트북");
        item.setPrice(100000);
        item.setContent("LG 그램 노트북 짱");

        Item newItem = itemRepository.save(item);
        Assert.assertNotNull(newItem);
    }

    @Test
    public void read() {
        Long id = 1L;
        Optional<Item> item = itemRepository.findById(id);

        Assert.assertTrue(item.isPresent());
    }


}
