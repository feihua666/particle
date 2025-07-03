package com.particle.cms.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.cms.app.structmapping.CmsSiteIndexViewRecordAppStructMapping;
import com.particle.cms.client.dto.data.CmsSiteIndexViewRecordVO;
import com.particle.cms.domain.CmsSiteIndexViewRecord;
import com.particle.cms.domain.CmsSiteIndexViewRecordId;
import com.particle.cms.domain.gateway.CmsSiteIndexViewRecordGateway;
import com.particle.cms.infrastructure.service.ICmsSiteIndexViewRecordService;
import com.particle.cms.infrastructure.dos.CmsSiteIndexViewRecordDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 站点首页访问记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Component
@Validated
public class CmsSiteIndexViewRecordDeleteCommandExecutor  extends AbstractBaseExecutor {

	private CmsSiteIndexViewRecordGateway cmsSiteIndexViewRecordGateway;
	private ICmsSiteIndexViewRecordService iCmsSiteIndexViewRecordService;

	/**
	 * 执行 站点首页访问记录 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<CmsSiteIndexViewRecordVO> execute(@Valid IdCommand deleteCommand) {
		CmsSiteIndexViewRecordId cmsSiteIndexViewRecordId = CmsSiteIndexViewRecordId.of(deleteCommand.getId());
		CmsSiteIndexViewRecord byId = cmsSiteIndexViewRecordGateway.getById(cmsSiteIndexViewRecordId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = cmsSiteIndexViewRecordGateway.delete(cmsSiteIndexViewRecordId,deleteCommand);
		if (delete) {
			return SingleResponse.of(CmsSiteIndexViewRecordAppStructMapping.instance.toCmsSiteIndexViewRecordVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
