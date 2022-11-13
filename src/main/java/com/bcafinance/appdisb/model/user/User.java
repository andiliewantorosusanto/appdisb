package com.bcafinance.appdisb.model.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bcafinance.appdisb.model.group.Group;
import com.bcafinance.appdisb.model.limit.Limit;
import com.bcafinance.appdisb.model.role.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "AD_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    @OneToOne(fetch = FetchType.EAGER)
    private Role role;
    @OneToOne(fetch = FetchType.EAGER)
    private Limit limit;
    @OneToOne(fetch = FetchType.EAGER)
    private Group group;
}

