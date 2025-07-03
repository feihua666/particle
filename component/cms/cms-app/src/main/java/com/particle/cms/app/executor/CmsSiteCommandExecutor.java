package com.particle.cms.app.executor;

import com.particle.cms.domain.gateway.CmsSiteGateway;
import com.particle.cms.infrastructure.service.ICmsSiteService;
import com.particle.cms.infrastructure.dos.CmsSiteDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 站点 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Component
@Validated
public class CmsSiteCommandExecutor  extends AbstractBaseExecutor {

	private CmsSiteGateway cmsSiteGateway;
	private ICmsSiteService iCmsSiteService;
	/**
	 * 注入使用set方法
	 * @param cmsSiteGateway
	 */
	@Autowired
	public void setCmsSiteGateway(CmsSiteGateway cmsSiteGateway) {
		this.cmsSiteGateway = cmsSiteGateway;
	}
	@Autowired
	public void setICmsSiteService(ICmsSiteService iCmsSiteService) {
		this.iCmsSiteService = iCmsSiteService;
	}
}
