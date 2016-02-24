/**
 * Copyright (c) 2015-2016 yejunwu126@126.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.jiangnan.es.cache.ehcache.listener;

import java.util.Properties;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description 默认ehcache监听器
 * @author ywu@wuxicloud.com
 * 2016年2月23日 下午1:27:49
 */
public class DefaultCacheEventListener implements CacheEventListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCacheEventListener.class);

	public DefaultCacheEventListener() {
		
	}
	
	public DefaultCacheEventListener(Properties props) {
		
	}
	
	@Override
	public void notifyElementRemoved(Ehcache cache, Element element)
			throws CacheException {
		LOGGER.info("cache {} remove element key = {}, value = {} ...", cache.getName(), element.getObjectKey(), element.getObjectValue());
	}

	@Override
	public void notifyElementPut(Ehcache cache, Element element)
			throws CacheException {
		LOGGER.info("cache {} put element key = {}, value = {} ...", cache.getName(), element.getObjectKey(), element.getObjectValue());
	}

	@Override
	public void notifyElementUpdated(Ehcache cache, Element element)
			throws CacheException {
		LOGGER.info("cache {} update element key = {}, value = {} ...", cache.getName(), element.getObjectKey(), element.getObjectValue());
	}

	@Override
	public void notifyElementExpired(Ehcache cache, Element element) {
		LOGGER.info("cache {} expire element key = {}, value = {} ...", cache.getName(), element.getObjectKey(), element.getObjectValue());
	}

	@Override
	public void notifyElementEvicted(Ehcache cache, Element element) {
		LOGGER.info("cache {} evict element key = {}, value = {} ...", cache.getName(), element.getObjectKey(), element.getObjectValue());
	}

	@Override
	public void notifyRemoveAll(Ehcache cache) {
		LOGGER.info("cache {} remove all element ...", cache.getName());
	}

	@Override
	public void dispose() {
		LOGGER.info("cache dispose...");
	}

	public Object clone() throws CloneNotSupportedException {  
		throw new CloneNotSupportedException();  
	}
}
