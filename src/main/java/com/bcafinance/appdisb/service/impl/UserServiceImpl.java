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
import com.bcafinance.appdisb.model.user.User;
import com.bcafinance.appdisb.model.user.UserRepository;
import com.bcafinance.appdisb.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        log.info("Saving User {} to the database",user.getName());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving Role {} to the database",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Saving Role {} to user {}",roleName,username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.setRole(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}",username);
        return userRepository.findByUsername(username);
    }

    @Override
    public Response<List<User>> getUsers(Request req) {
        Response<List<User>> res = new Response<List<User>>();
        try {
            Pageable firstPageWithTwoElements = PageRequest.of(req.getPage(), req.getSize());
            Page<User> p = userRepository.findAll(firstPageWithTwoElements);
            Meta meta = new Meta(p.getNumber(),p.getTotalPages(),p.getSize(),p.getTotalElements());
            res.setSuccess(p.getContent(), meta);
        } catch (Exception e) {
            res.setFailed();
        }
        
        return res;
    }
    
}
