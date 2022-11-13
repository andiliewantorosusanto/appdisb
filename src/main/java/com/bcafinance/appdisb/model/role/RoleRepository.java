package com.bcafinance.appdisb.model.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
    Role findByName(String name);
}