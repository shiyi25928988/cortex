package org.cortex.services;

import io.vertx.servicediscovery.Record;


public interface ServicesPublishService {

	void publish(Record record);
	void unpublish(Record record);
}
