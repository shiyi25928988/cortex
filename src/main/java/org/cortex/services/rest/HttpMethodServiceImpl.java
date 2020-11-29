package org.cortex.services.rest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.cortex.annotation.Path;
import org.cortex.annotation.PathParam;
import org.cortex.annotation.RequestBody;
import org.cortex.annotation.ResponseType;
import org.cortex.annotation.method.DELETE;
import org.cortex.annotation.method.GET;
import org.cortex.annotation.method.HEAD;
import org.cortex.annotation.method.OPTIONS;
import org.cortex.annotation.method.POST;
import org.cortex.annotation.method.PUT;
import org.cortex.exception.EmptyPathParameterException;
import org.cortex.exception.ReduplicativeMathodPathException;
import org.cortex.exception.SingleRequestBodyRequiredException;
import org.cortex.ioc.ReflectionUtils;
import org.cortex.module.CommonModule;

import com.google.common.base.Strings;
import com.google.inject.Injector;

import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpMethodServiceImpl implements HttpMethodService{
	
	private Set<Class<?>> classSet;

	private Map<String, Class<?>> classMap = new ConcurrentHashMap<>();

	private Map<String, Method> methodMap_GET = new ConcurrentHashMap<>();
	private Map<String, Method> methodMap_PUT = new ConcurrentHashMap<>();
	private Map<String, Method> methodMap_POST = new ConcurrentHashMap<>();
	private Map<String, Method> methodMap_DELETE = new ConcurrentHashMap<>();
	private Map<String, Method> methodMap_OPTIONS = new ConcurrentHashMap<>();
	private Map<String, Method> methodMap_HEAD = new ConcurrentHashMap<>();

	private Map<Method, List<String>> parameterMap = new ConcurrentHashMap<>();
	private Map<Method, Class<?>> requestBodyMap = new ConcurrentHashMap<>();

	public HttpMethodServiceImpl(final Set<Class<?>> classSet) {

		this.classSet = classSet;
		if (Objects.nonNull(this.classSet) && this.classSet.size() >= 1) {
			classSet.stream().forEach(clazz -> {
				Method[] methods = clazz.getDeclaredMethods();

				Stream.of(methods).forEach(m -> {
					// NO ELSE to ensure that we can add multi-annotation on the same method
					if (m.isAnnotationPresent(GET.class)) {
						try {
							setMethodAndClassMap(clazz, m, methodMap_GET);
						} catch (ReduplicativeMathodPathException | EmptyPathParameterException
								| SingleRequestBodyRequiredException e) {
							log.error(e.getLocalizedMessage());
						}
					}

					if (m.isAnnotationPresent(PUT.class)) {
						try {
							setMethodAndClassMap(clazz, m, methodMap_PUT);
						} catch (ReduplicativeMathodPathException | EmptyPathParameterException
								| SingleRequestBodyRequiredException e) {
							log.error(e.getLocalizedMessage());
						}
					}

					if (m.isAnnotationPresent(POST.class)) {
						try {
							setMethodAndClassMap(clazz, m, methodMap_POST);
						} catch (ReduplicativeMathodPathException | EmptyPathParameterException
								| SingleRequestBodyRequiredException e) {
							log.error(e.getLocalizedMessage());
						}
					}

					if (m.isAnnotationPresent(DELETE.class)) {
						try {
							setMethodAndClassMap(clazz, m, methodMap_DELETE);
						} catch (ReduplicativeMathodPathException | EmptyPathParameterException
								| SingleRequestBodyRequiredException e) {
							log.error(e.getLocalizedMessage());
						}
					}

					if (m.isAnnotationPresent(OPTIONS.class)) {
						try {
							setMethodAndClassMap(clazz, m, methodMap_OPTIONS);
						} catch (ReduplicativeMathodPathException | EmptyPathParameterException
								| SingleRequestBodyRequiredException e) {
							log.error(e.getLocalizedMessage());
						}
					}

					if (m.isAnnotationPresent(HEAD.class)) {
						try {
							setMethodAndClassMap(clazz, m, methodMap_HEAD);
						} catch (ReduplicativeMathodPathException | EmptyPathParameterException
								| SingleRequestBodyRequiredException e) {
							log.error(e.getLocalizedMessage());
						}
					}
				});
			});
		}
	}
	
	/**
	 * @param clazz
	 * @param method
	 * @param methodMap
	 * @throws ReduplicativeMathodPathException
	 * @throws SingleRequestBodyRequiredException
	 * @throws EmptyPathParameterException
	 */
	private void setMethodAndClassMap(Class<?> clazz, Method method, Map<String, Method> methodMap) throws  EmptyPathParameterException, SingleRequestBodyRequiredException{
		if (method.isAnnotationPresent(Path.class)) {
			Path path = method.getAnnotation(Path.class);
			if (!Strings.isNullOrEmpty(path.value())) {
				if (Objects.nonNull(methodMap.get(path.value()))) {
					throw new ReduplicativeMathodPathException("Request Path is already exist with : " + path.value());
				}
				methodMap.put(path.value(), method);
				classMap.put(path.value(), clazz);
				addMethodParameter(method);
			}
		}
	}

	/**
	 * @param method
	 * @throws EmptyPathParameterException
	 * @throws SingleRequestBodyRequiredException
	 */
	private void addMethodParameter(final Method method)
			throws EmptyPathParameterException, SingleRequestBodyRequiredException {
		if (method.getParameterCount() <= 0) {
			return;
		}
		List<String> list = new LinkedList<>();

		for (Parameter param : method.getParameters()) {
			
			if (param.isAnnotationPresent(PathParam.class)) {
				PathParam pathParam = (PathParam) param.getAnnotation(PathParam.class);
				if (Strings.isNullOrEmpty(pathParam.value())) {
					throw new EmptyPathParameterException();
				} else {
					list.add(pathParam.value());
				}
			} else if (param.isAnnotationPresent(RequestBody.class)) {
				if (Objects.nonNull(requestBodyMap.get(method))) {
					throw new SingleRequestBodyRequiredException(method.getName() + " required single parameter!!");
				}
				requestBodyMap.put(method, param.getType());
			}
		}
		parameterMap.put(method, list);
	}

	/**
	 * @param methodMap
	 * @throws Exception
	 */
	private void invoke(final Map<String, Method> methodMap) throws Exception {
		List<String> args = new ArrayList<>();
		
		String path = RestHelper.getContext().normalizedPath();
		
		Method method = methodMap.get(path);

		if (Objects.nonNull(method)) {
			Class<?> clazz = classMap.get(path);
			List<String> parameters = parameterMap.get(method);
			Class<?> requestBodyClass = requestBodyMap.get(method);

			if (Objects.nonNull(parameters) && !parameters.isEmpty()) {

				parameters.forEach(p -> {
					args.add(RestHelper.getRequest().getParam(p));
				});
				invoke(clazz, method, args.toArray());
				return;
			}
			if (Objects.nonNull(requestBodyClass)) {
				
				
				RestHelper.getRequest().body().onComplete(handler->{
					JsonObject jsonObject = new JsonObject(handler.result().toString());
					
					try {
						invoke(clazz, method, jsonObject.mapTo(requestBodyClass));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				
				return;

			}
			invoke(clazz, method);
		} else {
			//HttpRespHelper.send404Status();
		}
	}

	/**
	 * @param clazz
	 * @param method
	 * @throws Exception
	 */
	private void invoke(Class<?> clazz, Method method, Object... args) throws Exception {
		if (Objects.nonNull(clazz)) {
			if (Objects.nonNull(method)) {
				Object obj = ReflectionUtils.newInstance(clazz);
				Field[] fields = clazz.getDeclaredFields();
				Injector injector = CommonModule.getInjector();
				Stream.of(fields).forEach(field -> {
					if (field.isAnnotationPresent(com.google.inject.Inject.class)
							|| field.isAnnotationPresent(javax.inject.Inject.class)) {
						try {
							ReflectionUtils.setField(obj, field, injector.getInstance(field.getType()));
						} catch (Exception e) {
							e.printStackTrace();
							log.error(e.getMessage());
						}
					}
				});
				
				Object invokeResult = ReflectionUtils.invokeMethod(obj, method, args);
				
				ResponseType responseType = method.getAnnotation(ResponseType.class);
				if(!Objects.isNull(responseType)) {
					RestHelper.getResponse().putHeader("Content-Type", responseType.type().getType());
				}
				
				if(Objects.isNull(invokeResult)) {
					RestHelper.getResponse().end();
				}
				
				if(invokeResult instanceof String) {
					RestHelper.getResponse().send((String)invokeResult);
				}
				
				RestHelper.getResponse().send(JsonObject.mapFrom(invokeResult).encode());
				//HttpRespHelper.sendResponseData(ReflectionUtils.invokeMethod(obj, method, args));
			}
		}
	}
	
	@Override
	public void doGet() {
		try {
			log.info("doGet");
			invoke(methodMap_GET);
		} catch (Exception e) {
			log.error(e.toString());
		}
		
	}

	@Override
	public void doPut() {
		try {
			invoke(methodMap_PUT);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void doPost() {
		try {
			log.info("doPost");
			invoke(methodMap_POST);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void doDelete() {
		log.info("doing DELETE");
		
	}

	@Override
	public void doHead() {
		log.info("doing HEAD");
		
	}

	@Override
	public void doOptions() {
		log.info("doing OPTIONS");
		
	}

	@Override
	public void doTrace() {
		log.info("doing OPTIONS");
		
	}
	
	@Override
	public void doPatch() {
		log.info("doing OPTIONS");
		
	}

}
