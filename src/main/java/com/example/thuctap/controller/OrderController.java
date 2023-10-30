package com.example.thuctap.controller;

import com.example.thuctap.bean.Account;
import com.example.thuctap.bean.Order;
import com.example.thuctap.service.AccountService;
import com.example.thuctap.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
    @Autowired
    AccountService accountService;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order/checkout", method = RequestMethod.GET)
    public String checkOut(HttpServletRequest request) {
        // Lấy thông tin người dùng từ SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Lấy thông tin tài khoản từ cơ sở dữ liệu (hoặc bộ nhớ cache, nếu bạn có)
        Account account = accountService.detailAccountByUserName(username);

        // Lưu trữ thông tin tài khoản vào phiên
        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", account);
        return "order/checkout";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String listOrder(Model model, HttpServletRequest request, @RequestParam(defaultValue = "0") int page) {
        // Lấy thông tin người dùng từ SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Lấy thông tin tài khoản từ cơ sở dữ liệu (hoặc bộ nhớ cache, nếu bạn có)
        Account account = accountService.detailAccountByUserName(username);

        // Lưu trữ thông tin tài khoản vào phiên
        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", account);
        Page<Order> itemsOrder = orderService.getAllOrderPageByUsername(username, PageRequest.of(page, 6));
        model.addAttribute("listOrder", itemsOrder);
        model.addAttribute("currentPage", page);
        return "order/listOrder";
    }

    @RequestMapping(value = "/order/detail/{idOrder}", method = RequestMethod.GET)
    public String viewDetailOrder(@PathVariable("idOrder") Long idOrder, Model model) {
        Order detailOrder = orderService.getOneOrderByID(idOrder);
        model.addAttribute("orderDetail", detailOrder);
        return "order/detailOrder";
    }
}
