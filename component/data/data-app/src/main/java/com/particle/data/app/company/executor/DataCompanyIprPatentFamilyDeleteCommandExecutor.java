package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentFamilyAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentFamilyVO;
import com.particle.data.domain.company.DataCompanyIprPatentFamily;
import com.particle.data.domain.company.DataCompanyIprPatentFamilyId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentFamilyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentFamilyService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentFamilyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利同族信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:43
 */
@Component
@Validated
public class DataCompanyIprPatentFamilyDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentFamilyGateway dataCompanyIprPatentFamilyGateway;
	private IDataCompanyIprPatentFamilyService iDataCompanyIprPatentFamilyService;

	/**
	 * 执行 企业知识产权专利同族信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentFamilyVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPatentFamilyId dataCompanyIprPatentFamilyId = DataCompanyIprPatentFamilyId.of(deleteCommand.getId());
		DataCompanyIprPatentFamily byId = dataCompanyIprPatentFamilyGateway.getById(dataCompanyIprPatentFamilyId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPatentFamilyGateway.delete(dataCompanyIprPatentFamilyId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPatentFamilyAppStructMapping.instance.toDataCompanyIprPatentFamilyVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentFamilyGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentFamilyGateway(DataCompanyIprPatentFamilyGateway dataCompanyIprPatentFamilyGateway) {
		this.dataCompanyIprPatentFamilyGateway = dataCompanyIprPatentFamilyGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentFamilyService(IDataCompanyIprPatentFamilyService iDataCompanyIprPatentFamilyService) {
		this.iDataCompanyIprPatentFamilyService = iDataCompanyIprPatentFamilyService;
	}
}
