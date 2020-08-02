package com.iiplabs.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.iiplabs.web.App;
import com.iiplabs.web.reps.ILocationRepository;

@ContextConfiguration(classes = App.class)
@DataJpaTest
public class JpaTest {

	@Autowired
	private ILocationRepository locationRepository;
	
	@Test
	void injectedComponents() {
		assertThat(locationRepository).isNotNull();
	}
	
}