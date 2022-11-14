package com.bcafinance.appdisb.service;

import java.util.List;

import com.bcafinance.appdisb.http.Request;
import com.bcafinance.appdisb.http.Response;
import com.bcafinance.appdisb.model.limit.Limit;

public interface LimitService {
    Limit saveLimit(Limit Limit);
    Limit getLimit(String name);
    Response<List<Limit>> getLimits(Request req);
}
