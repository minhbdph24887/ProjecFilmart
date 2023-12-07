package com.example.thuctap;

import com.example.thuctap.bean.Account;
import com.example.thuctap.bean.Authority;
import com.example.thuctap.bean.Role;
import com.example.thuctap.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runRole(AdminService adminService){
//		return args -> {
//			adminService.addRole(new Role("ADMIN", "Quản Trị Viên"));
//			adminService.addRole(new Role("USER", "Người Dùng"));
//			adminService.addRole(new Role("STAFF", "Nhân Viên"));
//		};
//	}
//
//	@Bean
//	CommandLineRunner runAccount(AdminService adminService){
//		return args -> {
//			adminService.addAccount(new Account("minhbdph2488712", "Bùi Đức Minh", "minhbdph24887@gmail.com", "0388598451", true, LocalDate.parse("2023-03-09"), "Hà Nội", "123"));
//			adminService.addAccount(new Account("account0123", "Nguyễn Văn A", "anv12@gmail.com", "0123456789", false, LocalDate.parse("2023-03-09"), "Hà Nội", "123"));
//		};
//	}
//
//	@Bean
//	CommandLineRunner runAuthentication(AdminService adminService){
//		return args -> {
//			Account account = adminService.detailAccount("minhbdph2488712");
//			Role role = adminService.detailRole("ADMIN");
//			adminService.addAuthorities(new Authority(account, role));
//		};
//	}
}
