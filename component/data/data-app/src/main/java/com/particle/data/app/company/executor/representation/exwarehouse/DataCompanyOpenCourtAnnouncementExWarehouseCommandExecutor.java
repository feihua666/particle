package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementDO;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业开庭公告出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyOpenCourtAnnouncementService iDataCompanyOpenCourtAnnouncementService;

	/**
	 * 企业开庭公告出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> exWarehouse(@Valid DataCompanyOpenCourtAnnouncementExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyOpenCourtAnnouncementDO> dataCompanyOpenCourtAnnouncementDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyId() != null) {
			dataCompanyOpenCourtAnnouncementDOPage = iDataCompanyOpenCourtAnnouncementService.listPageByCompanyId(dataCompanyExWarehouseQueryCommand.getCompanyId(),
					dataCompanyExWarehouseQueryCommand.getCaseNo(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyOpenCourtAnnouncementDOPage == null || dataCompanyOpenCourtAnnouncementDOPage.getRecords() == null || dataCompanyOpenCourtAnnouncementDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyOpenCourtAnnouncementAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyOpenCourtAnnouncementDOPage);
	}
	/**
	 * 企业开庭公告出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> exWarehouseByDataMd5(String dataMd5) {
		DataCompanyOpenCourtAnnouncementDO dataCompanyOpenCourtAnnouncementDO = iDataCompanyOpenCourtAnnouncementService.getByDataMd5(dataMd5);
		if (dataCompanyOpenCourtAnnouncementDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyOpenCourtAnnouncementExWarehouseVO dataCompanyOpenCourtAnnouncementExWarehouseVO = DataCompanyOpenCourtAnnouncementAppStructMapping.instance.dataCompanyOpenCourtAnnouncementDOToDataCompanyOpenCourtAnnouncementExWarehouseVO(dataCompanyOpenCourtAnnouncementDO);
		return SingleResponse.of(dataCompanyOpenCourtAnnouncementExWarehouseVO);
	}
	/**
	 * 企业开庭公告出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyOpenCourtAnnouncementDO dataCompanyOpenCourtAnnouncementDO = iDataCompanyOpenCourtAnnouncementService.getById(id);
		if (dataCompanyOpenCourtAnnouncementDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyOpenCourtAnnouncementExWarehouseVO dataCompanyOpenCourtAnnouncementExWarehouseVO = DataCompanyOpenCourtAnnouncementAppStructMapping.instance.dataCompanyOpenCourtAnnouncementDOToDataCompanyOpenCourtAnnouncementExWarehouseVO(dataCompanyOpenCourtAnnouncementDO);
		return SingleResponse.of(dataCompanyOpenCourtAnnouncementExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyOpenCourtAnnouncementService(IDataCompanyOpenCourtAnnouncementService iDataCompanyOpenCourtAnnouncementService) {
		this.iDataCompanyOpenCourtAnnouncementService = iDataCompanyOpenCourtAnnouncementService;
	}
}
