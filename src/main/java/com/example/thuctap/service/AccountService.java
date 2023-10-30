package com.example.thuctap.service;

import com.example.thuctap.bean.Account;
import com.example.thuctap.bean.Role;

public interface AccountService {
    Account detailAccountByUserName(String username);

    Role getAllRoleByUserNameAndPassword(String username, String password);
}
