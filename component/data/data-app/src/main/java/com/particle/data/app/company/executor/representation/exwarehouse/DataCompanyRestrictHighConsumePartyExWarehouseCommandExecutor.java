package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyRestrictHighConsumePartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyRestrictHighConsumePartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumePartyExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumePartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyRestrictHighConsumePartyService;
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
 * 企业限制高消费当事人出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyRestrictHighConsumePartyService iDataCompanyRestrictHighConsumePartyService;

	/**
	 * 企业限制高消费当事人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyRestrictHighConsumePartyExWarehouseVO> exWarehouse(@Valid DataCompanyRestrictHighConsumePartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyRestrictHighConsumePartyDO> dataCompanyRestrictHighConsumePartyDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyRestrictHighConsumePartyDOPage = iDataCompanyRestrictHighConsumePartyService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyRestrictHighConsumePartyDOPage == null || dataCompanyRestrictHighConsumePartyDOPage.getRecords() == null || dataCompanyRestrictHighConsumePartyDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyRestrictHighConsumePartyAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyRestrictHighConsumePartyDOPage);
	}
	/**
	 * 企业限制高消费当事人出库
	 * @param companyRestrictHighConsumeId
	 * @return
	 */
	public MultiResponse<DataCompanyRestrictHighConsumePartyExWarehouseVO> exWarehouseByCompanyRestrictHighConsumeId(Long companyRestrictHighConsumeId) {
		List<DataCompanyRestrictHighConsumePartyDO> dataCompanyRestrictHighConsumePartyDOList = iDataCompanyRestrictHighConsumePartyService.getByCompanyRestrictHighConsumeId(companyRestrictHighConsumeId);
        if (CollectionUtil.isEmpty(dataCompanyRestrictHighConsumePartyDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		List<DataCompanyRestrictHighConsumePartyExWarehouseVO> dataCompanyRestrictHighConsumePartyExWarehouseVOS = DataCompanyRestrictHighConsumePartyAppStructMapping.instance.dataCompanyRestrictHighConsumePartyDOsToDataCompanyRestrictHighConsumePartyExWarehouseVOs(dataCompanyRestrictHighConsumePartyDOList);
		return MultiResponse.of(dataCompanyRestrictHighConsumePartyExWarehouseVOS);
	}
	/**
	 * 企业限制高消费当事人出库
	 * @param companyRestrictHighConsumeIds
	 * @return
	 */
	public MultiResponse<DataCompanyRestrictHighConsumePartyExWarehouseVO> exWarehouseByCompanyRestrictHighConsumeIds(List<Long> companyRestrictHighConsumeIds) {
		List<DataCompanyRestrictHighConsumePartyDO> dataCompanyRestrictHighConsumePartyDOList = iDataCompanyRestrictHighConsumePartyService.getByCompanyRestrictHighConsumeIds(companyRestrictHighConsumeIds);
		if (CollectionUtil.isEmpty(dataCompanyRestrictHighConsumePartyDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyRestrictHighConsumePartyExWarehouseVO> dataCompanyRestrictHighConsumePartyExWarehouseVOS = DataCompanyRestrictHighConsumePartyAppStructMapping.instance.dataCompanyRestrictHighConsumePartyDOsToDataCompanyRestrictHighConsumePartyExWarehouseVOs(dataCompanyRestrictHighConsumePartyDOList);
		return MultiResponse.of(dataCompanyRestrictHighConsumePartyExWarehouseVOS);
	}
	/**
	 * 企业限制高消费当事人出库
	 * @param companyRestrictHighConsumeId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumePartyExWarehouseVO> exWarehouseByCompanyRestrictHighConsumeIdAndDataMd5(Long companyRestrictHighConsumeId,String dataMd5) {
		DataCompanyRestrictHighConsumePartyDO dataCompanyRestrictHighConsumePartyDO = iDataCompanyRestrictHighConsumePartyService.getByCompanyRestrictHighConsumeIdAndDataMd5(companyRestrictHighConsumeId,dataMd5);
		if (dataCompanyRestrictHighConsumePartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyRestrictHighConsumePartyExWarehouseVO dataCompanyRestrictHighConsumePartyExWarehouseVO = DataCompanyRestrictHighConsumePartyAppStructMapping.instance.dataCompanyRestrictHighConsumePartyDOToDataCompanyRestrictHighConsumePartyExWarehouseVO(dataCompanyRestrictHighConsumePartyDO);
		return SingleResponse.of(dataCompanyRestrictHighConsumePartyExWarehouseVO);
	}
	/**
	 * 企业限制高消费当事人出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumePartyExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyRestrictHighConsumePartyDO dataCompanyRestrictHighConsumePartyDO = iDataCompanyRestrictHighConsumePartyService.getById(id);
		if (dataCompanyRestrictHighConsumePartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyRestrictHighConsumePartyExWarehouseVO dataCompanyRestrictHighConsumePartyExWarehouseVO = DataCompanyRestrictHighConsumePartyAppStructMapping.instance.dataCompanyRestrictHighConsumePartyDOToDataCompanyRestrictHighConsumePartyExWarehouseVO(dataCompanyRestrictHighConsumePartyDO);
		return SingleResponse.of(dataCompanyRestrictHighConsumePartyExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyRestrictHighConsumePartyService(IDataCompanyRestrictHighConsumePartyService iDataCompanyRestrictHighConsumePartyService) {
		this.iDataCompanyRestrictHighConsumePartyService = iDataCompanyRestrictHighConsumePartyService;
	}
}
