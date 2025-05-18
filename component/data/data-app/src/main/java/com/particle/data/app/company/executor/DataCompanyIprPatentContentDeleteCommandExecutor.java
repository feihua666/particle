package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentContentAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentContentVO;
import com.particle.data.domain.company.DataCompanyIprPatentContent;
import com.particle.data.domain.company.DataCompanyIprPatentContentId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentContentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentContentService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentContentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Component
@Validated
public class DataCompanyIprPatentContentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentContentGateway dataCompanyIprPatentContentGateway;
	private IDataCompanyIprPatentContentService iDataCompanyIprPatentContentService;

	/**
	 * 执行 企业知识产权专利内容 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentContentVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPatentContentId dataCompanyIprPatentContentId = DataCompanyIprPatentContentId.of(deleteCommand.getId());
		DataCompanyIprPatentContent byId = dataCompanyIprPatentContentGateway.getById(dataCompanyIprPatentContentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPatentContentGateway.delete(dataCompanyIprPatentContentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPatentContentAppStructMapping.instance.toDataCompanyIprPatentContentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentContentGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentContentGateway(DataCompanyIprPatentContentGateway dataCompanyIprPatentContentGateway) {
		this.dataCompanyIprPatentContentGateway = dataCompanyIprPatentContentGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentContentService(IDataCompanyIprPatentContentService iDataCompanyIprPatentContentService) {
		this.iDataCompanyIprPatentContentService = iDataCompanyIprPatentContentService;
	}
}
