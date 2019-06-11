package com.practice.my.practice.commons.controller;

import com.practice.my.practice.commons.util.LoggerUtils;
import com.practice.my.practice.commons.util.StringUtils;
import com.practice.my.practice.commons.vcode.Captcha;
import com.practice.my.practice.commons.vcode.SpecCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 前端视图控制器
 *
 * @author FOM
 */

@Controller
public class CommonController extends BaseController{

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }

    /**
     * 404页面
     *
     * @return
     */
    @RequestMapping("/404")
    public String error404() {
        return "error/404";
    }

    /**
     * 405页面
     *
     * @return
     */
    @RequestMapping("/405")
    public String error405() {
        return "error/405";
    }

    /**
     * 500页面
     *
     * @return
     */
    @RequestMapping("/500")
    public String error500() {
        return "error/500";
    }

    /**
     * 自动匹配	测试
     *
     * @param module
     * @param url
     * @return
     */
    @RequestMapping("modules/{module}/{url}")
    public String module(@PathVariable("module") String module, @PathVariable("url") String url) {
        return "modules/" + module + "/" + url;
    }

    /**
     * 500错误
     *
     * @param request
     * @return
     */
    @RequestMapping("/500")
    public ModelAndView _500(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("error/500");

        Throwable t = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String defaultMessage = "未知";
        if (null == t) {
            view.addObject("line", defaultMessage);
            view.addObject("clazz", defaultMessage);
            view.addObject("methodName", defaultMessage);
            return view;
        }
        String message = t.getMessage();//错误信息
        StackTraceElement[] stack = t.getStackTrace();
        view.addObject("message", message);
        if (null != stack && stack.length != 0) {
            StackTraceElement element = stack[0];
            int line = element.getLineNumber();//错误行号
            String clazz = element.getClassName();//错误java类
            String fileName = element.getFileName();

            String methodName = element.getMethodName();//错误方法
            view.addObject("line", line);
            view.addObject("clazz", clazz);
            view.addObject("methodName", methodName);
            LoggerUtils.fmtError(getClass(), "line:%s,clazz:%s,fileName:%s,methodName:%s()",
                    line, clazz, fileName, methodName);
        }
        return view;
    }

    /**
     * 获取验证码（jpg版本）
     *
     * @param response
     */
    @RequestMapping(value = "/getJPGCode", method = RequestMethod.GET)
    public void getJPGCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpg");
            /**
             * jgp格式验证码
             * 宽，高，位数。
             */
            Captcha captcha = new SpecCaptcha(146, 33, 4);
            //输出
            captcha.out(response.getOutputStream());
            HttpSession session = request.getSession(true);
            //存入Session
            session.setAttribute("_code", captcha.text().toLowerCase());
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "获取验证码异常：%s", e.getMessage());
        }
    }

    /**
     * 跳转到其他网站
     *
     * @param url
     * @return
     */
    @RequestMapping(value = "/www/open/goto", method = RequestMethod.GET)
    public ModelAndView _goto(String url) {

        return new ModelAndView("www/go_to", "url", url);
    }

    /**
     * 踢出页面
     *
     * @return
     */
    @RequestMapping(value = "/kickedOut", method = RequestMethod.GET)
    public ModelAndView kickedOut(HttpServletRequest request, UrlPathHelper pp) {
        //如果是踢出后，来源地址是：http://shiro.itboy.net/u/login.shtml;JSESSIONID=4f1538d9-df19-48c8-b4b1-aadacadde23a
        //如果来源是null，那么就重定向到首页。这个时候，如果首页是要登录，那就会跳转到登录页
        if (StringUtils.isBlank(request.getHeader("Referer"))) {
            return redirectView("/");
        }
        return new ModelAndView("common/kicked_out");
    }

    /**
     * 没有权限提示页面
     *
     * @return
     */
    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public ModelAndView unauthorized() {
        return new ModelAndView("common/unauthorized");
    }

    @RequestMapping(value = "/shiro", method = RequestMethod.GET)
    public ModelAndView shiro() {
        return new ModelAndView("common/shiro");
    }

}
