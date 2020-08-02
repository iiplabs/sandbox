package com.iiplabs.web.services;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiplabs.web.model.Location;
import com.iiplabs.web.reps.ILocationRepository;

@Service
public class LocationService implements ILocationService {

	private static Logger logger = LogManager.getLogger(LocationService.class);
	
	@Autowired
	private ILocationRepository locationRepository;
	
	@Override
	@Transactional(readOnly=true)
	@Retryable
	public Optional<Location> findByInetId(final String inetId) {
		return locationRepository.findByInetId(inetId);
	}
	
}
