# Của Hàng Bán Quần Áo Filmart
### Khởi tạo truy cập người dùng 
	@Bean
	CommandLineRunner runRole(AdminService adminService){
		return args -> {
			adminService.addRole(new Role("ADMIN", "Quản Trị Viên"));
			adminService.addRole(new Role("USER", "Người Dùng"));
			adminService.addRole(new Role("STAFF", "Nhân Viên"));
		};
	}

	@Bean
	CommandLineRunner runAccount(AdminService adminService){
		return args -> {
			adminService.addAccount(new Account("minhbdph2488712", "Bùi Đức Minh", "minhbdph24887@gmail.com", "0388598451", true, LocalDate.parse("2023-03-09"), "Hà Nội", "123"));
			adminService.addAccount(new Account("account0123", "Nguyễn Văn A", "anv12@gmail.com", "0123456789", false, LocalDate.parse("2023-03-09"), "Hà Nội", "123"));
		};
	}

	@Bean
	CommandLineRunner runAuthentication(AdminService adminService){
		return args -> {
			Account account = adminService.detailAccount("minhbdph2488712");
			Role role = adminService.detailRole("ADMIN");
			adminService.addAuthorities(new Authority(account, role));
		};
	}

### Sau khi chạy đoạn code này ở phần DemoApplication.java thì phải comment lại đoạn code tránh trường hợp xung dột người dùng rồi báo lỗi
## Khi chạy thành công thì sẽ vào đường dẫn là http://localhost:8080/product rồi đăng nhập tài khoản với Username là "minhbdph2488712" và password là "123" để được sử dụng với quyền truy cập là Admin
