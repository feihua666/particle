package com.particle.global.openapi.filter;

import com.particle.global.openapi.api.OpenApi;
import com.particle.global.openapi.api.OpenapiHelper;
import com.particle.global.web.filter.RequestResponseLogMatchResponseResolver;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 开放接口需要包装response
 * </p>
 *
 * @author yangwei
 * @since 2023-08-22 18:46
 */
public class OpenapiRequestResponseLogMatchResponseResolver implements RequestResponseLogMatchResponseResolver {

	@Autowired
	private List<OpenApi> openApiList;

	@Override
	public boolean matchs(HttpServletRequest request) {
		for (OpenApi openApi : openApiList) {
			if (openApi.support(request)) {
				return true;
			}
		}
		return false;
	}
}
