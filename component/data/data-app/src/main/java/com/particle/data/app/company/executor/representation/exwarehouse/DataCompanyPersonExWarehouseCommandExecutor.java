package com.particle.data.app.company.executor.representation.exwarehouse;

import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyPersonAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPersonExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPersonExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyPersonDO;
import com.particle.data.infrastructure.company.service.IDataCompanyPersonService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业个人出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyPersonExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyPersonService iDataCompanyPersonService;

	/**
	 * 企业个人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPersonExWarehouseVO> exWarehouse(@Valid DataCompanyPersonExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		DataCompanyPersonDO dataCompanyPersonDO = null;
        if (dataCompanyExWarehouseQueryCommand.getId() != null) {
			dataCompanyPersonDO = iDataCompanyPersonService.getById(dataCompanyExWarehouseQueryCommand.getId());
        }else if (dataCompanyExWarehouseQueryCommand.getIdNo() != null) {
			dataCompanyPersonDO = iDataCompanyPersonService.getByIdNo(dataCompanyExWarehouseQueryCommand.getIdNo());
		}else if (dataCompanyExWarehouseQueryCommand.getIdNoMd5() != null) {
			dataCompanyPersonDO = iDataCompanyPersonService.getByIdNoMd5(dataCompanyExWarehouseQueryCommand.getIdNoMd5());
		}else if (dataCompanyExWarehouseQueryCommand.getIdNoSha256() != null) {
			dataCompanyPersonDO = iDataCompanyPersonService.getByIdNoSha256(dataCompanyExWarehouseQueryCommand.getIdNoSha256());
		}else if (dataCompanyExWarehouseQueryCommand.getIdNoSm3() != null) {
			dataCompanyPersonDO = iDataCompanyPersonService.getByIdNoSm3(dataCompanyExWarehouseQueryCommand.getIdNoSm3());
		}

        if (dataCompanyPersonDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		DataCompanyPersonExWarehouseVO dataCompanyPersonExWarehouseVO = DataCompanyPersonAppStructMapping.instance.dataCompanyPersonDOToDataCompanyPersonExWarehouseVO(dataCompanyPersonDO);
		return SingleResponse.of(dataCompanyPersonExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyPersonService(IDataCompanyPersonService iDataCompanyPersonService) {
		this.iDataCompanyPersonService = iDataCompanyPersonService;
	}
}
