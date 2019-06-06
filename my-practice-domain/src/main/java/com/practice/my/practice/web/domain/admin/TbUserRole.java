package com.practice.my.practice.web.domain.admin;

import com.practice.my.practice.commons.persistence.BaseEntity;
import lombok.Data;

/**
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/6 14:56
 */
@Data
public class TbUserRole extends BaseEntity {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

}
