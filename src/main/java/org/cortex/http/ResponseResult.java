package org.cortex.http;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author SHIYI
 *
 * @param <T>
 */
@Data
@Accessors(chain = true)
public class ResponseResult<T> {
	
	@JsonProperty("DATA")	
	T data;
}
