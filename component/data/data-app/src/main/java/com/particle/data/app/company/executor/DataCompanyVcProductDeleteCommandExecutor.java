package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.company.structmapping.DataCompanyVcProductAppStructMapping;
import com.particle.data.client.company.dto.data.DataCompanyVcProductVO;
import com.particle.data.domain.company.DataCompanyVcProduct;
import com.particle.data.domain.company.DataCompanyVcProductId;
import com.particle.data.domain.company.gateway.DataCompanyVcProductGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyVcProductService;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 企业融资产品 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Component
@Validated
public class DataCompanyVcProductDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcProductGateway dataCompanyVcProductGateway;
	private IDataCompanyVcProductService iDataCompanyVcProductService;

	/**
	 * 执行 企业融资产品 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductVO> execute(@Valid IdCommand deleteCommand) {
		DataCompanyVcProductId dataCompanyVcProductId = DataCompanyVcProductId.of(deleteCommand.getId());
		DataCompanyVcProduct byId = dataCompanyVcProductGateway.getById(dataCompanyVcProductId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dataCompanyVcProductGateway.delete(dataCompanyVcProductId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DataCompanyVcProductAppStructMapping.instance.toDataCompanyVcProductVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dataCompanyVcProductGateway
	 */
	@Autowired
	public void setDataCompanyVcProductGateway(DataCompanyVcProductGateway dataCompanyVcProductGateway) {
		this.dataCompanyVcProductGateway = dataCompanyVcProductGateway;
	}
	@Autowired
	public void setIDataCompanyVcProductService(IDataCompanyVcProductService iDataCompanyVcProductService) {
		this.iDataCompanyVcProductService = iDataCompanyVcProductService;
	}
}
