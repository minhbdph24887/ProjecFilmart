package com.example.thuctap.repository;

import com.example.thuctap.bean.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query(value = "select * from OrderDetail where OrderID = :idOrder", nativeQuery = true)
    List<OrderDetail> getAllOrderDetailByOrderID(@Param("idOrder") Long idOrder);
}
