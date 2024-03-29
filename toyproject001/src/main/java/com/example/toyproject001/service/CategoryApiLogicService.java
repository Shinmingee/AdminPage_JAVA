package com.example.toyproject001.service;

import com.example.toyproject001.controller.ifs.CrudInterface;
import com.example.toyproject001.model.entity.Category;
import com.example.toyproject001.model.network.Header;
import com.example.toyproject001.model.network.request.CategoryApiRequest;
import com.example.toyproject001.model.network.response.CategoryApiResponse;
import com.example.toyproject001.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryApiLogicService implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {

        CategoryApiRequest body = request.getData();

        Category category = Category.builder()
                .type(body.getType())
                .title(body.getTitle())
                .createdAt(LocalDateTime.now())
                .createdBy(body.getCreatedBy())
                .build();

        Category newCategory = categoryRepository.save(category);
        return Header.OK(response(newCategory));
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {

        Optional<Category> optional = categoryRepository.findById(id);
        return optional.map(category -> response(category))
                .map(categoryApiResponse -> Header.OK(categoryApiResponse))
                .orElseGet(()->Header.ERROR("데이터 없음!"));

    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {

        CategoryApiRequest body = request.getData();

        Optional<Category> optional = categoryRepository.findById(body.getId());

        return optional.map(category ->{
                    category.setType(body.getType())
                            .setTitle(body.getTitle())
                            .setCreatedAt(body.getCreatedAt())
                            .setCreatedBy(body.getCreatedBy())
                            .setUpdatedAt(body.getUpdatedAt())
                            .setUpdatedBy(body.getUpdatedBy());
                    return category;
                })
                .map(category -> categoryRepository.save(category))
                .map(updateCategory->response(updateCategory))
                .map(categoryApiResponse -> Header.OK(categoryApiResponse))
                .orElseGet(()->Header.ERROR("데이터 없음!"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);

        return optional.map(category -> {
            categoryRepository.delete(category);
            return Header.OK();
        })
                .orElseGet(()->Header.ERROR("데이터 없음!"));
    }

    public CategoryApiResponse response(Category category){

        CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                .id(category.getId())
                .type(category.getType())
                .title(category.getTitle())
                .createdAt(category.getCreatedAt())
                .createdBy(category.getCreatedBy())
                .updatedAt(category.getUpdatedAt())
                .updatedBy(category.getUpdatedBy())
                .build();

        return categoryApiResponse;
    }

    public Header<List<CategoryApiResponse>> search (Pageable pageable){
        Page<Category> categories = categoryRepository.findAll(pageable);

        List<CategoryApiResponse> categoryApiResponseList = categories.stream()
                .map(category -> response(category))
                .collect(Collectors.toList());

        return Header.OK(categoryApiResponseList);

    }
}
