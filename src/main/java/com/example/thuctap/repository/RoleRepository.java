package com.example.thuctap.repository;

import com.example.thuctap.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Query(value = "select * from Roles where Status = 1", nativeQuery = true)
    List<Role> getAllRole1();

    @Modifying
    @Transactional
    @Query(value = "update Roles set Status= 0 where ID= :idRole", nativeQuery = true)
    void deleteRole(@Param("idRole") String idRole);

    @Query(value = "select Roles.* from Accounts join Authorities on Accounts.Username = Authorities.Username join Roles on Authorities.RoleID = Roles.ID where Accounts.Username = :username and Accounts.Password = :password", nativeQuery = true)
    Role getAllRoleByUserNameAndPassword(@Param("username") String username, @Param("password") String password);
}
