package com.example.toyproject001.service;

import com.example.toyproject001.controller.ifs.CrudInterface;
import com.example.toyproject001.model.entity.Item;
import com.example.toyproject001.model.network.Header;
import com.example.toyproject001.model.network.request.ItemApiRequest;
import com.example.toyproject001.model.network.response.ItemApiResponse;
import com.example.toyproject001.repository.ItemRepository;
import com.example.toyproject001.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;



@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        //요청받은 데이터를 저장시키고 response 를 보내줘야 한다

        ItemApiRequest body = request.getData(); //요청받는 request에서 데이터 뽑아서 itemApiRequest에 저장

        Item item = Item.builder()
                .status(body.getStatus())
                .name(body.getName())
                .title(body.getTitle())
                .content(body.getContent())
                .brandName(body.getBrandName())
                .price(body.getPrice())
                .registeredAt(LocalDateTime.now())
                .unregisteredAt(body.getUnregisteredAt())
                .partner(partnerRepository.getOne(body.getPartnerId()))
                .build(); //Item 객체 하나 만들어서 담아놈

        Item newItem = itemRepository.save(item); //newItem 에 repository에 저장한 item 담음
        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {

        //아이디로 분류해서 데이터 불러오기
        //데이터 불러오기.
        Optional<Item> optional = itemRepository.findById(id);

        return optional
                .map(item -> response(item))
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {

        ItemApiRequest itemApiRequest = request.getData();

        Optional<Item> optional = itemRepository.findById(itemApiRequest.getId());

        return optional.map(item -> {
                item.setStatus(itemApiRequest.getStatus())
                    .setStatus(itemApiRequest.getStatus())
                    .setName(itemApiRequest.getName())
                    .setContent(itemApiRequest.getContent())
                    .setPrice(itemApiRequest.getPrice())
                    .setBrandName(itemApiRequest.getBrandName())
                    .setTitle(itemApiRequest.getTitle())
                    .setRegisteredAt(itemApiRequest.getRegisteredAt())
                    .setUnregisteredAt(itemApiRequest.getUnregisteredAt())
                    .setPartner(partnerRepository.getOne(itemApiRequest.getPartnerId()));
            return item;
        })
                .map(item->itemRepository.save(item))
                .map(updateItem->response(updateItem))
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {

        Optional<Item> optional = itemRepository.findById(id);

        return optional.map(item -> {itemRepository.delete(item);
                    return Header.OK();
        })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }


    //response :
    public Header<ItemApiResponse> response(Item item){
        //Item -> ItemApiResponse
        ItemApiResponse body = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .brandName(item.getBrandName())
                .price(item.getPrice())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();

        return Header.OK(body);

    }

}
