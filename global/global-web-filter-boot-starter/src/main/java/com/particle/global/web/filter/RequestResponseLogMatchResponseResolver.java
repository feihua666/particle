package com.particle.global.web.filter;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 提供一个额外 match ，以包装可重复读 response
 * </p>
 *
 * @author yangwei
 * @since 2023-08-22 18:42
 */
public interface RequestResponseLogMatchResponseResolver {

	/**
	 * 匹配
	 * @param request
	 * @return
	 */
	boolean matchs(HttpServletRequest request);
}
