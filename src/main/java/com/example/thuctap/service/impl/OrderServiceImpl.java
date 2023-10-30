package com.example.thuctap.service.impl;

import com.example.thuctap.bean.Order;
import com.example.thuctap.bean.OrderDetail;
import com.example.thuctap.repository.OrderDetailRepository;
import com.example.thuctap.repository.OrderRepository;
import com.example.thuctap.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public Order createOrderByJson(JsonNode orderJson) {
        ObjectMapper mapper = new ObjectMapper();
        Order order1 = mapper.convertValue(orderJson, Order.class);
        System.out.println("vvvvvvvvvvvvvvvvvvvv" + order1);
        orderRepository.save(order1);
        TypeReference<List<OrderDetail>> listTypeReference = new TypeReference<List<OrderDetail>>() {
        };
        System.out.println("zzzzzzzzzzzzzzzz" + listTypeReference);
        List<OrderDetail> orderDetails = mapper.convertValue(orderJson.get("orderDetails"), listTypeReference)
                .stream().peek(dfo -> dfo.setOrder(order1)).collect(Collectors.toList());
        System.out.println("ddddddddddddddddd" + orderDetails);
        orderDetailRepository.saveAll(orderDetails);
        return order1;
    }

    @Override
    public Order getOneOrderByID(Long idOrder) {
        return orderRepository.findById(idOrder).get();
    }

    @Override
    public Page<Order> getAllOrderPageByUsername(String username, Pageable pageable) {
        return orderRepository.findByUsername(username, pageable);
    }
}
