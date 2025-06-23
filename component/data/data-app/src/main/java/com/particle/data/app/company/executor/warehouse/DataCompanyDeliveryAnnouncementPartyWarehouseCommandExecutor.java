package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementPartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementPartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementPartyUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDeliveryAnnouncementPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementPartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementPartyExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementPartyService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业送达公告当事人入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementPartyWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor;
	private DataCompanyDeliveryAnnouncementPartyCreateCommandExecutor dataCompanyDeliveryAnnouncementPartyCreateCommandExecutor;
	private IDataCompanyDeliveryAnnouncementPartyService iDataCompanyDeliveryAnnouncementPartyService;
	private DataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor dataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor;


	/**
	 * 企业送达公告当事人入库
	 * @param dataCompanyDeliveryAnnouncementPartyWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> warehouse(DataCompanyDeliveryAnnouncementPartyWarehouseCommand dataCompanyDeliveryAnnouncementPartyWarehouseCommand) {
		SingleResponse<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> dataCompanyDeliveryAnnouncementPartyExWarehouseVOSingleResponse = null;
		DataCompanyDeliveryAnnouncementPartyExWarehouseVO dataCompanyDeliveryAnnouncementPartyExWarehouseVO = null;
        if (dataCompanyDeliveryAnnouncementPartyWarehouseCommand.getCompanyDeliveryAnnouncementId() != null) {
			dataCompanyDeliveryAnnouncementPartyExWarehouseVOSingleResponse = dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor.exWarehouseByCompanyDeliveryAnnouncementIdAndDataMd5(dataCompanyDeliveryAnnouncementPartyWarehouseCommand.getCompanyDeliveryAnnouncementId(),dataCompanyDeliveryAnnouncementPartyWarehouseCommand.obtainDataMd5());
		}

		dataCompanyDeliveryAnnouncementPartyExWarehouseVO = dataCompanyDeliveryAnnouncementPartyExWarehouseVOSingleResponse == null ? null : dataCompanyDeliveryAnnouncementPartyExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyDeliveryAnnouncementPartyExWarehouseVO == null) {
			DataCompanyDeliveryAnnouncementPartyCreateCommand dataCompanyDeliveryAnnouncementPartyCreateCommand = DataCompanyDeliveryAnnouncementPartyCreateCommand.createByWarehouseCommand(dataCompanyDeliveryAnnouncementPartyWarehouseCommand);
			SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> dataCompanyDeliveryAnnouncementPartyVOSingleResponse = dataCompanyDeliveryAnnouncementPartyCreateCommandExecutor.execute(dataCompanyDeliveryAnnouncementPartyCreateCommand);
			Long id = dataCompanyDeliveryAnnouncementPartyVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyDeliveryAnnouncementPartyExWarehouseVOSingleResponse = dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyDeliveryAnnouncementPartyExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyDeliveryAnnouncementPartyExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyDeliveryAnnouncementPartyWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyDeliveryAnnouncementPartyExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyDeliveryAnnouncementPartyWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyDeliveryAnnouncementPartyService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyDeliveryAnnouncementPartyExWarehouseVOSingleResponse = dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyDeliveryAnnouncementPartyExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyDeliveryAnnouncementPartyUpdateCommand dataCompanyDeliveryAnnouncementPartyUpdateCommand = DataCompanyDeliveryAnnouncementPartyUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyDeliveryAnnouncementPartyExWarehouseVO.getVersion(),
						dataCompanyDeliveryAnnouncementPartyWarehouseCommand
				);
				dataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor.execute(dataCompanyDeliveryAnnouncementPartyUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyDeliveryAnnouncementPartyExWarehouseVOSingleResponse = dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyDeliveryAnnouncementPartyExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor) {
		this.dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor = dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyDeliveryAnnouncementPartyCreateCommandExecutor(DataCompanyDeliveryAnnouncementPartyCreateCommandExecutor dataCompanyDeliveryAnnouncementPartyCreateCommandExecutor) {
		this.dataCompanyDeliveryAnnouncementPartyCreateCommandExecutor = dataCompanyDeliveryAnnouncementPartyCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyDeliveryAnnouncementPartyService(IDataCompanyDeliveryAnnouncementPartyService iDataCompanyDeliveryAnnouncementPartyService) {
		this.iDataCompanyDeliveryAnnouncementPartyService = iDataCompanyDeliveryAnnouncementPartyService;
	}
	@Autowired
	public void setDataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor(DataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor dataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor) {
		this.dataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor = dataCompanyDeliveryAnnouncementPartyUpdateCommandExecutor;
	}
}
