package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyStatisticCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyStatisticUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyStatisticExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyStatisticCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyStatisticUpdateCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyStatisticExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyStatisticWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyStatisticExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyStatisticService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业统计入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyStatisticWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyStatisticExWarehouseCommandExecutor dataCompanyStatisticExWarehouseCommandExecutor;
	private DataCompanyStatisticCreateCommandExecutor dataCompanyStatisticCreateCommandExecutor;
	private IDataCompanyStatisticService iDataCompanyStatisticService;
	private DataCompanyStatisticUpdateCommandExecutor dataCompanyStatisticUpdateCommandExecutor;


	/**
	 * 企业统计入库
	 * @param dataCompanyStatisticWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyStatisticExWarehouseVO> warehouse(DataCompanyStatisticWarehouseCommand dataCompanyStatisticWarehouseCommand) {
		DataCompanyStatisticExWarehouseQueryCommand dataCompanyStatisticExWarehouseQueryCommand = DataCompanyStatisticExWarehouseQueryCommand.create(dataCompanyStatisticWarehouseCommand.getCompanyId());
		SingleResponse<DataCompanyStatisticExWarehouseVO> dataCompanyStatisticExWarehouseVOSingleResponse = dataCompanyStatisticExWarehouseCommandExecutor.exWarehouse(dataCompanyStatisticExWarehouseQueryCommand);
		DataCompanyStatisticExWarehouseVO dataCompanyStatisticExWarehouseVO = dataCompanyStatisticExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyStatisticExWarehouseVO == null) {
			DataCompanyStatisticCreateCommand dataCompanyStatisticCreateCommand = DataCompanyStatisticCreateCommand.createByWarehouseCommand(dataCompanyStatisticWarehouseCommand);
			dataCompanyStatisticCreateCommandExecutor.execute(dataCompanyStatisticCreateCommand);
			// 新增后重新查询，返回最新数据
			dataCompanyStatisticExWarehouseVOSingleResponse = dataCompanyStatisticExWarehouseCommandExecutor.exWarehouse(dataCompanyStatisticExWarehouseQueryCommand);
			return dataCompanyStatisticExWarehouseVOSingleResponse;
		}else {
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyStatisticWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyStatisticExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyStatisticWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyStatisticService.updateLatestHandleAt(dataCompanyStatisticExWarehouseVO.getId());
				// 如果没有需要更新的字段，则直接返回
				return dataCompanyStatisticExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyStatisticUpdateCommand dataCompanyStatisticUpdateCommand = DataCompanyStatisticUpdateCommand.createByWarehouseCommand(
						dataCompanyStatisticExWarehouseVO.getId(),
						dataCompanyStatisticExWarehouseVO.getVersion(),
						dataCompanyStatisticWarehouseCommand
				);
				dataCompanyStatisticUpdateCommandExecutor.execute(dataCompanyStatisticUpdateCommand);
			}

			// 更新完成后，新增的情况已经在新增逻辑里面直接返回了，查询返回
			dataCompanyStatisticExWarehouseVOSingleResponse = dataCompanyStatisticExWarehouseCommandExecutor.exWarehouse(dataCompanyStatisticExWarehouseQueryCommand);
			return dataCompanyStatisticExWarehouseVOSingleResponse;
		}
	}

	@Autowired
	public void setDataCompanyStatisticExWarehouseCommandExecutor(DataCompanyStatisticExWarehouseCommandExecutor dataCompanyStatisticExWarehouseCommandExecutor) {
		this.dataCompanyStatisticExWarehouseCommandExecutor = dataCompanyStatisticExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyStatisticCreateCommandExecutor(DataCompanyStatisticCreateCommandExecutor dataCompanyStatisticCreateCommandExecutor) {
		this.dataCompanyStatisticCreateCommandExecutor = dataCompanyStatisticCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyStatisticService(IDataCompanyStatisticService iDataCompanyStatisticService) {
		this.iDataCompanyStatisticService = iDataCompanyStatisticService;
	}
	@Autowired
	public void setDataCompanyStatisticUpdateCommandExecutor(DataCompanyStatisticUpdateCommandExecutor dataCompanyStatisticUpdateCommandExecutor) {
		this.dataCompanyStatisticUpdateCommandExecutor = dataCompanyStatisticUpdateCommandExecutor;
	}
}
