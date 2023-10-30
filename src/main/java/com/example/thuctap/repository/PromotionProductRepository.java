package com.example.thuctap.repository;

import com.example.thuctap.bean.PromotionProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionProductRepository extends JpaRepository<PromotionProduct, Long> {
    @Query(value = "select * from PromotionsProduct where PromotionsID= :idPromotion", nativeQuery = true)
    Page<PromotionProduct> getAllPromotionProduct(Pageable pageable, @Param("idPromotion") Long idPromotion);
}
