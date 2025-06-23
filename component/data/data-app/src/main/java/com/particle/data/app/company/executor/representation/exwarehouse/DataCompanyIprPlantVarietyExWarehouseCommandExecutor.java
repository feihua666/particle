package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPlantVarietyAppStructMapping;
import com.particle.data.app.company.structmapping.DataCompanyIprPlantVarietyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPlantVarietyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyDO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPlantVarietyService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权植物新品种出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPlantVarietyService iDataCompanyIprPlantVarietyService;

	/**
	 * 企业知识产权植物新品种出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPlantVarietyExWarehouseVO> exWarehouse(@Valid DataCompanyIprPlantVarietyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprPlantVarietyDO> dataCompanyIprPlantVarietyDOPage = iDataCompanyIprPlantVarietyService.listPageByApplicantNameCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
				dataCompanyExWarehouseQueryCommand.getPublicNo(),
				dataCompanyExWarehouseQueryCommand.getApplyNo(),
				dataCompanyExWarehouseQueryCommand);
		if (dataCompanyIprPlantVarietyDOPage == null || dataCompanyIprPlantVarietyDOPage.getRecords() == null || dataCompanyIprPlantVarietyDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprPlantVarietyAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprPlantVarietyDOPage);
	}
	/**
	 * 企业知识产权植物新品种出库
	 * @param publicNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyExWarehouseVO> exWarehouseByPublicNo(String publicNo) {
		DataCompanyIprPlantVarietyDO dataCompanyIprPlantVarietyDO = iDataCompanyIprPlantVarietyService.getByPublicNo(publicNo);
		if (dataCompanyIprPlantVarietyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPlantVarietyExWarehouseVO dataCompanyIprPlantVarietyExWarehouseVO = DataCompanyIprPlantVarietyAppStructMapping.instance.dataCompanyIprPlantVarietyDOToDataCompanyIprPlantVarietyExWarehouseVO(dataCompanyIprPlantVarietyDO);
		return SingleResponse.of(dataCompanyIprPlantVarietyExWarehouseVO);
	}
	/**
	 * 企业知识产权植物新品种出库
	 * @param applyNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyExWarehouseVO> exWarehouseByApplyNo(String applyNo) {
		DataCompanyIprPlantVarietyDO dataCompanyIprPlantVarietyDO = iDataCompanyIprPlantVarietyService.getByApplyNo(applyNo);
		if (dataCompanyIprPlantVarietyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPlantVarietyExWarehouseVO dataCompanyIprPlantVarietyExWarehouseVO = DataCompanyIprPlantVarietyAppStructMapping.instance.dataCompanyIprPlantVarietyDOToDataCompanyIprPlantVarietyExWarehouseVO(dataCompanyIprPlantVarietyDO);
		return SingleResponse.of(dataCompanyIprPlantVarietyExWarehouseVO);
	}
	/**
	 * 企业知识产权植物新品种出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprPlantVarietyDO dataCompanyIprPlantVarietyDO = iDataCompanyIprPlantVarietyService.getById(id);
		if (dataCompanyIprPlantVarietyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPlantVarietyExWarehouseVO dataCompanyIprPlantVarietyExWarehouseVO = DataCompanyIprPlantVarietyAppStructMapping.instance.dataCompanyIprPlantVarietyDOToDataCompanyIprPlantVarietyExWarehouseVO(dataCompanyIprPlantVarietyDO);
		return SingleResponse.of(dataCompanyIprPlantVarietyExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprPlantVarietyService(IDataCompanyIprPlantVarietyService iDataCompanyIprPlantVarietyService) {
		this.iDataCompanyIprPlantVarietyService = iDataCompanyIprPlantVarietyService;
	}
}
