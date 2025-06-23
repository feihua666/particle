package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanySpotCheckAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanySpotCheckExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySpotCheckExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanySpotCheckDO;
import com.particle.data.infrastructure.company.service.IDataCompanySpotCheckService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业抽查检查出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanySpotCheckExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanySpotCheckService iDataCompanySpotCheckService;

	/**
	 * 企业抽查检查出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanySpotCheckExWarehouseVO> exWarehouse(@Valid DataCompanySpotCheckExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanySpotCheckDO> dataCompanySpotCheckDOPage = iDataCompanySpotCheckService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanySpotCheckDOPage == null || dataCompanySpotCheckDOPage.getRecords() == null || dataCompanySpotCheckDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanySpotCheckAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanySpotCheckDOPage);
	}
	/**
	 * 企业抽查检查出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanySpotCheckExWarehouseVO> exWarehouseByCompanyIdAndDataMd5(Long companyId,String dataMd5) {
		DataCompanySpotCheckDO dataCompanySpotCheckDO = iDataCompanySpotCheckService.getByCompanyIdAndDataMd5(companyId,dataMd5);
		if (dataCompanySpotCheckDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanySpotCheckExWarehouseVO dataCompanySpotCheckExWarehouseVO = DataCompanySpotCheckAppStructMapping.instance.dataCompanySpotCheckDOToDataCompanySpotCheckExWarehouseVO(dataCompanySpotCheckDO);
		return SingleResponse.of(dataCompanySpotCheckExWarehouseVO);
	}
	/**
	 * 企业抽查检查出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanySpotCheckExWarehouseVO> exWarehouseById(Long id) {
		DataCompanySpotCheckDO dataCompanySpotCheckDO = iDataCompanySpotCheckService.getById(id);
		if (dataCompanySpotCheckDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanySpotCheckExWarehouseVO dataCompanySpotCheckExWarehouseVO = DataCompanySpotCheckAppStructMapping.instance.dataCompanySpotCheckDOToDataCompanySpotCheckExWarehouseVO(dataCompanySpotCheckDO);
		return SingleResponse.of(dataCompanySpotCheckExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanySpotCheckService(IDataCompanySpotCheckService iDataCompanySpotCheckService) {
		this.iDataCompanySpotCheckService = iDataCompanySpotCheckService;
	}
}
