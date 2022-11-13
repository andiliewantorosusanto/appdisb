package com.bcafinance.appdisb.service;

import java.util.List;

import com.bcafinance.appdisb.http.Request;
import com.bcafinance.appdisb.http.Response;
import com.bcafinance.appdisb.model.role.Role;
import com.bcafinance.appdisb.model.user.User;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    Response<List<User>> getUsers(Request req);
}
