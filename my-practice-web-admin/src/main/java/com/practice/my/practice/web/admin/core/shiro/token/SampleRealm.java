package com.practice.my.practice.web.admin.core.shiro.token;

import com.practice.my.practice.commons.constant.Constant;
import com.practice.my.practice.commons.util.StringUtils;
import com.practice.my.practice.web.admin.core.shiro.token.manager.TokenManager;
import com.practice.my.practice.web.admin.sys.service.MenuService;
import com.practice.my.practice.web.admin.sys.service.RoleService;
import com.practice.my.practice.web.admin.sys.service.TbserService;
import com.practice.my.practice.web.domain.admin.TbMenu;
import com.practice.my.practice.web.domain.admin.TbUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 *
 * shiro 认证 + 授权   重写
 *
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/5 16:09
 */
public class SampleRealm extends AuthorizingRealm {
    @Autowired
    TbserService tbUserService;
    @Autowired
    MenuService menuService;
    @Autowired
    RoleService roleService;

    public SampleRealm() {
        super();
    }

    /**
     * 对用户进行授权和授予权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        Long userId = TokenManager.getUserId();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        //根据用户ID查询角色（role），放入到Authorization里。
        Set<String> roles = this.roleService.findRoleByUserId(userId);
        info.setRoles(roles);
        //根据用户ID查询权限（permission），放入到Authorization里。
        List<String> permsList = null;
        if (userId == Constant.SUPER_ADMIN) {
            List<TbMenu> menuList = this.menuService.listMenu(Constant.SUPER_ADMIN);
            permsList = new ArrayList<>(menuList.size());
            for (TbMenu menu : menuList) {
                permsList.add(menu.getPermission());
            }
        } else {
            permsList = this.menuService.findPermissionByUserId(userId);
        }
        Set<String> permsSet = new HashSet<String>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     *  认证信息，主要针对用户登录，
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {

        ShiroToken token = (ShiroToken) authcToken;
        TbUser user = this.tbUserService.login(token.getUsername(),token.getPswd());
        if(null == user){
            throw new AccountException("帐号或密码不正确！");
            /**
             * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
             */
        }else if(TbUser._0.equals(user.getStatus())){
            throw new DisabledAccountException("帐号已经禁止登录！");
        }else{
            //更新登录时间 last login time
            user.setLastLoginTime(new Date());
            this.tbUserService.updateByPrimaryKeySelective(user);
        }
        ByteSource slat = ByteSource.Util.bytes(user.getUsername());
        return new SimpleAuthenticationInfo(user,user.getPassword(), slat, getName());
    }


    /**
     * 清空当前用户权限信息
     */
    public  void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
    /**
     * 指定principalCollection 清除
     */
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
}
