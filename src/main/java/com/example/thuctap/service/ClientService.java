package com.example.thuctap.service;

import com.example.thuctap.bean.Category;
import com.example.thuctap.bean.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    List<Category> viewCategory();

    Page<ProductDetail> getProductsByCategory(Pageable pageable, Long categoryId);

    Page<ProductDetail> getAllProductDetail(Pageable pageable);

    ProductDetail detailProductDetail(Long idProductDetail);

    List<ProductDetail> findProductDetailByCategory(Long idCategory);
}
