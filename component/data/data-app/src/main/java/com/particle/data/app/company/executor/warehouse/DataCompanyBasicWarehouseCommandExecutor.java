package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyBasicCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyBasicUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyBasicExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyBasicCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyBasicUpdateCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyBasicExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyBasicWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyBasicExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyBasicService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业基本信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyBasicWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyBasicExWarehouseCommandExecutor dataCompanyBasicExWarehouseCommandExecutor;
	private DataCompanyBasicCreateCommandExecutor dataCompanyBasicCreateCommandExecutor;
	private IDataCompanyBasicService iDataCompanyBasicService;
	private DataCompanyBasicUpdateCommandExecutor dataCompanyBasicUpdateCommandExecutor;


	/**
	 * 企业基本信息入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyBasicExWarehouseVO> warehouse(DataCompanyBasicWarehouseCommand dataCompanyBasicWarehouseCommand) {
		DataCompanyBasicExWarehouseQueryCommand dataCompanyBasicExWarehouseQueryCommand = DataCompanyBasicExWarehouseQueryCommand.create(dataCompanyBasicWarehouseCommand.getCompanyId());
		SingleResponse<DataCompanyBasicExWarehouseVO> dataCompanyBasicExWarehouseVOSingleResponse = dataCompanyBasicExWarehouseCommandExecutor.exWarehouse(dataCompanyBasicExWarehouseQueryCommand);
		DataCompanyBasicExWarehouseVO dataCompanyBasicExWarehouseVO = dataCompanyBasicExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyBasicExWarehouseVO == null) {
			DataCompanyBasicCreateCommand dataCompanyBasicCreateCommand = DataCompanyBasicCreateCommand.createByWarehouseCommand(dataCompanyBasicWarehouseCommand);
			dataCompanyBasicCreateCommandExecutor.execute(dataCompanyBasicCreateCommand);
			// 新增后重新查询，返回最新数据
			dataCompanyBasicExWarehouseVOSingleResponse = dataCompanyBasicExWarehouseCommandExecutor.exWarehouse(dataCompanyBasicExWarehouseQueryCommand);
			return dataCompanyBasicExWarehouseVOSingleResponse;
		}else {
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyBasicWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyBasicExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyBasicWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyBasicService.updateLatestHandleAt(dataCompanyBasicExWarehouseVO.getId());
				// 如果没有需要更新的字段，则直接返回
				return dataCompanyBasicExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyBasicUpdateCommand dataCompanyBasicUpdateCommand = DataCompanyBasicUpdateCommand.createByWarehouseCommand(
						dataCompanyBasicExWarehouseVO.getId(),
						dataCompanyBasicExWarehouseVO.getVersion(),
						dataCompanyBasicWarehouseCommand
				);
				dataCompanyBasicUpdateCommandExecutor.execute(dataCompanyBasicUpdateCommand);
			}

			// 更新完成后，新增的情况已经在新增逻辑里面直接返回了，查询返回
			dataCompanyBasicExWarehouseVOSingleResponse = dataCompanyBasicExWarehouseCommandExecutor.exWarehouse(dataCompanyBasicExWarehouseQueryCommand);
			return dataCompanyBasicExWarehouseVOSingleResponse;
		}
	}

	@Autowired
	public void setDataCompanyBasicExWarehouseCommandExecutor(DataCompanyBasicExWarehouseCommandExecutor dataCompanyBasicExWarehouseCommandExecutor) {
		this.dataCompanyBasicExWarehouseCommandExecutor = dataCompanyBasicExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyBasicCreateCommandExecutor(DataCompanyBasicCreateCommandExecutor dataCompanyBasicCreateCommandExecutor) {
		this.dataCompanyBasicCreateCommandExecutor = dataCompanyBasicCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyBasicService(IDataCompanyBasicService iDataCompanyBasicService) {
		this.iDataCompanyBasicService = iDataCompanyBasicService;
	}
	@Autowired
	public void setDataCompanyBasicUpdateCommandExecutor(DataCompanyBasicUpdateCommandExecutor dataCompanyBasicUpdateCommandExecutor) {
		this.dataCompanyBasicUpdateCommandExecutor = dataCompanyBasicUpdateCommandExecutor;
	}
}
