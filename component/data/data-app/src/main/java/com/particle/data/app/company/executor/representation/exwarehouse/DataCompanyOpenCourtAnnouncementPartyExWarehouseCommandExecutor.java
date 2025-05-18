package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementPartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyOpenCourtAnnouncementPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementPartyExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementPartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementPartyService;
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
 * 企业开庭公告当事人出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyOpenCourtAnnouncementPartyService iDataCompanyOpenCourtAnnouncementPartyService;

	/**
	 * 企业开庭公告当事人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> exWarehouse(@Valid DataCompanyOpenCourtAnnouncementPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyOpenCourtAnnouncementPartyDO> dataCompanyOpenCourtAnnouncementPartyDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyOpenCourtAnnouncementPartyDOPage = iDataCompanyOpenCourtAnnouncementPartyService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyOpenCourtAnnouncementPartyDOPage == null || dataCompanyOpenCourtAnnouncementPartyDOPage.getRecords() == null || dataCompanyOpenCourtAnnouncementPartyDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyOpenCourtAnnouncementPartyAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyOpenCourtAnnouncementPartyDOPage);
	}
	/**
	 * 企业开庭公告当事人出库
	 * @param companyOpenCourtAnnouncementId
	 * @return
	 */
	public MultiResponse<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> exWarehouseByCompanyOpenCourtAnnouncementId(Long companyOpenCourtAnnouncementId) {
		List<DataCompanyOpenCourtAnnouncementPartyDO> dataCompanyOpenCourtAnnouncementPartyDOList = iDataCompanyOpenCourtAnnouncementPartyService.getByCompanyOpenCourtAnnouncementId(companyOpenCourtAnnouncementId);
        if (CollectionUtil.isEmpty(dataCompanyOpenCourtAnnouncementPartyDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		List<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> dataCompanyOpenCourtAnnouncementPartyExWarehouseVOS = DataCompanyOpenCourtAnnouncementPartyAppStructMapping.instance.dataCompanyOpenCourtAnnouncementPartyDOsToDataCompanyOpenCourtAnnouncementPartyExWarehouseVOs(dataCompanyOpenCourtAnnouncementPartyDOList);
		return MultiResponse.of(dataCompanyOpenCourtAnnouncementPartyExWarehouseVOS);
	}
	/**
	 * 企业开庭公告当事人出库
	 * @param companyOpenCourtAnnouncementIds
	 * @return
	 */
	public MultiResponse<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> exWarehouseByCompanyOpenCourtAnnouncementIds(List<Long> companyOpenCourtAnnouncementIds) {
		List<DataCompanyOpenCourtAnnouncementPartyDO> dataCompanyOpenCourtAnnouncementPartyDOList = iDataCompanyOpenCourtAnnouncementPartyService.getByCompanyOpenCourtAnnouncementIds(companyOpenCourtAnnouncementIds);
		if (CollectionUtil.isEmpty(dataCompanyOpenCourtAnnouncementPartyDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> dataCompanyOpenCourtAnnouncementPartyExWarehouseVOS = DataCompanyOpenCourtAnnouncementPartyAppStructMapping.instance.dataCompanyOpenCourtAnnouncementPartyDOsToDataCompanyOpenCourtAnnouncementPartyExWarehouseVOs(dataCompanyOpenCourtAnnouncementPartyDOList);
		return MultiResponse.of(dataCompanyOpenCourtAnnouncementPartyExWarehouseVOS);
	}
	/**
	 * 企业开庭公告当事人出库
	 * @param companyOpenCourtAnnouncementId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> exWarehouseByCompanyOpenCourtAnnouncementIdAndDataMd5(Long companyOpenCourtAnnouncementId,String dataMd5) {
		DataCompanyOpenCourtAnnouncementPartyDO dataCompanyOpenCourtAnnouncementPartyDO = iDataCompanyOpenCourtAnnouncementPartyService.getByCompanyOpenCourtAnnouncementIdAndDataMd5(companyOpenCourtAnnouncementId,dataMd5);
		if (dataCompanyOpenCourtAnnouncementPartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyOpenCourtAnnouncementPartyExWarehouseVO dataCompanyOpenCourtAnnouncementPartyExWarehouseVO = DataCompanyOpenCourtAnnouncementPartyAppStructMapping.instance.dataCompanyOpenCourtAnnouncementPartyDOToDataCompanyOpenCourtAnnouncementPartyExWarehouseVO(dataCompanyOpenCourtAnnouncementPartyDO);
		return SingleResponse.of(dataCompanyOpenCourtAnnouncementPartyExWarehouseVO);
	}
	/**
	 * 企业开庭公告当事人出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyOpenCourtAnnouncementPartyDO dataCompanyOpenCourtAnnouncementPartyDO = iDataCompanyOpenCourtAnnouncementPartyService.getById(id);
		if (dataCompanyOpenCourtAnnouncementPartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyOpenCourtAnnouncementPartyExWarehouseVO dataCompanyOpenCourtAnnouncementPartyExWarehouseVO = DataCompanyOpenCourtAnnouncementPartyAppStructMapping.instance.dataCompanyOpenCourtAnnouncementPartyDOToDataCompanyOpenCourtAnnouncementPartyExWarehouseVO(dataCompanyOpenCourtAnnouncementPartyDO);
		return SingleResponse.of(dataCompanyOpenCourtAnnouncementPartyExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyOpenCourtAnnouncementPartyService(IDataCompanyOpenCourtAnnouncementPartyService iDataCompanyOpenCourtAnnouncementPartyService) {
		this.iDataCompanyOpenCourtAnnouncementPartyService = iDataCompanyOpenCourtAnnouncementPartyService;
	}
}
