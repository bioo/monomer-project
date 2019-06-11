package com.practice.my.practice.web.admin.sys.service;

import com.practice.my.practice.web.admin.core.mybatis.page.Pagination;
import com.practice.my.practice.web.domain.admin.TbUser;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

/**
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/6 15:59
 */
public interface TbserService {



    TbUser insert(TbUser record);

    TbUser insertSelective(TbUser record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);

    TbUser login(String email ,String pswd);

    TbUser selectByPrimaryKey(Long id);

    TbUser findUserByEmail(String email);








//    Pagination<TbUser> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
//
//    Map<String, Object> deleteUserById(String ids);
//
//    Map<String, Object> updateForbidUserById(Long id, Long status);
//
//    Pagination<UserRoleAllocationBo> findUserAndRole(ModelMap modelMap,
//                                                     Integer pageNo, Integer pageSize);
//
//    List<URoleBo> selectRoleByUserId(Long id);
//
//    Map<String, Object> addRole2User(Long userId, String ids);
//
//    Map<String, Object> deleteRoleByUserIds(String userIds);

}
