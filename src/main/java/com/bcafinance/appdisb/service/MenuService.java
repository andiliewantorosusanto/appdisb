package com.bcafinance.appdisb.service;

import java.util.List;

import com.bcafinance.appdisb.http.Request;
import com.bcafinance.appdisb.http.Response;
import com.bcafinance.appdisb.model.menu.Menu;

public interface MenuService {
    Menu saveMenu(Menu Menu);
    Menu getMenu(String name);
    Response<List<Menu>> getMenus(Request req);
}
