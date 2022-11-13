package com.bcafinance.appdisb.model.user;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User,String> {
    User findByUsername(String username);
}