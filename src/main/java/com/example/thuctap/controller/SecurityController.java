package com.example.thuctap.controller;

import com.example.thuctap.bean.Account;
import com.example.thuctap.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SecurityController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/login/form")
    public String loginForm(Model model) {
        model.addAttribute("message", "Vui Lòng Đăng Nhập Để Sử Dụng Phần Mềm");
        return "security/login";
    }

    @RequestMapping(value = "/login/success")
    public String loginSuccess(Model model, HttpServletRequest request) {
        // Lấy thông tin người dùng từ SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Lấy thông tin tài khoản từ cơ sở dữ liệu (hoặc bộ nhớ cache, nếu bạn có)
        Account account = accountService.detailAccountByUserName(username);

        // Lưu trữ thông tin tài khoản vào phiên
        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", account);

        model.addAttribute("message", "Đăng Nhập Thành Công");
        return "redirect:/product";
    }


    @RequestMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("message", "Sai Thông Tin Đăng Nhập");
        return "security/login";
    }

    @RequestMapping(value = "/login/unauthorized")
    public String loginUnauthorized(Model model) {
        model.addAttribute("message", "Bạn Không Có Quyền Truy Cập Vào Chức Năng Này");
        return "security/login";
    }

    @RequestMapping(value = "/logout/success")
    public String logoutSuccess(Model model) {
        model.addAttribute("message", "Đăng Xuất Thành Công");
        return "redirect:/product";
    }
}
