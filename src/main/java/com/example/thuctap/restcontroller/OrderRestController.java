package com.example.thuctap.restcontroller;

import com.example.thuctap.bean.Order;
import com.example.thuctap.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class OrderRestController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/rest/order", method = RequestMethod.POST)
    public Order createOrder(@RequestBody JsonNode orderJson) {
        return orderService.createOrderByJson(orderJson);
    }
}
