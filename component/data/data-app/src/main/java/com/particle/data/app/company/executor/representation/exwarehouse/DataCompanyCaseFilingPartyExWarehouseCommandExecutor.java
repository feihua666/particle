package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyCaseFilingPartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCaseFilingPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingPartyExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingPartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyCaseFilingPartyService;
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
 * 企业立案信息当事人出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyCaseFilingPartyExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyCaseFilingPartyService iDataCompanyCaseFilingPartyService;

	/**
	 * 企业立案信息当事人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyCaseFilingPartyExWarehouseVO> exWarehouse(@Valid DataCompanyCaseFilingPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyCaseFilingPartyDO> dataCompanyCaseFilingPartyDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyCaseFilingPartyDOPage = iDataCompanyCaseFilingPartyService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyCaseFilingPartyDOPage == null || dataCompanyCaseFilingPartyDOPage.getRecords() == null || dataCompanyCaseFilingPartyDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyCaseFilingPartyAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyCaseFilingPartyDOPage);
	}
	/**
	 * 企业立案信息当事人出库
	 * @param companyCaseFilingId
	 * @return
	 */
	public MultiResponse<DataCompanyCaseFilingPartyExWarehouseVO> exWarehouseByCompanyCaseFilingId(Long companyCaseFilingId) {
		List<DataCompanyCaseFilingPartyDO> dataCompanyCaseFilingPartyDOList = iDataCompanyCaseFilingPartyService.getByCompanyCaseFilingId(companyCaseFilingId);
        if (CollectionUtil.isEmpty(dataCompanyCaseFilingPartyDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		List<DataCompanyCaseFilingPartyExWarehouseVO> dataCompanyCaseFilingPartyExWarehouseVOS = DataCompanyCaseFilingPartyAppStructMapping.instance.dataCompanyCaseFilingPartyDOsToDataCompanyCaseFilingPartyExWarehouseVOs(dataCompanyCaseFilingPartyDOList);
		return MultiResponse.of(dataCompanyCaseFilingPartyExWarehouseVOS);
	}
	/**
	 * 企业立案信息当事人出库
	 * @param companyCaseFilingIds
	 * @return
	 */
	public MultiResponse<DataCompanyCaseFilingPartyExWarehouseVO> exWarehouseByCompanyCaseFilingIds(List<Long> companyCaseFilingIds) {
		List<DataCompanyCaseFilingPartyDO> dataCompanyCaseFilingPartyDOList = iDataCompanyCaseFilingPartyService.listByCompanyCaseFilingIds(companyCaseFilingIds);
		if (CollectionUtil.isEmpty(dataCompanyCaseFilingPartyDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyCaseFilingPartyExWarehouseVO> dataCompanyCaseFilingPartyExWarehouseVOS = DataCompanyCaseFilingPartyAppStructMapping.instance.dataCompanyCaseFilingPartyDOsToDataCompanyCaseFilingPartyExWarehouseVOs(dataCompanyCaseFilingPartyDOList);
		return MultiResponse.of(dataCompanyCaseFilingPartyExWarehouseVOS);
	}
	/**
	 * 企业立案信息当事人出库
	 * @param companyCaseFilingId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingPartyExWarehouseVO> exWarehouseByCompanyCaseFilingIdAndDataMd5(Long companyCaseFilingId,String dataMd5) {
		DataCompanyCaseFilingPartyDO dataCompanyCaseFilingPartyDO = iDataCompanyCaseFilingPartyService.getByCompanyCaseFilingIdAndDataMd5(companyCaseFilingId,dataMd5);
		if (dataCompanyCaseFilingPartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyCaseFilingPartyExWarehouseVO dataCompanyCaseFilingPartyExWarehouseVO = DataCompanyCaseFilingPartyAppStructMapping.instance.dataCompanyCaseFilingPartyDOToDataCompanyCaseFilingPartyExWarehouseVO(dataCompanyCaseFilingPartyDO);
		return SingleResponse.of(dataCompanyCaseFilingPartyExWarehouseVO);
	}
	/**
	 * 企业立案信息当事人出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingPartyExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyCaseFilingPartyDO dataCompanyCaseFilingPartyDO = iDataCompanyCaseFilingPartyService.getById(id);
		if (dataCompanyCaseFilingPartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyCaseFilingPartyExWarehouseVO dataCompanyCaseFilingPartyExWarehouseVO = DataCompanyCaseFilingPartyAppStructMapping.instance.dataCompanyCaseFilingPartyDOToDataCompanyCaseFilingPartyExWarehouseVO(dataCompanyCaseFilingPartyDO);
		return SingleResponse.of(dataCompanyCaseFilingPartyExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyCaseFilingPartyService(IDataCompanyCaseFilingPartyService iDataCompanyCaseFilingPartyService) {
		this.iDataCompanyCaseFilingPartyService = iDataCompanyCaseFilingPartyService;
	}
}
