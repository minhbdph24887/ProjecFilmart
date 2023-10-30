package com.example.thuctap.service;

import com.example.thuctap.bean.Order;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Order createOrderByJson(JsonNode orderJson);

    Order getOneOrderByID(Long idOrder);

    Page<Order> getAllOrderPageByUsername(String username, Pageable pageable);
}
