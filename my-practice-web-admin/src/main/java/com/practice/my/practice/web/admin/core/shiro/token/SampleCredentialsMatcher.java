package com.practice.my.practice.web.admin.core.shiro.token;

import com.practice.my.practice.commons.encryption.AesEncryptUtil;
import com.practice.my.practice.web.admin.core.shiro.util.ShiroUtil;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;

/**
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/5 16:11
 */
public class SampleCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //完全由自定义用户输入的密码，和数据库中的密码比对规则
        UsernamePasswordToken uTtoken = (UsernamePasswordToken) token;
        String pwd = new String(uTtoken.getPassword());
        // 把密码中的去uuidSalt(将前台传入的密码解密)
        Session session = SecurityUtils.getSubject().getSession();
        String key = (String) session.getAttribute("uuidsalt");
        try {
            pwd = AesEncryptUtil.desEncrypt(pwd, key, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //密码解码成功后，立即将session的盐值删除
        session.removeAttribute("uuidsalt");
        if(pwd == null)throw new SecurityException("受到了重放攻击");
        //pwd再进行md5加密
//		ByteSource credentialsSalt = ByteSource.Util.bytes(uToken.getUsername());//使用用户名作为盐值
        String formPasswordpwd = ShiroUtil.shiroMD5(pwd, uTtoken.getUsername());
        //从info中获取数据库中的密码
        String accountCredentials = (String) getCredentials(info);
        return formPasswordpwd.equals(accountCredentials);
    }

//	public static void main(String[] args) {
//		String formPasswordpwd = (new SimpleHash("MD5","202cb962ac59075b964b07152d234b70","admin",1024)).toString();
//		System.out.println(formPasswordpwd);
//	}
}
