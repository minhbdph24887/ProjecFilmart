package com.example.thuctap.bean;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Authorities")
@SuppressWarnings("serial")
public class Authority implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuthority;

    @ManyToOne()
    @JoinColumn(name = "Username")
    private Account account;

    @ManyToOne()
    @JoinColumn(name = "RoleID")
    private Role role;

    public Authority(Account account, Role role) {
        this.account = account;
        this.role = role;
    }
}
