package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementContentAppStructMapping;
import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementContentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementContentDO;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementContentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementContentService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业法院公告内容出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementContentExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyCourtAnnouncementContentService iDataCompanyCourtAnnouncementContentService;

	/**
	 * 企业法院公告内容出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementContentExWarehouseVO> exWarehouse(@Valid DataCompanyCourtAnnouncementContentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		DataCompanyCourtAnnouncementContentDO dataCompanyCourtAnnouncementContentDO = iDataCompanyCourtAnnouncementContentService.getByCompanyCourtAnnouncementId(dataCompanyExWarehouseQueryCommand.getCompanyCourtAnnouncementId());
        if (dataCompanyCourtAnnouncementContentDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		DataCompanyCourtAnnouncementContentExWarehouseVO dataCompanyCourtAnnouncementContentExWarehouseVO = DataCompanyCourtAnnouncementContentAppStructMapping.instance.dataCompanyCourtAnnouncementContentDOToDataCompanyCourtAnnouncementContentExWarehouseVO(dataCompanyCourtAnnouncementContentDO);
		return SingleResponse.of(dataCompanyCourtAnnouncementContentExWarehouseVO);
	}
	/**
	 * 企业法院公告内容出库
	 * @param companyCourtAnnouncementIds
	 * @return
	 */
	public MultiResponse<DataCompanyCourtAnnouncementContentExWarehouseVO> exWarehouseByCompanyCourtAnnouncementIds(List<Long> companyCourtAnnouncementIds) {
		List<DataCompanyCourtAnnouncementContentDO> dataCompanyCourtAnnouncementContentDOList = iDataCompanyCourtAnnouncementContentService.listByCompanyCourtAnnouncementIds(companyCourtAnnouncementIds);
		if (CollectionUtil.isEmpty(dataCompanyCourtAnnouncementContentDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyCourtAnnouncementContentExWarehouseVO> dataCompanyCourtAnnouncementContentExWarehouseVOS = DataCompanyCourtAnnouncementContentAppStructMapping.instance.dataCompanyCourtAnnouncementContentDOsToDataCompanyCourtAnnouncementContentExWarehouseVOs(dataCompanyCourtAnnouncementContentDOList);
		return MultiResponse.of(dataCompanyCourtAnnouncementContentExWarehouseVOS);
	}
	@Autowired
	public void setiDataCompanyCourtAnnouncementContentService(IDataCompanyCourtAnnouncementContentService iDataCompanyCourtAnnouncementContentService) {
		this.iDataCompanyCourtAnnouncementContentService = iDataCompanyCourtAnnouncementContentService;
	}
}
