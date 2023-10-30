package com.example.thuctap.repository;

import com.example.thuctap.bean.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select * from Orders where Username = :username", nativeQuery = true)
    Page<Order> findByUsername(@Param("username") String username, Pageable pageable);
}
