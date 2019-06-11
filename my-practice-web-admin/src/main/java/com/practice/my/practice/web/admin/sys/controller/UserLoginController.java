package com.practice.my.practice.web.admin.sys.controller;

import com.practice.my.practice.commons.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/10 21:02
 */
@RequestMapping("sys")
public class UserLoginController extends BaseController {

    /**
     * 登录界面
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model, HttpSession session){
        Subject curUser = SecurityUtils.getSubject();
        if (curUser.isAuthenticated()||curUser.isRemembered()) {
            return redirect("/sys/index");
        }
        // 生成一组16位的随机数
        int hashcodeV = UUID.randomUUID().hashCode();
        if (hashcodeV < 0)
            hashcodeV = -hashcodeV;
        String uuidsalt = String.format("%016d", hashcodeV);
        // 吧uuid盐值同时保存到前台和后台
        model.addAttribute("uuidsalt", uuidsalt);
        session.setAttribute("uuidsalt", uuidsalt);
        return "login";
    }
}
