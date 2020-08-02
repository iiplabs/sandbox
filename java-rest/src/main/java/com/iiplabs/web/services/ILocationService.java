package com.iiplabs.web.services;

import java.util.Optional;

import com.iiplabs.web.model.Location;

public interface ILocationService {

	Optional<Location> findByInetId(final String inetId);

}
