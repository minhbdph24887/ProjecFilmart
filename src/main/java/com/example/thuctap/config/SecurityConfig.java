package com.example.thuctap.config;

import com.example.thuctap.bean.Account;
import com.example.thuctap.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AccountService accountService;

    // Cơ Chế Mã Hóa Mật Khẩu
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cung Cấp Nguồn Dữ Liệu Đăng Nhập
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            try {
                System.out.println("aaaaaaaaaaaaaaaaaaaaav : " + username);
                Account account = accountService.detailAccountByUserName(username);
                String password = passwordEncoder().encode(account.getPassword());
                System.out.println("bbbbbbbbbbbbbbbbbbbbb : " + password);
                String roles = accountService.getAllRoleByUserNameAndPassword(username, account.getPassword()).getIdRole();
                System.out.println("ddddddddddddddd : " + account.getPassword());
                System.out.println("cccccccccccccccccccccc : " + roles);
                return User.withUsername(username).password(password).roles(roles).build();
            } catch (NoSuchElementException e) {
                System.out.println(e);
                throw new UsernameNotFoundException(username + "Không Tồn Tại");
            }
        });
    }

    // Phân Quyền Sử Dụng
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/order/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/login/form")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/login/success", false)
                .failureUrl("/login/error");

        http.exceptionHandling()
                .accessDeniedPage("/login/unauthorized");

        http.rememberMe()
                .tokenValiditySeconds(86400);

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/success");
    }

    // Cho Phép Truy Xuất REST API Từ Bên Ngoài(Domain Khác)
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
