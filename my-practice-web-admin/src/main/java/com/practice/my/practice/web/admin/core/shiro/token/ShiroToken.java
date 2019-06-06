package com.practice.my.practice.web.admin.core.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

/**
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/5 21:17
 */
public class ShiroToken extends UsernamePasswordToken implements Serializable {
    /**
     * 登录密码[字符串类型] 因为父类是char[] ]
     **/
    private String pswd ;
    public ShiroToken(String username, String pswd) {
        super(username,pswd);
        this.pswd = pswd ;
    }

    public String getPswd() {
        return pswd;
    }


    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
}
