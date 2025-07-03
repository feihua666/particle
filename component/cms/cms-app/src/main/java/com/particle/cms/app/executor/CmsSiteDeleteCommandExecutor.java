package com.particle.cms.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.cms.app.structmapping.CmsSiteAppStructMapping;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.domain.CmsSite;
import com.particle.cms.domain.CmsSiteId;
import com.particle.cms.domain.gateway.CmsSiteGateway;
import com.particle.cms.infrastructure.service.ICmsSiteService;
import com.particle.cms.infrastructure.dos.CmsSiteDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 站点 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Component
@Validated
public class CmsSiteDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CmsSiteGateway cmsSiteGateway;
	private ICmsSiteService iCmsSiteService;

	/**
	 * 执行 站点 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CmsSiteVO> execute(@Valid IdCommand deleteCommand) {
		CmsSiteId cmsSiteId = CmsSiteId.of(deleteCommand.getId());
		CmsSite byId = cmsSiteGateway.getById(cmsSiteId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = cmsSiteGateway.delete(cmsSiteId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CmsSiteAppStructMapping.instance.toCmsSiteVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
