package com.particle.cms.app.executor;

import com.particle.cms.domain.gateway.CmsSiteIndexViewRecordGateway;
import com.particle.cms.infrastructure.service.ICmsSiteIndexViewRecordService;
import com.particle.cms.infrastructure.dos.CmsSiteIndexViewRecordDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 站点首页访问记录 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Component
@Validated
public class CmsSiteIndexViewRecordCommandExecutor  extends AbstractBaseExecutor {

	private CmsSiteIndexViewRecordGateway cmsSiteIndexViewRecordGateway;
	private ICmsSiteIndexViewRecordService iCmsSiteIndexViewRecordService;
	/**
	 * 注入使用set方法
	 * @param cmsSiteIndexViewRecordGateway
	 */
	@Autowired
	public void setCmsSiteIndexViewRecordGateway(CmsSiteIndexViewRecordGateway cmsSiteIndexViewRecordGateway) {
		this.cmsSiteIndexViewRecordGateway = cmsSiteIndexViewRecordGateway;
	}
	@Autowired
	public void setICmsSiteIndexViewRecordService(ICmsSiteIndexViewRecordService iCmsSiteIndexViewRecordService) {
		this.iCmsSiteIndexViewRecordService = iCmsSiteIndexViewRecordService;
	}
}
