package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyPunishmentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPunishmentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPunishmentExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyPunishmentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyPunishmentService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业行政处罚出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyPunishmentExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyPunishmentService iDataCompanyPunishmentService;

	/**
	 * 企业行政处罚出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyPunishmentExWarehouseVO> exWarehouse(@Valid DataCompanyPunishmentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyPunishmentDO> dataCompanyPunishmentDOPage = iDataCompanyPunishmentService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyPunishmentDOPage == null || dataCompanyPunishmentDOPage.getRecords() == null || dataCompanyPunishmentDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyPunishmentAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyPunishmentDOPage);
	}
	/**
	 * 企业行政处罚出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyPunishmentExWarehouseVO> exWarehouseByCompanyIdAndDataMd5(Long companyId,String dataMd5) {
		DataCompanyPunishmentDO dataCompanyPunishmentDO = iDataCompanyPunishmentService.getByCompanyIdAndDataMd5(companyId,dataMd5);
		if (dataCompanyPunishmentDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyPunishmentExWarehouseVO dataCompanyPunishmentExWarehouseVO = DataCompanyPunishmentAppStructMapping.instance.dataCompanyPunishmentDOToDataCompanyPunishmentExWarehouseVO(dataCompanyPunishmentDO);
		return SingleResponse.of(dataCompanyPunishmentExWarehouseVO);
	}
	/**
	 * 企业行政处罚出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyPunishmentExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyPunishmentDO dataCompanyPunishmentDO = iDataCompanyPunishmentService.getById(id);
		if (dataCompanyPunishmentDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyPunishmentExWarehouseVO dataCompanyPunishmentExWarehouseVO = DataCompanyPunishmentAppStructMapping.instance.dataCompanyPunishmentDOToDataCompanyPunishmentExWarehouseVO(dataCompanyPunishmentDO);
		return SingleResponse.of(dataCompanyPunishmentExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyPunishmentService(IDataCompanyPunishmentService iDataCompanyPunishmentService) {
		this.iDataCompanyPunishmentService = iDataCompanyPunishmentService;
	}
}
