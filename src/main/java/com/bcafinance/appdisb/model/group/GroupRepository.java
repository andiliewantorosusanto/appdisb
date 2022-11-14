package com.bcafinance.appdisb.model.group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,String> {
    Group findByName(String name);
}