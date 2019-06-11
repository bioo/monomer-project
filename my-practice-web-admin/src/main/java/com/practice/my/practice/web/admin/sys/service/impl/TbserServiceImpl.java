package com.practice.my.practice.web.admin.sys.service.impl;

import com.practice.my.practice.web.admin.sys.dao.TbUserMapper;
import com.practice.my.practice.web.admin.sys.service.TbserService;
import com.practice.my.practice.web.domain.admin.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/6 16:02
 */
@Service
public class TbserServiceImpl implements TbserService {

    @Autowired
    TbUserMapper tbUserMapper;

    @Override
    public TbUser insert(TbUser record) {
        return null;
    }

    @Override
    public TbUser insertSelective(TbUser record) {
        return null;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(TbUser entity) {
        return this.tbUserMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int updateByPrimaryKey(TbUser record) {
        return 0;
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public TbUser login(String username, String password) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("username", username);
        map.put("password", password);
        TbUser user = this.tbUserMapper.login(map);
        return user;
    }

    @Override
    public TbUser selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public TbUser findUserByEmail(String email) {
        return null;
    }
}
