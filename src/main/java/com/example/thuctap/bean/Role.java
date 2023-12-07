package com.example.thuctap.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Roles")
@SuppressWarnings("serial")
public class Role implements Serializable {
    @Id
    @Column(name = "ID")
    private String idRole;

    @Column(name = "Name")
    private String nameRole;

    @Column(name = "Status")
    private Integer status;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    List<Authority> authorities;

    public String getAllStatus() {
        if (this.status == 1) {
            return "Đang Hoạt Động";
        } else {
            return "Ngừng Hoạt Động";
        }
    }

    public Role(String idRole, String nameRole) {
        this.idRole = idRole;
        this.nameRole = nameRole;
    }
}
