package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyVcProductCompetitiveProductRelAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcProductCompetitiveProductRelExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductCompetitiveProductRelExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductCompetitiveProductRelDO;
import com.particle.data.infrastructure.company.service.IDataCompanyVcProductCompetitiveProductRelService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业融资产品竞品关系出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyVcProductCompetitiveProductRelExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyVcProductCompetitiveProductRelService iDataCompanyVcProductCompetitiveProductRelService;

	/**
	 * 企业融资产品竞品关系出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductCompetitiveProductRelExWarehouseVO> exWarehouse(@Valid DataCompanyVcProductCompetitiveProductRelExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		return exWarehouseByCompanyVcProductIdAndCompanyVcCompetitiveProductId(dataCompanyExWarehouseQueryCommand.getCompanyVcProductId(), dataCompanyExWarehouseQueryCommand.getCompanyVcCompetitiveProductId());
	}
	/**
	 * 企业融资产品竞品关系出库
	 * @param companyVcCompetitiveProductId
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductCompetitiveProductRelExWarehouseVO> exWarehouseByCompanyVcProductIdAndCompanyVcCompetitiveProductId(Long companyVcProductId,Long companyVcCompetitiveProductId) {
		DataCompanyVcProductCompetitiveProductRelDO dataCompanyVcProductCompetitiveProductRelDO = iDataCompanyVcProductCompetitiveProductRelService.getByCompanyVcProductIdAndCompanyVcCompetitiveProductId(companyVcProductId,companyVcCompetitiveProductId);
		if (dataCompanyVcProductCompetitiveProductRelDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyVcProductCompetitiveProductRelExWarehouseVO dataCompanyVcProductCompetitiveProductRelExWarehouseVO = DataCompanyVcProductCompetitiveProductRelAppStructMapping.instance.dataCompanyVcProductCompetitiveProductRelDOToDataCompanyVcProductCompetitiveProductRelExWarehouseVO(dataCompanyVcProductCompetitiveProductRelDO);
		return SingleResponse.of(dataCompanyVcProductCompetitiveProductRelExWarehouseVO);
	}
	/**
	 * 企业融资产品竞品关系出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductCompetitiveProductRelExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyVcProductCompetitiveProductRelDO dataCompanyVcProductCompetitiveProductRelDO = iDataCompanyVcProductCompetitiveProductRelService.getById(id);
		if (dataCompanyVcProductCompetitiveProductRelDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyVcProductCompetitiveProductRelExWarehouseVO dataCompanyVcProductCompetitiveProductRelExWarehouseVO = DataCompanyVcProductCompetitiveProductRelAppStructMapping.instance.dataCompanyVcProductCompetitiveProductRelDOToDataCompanyVcProductCompetitiveProductRelExWarehouseVO(dataCompanyVcProductCompetitiveProductRelDO);
		return SingleResponse.of(dataCompanyVcProductCompetitiveProductRelExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyVcProductCompetitiveProductRelService(IDataCompanyVcProductCompetitiveProductRelService iDataCompanyVcProductCompetitiveProductRelService) {
		this.iDataCompanyVcProductCompetitiveProductRelService = iDataCompanyVcProductCompetitiveProductRelService;
	}
}
