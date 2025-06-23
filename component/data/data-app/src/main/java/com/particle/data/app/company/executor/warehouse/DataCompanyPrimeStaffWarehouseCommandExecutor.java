package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyPrimeStaffCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPrimeStaffUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyPrimeStaffExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPrimeStaffWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业主要人员入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyPrimeStaffWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyPrimeStaffExWarehouseCommandExecutor dataCompanyPrimeStaffExWarehouseCommandExecutor;
	private DataCompanyPrimeStaffCreateCommandExecutor dataCompanyPrimeStaffCreateCommandExecutor;
	private IDataCompanyPrimeStaffService iDataCompanyPrimeStaffService;
	private DataCompanyPrimeStaffUpdateCommandExecutor dataCompanyPrimeStaffUpdateCommandExecutor;


	/**
	 * 企业主要人员入库
	 * @param dataCompanyPrimeStaffWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffExWarehouseVO> warehouse(DataCompanyPrimeStaffWarehouseCommand dataCompanyPrimeStaffWarehouseCommand) {
		SingleResponse<DataCompanyPrimeStaffExWarehouseVO> dataCompanyPrimeStaffExWarehouseVOSingleResponse = null;
		DataCompanyPrimeStaffExWarehouseVO dataCompanyPrimeStaffExWarehouseVO = null;
		String staffName = dataCompanyPrimeStaffWarehouseCommand.getStaffName();
		if (StrUtil.isNotEmpty(staffName)) {
			dataCompanyPrimeStaffExWarehouseVOSingleResponse = dataCompanyPrimeStaffExWarehouseCommandExecutor.exWarehouseByCompanyIdAndStaffName(dataCompanyPrimeStaffWarehouseCommand.getCompanyId(),staffName);
		}

		dataCompanyPrimeStaffExWarehouseVO = dataCompanyPrimeStaffExWarehouseVOSingleResponse == null ? null : dataCompanyPrimeStaffExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyPrimeStaffExWarehouseVO == null) {
			DataCompanyPrimeStaffCreateCommand dataCompanyPrimeStaffCreateCommand = DataCompanyPrimeStaffCreateCommand.createByWarehouseCommand(dataCompanyPrimeStaffWarehouseCommand);
			SingleResponse<DataCompanyPrimeStaffVO> dataCompanyPrimeStaffVOSingleResponse = dataCompanyPrimeStaffCreateCommandExecutor.execute(dataCompanyPrimeStaffCreateCommand);
			Long id = dataCompanyPrimeStaffVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyPrimeStaffExWarehouseVOSingleResponse = dataCompanyPrimeStaffExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyPrimeStaffExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyPrimeStaffExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyPrimeStaffWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyPrimeStaffExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyPrimeStaffWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyPrimeStaffService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyPrimeStaffExWarehouseVOSingleResponse = dataCompanyPrimeStaffExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyPrimeStaffExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyPrimeStaffUpdateCommand dataCompanyPrimeStaffUpdateCommand = DataCompanyPrimeStaffUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyPrimeStaffExWarehouseVO.getVersion(),
						dataCompanyPrimeStaffWarehouseCommand
				);
				dataCompanyPrimeStaffUpdateCommandExecutor.execute(dataCompanyPrimeStaffUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyPrimeStaffExWarehouseVOSingleResponse = dataCompanyPrimeStaffExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyPrimeStaffExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyPrimeStaffExWarehouseCommandExecutor(DataCompanyPrimeStaffExWarehouseCommandExecutor dataCompanyPrimeStaffExWarehouseCommandExecutor) {
		this.dataCompanyPrimeStaffExWarehouseCommandExecutor = dataCompanyPrimeStaffExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyPrimeStaffCreateCommandExecutor(DataCompanyPrimeStaffCreateCommandExecutor dataCompanyPrimeStaffCreateCommandExecutor) {
		this.dataCompanyPrimeStaffCreateCommandExecutor = dataCompanyPrimeStaffCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyPrimeStaffService(IDataCompanyPrimeStaffService iDataCompanyPrimeStaffService) {
		this.iDataCompanyPrimeStaffService = iDataCompanyPrimeStaffService;
	}
	@Autowired
	public void setDataCompanyPrimeStaffUpdateCommandExecutor(DataCompanyPrimeStaffUpdateCommandExecutor dataCompanyPrimeStaffUpdateCommandExecutor) {
		this.dataCompanyPrimeStaffUpdateCommandExecutor = dataCompanyPrimeStaffUpdateCommandExecutor;
	}
}
