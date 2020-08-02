package com.iiplabs.web.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiplabs.web.exceptions.ItemNotFoundException;
import com.iiplabs.web.model.Location;
import com.iiplabs.web.services.ILocationService;


@RequestMapping("/api")
@RestController
@Validated
public class LocationController {

	@Autowired
	private ILocationService locationService;

	@PreAuthorize("permitAll()")
	@GetMapping("/location/{inetId}")
	public Location findByInetId(@PathVariable("inetId") String inetId) {
		Optional<Location> o = locationService.findByInetId(inetId);
		
		if (!o.isPresent()) {
			throw new ItemNotFoundException(String.format("inetId-%s", inetId));
		}
		
		return o.get();
	}
	
}
