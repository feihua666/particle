package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyEquityPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyEquityPledgeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEquityPledgeExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyEquityPledgeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyEquityPledgeService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业股权出质出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyEquityPledgeExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyEquityPledgeService iDataCompanyEquityPledgeService;

	/**
	 * 企业股权出质出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyEquityPledgeExWarehouseVO> exWarehouse(@Valid DataCompanyEquityPledgeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyEquityPledgeDO> dataCompanyEquityPledgeDOPage = iDataCompanyEquityPledgeService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyEquityPledgeDOPage == null || dataCompanyEquityPledgeDOPage.getRecords() == null || dataCompanyEquityPledgeDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyEquityPledgeAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyEquityPledgeDOPage);
	}
	/**
	 * 企业股权出质出库
	 * @param regNo
	 * @return
	 */
	public SingleResponse<DataCompanyEquityPledgeExWarehouseVO> exWarehouseByCompanyIdAndRegNo(Long companyId,String regNo) {
		DataCompanyEquityPledgeDO dataCompanyEquityPledgeDO = iDataCompanyEquityPledgeService.getByCompanyIdAndRegNo(companyId,regNo);
		if (dataCompanyEquityPledgeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyEquityPledgeExWarehouseVO dataCompanyEquityPledgeExWarehouseVO = DataCompanyEquityPledgeAppStructMapping.instance.dataCompanyEquityPledgeDOToDataCompanyEquityPledgeExWarehouseVO(dataCompanyEquityPledgeDO);
		return SingleResponse.of(dataCompanyEquityPledgeExWarehouseVO);
	}
	/**
	 * 企业股权出质出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyEquityPledgeExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyEquityPledgeDO dataCompanyEquityPledgeDO = iDataCompanyEquityPledgeService.getById(id);
		if (dataCompanyEquityPledgeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyEquityPledgeExWarehouseVO dataCompanyEquityPledgeExWarehouseVO = DataCompanyEquityPledgeAppStructMapping.instance.dataCompanyEquityPledgeDOToDataCompanyEquityPledgeExWarehouseVO(dataCompanyEquityPledgeDO);
		return SingleResponse.of(dataCompanyEquityPledgeExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyEquityPledgeService(IDataCompanyEquityPledgeService iDataCompanyEquityPledgeService) {
		this.iDataCompanyEquityPledgeService = iDataCompanyEquityPledgeService;
	}
}
