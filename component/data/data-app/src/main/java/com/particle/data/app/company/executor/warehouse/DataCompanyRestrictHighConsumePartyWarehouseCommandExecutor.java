package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyRestrictHighConsumePartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyRestrictHighConsumePartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumePartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumePartyUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyRestrictHighConsumePartyWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumePartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumePartyExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyRestrictHighConsumePartyService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业限制高消费当事人入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumePartyWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor;
	private DataCompanyRestrictHighConsumePartyCreateCommandExecutor dataCompanyRestrictHighConsumePartyCreateCommandExecutor;
	private IDataCompanyRestrictHighConsumePartyService iDataCompanyRestrictHighConsumePartyService;
	private DataCompanyRestrictHighConsumePartyUpdateCommandExecutor dataCompanyRestrictHighConsumePartyUpdateCommandExecutor;


	/**
	 * 企业限制高消费当事人入库
	 * @param dataCompanyRestrictHighConsumePartyWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumePartyExWarehouseVO> warehouse(DataCompanyRestrictHighConsumePartyWarehouseCommand dataCompanyRestrictHighConsumePartyWarehouseCommand) {
		SingleResponse<DataCompanyRestrictHighConsumePartyExWarehouseVO> dataCompanyRestrictHighConsumePartyExWarehouseVOSingleResponse = null;
		DataCompanyRestrictHighConsumePartyExWarehouseVO dataCompanyRestrictHighConsumePartyExWarehouseVO = null;
        if (dataCompanyRestrictHighConsumePartyWarehouseCommand.getCompanyRestrictHighConsumeId() != null) {
			dataCompanyRestrictHighConsumePartyExWarehouseVOSingleResponse = dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor.exWarehouseByCompanyRestrictHighConsumeIdAndDataMd5(dataCompanyRestrictHighConsumePartyWarehouseCommand.getCompanyRestrictHighConsumeId(),dataCompanyRestrictHighConsumePartyWarehouseCommand.obtainDataMd5());
		}

		dataCompanyRestrictHighConsumePartyExWarehouseVO = dataCompanyRestrictHighConsumePartyExWarehouseVOSingleResponse == null ? null : dataCompanyRestrictHighConsumePartyExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyRestrictHighConsumePartyExWarehouseVO == null) {
			DataCompanyRestrictHighConsumePartyCreateCommand dataCompanyRestrictHighConsumePartyCreateCommand = DataCompanyRestrictHighConsumePartyCreateCommand.createByWarehouseCommand(dataCompanyRestrictHighConsumePartyWarehouseCommand);
			SingleResponse<DataCompanyRestrictHighConsumePartyVO> dataCompanyRestrictHighConsumePartyVOSingleResponse = dataCompanyRestrictHighConsumePartyCreateCommandExecutor.execute(dataCompanyRestrictHighConsumePartyCreateCommand);
			Long id = dataCompanyRestrictHighConsumePartyVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyRestrictHighConsumePartyExWarehouseVOSingleResponse = dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyRestrictHighConsumePartyExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyRestrictHighConsumePartyExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyRestrictHighConsumePartyWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyRestrictHighConsumePartyExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyRestrictHighConsumePartyWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyRestrictHighConsumePartyService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyRestrictHighConsumePartyExWarehouseVOSingleResponse = dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyRestrictHighConsumePartyExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyRestrictHighConsumePartyUpdateCommand dataCompanyRestrictHighConsumePartyUpdateCommand = DataCompanyRestrictHighConsumePartyUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyRestrictHighConsumePartyExWarehouseVO.getVersion(),
						dataCompanyRestrictHighConsumePartyWarehouseCommand
				);
				dataCompanyRestrictHighConsumePartyUpdateCommandExecutor.execute(dataCompanyRestrictHighConsumePartyUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyRestrictHighConsumePartyExWarehouseVOSingleResponse = dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyRestrictHighConsumePartyExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor(DataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor) {
		this.dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor = dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyRestrictHighConsumePartyCreateCommandExecutor(DataCompanyRestrictHighConsumePartyCreateCommandExecutor dataCompanyRestrictHighConsumePartyCreateCommandExecutor) {
		this.dataCompanyRestrictHighConsumePartyCreateCommandExecutor = dataCompanyRestrictHighConsumePartyCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyRestrictHighConsumePartyService(IDataCompanyRestrictHighConsumePartyService iDataCompanyRestrictHighConsumePartyService) {
		this.iDataCompanyRestrictHighConsumePartyService = iDataCompanyRestrictHighConsumePartyService;
	}
	@Autowired
	public void setDataCompanyRestrictHighConsumePartyUpdateCommandExecutor(DataCompanyRestrictHighConsumePartyUpdateCommandExecutor dataCompanyRestrictHighConsumePartyUpdateCommandExecutor) {
		this.dataCompanyRestrictHighConsumePartyUpdateCommandExecutor = dataCompanyRestrictHighConsumePartyUpdateCommandExecutor;
	}
}
