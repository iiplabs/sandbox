package com.iiplabs.web.reps;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.iiplabs.web.model.Location;

public interface ILocationRepository extends JpaRepository<Location, Long>, JpaSpecificationExecutor<Location> {

	@Transactional(readOnly=true)
	Optional<Location> findByInetId(String inetId);
	
}
