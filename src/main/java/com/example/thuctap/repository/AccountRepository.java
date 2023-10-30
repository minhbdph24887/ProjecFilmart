package com.example.thuctap.repository;

import com.example.thuctap.bean.Account;
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
public interface AccountRepository extends JpaRepository<Account, String> {
    // Hien thi cac Account Hoat Dong
    @Query(value = "select * from Accounts where Status = 1", nativeQuery = true)
    List<Account> getAllAccount1();

    // update trạng thái của Admin
    @Modifying
    @Transactional
    @Query(value = "update Accounts set Status= 0 where Username= :username", nativeQuery = true)
    void deleteAccount(@Param("username") String username);

    @Query(value = "select * from Accounts inner join Authorities on Accounts.Username = Authorities.Username inner join Roles on Authorities.RoleID = Roles.ID where Roles.ID = 'ADMIN'", nativeQuery = true)
    Page<Account> getAllAccountAdmin(Pageable pageable);

    @Query(value = "select * from Accounts inner join Authorities on Accounts.Username = Authorities.Username inner join Roles on Authorities.RoleID = Roles.ID where Roles.ID = 'USER'", nativeQuery = true)
    Page<Account> getAllAccountClient(Pageable pageable);
}
