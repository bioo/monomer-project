package com.practice.my.practice.commons.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.practice.my.practice.commons.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.MessageFormat;
import java.util.*;

/**
 * 基础类 ，Controller的基类
 * 
 * @author FOM
 *
 */
public class BaseController {

	


	protected int pageNo = 1;
	public static int pageSize = 10;
//	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected final static Logger logger = Logger.getLogger(BaseController.class);
	protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

	private final static String PARAM_PAGE_NO = "pageNo";

	protected String pageSizeName = "pageSize";

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		BaseController.pageSize = pageSize;
	}


	/**
	 * 往Request里带值
	 *
	 * @param request
	 * @param key
	 * @param value
	 */
	protected static void setValue2Request(HttpServletRequest request, String key, Object value) {
		request.setAttribute(key, value);
	}

	/**
	 * [获取session]
	 *
	 * @param request
	 * @return
	 */
	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}

	/**
	 * 重定向方法
	 * @param redirectUrl
	 * @param parament
	 * @return
	 */
	public ModelAndView redirectView(String redirectUrl, Map<String, Object>... parament) {
		ModelAndView view = new ModelAndView(new RedirectView(redirectUrl));
		if (null != parament && parament.length > 0) {
			view.addAllObjects(parament[0]);
		}
		return view;
	}

	/**
	 * 重定向方法
	 *
	 * @param format
	 *            url
	 * @param arguments
	 * @return
	 */
	protected String redirect(String format, Object... arguments) {
		StringBuffer r = new StringBuffer("redirect:");
		r.append(MessageFormat.format(format, arguments));
		return r.toString();
	}


	/**
	 * 获取请求头
	 * @param params
	 * @return
	 */
	private Map<String, Object> handleParams(Map<String, Object> params) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (null != params) {
			Set<Map.Entry<String, Object>> entrySet = params.entrySet();

			for (Iterator<Map.Entry<String, Object>> it = entrySet.iterator(); it.hasNext(); ) {
				Map.Entry<String, Object> entry = it.next();
				if (entry.getValue() != null) {
					result.put(entry.getKey(), StringUtils.trimToEmpty((String) entry.getValue()));
				}
			}
		}
		return result;
	}

	/**
	 * 返回JSON格式
	 * 
	 * @param object
	 *            需要转换的对象
	 * @return
	 */
	protected String toJson(Object object) {
		return JSON.toJSONString(object, SerializerFeature.BrowserSecure);
	}

	/**
	 * 
	 * @param object
	 *            需要转换的对象
	 * @param format
	 *            格式化日期
	 * @return
	 */
	protected String toJosn(Object object, String format) {
		if (format == null) {
			return toJson(object);
		}
		return JSON.toJSONStringWithDateFormat(object, format, SerializerFeature.WriteDateUseDateFormat);
	}

}
