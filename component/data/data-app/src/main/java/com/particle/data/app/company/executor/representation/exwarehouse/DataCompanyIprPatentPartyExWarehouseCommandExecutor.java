package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentPartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPartyExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPartyService;
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
 * 企业知识产权专利当事人出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentPartyExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentPartyService iDataCompanyIprPatentPartyService;

	/**
	 * 企业知识产权专利当事人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentPartyExWarehouseVO> exWarehouse(@Valid DataCompanyIprPatentPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprPatentPartyDO> dataCompanyIprPatentPartyDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyIprPatentPartyDOPage = iDataCompanyIprPatentPartyService.listPageByCompanyIprPatentId(dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId(),
					dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyIprPatentPartyDOPage == null || dataCompanyIprPatentPartyDOPage.getRecords() == null || dataCompanyIprPatentPartyDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprPatentPartyAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprPatentPartyDOPage);
	}
	/**
	 * 企业知识产权专利当事人出库
	 * @param companyIprPatentIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentPartyExWarehouseVO> exWarehouseByCompanyIprPatentIds(List<Long> companyIprPatentIds) {
		List<DataCompanyIprPatentPartyDO> dataCompanyIprPatentPartyDOList = iDataCompanyIprPatentPartyService.listByCompanyIprPatentIds(companyIprPatentIds);
		if (CollectionUtil.isEmpty(dataCompanyIprPatentPartyDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprPatentPartyExWarehouseVO> dataCompanyIprPatentPartyExWarehouseVOS = DataCompanyIprPatentPartyAppStructMapping.instance.dataCompanyIprPatentPartyDOsToDataCompanyIprPatentPartyExWarehouseVOs(dataCompanyIprPatentPartyDOList);
		return MultiResponse.of(dataCompanyIprPatentPartyExWarehouseVOS);
	}
	/**
	 * 企业知识产权专利当事人出库
	 * @param companyCaseFilingId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPartyExWarehouseVO> exWarehouseByCompanyIprPatentIdAndDataMd5(Long companyCaseFilingId,String dataMd5) {
		DataCompanyIprPatentPartyDO dataCompanyIprPatentPartyDO = iDataCompanyIprPatentPartyService.getByCompanyIprPatentIdAndDataMd5(companyCaseFilingId,dataMd5);
		if (dataCompanyIprPatentPartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentPartyExWarehouseVO dataCompanyIprPatentPartyExWarehouseVO = DataCompanyIprPatentPartyAppStructMapping.instance.dataCompanyIprPatentPartyDOToDataCompanyIprPatentPartyExWarehouseVO(dataCompanyIprPatentPartyDO);
		return SingleResponse.of(dataCompanyIprPatentPartyExWarehouseVO);
	}
	/**
	 * 企业知识产权专利当事人出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPartyExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprPatentPartyDO dataCompanyIprPatentPartyDO = iDataCompanyIprPatentPartyService.getById(id);
		if (dataCompanyIprPatentPartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentPartyExWarehouseVO dataCompanyIprPatentPartyExWarehouseVO = DataCompanyIprPatentPartyAppStructMapping.instance.dataCompanyIprPatentPartyDOToDataCompanyIprPatentPartyExWarehouseVO(dataCompanyIprPatentPartyDO);
		return SingleResponse.of(dataCompanyIprPatentPartyExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprPatentPartyService(IDataCompanyIprPatentPartyService iDataCompanyIprPatentPartyService) {
		this.iDataCompanyIprPatentPartyService = iDataCompanyIprPatentPartyService;
	}
}
