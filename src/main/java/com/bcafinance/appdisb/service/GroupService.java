package com.bcafinance.appdisb.service;

import java.util.List;

import com.bcafinance.appdisb.http.Request;
import com.bcafinance.appdisb.http.Response;
import com.bcafinance.appdisb.model.group.Group;

public interface GroupService {
    Group saveGroup(Group Group);
    Group getGroup(String name);
    Response<List<Group>> getGroups(Request req);
}
