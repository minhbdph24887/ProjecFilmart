package com.example.thuctap.bean;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "PromotionsProduct")
public class PromotionProduct {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPromotionProduct;

    @ManyToOne
    @JoinColumn(name = "PromotionsID")
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "ProductDetailID")
    private ProductDetail productDetail;

    @Column(name = "Status")
    private Integer status;

    public String getAllStatus() {
        if (this.status == 1) {
            return "Đang Hoạt Động";
        } else {
            return "Ngừng Hoạt Động";
        }
    }
}
