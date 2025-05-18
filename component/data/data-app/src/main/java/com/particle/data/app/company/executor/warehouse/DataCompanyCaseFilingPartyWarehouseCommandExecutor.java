package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyCaseFilingPartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCaseFilingPartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCaseFilingPartyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingPartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingPartyUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCaseFilingPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingPartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingPartyExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyCaseFilingPartyService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业立案信息当事人入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyCaseFilingPartyWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyCaseFilingPartyExWarehouseCommandExecutor dataCompanyCaseFilingPartyExWarehouseCommandExecutor;
	private DataCompanyCaseFilingPartyCreateCommandExecutor dataCompanyCaseFilingPartyCreateCommandExecutor;
	private IDataCompanyCaseFilingPartyService iDataCompanyCaseFilingPartyService;
	private DataCompanyCaseFilingPartyUpdateCommandExecutor dataCompanyCaseFilingPartyUpdateCommandExecutor;


	/**
	 * 企业立案信息当事人入库
	 * @param dataCompanyCaseFilingPartyWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingPartyExWarehouseVO> warehouse(DataCompanyCaseFilingPartyWarehouseCommand dataCompanyCaseFilingPartyWarehouseCommand) {
		SingleResponse<DataCompanyCaseFilingPartyExWarehouseVO> dataCompanyCaseFilingPartyExWarehouseVOSingleResponse = null;
		DataCompanyCaseFilingPartyExWarehouseVO dataCompanyCaseFilingPartyExWarehouseVO = null;
        if (dataCompanyCaseFilingPartyWarehouseCommand.getCompanyCaseFilingId() != null) {
			dataCompanyCaseFilingPartyExWarehouseVOSingleResponse = dataCompanyCaseFilingPartyExWarehouseCommandExecutor.exWarehouseByCompanyCaseFilingIdAndDataMd5(dataCompanyCaseFilingPartyWarehouseCommand.getCompanyCaseFilingId(),dataCompanyCaseFilingPartyWarehouseCommand.obtainDataMd5());
		}

		dataCompanyCaseFilingPartyExWarehouseVO = dataCompanyCaseFilingPartyExWarehouseVOSingleResponse == null ? null : dataCompanyCaseFilingPartyExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyCaseFilingPartyExWarehouseVO == null) {
			DataCompanyCaseFilingPartyCreateCommand dataCompanyCaseFilingPartyCreateCommand = DataCompanyCaseFilingPartyCreateCommand.createByWarehouseCommand(dataCompanyCaseFilingPartyWarehouseCommand);
			SingleResponse<DataCompanyCaseFilingPartyVO> dataCompanyCaseFilingPartyVOSingleResponse = dataCompanyCaseFilingPartyCreateCommandExecutor.execute(dataCompanyCaseFilingPartyCreateCommand);
			Long id = dataCompanyCaseFilingPartyVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyCaseFilingPartyExWarehouseVOSingleResponse = dataCompanyCaseFilingPartyExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyCaseFilingPartyExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyCaseFilingPartyExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyCaseFilingPartyWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyCaseFilingPartyExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyCaseFilingPartyWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyCaseFilingPartyService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyCaseFilingPartyExWarehouseVOSingleResponse = dataCompanyCaseFilingPartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyCaseFilingPartyExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyCaseFilingPartyUpdateCommand dataCompanyCaseFilingPartyUpdateCommand = DataCompanyCaseFilingPartyUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyCaseFilingPartyExWarehouseVO.getVersion(),
						dataCompanyCaseFilingPartyWarehouseCommand
				);
				dataCompanyCaseFilingPartyUpdateCommandExecutor.execute(dataCompanyCaseFilingPartyUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyCaseFilingPartyExWarehouseVOSingleResponse = dataCompanyCaseFilingPartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyCaseFilingPartyExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyCaseFilingPartyExWarehouseCommandExecutor(DataCompanyCaseFilingPartyExWarehouseCommandExecutor dataCompanyCaseFilingPartyExWarehouseCommandExecutor) {
		this.dataCompanyCaseFilingPartyExWarehouseCommandExecutor = dataCompanyCaseFilingPartyExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyCaseFilingPartyCreateCommandExecutor(DataCompanyCaseFilingPartyCreateCommandExecutor dataCompanyCaseFilingPartyCreateCommandExecutor) {
		this.dataCompanyCaseFilingPartyCreateCommandExecutor = dataCompanyCaseFilingPartyCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyCaseFilingPartyService(IDataCompanyCaseFilingPartyService iDataCompanyCaseFilingPartyService) {
		this.iDataCompanyCaseFilingPartyService = iDataCompanyCaseFilingPartyService;
	}
	@Autowired
	public void setDataCompanyCaseFilingPartyUpdateCommandExecutor(DataCompanyCaseFilingPartyUpdateCommandExecutor dataCompanyCaseFilingPartyUpdateCommandExecutor) {
		this.dataCompanyCaseFilingPartyUpdateCommandExecutor = dataCompanyCaseFilingPartyUpdateCommandExecutor;
	}
}
