package com.iiplabs.wsc.model.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.iiplabs.wsc.model.dao.base.BaseDao;

@Entity
@Table(name="tokens")
@SuppressWarnings("serial")
public class TokenDao extends BaseDao {

	@NotNull
	@Size(max=36)
	@Id
	@Column(name="token_id", updatable=false)
	private String tokenId;
	
	@NotNull
	@Column(name="expiry_date")
	private Date expiryDate;

	@ManyToOne
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="usr", referencedColumnName="id")
	@Fetch(FetchMode.JOIN)
	private UserDao user;

	@NotNull
	@Size(max=2)
	@Column(name="status")
	private String status;
	
	public TokenDao() {
		super();
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public UserDao getUser() {
		return user;
	}

	public void setUser(UserDao user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
