package com.example.toyproject001.service;

import com.example.toyproject001.controller.ifs.CrudInterface;
import com.example.toyproject001.model.entity.Partner;
import com.example.toyproject001.model.network.Header;
import com.example.toyproject001.model.network.request.PartnerApiRequest;
import com.example.toyproject001.model.network.response.PartnerApiResponse;
import com.example.toyproject001.repository.CategoryRepository;
import com.example.toyproject001.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PartnerApiLogicService implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        //request-> Entity -> response
        PartnerApiRequest body = request.getData();

        Partner partner = Partner.builder()
                .name(body.getName())
                .status(body.getStatus())
                .address(body.getAddress())
                .callCenter(body.getCallCenter())
                .partnerNumber(body.getPartnerNumber())
                .businessNumber(body.getBusinessNumber())
                .ceoName(body.getCeoName())
                .registeredAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .createdBy(body.getCreatedBy())
                .category(categoryRepository.getOne(body.getCategoryId()))
                .build();

        Partner newPartner = partnerRepository.save(partner);
        return response(newPartner);

    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {

        Optional<Partner> optional = partnerRepository.findById(id);

        return optional.map(partner -> response(partner))
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        //id check
        //request data 가져와!
        PartnerApiRequest body = request.getData();

        //id 비교
        Optional<Partner> optional = partnerRepository.findById(body.getId());

        //id가 맞으면, map 해서 partner 에 담는다.
        return optional.map(partner -> {
            partner.setName(body.getName())
                    .setStatus(body.getStatus())
                    .setAddress(body.getAddress())
                    .setPartnerNumber(body.getPartnerNumber())
                    .setBusinessNumber(body.getBusinessNumber())
                    .setCeoName(body.getCeoName())
                    .setRegisteredAt(body.getRegisteredAt())
                    .setUnregisteredAt(body.getUnregisteredAt())
                    .setUpdatedAt(body.getUpdatedAt())
                    .setUpdatedBy(body.getUpdatedBy())
                    .setCategory(categoryRepository.getOne(body.getCategoryId()));
            return partner;
        })
                //반환된 partner 값을 레파지토리에 저장, response에 저장된 partner entity를 넘겨준다.
                .map(partner -> partnerRepository.save(partner))
                .map(updatePartner -> response(updatePartner))
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {
        Optional<Partner> optional = partnerRepository.findById(id);

        return optional.map(partner -> {
            partnerRepository.delete(partner);
            return Header.OK();
        })
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    //Entity -> response
    public Header<PartnerApiResponse> response(Partner partner){

        PartnerApiResponse body = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .unregisteredAt(partner.getRegisteredAt())
                .createdAt(partner.getCreatedAt())
                .createdBy(partner.getCreatedBy())
                .updatedBy(partner.getUpdatedBy())
                .updatedAt(partner.getUpdatedAt())
                .categoryId(partner.getCategory().getId())
                .build();

        return Header.OK(body);
    }
}
