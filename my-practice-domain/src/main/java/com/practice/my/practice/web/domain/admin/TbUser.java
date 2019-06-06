package com.practice.my.practice.web.domain.admin;

import com.practice.my.practice.commons.persistence.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/6 14:29
 */
@Data
public class TbUser extends BaseEntity {
    //0:禁止登录
    public static final Long _0 = new Long(0);
    //1:有效
    public static final Long _1 = new Long(1);

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱号
     */
    private String email;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 用户状态（1有效，0禁用）
     */
    private Integer status;

    /**
     * 账号过期时间
     */
    private Date overdueTime;

    /**
     * 无参构造
     */
    public TbUser() {
    }

    /**
     * 有参构造
     * @param user
     */
    public TbUser(TbUser user) {
        setId(user.getId());
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.lastLoginTime = user.getLastLoginTime();
        this.status = user.getStatus();
        this.overdueTime = user.getOverdueTime();
        setCreateUserId(user.getCreateUserId());
        setCreateTime(user.getCreateTime());
        setUpdateUserId(user.getUpdateUserId());
        setUpdateTime(user.getUpdateTime());
    }
}
