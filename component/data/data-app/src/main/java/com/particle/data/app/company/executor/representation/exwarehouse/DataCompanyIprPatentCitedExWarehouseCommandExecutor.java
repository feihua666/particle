package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentCitedAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentCitedExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentCitedExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCitedDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentCitedService;
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
 * 企业知识产权专利被引证信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentCitedExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentCitedService iDataCompanyIprPatentCitedService;

	/**
	 * 企业知识产权专利被引证信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentCitedExWarehouseVO> exWarehouse(@Valid DataCompanyIprPatentCitedExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprPatentCitedDO> dataCompanyIprPatentCitedDOPage = null;
		dataCompanyIprPatentCitedDOPage = iDataCompanyIprPatentCitedService.listPageByCompanyIprPatentId(dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId(),
				dataCompanyExWarehouseQueryCommand);

		if (dataCompanyIprPatentCitedDOPage == null || dataCompanyIprPatentCitedDOPage.getRecords() == null || dataCompanyIprPatentCitedDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprPatentCitedAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprPatentCitedDOPage);
	}
	/**
	 * 企业知识产权专利被引证信息出库
	 * @param companyCaseFilingIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentCitedExWarehouseVO> exWarehouseByCompanyIprPatentIds(List<Long> companyCaseFilingIds) {
		List<DataCompanyIprPatentCitedDO> dataCompanyIprPatentCitedDOList = iDataCompanyIprPatentCitedService.listByCompanyIprPatentIds(companyCaseFilingIds);
		if (CollectionUtil.isEmpty(dataCompanyIprPatentCitedDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprPatentCitedExWarehouseVO> dataCompanyIprPatentCitedExWarehouseVOS = DataCompanyIprPatentCitedAppStructMapping.instance.dataCompanyIprPatentCitedDOsToDataCompanyIprPatentCitedExWarehouseVOs(dataCompanyIprPatentCitedDOList);
		return MultiResponse.of(dataCompanyIprPatentCitedExWarehouseVOS);
	}
	/**
	 * 企业知识产权专利被引证信息出库
	 * @param applyNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCitedExWarehouseVO> exWarehouseByCompanyIprPatentIdAndApplyNo(Long companyIprPatentId,String applyNo) {
		DataCompanyIprPatentCitedDO dataCompanyIprPatentCitedDO = iDataCompanyIprPatentCitedService.getByCompanyIprPatentIdAndApplyNo(companyIprPatentId,applyNo);
		if (dataCompanyIprPatentCitedDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentCitedExWarehouseVO dataCompanyIprPatentCitedExWarehouseVO = DataCompanyIprPatentCitedAppStructMapping.instance.dataCompanyIprPatentCitedDOToDataCompanyIprPatentCitedExWarehouseVO(dataCompanyIprPatentCitedDO);
		return SingleResponse.of(dataCompanyIprPatentCitedExWarehouseVO);
	}
	/**
	 * 企业知识产权专利被引证信息出库
	 * @param publicNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCitedExWarehouseVO> exWarehouseByCompanyIprPatentIdAndPublicNo(Long companyIprPatentId,String publicNo) {
		DataCompanyIprPatentCitedDO dataCompanyIprPatentCitedDO = iDataCompanyIprPatentCitedService.getByCompanyIprPatentIdAndPublicNo(companyIprPatentId,publicNo);
		if (dataCompanyIprPatentCitedDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentCitedExWarehouseVO dataCompanyIprPatentCitedExWarehouseVO = DataCompanyIprPatentCitedAppStructMapping.instance.dataCompanyIprPatentCitedDOToDataCompanyIprPatentCitedExWarehouseVO(dataCompanyIprPatentCitedDO);
		return SingleResponse.of(dataCompanyIprPatentCitedExWarehouseVO);
	}
	/**
	 * 企业知识产权专利被引证信息出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCitedExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprPatentCitedDO dataCompanyIprPatentCitedDO = iDataCompanyIprPatentCitedService.getById(id);
		if (dataCompanyIprPatentCitedDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentCitedExWarehouseVO dataCompanyIprPatentCitedExWarehouseVO = DataCompanyIprPatentCitedAppStructMapping.instance.dataCompanyIprPatentCitedDOToDataCompanyIprPatentCitedExWarehouseVO(dataCompanyIprPatentCitedDO);
		return SingleResponse.of(dataCompanyIprPatentCitedExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprPatentCitedService(IDataCompanyIprPatentCitedService iDataCompanyIprPatentCitedService) {
		this.iDataCompanyIprPatentCitedService = iDataCompanyIprPatentCitedService;
	}
}
