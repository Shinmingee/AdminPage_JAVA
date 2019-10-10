package com.example.toyproject001.repository;

import com.example.toyproject001.Toyproject001ApplicationTests;
import com.example.toyproject001.model.entity.Item;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends Toyproject001ApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = new Item();

        item.setStatus("UNREGISTERED");
        item.setName("LG 노트북");
        item.setTitle("LG 노트북 P200");
        item.setPrice(1500000);
        item.setContent("2018년도 노트북 입니다");
        item.setBrandName("LG");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");
        //item.setPartnerId(3L); //partner01 id = 3  Long -> partner

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
