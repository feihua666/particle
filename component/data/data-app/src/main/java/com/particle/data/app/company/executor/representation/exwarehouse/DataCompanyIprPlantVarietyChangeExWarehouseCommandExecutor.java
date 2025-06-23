package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPlantVarietyChangeAppStructMapping;
import com.particle.data.app.company.structmapping.DataCompanyIprPlantVarietyChangeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyChangeDO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyChangeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPlantVarietyChangeService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业知识产权植物新品种变更信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPlantVarietyChangeService iDataCompanyIprPlantVarietyChangeService;

	/**
	 * 企业知识产权植物新品种变更信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPlantVarietyChangeExWarehouseVO> exWarehouse(@Valid DataCompanyIprPlantVarietyChangeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprPlantVarietyChangeDO> dataCompanyIprPlantVarietyChangeDOPage = iDataCompanyIprPlantVarietyChangeService.listPageByCompanyIprPlantVarietyId(dataCompanyExWarehouseQueryCommand.getCompanyIprPlantVarietyId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyIprPlantVarietyChangeDOPage == null || dataCompanyIprPlantVarietyChangeDOPage.getRecords() == null || dataCompanyIprPlantVarietyChangeDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprPlantVarietyChangeAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprPlantVarietyChangeDOPage);
	}
	/**
	 * 企业知识产权植物新品种变更信息出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyChangeExWarehouseVO> exWarehouseByCompanyIprPlantVarietyIdAndDataMd5(Long companyIprPlantVarietyId,String dataMd5) {
		DataCompanyIprPlantVarietyChangeDO dataCompanyIprPlantVarietyChangeDO = iDataCompanyIprPlantVarietyChangeService.getByCompanyIprPlantVarietyIdAndDataMd5(companyIprPlantVarietyId,dataMd5);
		if (dataCompanyIprPlantVarietyChangeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPlantVarietyChangeExWarehouseVO dataCompanyIprPlantVarietyChangeExWarehouseVO = DataCompanyIprPlantVarietyChangeAppStructMapping.instance.dataCompanyIprPlantVarietyChangeDOToDataCompanyIprPlantVarietyChangeExWarehouseVO(dataCompanyIprPlantVarietyChangeDO);
		return SingleResponse.of(dataCompanyIprPlantVarietyChangeExWarehouseVO);
	}
	/**
	 * 企业知识产权植物新品种变更信息出库
	 * @param companyIprPlantVarietyIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprPlantVarietyChangeExWarehouseVO> exWarehouseByCompanyIprPlantVarietyIds(List<Long> companyIprPlantVarietyIds) {
		List<DataCompanyIprPlantVarietyChangeDO> dataCompanyIprPlantVarietyChangeDOList = iDataCompanyIprPlantVarietyChangeService.listByCompanyIprPlantVarietyIds(companyIprPlantVarietyIds);
		if (CollectionUtil.isEmpty(dataCompanyIprPlantVarietyChangeDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprPlantVarietyChangeExWarehouseVO> dataCompanyIprPlantVarietyChangeExWarehouseVOS = DataCompanyIprPlantVarietyChangeAppStructMapping.instance.dataCompanyIprPlantVarietyChangeDOsToDataCompanyIprPlantVarietyChangeExWarehouseVOs(dataCompanyIprPlantVarietyChangeDOList);
		return MultiResponse.of(dataCompanyIprPlantVarietyChangeExWarehouseVOS);
	}
	/**
	 * 企业知识产权植物新品种变更信息出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyChangeExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprPlantVarietyChangeDO dataCompanyIprPlantVarietyChangeDO = iDataCompanyIprPlantVarietyChangeService.getById(id);
		if (dataCompanyIprPlantVarietyChangeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPlantVarietyChangeExWarehouseVO dataCompanyIprPlantVarietyChangeExWarehouseVO = DataCompanyIprPlantVarietyChangeAppStructMapping.instance.dataCompanyIprPlantVarietyChangeDOToDataCompanyIprPlantVarietyChangeExWarehouseVO(dataCompanyIprPlantVarietyChangeDO);
		return SingleResponse.of(dataCompanyIprPlantVarietyChangeExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprPlantVarietyChangeService(IDataCompanyIprPlantVarietyChangeService iDataCompanyIprPlantVarietyChangeService) {
		this.iDataCompanyIprPlantVarietyChangeService = iDataCompanyIprPlantVarietyChangeService;
	}
}
