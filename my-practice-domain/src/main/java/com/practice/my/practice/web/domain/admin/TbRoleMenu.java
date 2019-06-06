package com.practice.my.practice.web.domain.admin;

import com.practice.my.practice.commons.persistence.BaseEntity;
import lombok.Data;

/**
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/6 14:58
 */
@Data
public class TbRoleMenu extends BaseEntity {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

}
