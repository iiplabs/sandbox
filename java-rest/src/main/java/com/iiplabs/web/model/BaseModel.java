package com.iiplabs.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@RequiredArgsConstructor 
@ToString 
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuppressWarnings("serial")
public abstract class BaseModel implements Serializable {

	@JsonIgnore
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;

	@EqualsAndHashCode.Include
	@Size(max=50)
	@NotNull
	@Column(name="inet_id")
	private String inetId;

	@JsonIgnore
	@CreatedDate
	@Column(name="created")
	private Date created;

	@JsonIgnore
	@LastModifiedDate
	@Column(name="updated")
	private Date updated;

	@JsonIgnore
	@CreatedBy
	@Column(name="created_by")
	private String createdBy;

	@JsonIgnore
	@LastModifiedBy
	@Column(name="updated_by")
	private String updatedBy;

	@JsonIgnore
	@Version
	@Column(name="optlock")
	private int version;
	
	protected int getVersion() {
		return version;
	}
	
	public boolean isNew() {
		return getId() == 0;
	}
	
}
