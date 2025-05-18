package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyJudgmentDocumentPartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentPartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentPartyService;
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
 * 企业裁判文书当事人出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-18 11:30:08
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyJudgmentDocumentPartyService iDataCompanyJudgmentDocumentPartyService;

	/**
	 * 企业裁判文书当事人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDocumentPartyExWarehouseVO> exWarehouse(@Valid DataCompanyJudgmentDocumentPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyJudgmentDocumentPartyDO> dataCompanyJudgmentDocumentPartyDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyJudgmentDocumentPartyDOPage = iDataCompanyJudgmentDocumentPartyService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyJudgmentDocumentPartyDOPage == null || dataCompanyJudgmentDocumentPartyDOPage.getRecords() == null || dataCompanyJudgmentDocumentPartyDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyJudgmentDocumentPartyAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyJudgmentDocumentPartyDOPage);
	}
	/**
	 * 企业裁判文书当事人出库
	 * @param companyJudgmentDocumentId
	 * @return
	 */
	public MultiResponse<DataCompanyJudgmentDocumentPartyExWarehouseVO> exWarehouseByCompanyJudgmentDocumentId(Long companyJudgmentDocumentId) {
		List<DataCompanyJudgmentDocumentPartyDO> dataCompanyJudgmentDocumentPartyDOList = iDataCompanyJudgmentDocumentPartyService.getByCompanyJudgmentDocumentId(companyJudgmentDocumentId);
        if (CollectionUtil.isEmpty(dataCompanyJudgmentDocumentPartyDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		List<DataCompanyJudgmentDocumentPartyExWarehouseVO> dataCompanyJudgmentDocumentPartyExWarehouseVOS = DataCompanyJudgmentDocumentPartyAppStructMapping.instance.dataCompanyJudgmentDocumentPartyDOsToDataCompanyJudgmentDocumentPartyExWarehouseVOs(dataCompanyJudgmentDocumentPartyDOList);
		return MultiResponse.of(dataCompanyJudgmentDocumentPartyExWarehouseVOS);
	}
	/**
	 * 企业裁判文书当事人出库
	 * @param companyJudgmentDocumentIds
	 * @return
	 */
	public MultiResponse<DataCompanyJudgmentDocumentPartyExWarehouseVO> exWarehouseByCompanyJudgmentDocumentIds(List<Long> companyJudgmentDocumentIds) {
		List<DataCompanyJudgmentDocumentPartyDO> dataCompanyJudgmentDocumentPartyDOList = iDataCompanyJudgmentDocumentPartyService.getByCompanyJudgmentDocumentIds(companyJudgmentDocumentIds);
		if (CollectionUtil.isEmpty(dataCompanyJudgmentDocumentPartyDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyJudgmentDocumentPartyExWarehouseVO> dataCompanyJudgmentDocumentPartyExWarehouseVOS = DataCompanyJudgmentDocumentPartyAppStructMapping.instance.dataCompanyJudgmentDocumentPartyDOsToDataCompanyJudgmentDocumentPartyExWarehouseVOs(dataCompanyJudgmentDocumentPartyDOList);
		return MultiResponse.of(dataCompanyJudgmentDocumentPartyExWarehouseVOS);
	}
	/**
	 * 企业裁判文书当事人出库
	 * @param companyJudgmentDocumentId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentPartyExWarehouseVO> exWarehouseByCompanyJudgmentDocumentIdAndDataMd5(Long companyJudgmentDocumentId,String dataMd5) {
		DataCompanyJudgmentDocumentPartyDO dataCompanyJudgmentDocumentPartyDO = iDataCompanyJudgmentDocumentPartyService.getByCompanyJudgmentDocumentIdAndDataMd5(companyJudgmentDocumentId,dataMd5);
		if (dataCompanyJudgmentDocumentPartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyJudgmentDocumentPartyExWarehouseVO dataCompanyJudgmentDocumentPartyExWarehouseVO = DataCompanyJudgmentDocumentPartyAppStructMapping.instance.dataCompanyJudgmentDocumentPartyDOToDataCompanyJudgmentDocumentPartyExWarehouseVO(dataCompanyJudgmentDocumentPartyDO);
		return SingleResponse.of(dataCompanyJudgmentDocumentPartyExWarehouseVO);
	}
	/**
	 * 企业裁判文书当事人出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentPartyExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyJudgmentDocumentPartyDO dataCompanyJudgmentDocumentPartyDO = iDataCompanyJudgmentDocumentPartyService.getById(id);
		if (dataCompanyJudgmentDocumentPartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyJudgmentDocumentPartyExWarehouseVO dataCompanyJudgmentDocumentPartyExWarehouseVO = DataCompanyJudgmentDocumentPartyAppStructMapping.instance.dataCompanyJudgmentDocumentPartyDOToDataCompanyJudgmentDocumentPartyExWarehouseVO(dataCompanyJudgmentDocumentPartyDO);
		return SingleResponse.of(dataCompanyJudgmentDocumentPartyExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyJudgmentDocumentPartyService(IDataCompanyJudgmentDocumentPartyService iDataCompanyJudgmentDocumentPartyService) {
		this.iDataCompanyJudgmentDocumentPartyService = iDataCompanyJudgmentDocumentPartyService;
	}
}
