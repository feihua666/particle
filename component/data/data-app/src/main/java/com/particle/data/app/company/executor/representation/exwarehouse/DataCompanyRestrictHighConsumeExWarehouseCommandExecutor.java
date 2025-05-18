package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyRestrictHighConsumeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyRestrictHighConsumeService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业限制高消费出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumeExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyRestrictHighConsumeService iDataCompanyRestrictHighConsumeService;

	/**
	 * 企业限制高消费出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyRestrictHighConsumeExWarehouseVO> exWarehouse(@Valid DataCompanyRestrictHighConsumeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyRestrictHighConsumeDO> dataCompanyRestrictHighConsumeDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyRestrictHighConsumeDOPage = iDataCompanyRestrictHighConsumeService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getCaseNo(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyRestrictHighConsumeDOPage == null || dataCompanyRestrictHighConsumeDOPage.getRecords() == null || dataCompanyRestrictHighConsumeDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyRestrictHighConsumeAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyRestrictHighConsumeDOPage);
	}
	/**
	 * 企业限制高消费出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumeExWarehouseVO> exWarehouseByDataMd5(String dataMd5) {
		DataCompanyRestrictHighConsumeDO dataCompanyRestrictHighConsumeDO = iDataCompanyRestrictHighConsumeService.getByDataMd5(dataMd5);
		if (dataCompanyRestrictHighConsumeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyRestrictHighConsumeExWarehouseVO dataCompanyRestrictHighConsumeExWarehouseVO = DataCompanyRestrictHighConsumeAppStructMapping.instance.dataCompanyRestrictHighConsumeDOToDataCompanyRestrictHighConsumeExWarehouseVO(dataCompanyRestrictHighConsumeDO);
		return SingleResponse.of(dataCompanyRestrictHighConsumeExWarehouseVO);
	}
	/**
	 * 企业限制高消费出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumeExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyRestrictHighConsumeDO dataCompanyRestrictHighConsumeDO = iDataCompanyRestrictHighConsumeService.getById(id);
		if (dataCompanyRestrictHighConsumeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyRestrictHighConsumeExWarehouseVO dataCompanyRestrictHighConsumeExWarehouseVO = DataCompanyRestrictHighConsumeAppStructMapping.instance.dataCompanyRestrictHighConsumeDOToDataCompanyRestrictHighConsumeExWarehouseVO(dataCompanyRestrictHighConsumeDO);
		return SingleResponse.of(dataCompanyRestrictHighConsumeExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyRestrictHighConsumeService(IDataCompanyRestrictHighConsumeService iDataCompanyRestrictHighConsumeService) {
		this.iDataCompanyRestrictHighConsumeService = iDataCompanyRestrictHighConsumeService;
	}
}
