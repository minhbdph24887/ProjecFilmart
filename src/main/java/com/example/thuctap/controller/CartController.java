package com.example.thuctap.controller;

import com.example.thuctap.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CartController {
    @Autowired
    CartService service;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String getAllCart() {
        return "cart/viewCart";
    }
}
