/**
 * Copyright (c) 2015-2016 yejunwu126@126.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.jiangnan.es.cache.ehcache.listener;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheManagerEventListener;

/**
 * @description 默认cache manager 监听器
 * @author ywu@wuxicloud.com
 * 2016年2月23日 下午1:14:57
 */
public class DefaultCacheManagerEventListener implements
		CacheManagerEventListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCacheManagerEventListener.class);

	public DefaultCacheManagerEventListener() {
		
	}
	
	public DefaultCacheManagerEventListener(CacheManager cacheManager) {
		
	}
	
	public DefaultCacheManagerEventListener(CacheManager cacheManager, Properties prop) {
		
	}
	
	@Override
	public void init() throws CacheException {
		LOGGER.info("ehcache manager init...");
	}

	@Override
	public Status getStatus() {
		LOGGER.info("get ehcache status...");
		return null;
	}

	@Override
	public void dispose() throws CacheException {
		LOGGER.info("ehcache manager dispose...");
	}

	@Override
	public void notifyCacheAdded(String cacheName) {
		LOGGER.info("ehcache manager add cache '{}' ...", cacheName);
	}

	@Override
	public void notifyCacheRemoved(String cacheName) {
		LOGGER.info("ehcache manager remove cache '{}' ...", cacheName);
	}

}
