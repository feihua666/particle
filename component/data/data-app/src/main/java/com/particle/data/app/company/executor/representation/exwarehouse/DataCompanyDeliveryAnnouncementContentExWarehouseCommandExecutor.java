package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementContentAppStructMapping;
import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementContentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDeliveryAnnouncementContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementContentExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementContentExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementContentDO;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementContentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementContentService;
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
 * 企业送达公告内容出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyDeliveryAnnouncementContentService iDataCompanyDeliveryAnnouncementContentService;

	/**
	 * 企业送达公告内容出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementContentExWarehouseVO> exWarehouse(@Valid DataCompanyDeliveryAnnouncementContentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		DataCompanyDeliveryAnnouncementContentDO dataCompanyDeliveryAnnouncementContentDO = iDataCompanyDeliveryAnnouncementContentService.getByCompanyDeliveryAnnouncementId(dataCompanyExWarehouseQueryCommand.getCompanyDeliveryAnnouncementId());
        if (dataCompanyDeliveryAnnouncementContentDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		DataCompanyDeliveryAnnouncementContentExWarehouseVO dataCompanyDeliveryAnnouncementContentExWarehouseVO = DataCompanyDeliveryAnnouncementContentAppStructMapping.instance.dataCompanyDeliveryAnnouncementContentDOToDataCompanyDeliveryAnnouncementContentExWarehouseVO(dataCompanyDeliveryAnnouncementContentDO);
		return SingleResponse.of(dataCompanyDeliveryAnnouncementContentExWarehouseVO);
	}
	/**
	 * 企业送达公告内容出库
	 * @param companyDeliveryAnnouncementIds
	 * @return
	 */
	public MultiResponse<DataCompanyDeliveryAnnouncementContentExWarehouseVO> exWarehouseByCompanyDeliveryAnnouncementIds(List<Long> companyDeliveryAnnouncementIds) {
		List<DataCompanyDeliveryAnnouncementContentDO> dataCompanyDeliveryAnnouncementContentDOList = iDataCompanyDeliveryAnnouncementContentService.getByCompanyDeliveryAnnouncementIds(companyDeliveryAnnouncementIds);
		if (CollectionUtil.isEmpty(dataCompanyDeliveryAnnouncementContentDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyDeliveryAnnouncementContentExWarehouseVO> dataCompanyDeliveryAnnouncementContentExWarehouseVOS = DataCompanyDeliveryAnnouncementContentAppStructMapping.instance.dataCompanyDeliveryAnnouncementContentDOsToDataCompanyDeliveryAnnouncementContentExWarehouseVOs(dataCompanyDeliveryAnnouncementContentDOList);
		return MultiResponse.of(dataCompanyDeliveryAnnouncementContentExWarehouseVOS);
	}
	@Autowired
	public void setiDataCompanyDeliveryAnnouncementContentService(IDataCompanyDeliveryAnnouncementContentService iDataCompanyDeliveryAnnouncementContentService) {
		this.iDataCompanyDeliveryAnnouncementContentService = iDataCompanyDeliveryAnnouncementContentService;
	}
}
