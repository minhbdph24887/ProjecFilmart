package com.example.thuctap.service.impl;

import com.example.thuctap.bean.ProductDetail;
import com.example.thuctap.repository.ProductDetailRepository;
import com.example.thuctap.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Override
    public ProductDetail detailProductDetail(Long idProductDetail) {
        return productDetailRepository.findById(idProductDetail).orElse(null);
    }
}
