package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprIntegratedCircuitAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprIntegratedCircuitDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprIntegratedCircuitService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权集成电路出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprIntegratedCircuitExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprIntegratedCircuitService iDataCompanyIprIntegratedCircuitService;

	/**
	 * 企业知识产权集成电路出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> exWarehouse(@Valid DataCompanyIprIntegratedCircuitExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprIntegratedCircuitDO> dataCompanyIprIntegratedCircuitDOPage = iDataCompanyIprIntegratedCircuitService.listPageByRightHolderCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),dataCompanyExWarehouseQueryCommand.getPublicNo(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyIprIntegratedCircuitDOPage == null || dataCompanyIprIntegratedCircuitDOPage.getRecords() == null || dataCompanyIprIntegratedCircuitDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprIntegratedCircuitAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprIntegratedCircuitDOPage);
	}
	/**
	 * 企业知识产权集成电路出库
	 * @param publicNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> exWarehouseByPublicNo(String publicNo) {
		DataCompanyIprIntegratedCircuitDO dataCompanyIprIntegratedCircuitDO = iDataCompanyIprIntegratedCircuitService.getByPublicNo(publicNo);
		if (dataCompanyIprIntegratedCircuitDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprIntegratedCircuitExWarehouseVO dataCompanyIprIntegratedCircuitExWarehouseVO = DataCompanyIprIntegratedCircuitAppStructMapping.instance.dataCompanyIprIntegratedCircuitDOToDataCompanyIprIntegratedCircuitExWarehouseVO(dataCompanyIprIntegratedCircuitDO);
		return SingleResponse.of(dataCompanyIprIntegratedCircuitExWarehouseVO);
	}
	/**
	 * 企业知识产权集成电路出库
	 * @param publicNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> exWarehouseByCompanyIdAndPublicNo(Long companyId,String publicNo) {
		DataCompanyIprIntegratedCircuitDO dataCompanyIprIntegratedCircuitDO = iDataCompanyIprIntegratedCircuitService.getByRightHolderCompanyIdAndPublicNo(companyId,publicNo);
		if (dataCompanyIprIntegratedCircuitDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprIntegratedCircuitExWarehouseVO dataCompanyIprIntegratedCircuitExWarehouseVO = DataCompanyIprIntegratedCircuitAppStructMapping.instance.dataCompanyIprIntegratedCircuitDOToDataCompanyIprIntegratedCircuitExWarehouseVO(dataCompanyIprIntegratedCircuitDO);
		return SingleResponse.of(dataCompanyIprIntegratedCircuitExWarehouseVO);
	}
	/**
	 * 企业知识产权集成电路出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprIntegratedCircuitDO dataCompanyIprIntegratedCircuitDO = iDataCompanyIprIntegratedCircuitService.getById(id);
		if (dataCompanyIprIntegratedCircuitDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprIntegratedCircuitExWarehouseVO dataCompanyIprIntegratedCircuitExWarehouseVO = DataCompanyIprIntegratedCircuitAppStructMapping.instance.dataCompanyIprIntegratedCircuitDOToDataCompanyIprIntegratedCircuitExWarehouseVO(dataCompanyIprIntegratedCircuitDO);
		return SingleResponse.of(dataCompanyIprIntegratedCircuitExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprIntegratedCircuitService(IDataCompanyIprIntegratedCircuitService iDataCompanyIprIntegratedCircuitService) {
		this.iDataCompanyIprIntegratedCircuitService = iDataCompanyIprIntegratedCircuitService;
	}
}
