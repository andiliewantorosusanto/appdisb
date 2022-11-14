package com.bcafinance.appdisb.service;

import com.bcafinance.appdisb.http.Response;
import com.bcafinance.appdisb.http.auth.AuthResponse;
import com.bcafinance.appdisb.http.auth.LoginRequest;

public interface AuthService {
    boolean login(Response<AuthResponse> baseResponseAPI, LoginRequest body);
}
