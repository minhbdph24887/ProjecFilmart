package com.example.thuctap.repository;

import com.example.thuctap.bean.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
    @Query(value = "select * from Producers where Status= 1", nativeQuery = true)
    List<Producer> cbbProducer();

    @Modifying
    @Transactional
    @Query(value = "update Producers set Status= 0 where ID= :idProducer", nativeQuery = true)
    void deleteProducer(@Param("idProducer") Long idProducer);
}
