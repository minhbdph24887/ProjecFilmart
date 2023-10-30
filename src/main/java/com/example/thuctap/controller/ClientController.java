package com.example.thuctap.controller;

import com.example.thuctap.bean.Category;
import com.example.thuctap.bean.ProductDetail;
import com.example.thuctap.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    List<Category> listCategory = new ArrayList<>();
    @Autowired
    ClientService service;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String viewAllViewer(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam("cid") Optional<Long> cid) {
        listCategory = service.viewCategory();
        if (cid.isPresent()) {
            Page<ProductDetail> listProductDetail = service.getProductsByCategory(PageRequest.of(page, 6), cid.get());
            model.addAttribute("listCategory", listCategory);
            model.addAttribute("listProductDetail", listProductDetail);
        } else {
            Page<ProductDetail> listProductDetail = service.getAllProductDetail(PageRequest.of(page, 6));
            model.addAttribute("listCategory", listCategory);
            model.addAttribute("listProductDetail", listProductDetail);
        }
        return "viewer/indexViewer";
    }

    @RequestMapping(value = "/product/detail/{idProductDetail}", method = RequestMethod.GET)
    public String detailProductClient(Model model, @PathVariable("idProductDetail") Long idProductDetail) {
        ProductDetail productDetail = service.detailProductDetail(idProductDetail);
        listCategory = service.viewCategory();
        if (productDetail != null) {
            Category category = productDetail.getCategory(); // Lấy danh mục của sản phẩm
            List<ProductDetail> sameCategoryProducts = service.findProductDetailByCategory(category.getIdCategory()); // Lấy danh sách sản phẩm cùng loại
            model.addAttribute("detailProductDetail", productDetail);
            model.addAttribute("listCategory", listCategory);
            model.addAttribute("sameCategoryProducts", sameCategoryProducts); // Thêm danh sách sản phẩm cùng loại vào Model
        }
        return "viewer/detailProduct";
    }
}
