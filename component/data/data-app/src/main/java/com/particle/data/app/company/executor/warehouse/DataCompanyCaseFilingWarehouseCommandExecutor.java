package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyCaseFilingCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCaseFilingUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCaseFilingExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCaseFilingWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyCaseFilingService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业立案信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyCaseFilingWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyCaseFilingExWarehouseCommandExecutor dataCompanyCaseFilingExWarehouseCommandExecutor;
	private DataCompanyCaseFilingCreateCommandExecutor dataCompanyCaseFilingCreateCommandExecutor;
	private IDataCompanyCaseFilingService iDataCompanyCaseFilingService;
	private DataCompanyCaseFilingUpdateCommandExecutor dataCompanyCaseFilingUpdateCommandExecutor;


	/**
	 * 企业立案信息入库
	 * @param dataCompanyCaseFilingWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCaseFilingExWarehouseVO> warehouse(DataCompanyCaseFilingWarehouseCommand dataCompanyCaseFilingWarehouseCommand) {
		SingleResponse<DataCompanyCaseFilingExWarehouseVO> dataCompanyCaseFilingExWarehouseVOSingleResponse = null;
		DataCompanyCaseFilingExWarehouseVO dataCompanyCaseFilingExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyCaseFilingWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyCaseFilingExWarehouseVOSingleResponse = dataCompanyCaseFilingExWarehouseCommandExecutor.exWarehouseByDataMd5(obtainDataMd5);
		}

		dataCompanyCaseFilingExWarehouseVO = dataCompanyCaseFilingExWarehouseVOSingleResponse == null ? null : dataCompanyCaseFilingExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyCaseFilingExWarehouseVO == null) {
			DataCompanyCaseFilingCreateCommand dataCompanyCaseFilingCreateCommand = DataCompanyCaseFilingCreateCommand.createByWarehouseCommand(dataCompanyCaseFilingWarehouseCommand);
			SingleResponse<DataCompanyCaseFilingVO> dataCompanyCaseFilingVOSingleResponse = dataCompanyCaseFilingCreateCommandExecutor.execute(dataCompanyCaseFilingCreateCommand);
			Long id = dataCompanyCaseFilingVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyCaseFilingExWarehouseVOSingleResponse = dataCompanyCaseFilingExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyCaseFilingExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyCaseFilingExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyCaseFilingWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyCaseFilingExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyCaseFilingWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyCaseFilingService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyCaseFilingExWarehouseVOSingleResponse = dataCompanyCaseFilingExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyCaseFilingExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyCaseFilingUpdateCommand dataCompanyCaseFilingUpdateCommand = DataCompanyCaseFilingUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyCaseFilingExWarehouseVO.getVersion(),
						dataCompanyCaseFilingWarehouseCommand
				);
				dataCompanyCaseFilingUpdateCommandExecutor.execute(dataCompanyCaseFilingUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyCaseFilingExWarehouseVOSingleResponse = dataCompanyCaseFilingExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyCaseFilingExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyCaseFilingExWarehouseCommandExecutor(DataCompanyCaseFilingExWarehouseCommandExecutor dataCompanyCaseFilingExWarehouseCommandExecutor) {
		this.dataCompanyCaseFilingExWarehouseCommandExecutor = dataCompanyCaseFilingExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyCaseFilingCreateCommandExecutor(DataCompanyCaseFilingCreateCommandExecutor dataCompanyCaseFilingCreateCommandExecutor) {
		this.dataCompanyCaseFilingCreateCommandExecutor = dataCompanyCaseFilingCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyCaseFilingService(IDataCompanyCaseFilingService iDataCompanyCaseFilingService) {
		this.iDataCompanyCaseFilingService = iDataCompanyCaseFilingService;
	}
	@Autowired
	public void setDataCompanyCaseFilingUpdateCommandExecutor(DataCompanyCaseFilingUpdateCommandExecutor dataCompanyCaseFilingUpdateCommandExecutor) {
		this.dataCompanyCaseFilingUpdateCommandExecutor = dataCompanyCaseFilingUpdateCommandExecutor;
	}
}
