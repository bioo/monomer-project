package com.practice.my.practice.web.admin.core.shiro.service;

/**
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/5 22:08
 */
public interface ShiroManager {
    /**
     * 加载过滤配置信息
     * @return
     */
    public String loadFilterChainDefinitions();

    /**
     * 重新构建权限过滤器
     * 一般在修改了用户角色、用户等信息时，需要再次调用该方法
     */
    public void reCreateFilterChains();
}
