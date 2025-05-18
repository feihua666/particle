package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentStatisticCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentStatisticUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentStatisticExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentStatisticCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentStatisticUpdateCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentStatisticExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentStatisticWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentStatisticExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentStatisticService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权专利统计入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPatentStatisticWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPatentStatisticExWarehouseCommandExecutor dataCompanyIprPatentStatisticExWarehouseCommandExecutor;
	private DataCompanyIprPatentStatisticCreateCommandExecutor dataCompanyIprPatentStatisticCreateCommandExecutor;
	private IDataCompanyIprPatentStatisticService iDataCompanyIprPatentStatisticService;
	private DataCompanyIprPatentStatisticUpdateCommandExecutor dataCompanyIprPatentStatisticUpdateCommandExecutor;


	/**
	 * 企业知识产权专利统计入库
	 * @param dataCompanyIprPatentStatisticWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentStatisticExWarehouseVO> warehouse(DataCompanyIprPatentStatisticWarehouseCommand dataCompanyIprPatentStatisticWarehouseCommand) {
		DataCompanyIprPatentStatisticExWarehouseQueryCommand dataCompanyIprPatentStatisticExWarehouseQueryCommand = DataCompanyIprPatentStatisticExWarehouseQueryCommand.create(dataCompanyIprPatentStatisticWarehouseCommand.getCompanyIprPatentId());
		SingleResponse<DataCompanyIprPatentStatisticExWarehouseVO> dataCompanyIprPatentStatisticExWarehouseVOSingleResponse = dataCompanyIprPatentStatisticExWarehouseCommandExecutor.exWarehouse(dataCompanyIprPatentStatisticExWarehouseQueryCommand);
		DataCompanyIprPatentStatisticExWarehouseVO dataCompanyIprPatentStatisticExWarehouseVO = dataCompanyIprPatentStatisticExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPatentStatisticExWarehouseVO == null) {
			DataCompanyIprPatentStatisticCreateCommand dataCompanyIprPatentStatisticCreateCommand = DataCompanyIprPatentStatisticCreateCommand.createByWarehouseCommand(dataCompanyIprPatentStatisticWarehouseCommand);
			dataCompanyIprPatentStatisticCreateCommandExecutor.execute(dataCompanyIprPatentStatisticCreateCommand);
			// 新增后重新查询，返回最新数据
			dataCompanyIprPatentStatisticExWarehouseVOSingleResponse = dataCompanyIprPatentStatisticExWarehouseCommandExecutor.exWarehouse(dataCompanyIprPatentStatisticExWarehouseQueryCommand);
			return dataCompanyIprPatentStatisticExWarehouseVOSingleResponse;
		}else {
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPatentStatisticWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPatentStatisticExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPatentStatisticWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPatentStatisticService.updateLatestHandleAt(dataCompanyIprPatentStatisticExWarehouseVO.getId());
				// 如果没有需要更新的字段，则直接返回
				return dataCompanyIprPatentStatisticExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPatentStatisticUpdateCommand dataCompanyIprPatentStatisticUpdateCommand = DataCompanyIprPatentStatisticUpdateCommand.createByWarehouseCommand(
						dataCompanyIprPatentStatisticExWarehouseVO.getId(),
						dataCompanyIprPatentStatisticExWarehouseVO.getVersion(),
						dataCompanyIprPatentStatisticWarehouseCommand
				);
				dataCompanyIprPatentStatisticUpdateCommandExecutor.execute(dataCompanyIprPatentStatisticUpdateCommand);
			}

			// 更新完成后，新增的情况已经在新增逻辑里面直接返回了，查询返回
			dataCompanyIprPatentStatisticExWarehouseVOSingleResponse = dataCompanyIprPatentStatisticExWarehouseCommandExecutor.exWarehouse(dataCompanyIprPatentStatisticExWarehouseQueryCommand);
			return dataCompanyIprPatentStatisticExWarehouseVOSingleResponse;
		}
	}

	@Autowired
	public void setDataCompanyIprPatentStatisticExWarehouseCommandExecutor(DataCompanyIprPatentStatisticExWarehouseCommandExecutor dataCompanyIprPatentStatisticExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentStatisticExWarehouseCommandExecutor = dataCompanyIprPatentStatisticExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPatentStatisticCreateCommandExecutor(DataCompanyIprPatentStatisticCreateCommandExecutor dataCompanyIprPatentStatisticCreateCommandExecutor) {
		this.dataCompanyIprPatentStatisticCreateCommandExecutor = dataCompanyIprPatentStatisticCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPatentStatisticService(IDataCompanyIprPatentStatisticService iDataCompanyIprPatentStatisticService) {
		this.iDataCompanyIprPatentStatisticService = iDataCompanyIprPatentStatisticService;
	}
	@Autowired
	public void setDataCompanyIprPatentStatisticUpdateCommandExecutor(DataCompanyIprPatentStatisticUpdateCommandExecutor dataCompanyIprPatentStatisticUpdateCommandExecutor) {
		this.dataCompanyIprPatentStatisticUpdateCommandExecutor = dataCompanyIprPatentStatisticUpdateCommandExecutor;
	}
}
