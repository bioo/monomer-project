package com.practice.my.practice.web.admin.core.shiro.cache;

import org.apache.shiro.cache.Cache;

/**
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/5 14:40
 */
public interface ShiroCacheManager {
    <K, V> Cache<K, V> getCache(String name);

    void destroy();

}
