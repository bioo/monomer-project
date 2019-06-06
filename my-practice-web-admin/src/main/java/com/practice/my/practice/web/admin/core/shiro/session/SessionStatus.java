package com.practice.my.practice.web.admin.core.shiro.session;

import java.io.Serializable;

/**
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/5 15:00
 */
public class SessionStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    //是否踢出 true:有效，false：踢出。
    private Boolean onlineStatus = Boolean.TRUE;


    public Boolean isOnlineStatus(){
        return onlineStatus;
    }

    public Boolean getOnlineStatus() {
        return onlineStatus;
    }
    public void setOnlineStatus(Boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
}
