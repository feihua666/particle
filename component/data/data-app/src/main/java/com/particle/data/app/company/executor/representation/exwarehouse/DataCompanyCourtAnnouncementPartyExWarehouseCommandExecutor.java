package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementPartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementPartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementPartyService;
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
 * 企业法院公告当事人出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyCourtAnnouncementPartyService iDataCompanyCourtAnnouncementPartyService;

	/**
	 * 企业法院公告当事人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyCourtAnnouncementPartyExWarehouseVO> exWarehouse(@Valid DataCompanyCourtAnnouncementPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyCourtAnnouncementPartyDO> dataCompanyCourtAnnouncementPartyDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyCourtAnnouncementPartyDOPage = iDataCompanyCourtAnnouncementPartyService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyCourtAnnouncementPartyDOPage == null || dataCompanyCourtAnnouncementPartyDOPage.getRecords() == null || dataCompanyCourtAnnouncementPartyDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyCourtAnnouncementPartyAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyCourtAnnouncementPartyDOPage);
	}
	/**
	 * 企业法院公告当事人出库
	 * @param companyCourtAnnouncementId
	 * @return
	 */
	public MultiResponse<DataCompanyCourtAnnouncementPartyExWarehouseVO> exWarehouseByCompanyCourtAnnouncementId(Long companyCourtAnnouncementId) {
		List<DataCompanyCourtAnnouncementPartyDO> dataCompanyCourtAnnouncementPartyDOList = iDataCompanyCourtAnnouncementPartyService.getByCompanyCourtAnnouncementId(companyCourtAnnouncementId);
        if (CollectionUtil.isEmpty(dataCompanyCourtAnnouncementPartyDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		List<DataCompanyCourtAnnouncementPartyExWarehouseVO> dataCompanyCourtAnnouncementPartyExWarehouseVOS = DataCompanyCourtAnnouncementPartyAppStructMapping.instance.dataCompanyCourtAnnouncementPartyDOsToDataCompanyCourtAnnouncementPartyExWarehouseVOs(dataCompanyCourtAnnouncementPartyDOList);
		return MultiResponse.of(dataCompanyCourtAnnouncementPartyExWarehouseVOS);
	}
	/**
	 * 企业法院公告当事人出库
	 * @param companyCourtAnnouncementIds
	 * @return
	 */
	public MultiResponse<DataCompanyCourtAnnouncementPartyExWarehouseVO> exWarehouseByCompanyCourtAnnouncementIds(List<Long> companyCourtAnnouncementIds) {
		List<DataCompanyCourtAnnouncementPartyDO> dataCompanyCourtAnnouncementPartyDOList = iDataCompanyCourtAnnouncementPartyService.getByCompanyCourtAnnouncementIds(companyCourtAnnouncementIds);
		if (CollectionUtil.isEmpty(dataCompanyCourtAnnouncementPartyDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyCourtAnnouncementPartyExWarehouseVO> dataCompanyCourtAnnouncementPartyExWarehouseVOS = DataCompanyCourtAnnouncementPartyAppStructMapping.instance.dataCompanyCourtAnnouncementPartyDOsToDataCompanyCourtAnnouncementPartyExWarehouseVOs(dataCompanyCourtAnnouncementPartyDOList);
		return MultiResponse.of(dataCompanyCourtAnnouncementPartyExWarehouseVOS);
	}
	/**
	 * 企业法院公告当事人出库
	 * @param companyCourtAnnouncementId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementPartyExWarehouseVO> exWarehouseByCompanyCourtAnnouncementIdAndDataMd5(Long companyCourtAnnouncementId,String dataMd5) {
		DataCompanyCourtAnnouncementPartyDO dataCompanyCourtAnnouncementPartyDO = iDataCompanyCourtAnnouncementPartyService.getByCompanyCourtAnnouncementIdAndDataMd5(companyCourtAnnouncementId,dataMd5);
		if (dataCompanyCourtAnnouncementPartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyCourtAnnouncementPartyExWarehouseVO dataCompanyCourtAnnouncementPartyExWarehouseVO = DataCompanyCourtAnnouncementPartyAppStructMapping.instance.dataCompanyCourtAnnouncementPartyDOToDataCompanyCourtAnnouncementPartyExWarehouseVO(dataCompanyCourtAnnouncementPartyDO);
		return SingleResponse.of(dataCompanyCourtAnnouncementPartyExWarehouseVO);
	}
	/**
	 * 企业法院公告当事人出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementPartyExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyCourtAnnouncementPartyDO dataCompanyCourtAnnouncementPartyDO = iDataCompanyCourtAnnouncementPartyService.getById(id);
		if (dataCompanyCourtAnnouncementPartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyCourtAnnouncementPartyExWarehouseVO dataCompanyCourtAnnouncementPartyExWarehouseVO = DataCompanyCourtAnnouncementPartyAppStructMapping.instance.dataCompanyCourtAnnouncementPartyDOToDataCompanyCourtAnnouncementPartyExWarehouseVO(dataCompanyCourtAnnouncementPartyDO);
		return SingleResponse.of(dataCompanyCourtAnnouncementPartyExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyCourtAnnouncementPartyService(IDataCompanyCourtAnnouncementPartyService iDataCompanyCourtAnnouncementPartyService) {
		this.iDataCompanyCourtAnnouncementPartyService = iDataCompanyCourtAnnouncementPartyService;
	}
}
