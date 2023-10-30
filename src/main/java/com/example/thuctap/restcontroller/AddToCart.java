package com.example.thuctap.restcontroller;

import com.example.thuctap.bean.ProductDetail;
import com.example.thuctap.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class AddToCart {
    @Autowired
    CartService service;

    @RequestMapping(value = "/product/{idProductDetail}", method = RequestMethod.GET)
    public ProductDetail getOneCart(@PathVariable("idProductDetail") Long idProductDetail) {
        return service.detailProductDetail(idProductDetail);
    }
}
