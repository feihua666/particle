package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyAbnormalAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAbnormalExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAbnormalExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAbnormalDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAbnormalService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业经营异常出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyAbnormalExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyAbnormalService iDataCompanyAbnormalService;

	/**
	 * 企业经营异常出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAbnormalExWarehouseVO> exWarehouse(@Valid DataCompanyAbnormalExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyAbnormalDO> dataCompanyAbnormalDOPage = iDataCompanyAbnormalService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyAbnormalDOPage == null || dataCompanyAbnormalDOPage.getRecords() == null || dataCompanyAbnormalDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyAbnormalAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyAbnormalDOPage);
	}
	/**
	 * 企业经营异常出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyAbnormalExWarehouseVO> exWarehouseByCompanyIdAndDataMd5(Long companyId,String dataMd5) {
		DataCompanyAbnormalDO dataCompanyAbnormalDO = iDataCompanyAbnormalService.getByCompanyIdAndDataMd5(companyId,dataMd5);
		if (dataCompanyAbnormalDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAbnormalExWarehouseVO dataCompanyAbnormalExWarehouseVO = DataCompanyAbnormalAppStructMapping.instance.dataCompanyAbnormalDOToDataCompanyAbnormalExWarehouseVO(dataCompanyAbnormalDO);
		return SingleResponse.of(dataCompanyAbnormalExWarehouseVO);
	}
	/**
	 * 企业经营异常出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyAbnormalExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyAbnormalDO dataCompanyAbnormalDO = iDataCompanyAbnormalService.getById(id);
		if (dataCompanyAbnormalDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAbnormalExWarehouseVO dataCompanyAbnormalExWarehouseVO = DataCompanyAbnormalAppStructMapping.instance.dataCompanyAbnormalDOToDataCompanyAbnormalExWarehouseVO(dataCompanyAbnormalDO);
		return SingleResponse.of(dataCompanyAbnormalExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyAbnormalService(IDataCompanyAbnormalService iDataCompanyAbnormalService) {
		this.iDataCompanyAbnormalService = iDataCompanyAbnormalService;
	}
}
