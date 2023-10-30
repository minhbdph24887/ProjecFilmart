package com.example.thuctap.repository;

import com.example.thuctap.bean.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    @Query(value = "select * from ProductDetails inner join Categories on ProductDetails.CategoryID = Categories.ID where Categories.ID = :idCategory order by Categories.ID", nativeQuery = true)
    Page<ProductDetail> findProductDetailByCategory(Pageable pageable, @Param("idCategory") Long categoryId);

    @Query(value = "select * from ProductDetails where CategoryID = :idCategory", nativeQuery = true)
    List<ProductDetail> findProductDetailByCategory(@Param("idCategory") Long idCategory);

    @Query(value = "select * from ProductDetails where Status= 1", nativeQuery = true)
    List<ProductDetail> cbbProductDetails();

    @Modifying
    @Transactional
    @Query(value = "update ProductDetails set Status= 0 where ID= :idProductDetail", nativeQuery = true)
    void deleteProductDetail(@Param("idProductDetail") Long idProductDetail);
}
