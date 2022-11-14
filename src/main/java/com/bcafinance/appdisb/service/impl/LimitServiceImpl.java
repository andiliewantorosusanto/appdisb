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
import com.bcafinance.appdisb.model.limit.Limit;
import com.bcafinance.appdisb.model.limit.LimitRepository;
import com.bcafinance.appdisb.service.LimitService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class LimitServiceImpl implements LimitService{
    private final LimitRepository limitRepository;

    @Override
    public Limit saveLimit(Limit limit) {
        log.info("Saving Limit {} to the database",limit.getName());
        return limitRepository.save(limit);
    }

    @Override
    public Limit getLimit(String name) {
        log.info("Fetching limit {}",name);
        return limitRepository.findByName(name);
    }

    @Override
    public Response<List<Limit>> getLimits(Request req) {
        Response<List<Limit>> res = new Response<List<Limit>>();
        try {
            Pageable firstPageWithTwoElements = PageRequest.of(req.getPage(), req.getSize());
            Page<Limit> p = limitRepository.findAll(firstPageWithTwoElements);
            Meta meta = new Meta(p.getNumber(),p.getTotalPages(),p.getSize(),p.getTotalElements());
            res.setSuccess(p.getContent(), meta);
        } catch (Exception e) {
            res.setFailed();
        }
        
        return res;
    }
    
}
