package org.cortex.services.impl;

import org.cortex.services.ServicesPublishService;

import com.google.inject.Inject;

import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServicesPublishServiceImpl implements ServicesPublishService{
	

	@Inject
	ServiceDiscovery serviceDiscovery;
	
	@Override
	public void publish(Record record) {
		serviceDiscovery.publish(record, ar->{
			if(ar.succeeded()) {
				log.info(record.getName() + "published success!");
			} else {
				log.info(record.getName() + "published failed!");
			}
		});
	}

	@Override
	public void unpublish(Record record) {
		serviceDiscovery.unpublish(record.getRegistration());
	}

}
