package com.practice.my.practice.web.admin.dto;

import com.practice.my.practice.web.domain.admin.TbUser;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Session  + User Bo
 *
 * @author sojson.com
 */
@Data
public class UserOnlineDto extends TbUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Session Id
     */
    private String sessionId;

    /**
     * Session Host
     */
    private String host;

    /**
     * Session创建时间
     */
    private Date startTime;

    /**
     * Session最后交互时间
     */
    private Date lastAccess;

    /**
     * Session timeout
     */
    private long timeout;

    /**
     * session 是否踢出
     */
    private boolean sessionStatus = Boolean.TRUE;

    public UserOnlineDto(TbUser user) {
        super(user);
    }
}
