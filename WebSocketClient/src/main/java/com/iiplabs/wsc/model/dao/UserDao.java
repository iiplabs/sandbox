package com.iiplabs.wsc.model.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.iiplabs.wsc.model.dao.base.BaseDao;

@Entity
@Table(name="users")
@SuppressWarnings("serial")
public class UserDao extends BaseDao {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;

	@Size(max=50)
	@Column(name="email")
	private String email;
	
	@Size(max=100)
	@Column(name="password")
	private String password;

	public UserDao() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
