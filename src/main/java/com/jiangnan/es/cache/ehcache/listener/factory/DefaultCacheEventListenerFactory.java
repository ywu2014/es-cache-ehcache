/**
 * Copyright (c) 2015-2016 yejunwu126@126.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.jiangnan.es.cache.ehcache.listener.factory;

import java.util.Properties;

import com.jiangnan.es.cache.ehcache.listener.DefaultCacheEventListener;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

/**
 * @description 默认ehcache监听器创建工厂
 * @author ywu@wuxicloud.com
 * 2016年2月23日 下午1:37:46
 */
public class DefaultCacheEventListenerFactory extends CacheEventListenerFactory {

	@Override
	public CacheEventListener createCacheEventListener(Properties properties) {
		return new DefaultCacheEventListener();
	}

}
