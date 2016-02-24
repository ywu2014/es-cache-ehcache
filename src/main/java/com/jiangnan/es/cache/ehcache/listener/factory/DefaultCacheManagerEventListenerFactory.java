/**
 * Copyright (c) 2015-2016 yejunwu126@126.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.jiangnan.es.cache.ehcache.listener.factory;

import java.util.Properties;

import com.jiangnan.es.cache.ehcache.listener.DefaultCacheManagerEventListener;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.event.CacheManagerEventListener;
import net.sf.ehcache.event.CacheManagerEventListenerFactory;

/**
 * @description 默认ehcache manger监听器创建工厂
 * @author ywu@wuxicloud.com
 * 2016年2月23日 下午1:20:28
 */
public class DefaultCacheManagerEventListenerFactory extends
		CacheManagerEventListenerFactory {

	@Override
	public CacheManagerEventListener createCacheManagerEventListener(
			CacheManager cacheManager, Properties properties) {
		return new DefaultCacheManagerEventListener();
	}

}
