package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementDO;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业送达公告出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyDeliveryAnnouncementService iDataCompanyDeliveryAnnouncementService;

	/**
	 * 企业送达公告出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> exWarehouse(@Valid DataCompanyDeliveryAnnouncementExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyDeliveryAnnouncementDO> dataCompanyDeliveryAnnouncementDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyDeliveryAnnouncementDOPage = iDataCompanyDeliveryAnnouncementService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getCaseNo(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyDeliveryAnnouncementDOPage == null || dataCompanyDeliveryAnnouncementDOPage.getRecords() == null || dataCompanyDeliveryAnnouncementDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyDeliveryAnnouncementAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyDeliveryAnnouncementDOPage);
	}
	/**
	 * 企业送达公告出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> exWarehouseByDataMd5(String dataMd5) {
		DataCompanyDeliveryAnnouncementDO dataCompanyDeliveryAnnouncementDO = iDataCompanyDeliveryAnnouncementService.getByDataMd5(dataMd5);
		if (dataCompanyDeliveryAnnouncementDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyDeliveryAnnouncementExWarehouseVO dataCompanyDeliveryAnnouncementExWarehouseVO = DataCompanyDeliveryAnnouncementAppStructMapping.instance.dataCompanyDeliveryAnnouncementDOToDataCompanyDeliveryAnnouncementExWarehouseVO(dataCompanyDeliveryAnnouncementDO);
		return SingleResponse.of(dataCompanyDeliveryAnnouncementExWarehouseVO);
	}
	/**
	 * 企业送达公告出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyDeliveryAnnouncementDO dataCompanyDeliveryAnnouncementDO = iDataCompanyDeliveryAnnouncementService.getById(id);
		if (dataCompanyDeliveryAnnouncementDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyDeliveryAnnouncementExWarehouseVO dataCompanyDeliveryAnnouncementExWarehouseVO = DataCompanyDeliveryAnnouncementAppStructMapping.instance.dataCompanyDeliveryAnnouncementDOToDataCompanyDeliveryAnnouncementExWarehouseVO(dataCompanyDeliveryAnnouncementDO);
		return SingleResponse.of(dataCompanyDeliveryAnnouncementExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyDeliveryAnnouncementService(IDataCompanyDeliveryAnnouncementService iDataCompanyDeliveryAnnouncementService) {
		this.iDataCompanyDeliveryAnnouncementService = iDataCompanyDeliveryAnnouncementService;
	}
}
