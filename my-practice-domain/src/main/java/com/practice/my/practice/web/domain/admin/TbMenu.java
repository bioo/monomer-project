package com.practice.my.practice.web.domain.admin;

import com.practice.my.practice.commons.persistence.BaseEntity;
import lombok.Data;

/**
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/6 14:50
 */
@Data
public class TbMenu extends BaseEntity {
    /**
     * 父级菜单ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     *菜单url
     */
    private String menuUrl;

    /**
     * 权限（多个逗号分隔，如：user:add,user:delete）
     */
    private String permission;

    /**
     * 类型  0：目录   1：菜单   2：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 该类目是否为父类目，1为true，0为false
     */
    private Integer isParent;

}
