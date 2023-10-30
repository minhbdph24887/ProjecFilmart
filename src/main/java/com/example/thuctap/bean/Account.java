package com.example.thuctap.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Accounts")
@SuppressWarnings("serial")
public class Account implements Serializable {
    @Id
    @Column(name = "Username")
    private String username;

    @Column(name = "FullName")
    private String fullName;

    @Column(name = "Email")
    private String email;

    @Column(name = "NumberPhone")
    private String numberPhone;

    @Column(name = "Sex")
    private Boolean sex;

    @Column(name = "Birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Column(name = "Address")
    private String address;

    @Column(name = "Password")
    private String password;

    @Column(name = "Status")
    private Integer status;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<Authority> authorities;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<Order> orders;

    public String getAllStatus() {
        if (this.status != null) {
            if (this.status == 1) {
                return "Đang Hoạt Động";
            } else {
                return "Ngừng Hoạt Động";
            }
        } else {
            return "Trạng thái không xác định"; // hoặc bất kỳ giá trị nào bạn muốn trả về khi status là null
        }
    }

}
