package com.iiplabs.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@RequiredArgsConstructor 
@ToString(callSuper=true) 
@EqualsAndHashCode(callSuper=true, onlyExplicitlyIncluded=true)
@SuppressWarnings("serial")
@Entity
@Table(name="locations")
public class Location extends BaseModel {

	@Column(name="location_name")
	private String name;
	
}
