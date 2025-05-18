package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementContentAppStructMapping;
import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementContentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyOpenCourtAnnouncementContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementContentExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementContentExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementContentDO;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementContentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementContentService;
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
 * 企业开庭公告内容出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyOpenCourtAnnouncementContentService iDataCompanyOpenCourtAnnouncementContentService;

	/**
	 * 企业开庭公告内容出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementContentExWarehouseVO> exWarehouse(@Valid DataCompanyOpenCourtAnnouncementContentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		DataCompanyOpenCourtAnnouncementContentDO dataCompanyOpenCourtAnnouncementContentDO = iDataCompanyOpenCourtAnnouncementContentService.getByCompanyOpenCourtAnnouncementId(dataCompanyExWarehouseQueryCommand.getCompanyOpenCourtAnnouncementId());
        if (dataCompanyOpenCourtAnnouncementContentDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		DataCompanyOpenCourtAnnouncementContentExWarehouseVO dataCompanyOpenCourtAnnouncementContentExWarehouseVO = DataCompanyOpenCourtAnnouncementContentAppStructMapping.instance.dataCompanyOpenCourtAnnouncementContentDOToDataCompanyOpenCourtAnnouncementContentExWarehouseVO(dataCompanyOpenCourtAnnouncementContentDO);
		return SingleResponse.of(dataCompanyOpenCourtAnnouncementContentExWarehouseVO);
	}
	/**
	 * 企业开庭公告内容出库
	 * @param companyOpenCourtAnnouncementIds
	 * @return
	 */
	public MultiResponse<DataCompanyOpenCourtAnnouncementContentExWarehouseVO> exWarehouseByCompanyOpenCourtAnnouncementIds(List<Long> companyOpenCourtAnnouncementIds) {
		List<DataCompanyOpenCourtAnnouncementContentDO> dataCompanyOpenCourtAnnouncementContentDOList = iDataCompanyOpenCourtAnnouncementContentService.listByCompanyOpenCourtAnnouncementIds(companyOpenCourtAnnouncementIds);
		if (CollectionUtil.isEmpty(dataCompanyOpenCourtAnnouncementContentDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyOpenCourtAnnouncementContentExWarehouseVO> dataCompanyOpenCourtAnnouncementContentExWarehouseVOS = DataCompanyOpenCourtAnnouncementContentAppStructMapping.instance.dataCompanyOpenCourtAnnouncementContentDOsToDataCompanyOpenCourtAnnouncementContentExWarehouseVOs(dataCompanyOpenCourtAnnouncementContentDOList);
		return MultiResponse.of(dataCompanyOpenCourtAnnouncementContentExWarehouseVOS);
	}
	@Autowired
	public void setiDataCompanyOpenCourtAnnouncementContentService(IDataCompanyOpenCourtAnnouncementContentService iDataCompanyOpenCourtAnnouncementContentService) {
		this.iDataCompanyOpenCourtAnnouncementContentService = iDataCompanyOpenCourtAnnouncementContentService;
	}
}
