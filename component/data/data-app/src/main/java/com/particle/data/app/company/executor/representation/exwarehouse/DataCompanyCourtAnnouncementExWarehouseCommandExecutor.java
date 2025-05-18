package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCourtAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementDO;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业法院公告出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyCourtAnnouncementService iDataCompanyCourtAnnouncementService;

	/**
	 * 企业法院公告出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyCourtAnnouncementExWarehouseVO> exWarehouse(@Valid DataCompanyCourtAnnouncementExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyCourtAnnouncementDO> dataCompanyCourtAnnouncementDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyCourtAnnouncementDOPage = iDataCompanyCourtAnnouncementService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getAnnouncementNo(),
					dataCompanyExWarehouseQueryCommand.getCaseNo(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyCourtAnnouncementDOPage == null || dataCompanyCourtAnnouncementDOPage.getRecords() == null || dataCompanyCourtAnnouncementDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyCourtAnnouncementAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyCourtAnnouncementDOPage);
	}
	/**
	 * 企业法院公告出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementExWarehouseVO> exWarehouseByDataMd5(String dataMd5) {
		DataCompanyCourtAnnouncementDO dataCompanyCourtAnnouncementDO = iDataCompanyCourtAnnouncementService.getByDataMd5(dataMd5);
		if (dataCompanyCourtAnnouncementDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyCourtAnnouncementExWarehouseVO dataCompanyCourtAnnouncementExWarehouseVO = DataCompanyCourtAnnouncementAppStructMapping.instance.dataCompanyCourtAnnouncementDOToDataCompanyCourtAnnouncementExWarehouseVO(dataCompanyCourtAnnouncementDO);
		return SingleResponse.of(dataCompanyCourtAnnouncementExWarehouseVO);
	}
	/**
	 * 企业法院公告出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyCourtAnnouncementDO dataCompanyCourtAnnouncementDO = iDataCompanyCourtAnnouncementService.getById(id);
		if (dataCompanyCourtAnnouncementDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyCourtAnnouncementExWarehouseVO dataCompanyCourtAnnouncementExWarehouseVO = DataCompanyCourtAnnouncementAppStructMapping.instance.dataCompanyCourtAnnouncementDOToDataCompanyCourtAnnouncementExWarehouseVO(dataCompanyCourtAnnouncementDO);
		return SingleResponse.of(dataCompanyCourtAnnouncementExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyCourtAnnouncementService(IDataCompanyCourtAnnouncementService iDataCompanyCourtAnnouncementService) {
		this.iDataCompanyCourtAnnouncementService = iDataCompanyCourtAnnouncementService;
	}
}
