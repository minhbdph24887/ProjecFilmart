package com.example.thuctap.service.impl;

import com.example.thuctap.bean.Account;
import com.example.thuctap.bean.Role;
import com.example.thuctap.repository.AccountRepository;
import com.example.thuctap.repository.RoleRepository;
import com.example.thuctap.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Account detailAccountByUserName(String username) {
        return accountRepository.findById(username).get();
    }

    @Override
    public Role getAllRoleByUserNameAndPassword(String username, String password) {
        return roleRepository.getAllRoleByUserNameAndPassword(username, password);
    }
}
