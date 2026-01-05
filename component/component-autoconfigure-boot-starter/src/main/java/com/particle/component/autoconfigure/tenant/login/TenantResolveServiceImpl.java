package com.particle.component.autoconfigure.tenant.login;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.tenant.GrantedTenant;
import com.particle.global.security.tenant.ITenantResolveService;
import com.particle.global.tool.servlet.RequestTool;
import com.particle.tenant.adapter.feign.client.rpc.TenantRpcFeignClient;
import com.particle.tenant.client.dto.command.representation.TenantQueryAllCommand;
import com.particle.tenant.client.dto.data.TenantVO;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 租户处理实现类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-14 17:41
 */
public class TenantResolveServiceImpl implements ITenantResolveService {

	private static String header_c_host = "c-host";

	@Autowired
	private TenantRpcFeignClient tenantRpcFeignClient;

	@Override
	public GrantedTenant resolveGrantedTenant(ServletRequest request,boolean useLoginUser) {
		LoginUser loginUser = LoginUserTool.getLoginUser();
		if (loginUser != null && useLoginUser) {
			return loginUser.getCurrentTenant();
		}
		String domainWithPort = JakartaServletUtil.getHeaderIgnoreCase(((HttpServletRequest) request),header_c_host);
		if (Strings.isEmpty(domainWithPort)) {
			domainWithPort = RequestTool.getDomain(((HttpServletRequest) request), false, true);
		}


		return resolveGrantedTenant(domainWithPort);
	}

	@Override
	public GrantedTenant resolveGrantedTenant(String domainWithPort) {

		String domainOnly = domainWithPort.split(":")[0];
        List<TenantVO> tenantVOS = getAllSimpleIgnoreTenantLimit();

        for (TenantVO tenantVO : tenantVOS) {
            String tenantDomain = tenantVO.getTenantDomain();
            if (Strings.isEmpty(tenantDomain)) {
                continue;
            }
            for (String domain : tenantDomain.split(",")) {
                if (StrUtil.equalsAny(domain,domainOnly,domainWithPort)) {
                    return GrantedTenant.create(tenantVO.getId(),
                            tenantVO.getCode(),
                            tenantVO.getName(),
                            tenantVO.getTenantThemeJson(),
                            tenantVO.getTenantDefaultRouteJson(),
                            tenantVO.getTenantLogoJson(),
                            tenantVO.getConfigJson(),
                            tenantVO.getIsFormal()
                    );

                }
            }
        }
        return null;
    }

    /**
     * 获取所有租户
     * @return
     */
	private List<TenantVO> getAllSimpleIgnoreTenantLimit() {
        MultiResponse<TenantVO> allTenant = tenantRpcFeignClient.getAllTenant(TenantQueryAllCommand.createEmpty());
        return allTenant.getData();
    }
}
