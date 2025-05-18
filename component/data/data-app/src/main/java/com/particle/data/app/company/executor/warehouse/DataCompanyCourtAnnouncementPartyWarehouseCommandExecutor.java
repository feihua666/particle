package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementPartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementPartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementPartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementPartyUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementPartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementPartyService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业法院公告当事人入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementPartyWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor;
	private DataCompanyCourtAnnouncementPartyCreateCommandExecutor dataCompanyCourtAnnouncementPartyCreateCommandExecutor;
	private IDataCompanyCourtAnnouncementPartyService iDataCompanyCourtAnnouncementPartyService;
	private DataCompanyCourtAnnouncementPartyUpdateCommandExecutor dataCompanyCourtAnnouncementPartyUpdateCommandExecutor;


	/**
	 * 企业法院公告当事人入库
	 * @param dataCompanyCourtAnnouncementPartyWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementPartyExWarehouseVO> warehouse(DataCompanyCourtAnnouncementPartyWarehouseCommand dataCompanyCourtAnnouncementPartyWarehouseCommand) {
		SingleResponse<DataCompanyCourtAnnouncementPartyExWarehouseVO> dataCompanyCourtAnnouncementPartyExWarehouseVOSingleResponse = null;
		DataCompanyCourtAnnouncementPartyExWarehouseVO dataCompanyCourtAnnouncementPartyExWarehouseVO = null;
        if (dataCompanyCourtAnnouncementPartyWarehouseCommand.getCompanyCourtAnnouncementId() != null) {
			dataCompanyCourtAnnouncementPartyExWarehouseVOSingleResponse = dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor.exWarehouseByCompanyCourtAnnouncementIdAndDataMd5(dataCompanyCourtAnnouncementPartyWarehouseCommand.getCompanyCourtAnnouncementId(),dataCompanyCourtAnnouncementPartyWarehouseCommand.obtainDataMd5());
		}

		dataCompanyCourtAnnouncementPartyExWarehouseVO = dataCompanyCourtAnnouncementPartyExWarehouseVOSingleResponse == null ? null : dataCompanyCourtAnnouncementPartyExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyCourtAnnouncementPartyExWarehouseVO == null) {
			DataCompanyCourtAnnouncementPartyCreateCommand dataCompanyCourtAnnouncementPartyCreateCommand = DataCompanyCourtAnnouncementPartyCreateCommand.createByWarehouseCommand(dataCompanyCourtAnnouncementPartyWarehouseCommand);
			SingleResponse<DataCompanyCourtAnnouncementPartyVO> dataCompanyCourtAnnouncementPartyVOSingleResponse = dataCompanyCourtAnnouncementPartyCreateCommandExecutor.execute(dataCompanyCourtAnnouncementPartyCreateCommand);
			Long id = dataCompanyCourtAnnouncementPartyVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyCourtAnnouncementPartyExWarehouseVOSingleResponse = dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyCourtAnnouncementPartyExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyCourtAnnouncementPartyExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyCourtAnnouncementPartyWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyCourtAnnouncementPartyExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyCourtAnnouncementPartyWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyCourtAnnouncementPartyService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyCourtAnnouncementPartyExWarehouseVOSingleResponse = dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyCourtAnnouncementPartyExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyCourtAnnouncementPartyUpdateCommand dataCompanyCourtAnnouncementPartyUpdateCommand = DataCompanyCourtAnnouncementPartyUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyCourtAnnouncementPartyExWarehouseVO.getVersion(),
						dataCompanyCourtAnnouncementPartyWarehouseCommand
				);
				dataCompanyCourtAnnouncementPartyUpdateCommandExecutor.execute(dataCompanyCourtAnnouncementPartyUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyCourtAnnouncementPartyExWarehouseVOSingleResponse = dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyCourtAnnouncementPartyExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor(DataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor) {
		this.dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor = dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyCourtAnnouncementPartyCreateCommandExecutor(DataCompanyCourtAnnouncementPartyCreateCommandExecutor dataCompanyCourtAnnouncementPartyCreateCommandExecutor) {
		this.dataCompanyCourtAnnouncementPartyCreateCommandExecutor = dataCompanyCourtAnnouncementPartyCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyCourtAnnouncementPartyService(IDataCompanyCourtAnnouncementPartyService iDataCompanyCourtAnnouncementPartyService) {
		this.iDataCompanyCourtAnnouncementPartyService = iDataCompanyCourtAnnouncementPartyService;
	}
	@Autowired
	public void setDataCompanyCourtAnnouncementPartyUpdateCommandExecutor(DataCompanyCourtAnnouncementPartyUpdateCommandExecutor dataCompanyCourtAnnouncementPartyUpdateCommandExecutor) {
		this.dataCompanyCourtAnnouncementPartyUpdateCommandExecutor = dataCompanyCourtAnnouncementPartyUpdateCommandExecutor;
	}
}
