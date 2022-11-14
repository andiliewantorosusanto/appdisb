package com.bcafinance.appdisb.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bcafinance.appdisb.http.Meta;
import com.bcafinance.appdisb.http.Request;
import com.bcafinance.appdisb.http.Response;
import com.bcafinance.appdisb.model.menu.Menu;
import com.bcafinance.appdisb.model.menu.MenuRepository;
import com.bcafinance.appdisb.service.MenuService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class MenuServiceImpl implements MenuService{
    private final MenuRepository menuRepository;

    @Override
    public Menu saveMenu(Menu menu) {
        log.info("Saving Menu {} to the database",menu.getName());
        return menuRepository.save(menu);
    }

    @Override
    public Menu getMenu(String name) {
        log.info("Fetching menu {}",name);
        return menuRepository.findByName(name);
    }

    @Override
    public Response<List<Menu>> getMenus(Request req) {
        Response<List<Menu>> res = new Response<List<Menu>>();
        try {
            Pageable firstPageWithTwoElements = PageRequest.of(req.getPage(), req.getSize());
            Page<Menu> p = menuRepository.findAll(firstPageWithTwoElements);
            Meta meta = new Meta(p.getNumber(),p.getTotalPages(),p.getSize(),p.getTotalElements());
            res.setSuccess(p.getContent(), meta);
        } catch (Exception e) {
            res.setFailed();
        }
        
        return res;
    }
    
}
