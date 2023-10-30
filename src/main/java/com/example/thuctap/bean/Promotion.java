package com.example.thuctap.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Promotions")
public class Promotion {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPromotion;

    @Column(name = "Code")
    private String codePromotion;

    @Column(name = "Name")
    private String namePromotion;

    @Column(name = "Persen")
    private BigDecimal person;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "StartDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "EndDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "Description")
    private String description;

    @Column(name = "Status")
    private Integer status;

    @JsonIgnore
    @OneToMany(mappedBy = "promotion")
    List<PromotionProduct> promotionProducts;

    public String getAllStatus() {
        if (this.status == 1) {
            return "Đang Hoạt Động";
        } else if (this.status == 0) {
            return "Đã Hết Hạn";
        } else {
            return "Sắp Ra Mắt";
        }
    }

    public void updateStatusPromotions() {
        LocalDate now = LocalDate.now();
        if (now.isBefore(startDate)) {
            this.status = 2; // Sắp Ra Mắt
        } else if (now.isAfter(endDate) || now.isEqual(endDate)) {
            this.status = 0; // Đã Hết Hạn
        } else {
            this.status = 1; // Đang Hoạt Động
        }
    }
}
