package com.practice.my.practice.web.domain.admin;

import com.practice.my.practice.commons.persistence.BaseEntity;
import lombok.Data;

/**
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/6 14:48
 */
@Data
public class TbRole extends BaseEntity {
    /**
     * 角色名
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;
}
