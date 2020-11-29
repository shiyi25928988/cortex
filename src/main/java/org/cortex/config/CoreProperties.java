package org.cortex.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.cortex.annotation.PropertiesFile;

import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiyi
 *
 */
@Slf4j
public class CoreProperties {
	
	
	public static void setProperties(Class<?> mainClass) {
		PropertiesFile propertiesFile = mainClass.getAnnotation(PropertiesFile.class);
		if(!Objects.isNull(propertiesFile)) {
			String[] fileNames = propertiesFile.files();
			if(fileNames.length > 0) {
				Stream.of(fileNames).forEach(file ->{
					setProperties(file);
				});
			}
		}
	}

	public static void setProperties(String propertiesFileName) {
		try {
			System.getProperties().load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileName));
		} catch (IOException e) {
			log.error(e.getLocalizedMessage());
		}
	}
	
	/**
	 * @param key
	 * @return
	 */
	public static String getProperties(String key) {
		return getProperties(key, "");
	}
	
	/**
	 * @return
	 */
	public static List<String> getKeys(){
		List<String> list = new ArrayList<>();
		System.getProperties().keySet().forEach(key ->{
			if(key instanceof String) {
				list.add((String) key);
			}
		});
		return list;
	}
	
	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getProperties(String key, String defaultValue) {
		
		if(System.getProperties().containsKey(key)) {
			String value = System.getProperties().getProperty(key);
			if(Strings.isNullOrEmpty(value)) {
				return defaultValue;
			}else {
				return value;
			}
		}else {
			return defaultValue;
		}
		
	}
	
}
