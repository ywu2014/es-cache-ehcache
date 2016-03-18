/**
 * Copyright (c) 2015-2016 yejunwu126@126.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.jiangnan.es.cache.ehcache.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiangnan.es.common.web.controller.BaseController;
import com.jiangnan.es.util.CollectionUtils;
import com.jiangnan.es.util.DateUtils;

/**
 * @description ehcache监控
 * @author ywu@wuxicloud.com
 * 2016年3月16日 下午5:46:52
 */
@Controller
@RequestMapping("system/manage/cache/")
public class EhCacheMonitorController extends BaseController {
	
	@Resource()
	CacheManager cacheManager;
	
	/**
	 * 缓存监控概览
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String main(Model model) {
		String[] cacheNames = cacheManager.getCacheNames();
		List<Cache> caches = null;
		if (!CollectionUtils.isEmpty(cacheNames)) {
			caches = new ArrayList<Cache>(cacheNames.length);
			for (String cacheName : cacheNames) {
				Cache cache = cacheManager.getCache(cacheName);
				caches.add(cache);
			}
		}
		model.addAttribute("caches", caches);
		return "system/manage/ehcache/list";
	}
	
	/**
	 * 缓存区块详情
	 * @param cacheName
	 * @param model
	 * @return
	 */
	@RequestMapping("{name}/detail")
	public String detail(@PathVariable(value="name") String cacheName, Model model) {
		Cache cache = cacheManager.getCache(cacheName);
		model.addAttribute("cache", cache);
		return "system/manage/ehcache/detail";
	}
	
	/**
	 * 删除缓存元素
	 * @param cacheName
	 * @param cacheKey
	 * @param response
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("{cachename}/{key}/invalid")
	public void invalid(@PathVariable("cachename") String cacheName, 
						@PathVariable("key") String cacheKey,
						HttpServletResponse response) {
		Cache cache = cacheManager.getCache(cacheName);
		
		//对于shiro的认证、授权信息缓存,直接remove返回false,即remove失败,因为认证、授权缓存的key并不是string类型,计算hashcode并不一样
		List keys = cache.getKeys();
		if (!CollectionUtils.isEmpty(keys)) {
			for (Object key : keys) {
				if (key.toString().equals(cacheKey)) {
					cache.remove(key);
				}
			}
		}
		super.jsonResponse(response, ajaxResult(1, "清除成功"));
	}
	
	/**
	 * 缓存元素详情
	 * @param cacheName
	 * @param cacheKey
	 * @param response
	 */
	@RequestMapping("{cachename}/{key}/detail")
	public void cacheValueDetail(@PathVariable("cachename") String cacheName, 
								@PathVariable("key") String cacheKey, 
								HttpServletResponse response) {
		Cache cache = cacheManager.getCache(cacheName);
		
		//对于shiro的认证、授权信息缓存,直接remove返回false,即remove失败,因为认证、授权缓存的key并不是string类型,计算hashcode并不一样
		Element element = cache.get(cacheKey);
		if (null != element) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("objectValue", element.getObjectValue().toString());
	        //data.put("size", PrettyMemoryUtils.prettyByteSize(element.getSerializedSize()));
	        data.put("hitCount", element.getHitCount());

	        Date latestOfCreationAndUpdateTime = new Date(element.getLatestOfCreationAndUpdateTime());
	        data.put("latestOfCreationAndUpdateTime", DateUtils.format(latestOfCreationAndUpdateTime));
	        Date lastAccessTime = new Date(element.getLastAccessTime());
	        data.put("lastAccessTime", DateUtils.format(lastAccessTime));
	        if(element.getExpirationTime() == Long.MAX_VALUE) {
	            data.put("expirationTime", "不过期");
	        } else {
	            Date expirationTime = new Date(element.getExpirationTime());
	            data.put("expirationTime", DateUtils.format(expirationTime));
	        }

	        data.put("timeToIdle", element.getTimeToIdle());
	        data.put("timeToLive", element.getTimeToLive());
	        data.put("version", element.getVersion());
	        
	        super.jsonResponse(response, data);
		}
	}
}
