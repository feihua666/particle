package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyPersonCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPersonUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyPersonExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyPersonCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyPersonUpdateCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPersonExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPersonWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPersonExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyPersonService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业个人入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyPersonWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyPersonExWarehouseCommandExecutor dataCompanyPersonExWarehouseCommandExecutor;
	private DataCompanyPersonCreateCommandExecutor dataCompanyPersonCreateCommandExecutor;
	private IDataCompanyPersonService iDataCompanyPersonService;
	private DataCompanyPersonUpdateCommandExecutor dataCompanyPersonUpdateCommandExecutor;


	/**
	 * 企业个人入库
	 * @param dataCompanyPersonWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPersonExWarehouseVO> warehouse(DataCompanyPersonWarehouseCommand dataCompanyPersonWarehouseCommand) {
		DataCompanyPersonExWarehouseQueryCommand dataCompanyPersonExWarehouseQueryCommand = DataCompanyPersonExWarehouseQueryCommand.create(
				null,
				dataCompanyPersonWarehouseCommand.getIdNo(),
				null,
				null,
				null
		);
		SingleResponse<DataCompanyPersonExWarehouseVO> dataCompanyPersonExWarehouseVOSingleResponse = dataCompanyPersonExWarehouseCommandExecutor.exWarehouse(dataCompanyPersonExWarehouseQueryCommand);
		DataCompanyPersonExWarehouseVO dataCompanyPersonExWarehouseVO = dataCompanyPersonExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyPersonExWarehouseVO == null) {
			DataCompanyPersonCreateCommand dataCompanyPersonCreateCommand = DataCompanyPersonCreateCommand.createByWarehouseCommand(dataCompanyPersonWarehouseCommand);
			dataCompanyPersonCreateCommandExecutor.execute(dataCompanyPersonCreateCommand);
			// 新增后重新查询，返回最新数据
			dataCompanyPersonExWarehouseVOSingleResponse = dataCompanyPersonExWarehouseCommandExecutor.exWarehouse(dataCompanyPersonExWarehouseQueryCommand);
			return dataCompanyPersonExWarehouseVOSingleResponse;
		}else {
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyPersonWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyPersonExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyPersonWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyPersonService.updateLatestHandleAt(dataCompanyPersonExWarehouseVO.getId());
				// 如果没有需要更新的字段，则直接返回
				return dataCompanyPersonExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyPersonUpdateCommand dataCompanyPersonUpdateCommand = DataCompanyPersonUpdateCommand.createByWarehouseCommand(
						dataCompanyPersonExWarehouseVO.getId(),
						dataCompanyPersonExWarehouseVO.getVersion(),
						dataCompanyPersonWarehouseCommand
				);
				dataCompanyPersonUpdateCommandExecutor.execute(dataCompanyPersonUpdateCommand);
			}

			// 更新完成后，新增的情况已经在新增逻辑里面直接返回了，查询返回
			dataCompanyPersonExWarehouseVOSingleResponse = dataCompanyPersonExWarehouseCommandExecutor.exWarehouse(dataCompanyPersonExWarehouseQueryCommand);
			return dataCompanyPersonExWarehouseVOSingleResponse;
		}
	}

	@Autowired
	public void setDataCompanyPersonExWarehouseCommandExecutor(DataCompanyPersonExWarehouseCommandExecutor dataCompanyPersonExWarehouseCommandExecutor) {
		this.dataCompanyPersonExWarehouseCommandExecutor = dataCompanyPersonExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyPersonCreateCommandExecutor(DataCompanyPersonCreateCommandExecutor dataCompanyPersonCreateCommandExecutor) {
		this.dataCompanyPersonCreateCommandExecutor = dataCompanyPersonCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyPersonService(IDataCompanyPersonService iDataCompanyPersonService) {
		this.iDataCompanyPersonService = iDataCompanyPersonService;
	}
	@Autowired
	public void setDataCompanyPersonUpdateCommandExecutor(DataCompanyPersonUpdateCommandExecutor dataCompanyPersonUpdateCommandExecutor) {
		this.dataCompanyPersonUpdateCommandExecutor = dataCompanyPersonUpdateCommandExecutor;
	}
}
