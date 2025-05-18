package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentPaymentAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPaymentVO;
import com.particle.data.domain.company.DataCompanyIprPatentPayment;
import com.particle.data.domain.company.DataCompanyIprPatentPaymentId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentPaymentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPaymentService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPaymentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利缴费信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Component
@Validated
public class DataCompanyIprPatentPaymentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentPaymentGateway dataCompanyIprPatentPaymentGateway;
	private IDataCompanyIprPatentPaymentService iDataCompanyIprPatentPaymentService;

	/**
	 * 执行 企业知识产权专利缴费信息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPaymentVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyIprPatentPaymentId dataCompanyIprPatentPaymentId = DataCompanyIprPatentPaymentId.of(deleteCommand.getId());
		DataCompanyIprPatentPayment byId = dataCompanyIprPatentPaymentGateway.getById(dataCompanyIprPatentPaymentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyIprPatentPaymentGateway.delete(dataCompanyIprPatentPaymentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyIprPatentPaymentAppStructMapping.instance.toDataCompanyIprPatentPaymentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentPaymentGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentPaymentGateway(DataCompanyIprPatentPaymentGateway dataCompanyIprPatentPaymentGateway) {
		this.dataCompanyIprPatentPaymentGateway = dataCompanyIprPatentPaymentGateway;
	}
	@Autowired
	public void setIDataCompanyIprPatentPaymentService(IDataCompanyIprPatentPaymentService iDataCompanyIprPatentPaymentService) {
		this.iDataCompanyIprPatentPaymentService = iDataCompanyIprPatentPaymentService;
	}
}
