package com.iiplabs.wsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.iiplabs.wsc.model.dao.UserDao;

public interface IUserRepository extends JpaRepository<UserDao, Long>, JpaSpecificationExecutor<UserDao> {

}
