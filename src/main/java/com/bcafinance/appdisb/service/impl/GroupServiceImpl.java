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
import com.bcafinance.appdisb.model.group.Group;
import com.bcafinance.appdisb.model.group.GroupRepository;
import com.bcafinance.appdisb.service.GroupService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class GroupServiceImpl implements GroupService{
    private final GroupRepository groupRepository;

    @Override
    public Group saveGroup(Group group) {
        log.info("Saving Group {} to the database",group.getName());
        return groupRepository.save(group);
    }

    @Override
    public Group getGroup(String name) {
        log.info("Fetching group {}",name);
        return groupRepository.findByName(name);
    }

    @Override
    public Response<List<Group>> getGroups(Request req) {
        Response<List<Group>> res = new Response<List<Group>>();
        try {
            Pageable firstPageWithTwoElements = PageRequest.of(req.getPage(), req.getSize());
            Page<Group> p = groupRepository.findAll(firstPageWithTwoElements);
            Meta meta = new Meta(p.getNumber(),p.getTotalPages(),p.getSize(),p.getTotalElements());
            res.setSuccess(p.getContent(), meta);
        } catch (Exception e) {
            res.setFailed();
        }
        
        return res;
    }
    
}
