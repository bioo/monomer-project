package com.practice.my.practice.web.admin.sys.dao;

import com.practice.my.practice.web.domain.admin.TbUser;

import java.util.List;
import java.util.Map;

/**
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/7 16:08
 */
public interface TbUserMapper {
    /**
     * 登录接口
     * @param map
     * @return
     */
    TbUser login(Map<String, Object> map);

    /**
     * 更新，通过主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TbUser record);

    int deleteByPrimaryKey(Long id);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(Long id);



    int updateByPrimaryKey(TbUser record);

    TbUser findUserByEmail(String email);

//    List<URoleBo> selectRoleByUserId(Long id);

}
