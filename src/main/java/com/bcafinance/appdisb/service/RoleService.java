package com.bcafinance.appdisb.service;

import java.util.List;

import com.bcafinance.appdisb.http.Request;
import com.bcafinance.appdisb.http.Response;
import com.bcafinance.appdisb.model.role.Role;

public interface RoleService {
    Role saveRole(Role Role);
    Role getRole(String name);
    Response<List<Role>> getRoles(Request req);
}
