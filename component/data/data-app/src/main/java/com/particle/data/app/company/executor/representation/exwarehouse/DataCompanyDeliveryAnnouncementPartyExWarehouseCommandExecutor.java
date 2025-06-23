package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementPartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDeliveryAnnouncementPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementPartyExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementPartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementPartyService;
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
 * 企业送达公告当事人出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyDeliveryAnnouncementPartyService iDataCompanyDeliveryAnnouncementPartyService;

	/**
	 * 企业送达公告当事人出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> exWarehouse(@Valid DataCompanyDeliveryAnnouncementPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyDeliveryAnnouncementPartyDO> dataCompanyDeliveryAnnouncementPartyDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyDeliveryAnnouncementPartyDOPage = iDataCompanyDeliveryAnnouncementPartyService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyDeliveryAnnouncementPartyDOPage == null || dataCompanyDeliveryAnnouncementPartyDOPage.getRecords() == null || dataCompanyDeliveryAnnouncementPartyDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyDeliveryAnnouncementPartyAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyDeliveryAnnouncementPartyDOPage);
	}
	/**
	 * 企业送达公告当事人出库
	 * @param companyDeliveryAnnouncementId
	 * @return
	 */
	public MultiResponse<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> exWarehouseByCompanyDeliveryAnnouncementId(Long companyDeliveryAnnouncementId) {
		List<DataCompanyDeliveryAnnouncementPartyDO> dataCompanyDeliveryAnnouncementPartyDOList = iDataCompanyDeliveryAnnouncementPartyService.getByCompanyDeliveryAnnouncementId(companyDeliveryAnnouncementId);
        if (CollectionUtil.isEmpty(dataCompanyDeliveryAnnouncementPartyDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		List<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> dataCompanyDeliveryAnnouncementPartyExWarehouseVOS = DataCompanyDeliveryAnnouncementPartyAppStructMapping.instance.dataCompanyDeliveryAnnouncementPartyDOsToDataCompanyDeliveryAnnouncementPartyExWarehouseVOs(dataCompanyDeliveryAnnouncementPartyDOList);
		return MultiResponse.of(dataCompanyDeliveryAnnouncementPartyExWarehouseVOS);
	}
	/**
	 * 企业送达公告当事人出库
	 * @param companyDeliveryAnnouncementIds
	 * @return
	 */
	public MultiResponse<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> exWarehouseByCompanyDeliveryAnnouncementIds(List<Long> companyDeliveryAnnouncementIds) {
		List<DataCompanyDeliveryAnnouncementPartyDO> dataCompanyDeliveryAnnouncementPartyDOList = iDataCompanyDeliveryAnnouncementPartyService.getByCompanyDeliveryAnnouncementIds(companyDeliveryAnnouncementIds);
		if (CollectionUtil.isEmpty(dataCompanyDeliveryAnnouncementPartyDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> dataCompanyDeliveryAnnouncementPartyExWarehouseVOS = DataCompanyDeliveryAnnouncementPartyAppStructMapping.instance.dataCompanyDeliveryAnnouncementPartyDOsToDataCompanyDeliveryAnnouncementPartyExWarehouseVOs(dataCompanyDeliveryAnnouncementPartyDOList);
		return MultiResponse.of(dataCompanyDeliveryAnnouncementPartyExWarehouseVOS);
	}
	/**
	 * 企业送达公告当事人出库
	 * @param companyDeliveryAnnouncementId
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> exWarehouseByCompanyDeliveryAnnouncementIdAndDataMd5(Long companyDeliveryAnnouncementId,String dataMd5) {
		DataCompanyDeliveryAnnouncementPartyDO dataCompanyDeliveryAnnouncementPartyDO = iDataCompanyDeliveryAnnouncementPartyService.getByCompanyDeliveryAnnouncementIdAndDataMd5(companyDeliveryAnnouncementId,dataMd5);
		if (dataCompanyDeliveryAnnouncementPartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyDeliveryAnnouncementPartyExWarehouseVO dataCompanyDeliveryAnnouncementPartyExWarehouseVO = DataCompanyDeliveryAnnouncementPartyAppStructMapping.instance.dataCompanyDeliveryAnnouncementPartyDOToDataCompanyDeliveryAnnouncementPartyExWarehouseVO(dataCompanyDeliveryAnnouncementPartyDO);
		return SingleResponse.of(dataCompanyDeliveryAnnouncementPartyExWarehouseVO);
	}
	/**
	 * 企业送达公告当事人出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyDeliveryAnnouncementPartyDO dataCompanyDeliveryAnnouncementPartyDO = iDataCompanyDeliveryAnnouncementPartyService.getById(id);
		if (dataCompanyDeliveryAnnouncementPartyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyDeliveryAnnouncementPartyExWarehouseVO dataCompanyDeliveryAnnouncementPartyExWarehouseVO = DataCompanyDeliveryAnnouncementPartyAppStructMapping.instance.dataCompanyDeliveryAnnouncementPartyDOToDataCompanyDeliveryAnnouncementPartyExWarehouseVO(dataCompanyDeliveryAnnouncementPartyDO);
		return SingleResponse.of(dataCompanyDeliveryAnnouncementPartyExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyDeliveryAnnouncementPartyService(IDataCompanyDeliveryAnnouncementPartyService iDataCompanyDeliveryAnnouncementPartyService) {
		this.iDataCompanyDeliveryAnnouncementPartyService = iDataCompanyDeliveryAnnouncementPartyService;
	}
}
