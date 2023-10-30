package com.example.thuctap.service.impl;

import com.example.thuctap.bean.Category;
import com.example.thuctap.bean.ProductDetail;
import com.example.thuctap.repository.CategoryRepository;
import com.example.thuctap.repository.ProductDetailRepository;
import com.example.thuctap.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Override
    public List<Category> viewCategory() {
        return categoryRepository.viewCategoryByStatus1();
    }

    @Override
    public Page<ProductDetail> getProductsByCategory(Pageable pageable, Long categoryId) {
        return productDetailRepository.findProductDetailByCategory(pageable, categoryId);
    }

    @Override
    public Page<ProductDetail> getAllProductDetail(Pageable pageable) {
        return productDetailRepository.findAll(pageable);
    }

    @Override
    public ProductDetail detailProductDetail(Long idProductDetail) {
        return productDetailRepository.findById(idProductDetail).orElse(null);
    }

    @Override
    public List<ProductDetail> findProductDetailByCategory(Long idCategory) {
        return productDetailRepository.findProductDetailByCategory(idCategory);
    }
}
