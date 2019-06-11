package com.practice.my.practice.web.admin.common.controller;

import com.practice.my.practice.commons.controller.CommonController;
import com.practice.my.practice.commons.util.LoggerUtils;
import com.practice.my.practice.commons.vcode.Captcha;
import com.practice.my.practice.commons.vcode.GifCaptcha;
import com.practice.my.practice.web.admin.core.shiro.token.manager.TokenManager;
import com.practice.my.practice.web.admin.core.shiro.util.verifyCode.VerifyCodeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/10 21:20
 */
public class PageController extends CommonController {
    /**
     * 获取验证码
     *
     * @param response
     */
    @RequestMapping(value = "/getVCode", method = RequestMethod.GET)
    public void getVCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpg");

            //生成随机字串
            String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
            //存入Shiro会话session
            TokenManager.setVal2Session(VerifyCodeUtils.V_CODE, verifyCode.toLowerCase());
            //生成图片
            int w = 146, h = 33;
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "获取验证码异常：%s", e.getMessage());
        }
    }

    /**
     * 获取验证码（Gif版本）
     *
     * @param response
     */
    @RequestMapping(value = "/getGifCode", method = RequestMethod.GET)
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146, 42, 4);
            //输出
            ServletOutputStream out = response.getOutputStream();
            captcha.out(out);
            out.flush();
            //存入Shiro会话session
            System.out.println(captcha.text().toLowerCase());
            TokenManager.setVal2Session(VerifyCodeUtils.V_CODE, captcha.text().toLowerCase());
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "获取验证码异常：%s", e.getMessage());
        }
    }
}
