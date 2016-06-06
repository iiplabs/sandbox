package com.iiplabs.wsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.iiplabs.wsc.model.dao.TokenDao;

public interface ITokenRepository extends JpaRepository<TokenDao, String>, JpaSpecificationExecutor<TokenDao> {

}
