package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyHonorQualificationAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyHonorQualificationVO;
import com.particle.data.domain.company.DataCompanyHonorQualification;
import com.particle.data.domain.company.DataCompanyHonorQualificationId;
import com.particle.data.domain.company.gateway.DataCompanyHonorQualificationGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyHonorQualificationService;
import com.particle.data.infrastructure.company.dos.DataCompanyHonorQualificationDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业荣誉资质 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Component
@Validated
public class DataCompanyHonorQualificationDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyHonorQualificationGateway dataCompanyHonorQualificationGateway;
	private IDataCompanyHonorQualificationService iDataCompanyHonorQualificationService;

	/**
	 * 执行 企业荣誉资质 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyHonorQualificationVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyHonorQualificationId dataCompanyHonorQualificationId = DataCompanyHonorQualificationId.of(deleteCommand.getId());
		DataCompanyHonorQualification byId = dataCompanyHonorQualificationGateway.getById(dataCompanyHonorQualificationId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyHonorQualificationGateway.delete(dataCompanyHonorQualificationId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyHonorQualificationAppStructMapping.instance.toDataCompanyHonorQualificationVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyHonorQualificationGateway
	 */
	@Autowired
	public void setDataCompanyHonorQualificationGateway(DataCompanyHonorQualificationGateway dataCompanyHonorQualificationGateway) {
		this.dataCompanyHonorQualificationGateway = dataCompanyHonorQualificationGateway;
	}
	@Autowired
	public void setIDataCompanyHonorQualificationService(IDataCompanyHonorQualificationService iDataCompanyHonorQualificationService) {
		this.iDataCompanyHonorQualificationService = iDataCompanyHonorQualificationService;
	}
}
