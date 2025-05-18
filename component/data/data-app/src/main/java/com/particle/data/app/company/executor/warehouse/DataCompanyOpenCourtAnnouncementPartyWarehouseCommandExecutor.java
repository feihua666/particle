package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementPartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementPartyUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyOpenCourtAnnouncementPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementPartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementPartyExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementPartyService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业开庭公告当事人入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementPartyWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor;
	private DataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor dataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor;
	private IDataCompanyOpenCourtAnnouncementPartyService iDataCompanyOpenCourtAnnouncementPartyService;
	private DataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor dataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor;


	/**
	 * 企业开庭公告当事人入库
	 * @param dataCompanyOpenCourtAnnouncementPartyWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> warehouse(DataCompanyOpenCourtAnnouncementPartyWarehouseCommand dataCompanyOpenCourtAnnouncementPartyWarehouseCommand) {
		SingleResponse<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> dataCompanyOpenCourtAnnouncementPartyExWarehouseVOSingleResponse = null;
		DataCompanyOpenCourtAnnouncementPartyExWarehouseVO dataCompanyOpenCourtAnnouncementPartyExWarehouseVO = null;
        if (dataCompanyOpenCourtAnnouncementPartyWarehouseCommand.getCompanyOpenCourtAnnouncementId() != null) {
			dataCompanyOpenCourtAnnouncementPartyExWarehouseVOSingleResponse = dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor.exWarehouseByCompanyOpenCourtAnnouncementIdAndDataMd5(dataCompanyOpenCourtAnnouncementPartyWarehouseCommand.getCompanyOpenCourtAnnouncementId(),dataCompanyOpenCourtAnnouncementPartyWarehouseCommand.obtainDataMd5());
		}

		dataCompanyOpenCourtAnnouncementPartyExWarehouseVO = dataCompanyOpenCourtAnnouncementPartyExWarehouseVOSingleResponse == null ? null : dataCompanyOpenCourtAnnouncementPartyExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyOpenCourtAnnouncementPartyExWarehouseVO == null) {
			DataCompanyOpenCourtAnnouncementPartyCreateCommand dataCompanyOpenCourtAnnouncementPartyCreateCommand = DataCompanyOpenCourtAnnouncementPartyCreateCommand.createByWarehouseCommand(dataCompanyOpenCourtAnnouncementPartyWarehouseCommand);
			SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> dataCompanyOpenCourtAnnouncementPartyVOSingleResponse = dataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor.execute(dataCompanyOpenCourtAnnouncementPartyCreateCommand);
			Long id = dataCompanyOpenCourtAnnouncementPartyVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyOpenCourtAnnouncementPartyExWarehouseVOSingleResponse = dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyOpenCourtAnnouncementPartyExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyOpenCourtAnnouncementPartyExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyOpenCourtAnnouncementPartyWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyOpenCourtAnnouncementPartyExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyOpenCourtAnnouncementPartyWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyOpenCourtAnnouncementPartyService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyOpenCourtAnnouncementPartyExWarehouseVOSingleResponse = dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyOpenCourtAnnouncementPartyExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyOpenCourtAnnouncementPartyUpdateCommand dataCompanyOpenCourtAnnouncementPartyUpdateCommand = DataCompanyOpenCourtAnnouncementPartyUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyOpenCourtAnnouncementPartyExWarehouseVO.getVersion(),
						dataCompanyOpenCourtAnnouncementPartyWarehouseCommand
				);
				dataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor.execute(dataCompanyOpenCourtAnnouncementPartyUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyOpenCourtAnnouncementPartyExWarehouseVOSingleResponse = dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyOpenCourtAnnouncementPartyExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor) {
		this.dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor = dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor(DataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor dataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor) {
		this.dataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor = dataCompanyOpenCourtAnnouncementPartyCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyOpenCourtAnnouncementPartyService(IDataCompanyOpenCourtAnnouncementPartyService iDataCompanyOpenCourtAnnouncementPartyService) {
		this.iDataCompanyOpenCourtAnnouncementPartyService = iDataCompanyOpenCourtAnnouncementPartyService;
	}
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor(DataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor dataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor) {
		this.dataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor = dataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor;
	}
}
