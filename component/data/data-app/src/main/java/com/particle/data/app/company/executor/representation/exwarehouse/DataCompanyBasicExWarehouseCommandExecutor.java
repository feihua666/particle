package com.particle.data.app.company.executor.representation.exwarehouse;

import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyBasicAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyBasicExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyBasicExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyBasicDO;
import com.particle.data.infrastructure.company.service.IDataCompanyBasicService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业基本信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyBasicExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyBasicService iDataCompanyBasicService;

	/**
	 * 企业基本信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyBasicExWarehouseVO> exWarehouse(@Valid DataCompanyBasicExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		DataCompanyBasicDO dataCompanyBasicDO = iDataCompanyBasicService.getByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId());
        if (dataCompanyBasicDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		DataCompanyBasicExWarehouseVO dataCompanyBasicExWarehouseVO = DataCompanyBasicAppStructMapping.instance.dataCompanyBasicDOToDataCompanyBasicExWarehouseVO(dataCompanyBasicDO);
		return SingleResponse.of(dataCompanyBasicExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyBasicService(IDataCompanyBasicService iDataCompanyBasicService) {
		this.iDataCompanyBasicService = iDataCompanyBasicService;
	}
}
