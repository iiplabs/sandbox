package com.iiplabs.cacheservice.configuration;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.iiplabs.cacheservice.web.ICacheWebService;

@Configuration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class SpringConfigurationWebService {

    @Autowired
    private Bus cxfBus;
    
    @Autowired
    private ICacheWebService cacheWebService;
    
    @Bean
    public Endpoint claimData() {
        EndpointImpl endpoint = new EndpointImpl(cxfBus, cacheWebService);
        endpoint.setAddress("/cache");
        endpoint.publish();
        return endpoint;
    }
    
}
