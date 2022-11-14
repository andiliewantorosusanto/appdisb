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
import com.bcafinance.appdisb.model.role.Role;
import com.bcafinance.appdisb.model.role.RoleRepository;
import com.bcafinance.appdisb.service.RoleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        log.info("Saving Role {} to the database",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public Role getRole(String name) {
        log.info("Fetching role {}",name);
        return roleRepository.findByName(name);
    }

    @Override
    public Response<List<Role>> getRoles(Request req) {
        Response<List<Role>> res = new Response<List<Role>>();
        try {
            Pageable firstPageWithTwoElements = PageRequest.of(req.getPage(), req.getSize());
            Page<Role> p = roleRepository.findAll(firstPageWithTwoElements);
            Meta meta = new Meta(p.getNumber(),p.getTotalPages(),p.getSize(),p.getTotalElements());
            res.setSuccess(p.getContent(), meta);
        } catch (Exception e) {
            res.setFailed();
        }
        
        return res;
    }
    
}
