package com.particle.data.app.company.executor.representation.exwarehouse;

import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyStatisticAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyStatisticExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyStatisticExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyStatisticDO;
import com.particle.data.infrastructure.company.service.IDataCompanyStatisticService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业统计出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyStatisticExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyStatisticService iDataCompanyStatisticService;

	/**
	 * 企业统计出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyStatisticExWarehouseVO> exWarehouse(@Valid DataCompanyStatisticExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		DataCompanyStatisticDO dataCompanyStatisticDO = iDataCompanyStatisticService.getByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId());
        if (dataCompanyStatisticDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		DataCompanyStatisticExWarehouseVO dataCompanyStatisticExWarehouseVO = DataCompanyStatisticAppStructMapping.instance.dataCompanyStatisticDOToDataCompanyStatisticExWarehouseVO(dataCompanyStatisticDO);
		return SingleResponse.of(dataCompanyStatisticExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyStatisticService(IDataCompanyStatisticService iDataCompanyStatisticService) {
		this.iDataCompanyStatisticService = iDataCompanyStatisticService;
	}
}
