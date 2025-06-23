package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyPrimeStaffPositionCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPrimeStaffPositionUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffPositionCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffPositionUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPrimeStaffPositionWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffPositionVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffPositionService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业主要人员职位入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyPrimeStaffPositionWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyPrimeStaffPositionExWarehouseCommandExecutor dataCompanyPrimeStaffPositionExWarehouseCommandExecutor;
	private DataCompanyPrimeStaffPositionCreateCommandExecutor dataCompanyPrimeStaffPositionCreateCommandExecutor;
	private IDataCompanyPrimeStaffPositionService iDataCompanyPrimeStaffPositionService;
	private DataCompanyPrimeStaffPositionUpdateCommandExecutor dataCompanyPrimeStaffPositionUpdateCommandExecutor;


	/**
	 * 企业主要人员职位入库
	 * @param dataCompanyPrimeStaffPositionWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffPositionExWarehouseVO> warehouse(DataCompanyPrimeStaffPositionWarehouseCommand dataCompanyPrimeStaffPositionWarehouseCommand) {
		SingleResponse<DataCompanyPrimeStaffPositionExWarehouseVO> dataCompanyPrimeStaffPositionExWarehouseVOSingleResponse = null;
		DataCompanyPrimeStaffPositionExWarehouseVO dataCompanyPrimeStaffPositionExWarehouseVO = null;
		String positionName = dataCompanyPrimeStaffPositionWarehouseCommand.getPositionName();
		if (StrUtil.isNotEmpty(positionName)) {
			dataCompanyPrimeStaffPositionExWarehouseVOSingleResponse = dataCompanyPrimeStaffPositionExWarehouseCommandExecutor.exWarehouseByCompanyPrimeStaffIdAndPositionName(dataCompanyPrimeStaffPositionWarehouseCommand.getCompanyPrimeStaffId(),positionName);
		}

		dataCompanyPrimeStaffPositionExWarehouseVO = dataCompanyPrimeStaffPositionExWarehouseVOSingleResponse == null ? null : dataCompanyPrimeStaffPositionExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyPrimeStaffPositionExWarehouseVO == null) {
			DataCompanyPrimeStaffPositionCreateCommand dataCompanyPrimeStaffPositionCreateCommand = DataCompanyPrimeStaffPositionCreateCommand.createByWarehouseCommand(dataCompanyPrimeStaffPositionWarehouseCommand);
			SingleResponse<DataCompanyPrimeStaffPositionVO> dataCompanyPrimeStaffPositionVOSingleResponse = dataCompanyPrimeStaffPositionCreateCommandExecutor.execute(dataCompanyPrimeStaffPositionCreateCommand);
			Long id = dataCompanyPrimeStaffPositionVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyPrimeStaffPositionExWarehouseVOSingleResponse = dataCompanyPrimeStaffPositionExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyPrimeStaffPositionExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyPrimeStaffPositionExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyPrimeStaffPositionWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyPrimeStaffPositionExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyPrimeStaffPositionWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyPrimeStaffPositionService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyPrimeStaffPositionExWarehouseVOSingleResponse = dataCompanyPrimeStaffPositionExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyPrimeStaffPositionExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyPrimeStaffPositionUpdateCommand dataCompanyPrimeStaffPositionUpdateCommand = DataCompanyPrimeStaffPositionUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyPrimeStaffPositionExWarehouseVO.getVersion(),
						dataCompanyPrimeStaffPositionWarehouseCommand
				);
				dataCompanyPrimeStaffPositionUpdateCommandExecutor.execute(dataCompanyPrimeStaffPositionUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyPrimeStaffPositionExWarehouseVOSingleResponse = dataCompanyPrimeStaffPositionExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyPrimeStaffPositionExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyPrimeStaffPositionExWarehouseCommandExecutor(DataCompanyPrimeStaffPositionExWarehouseCommandExecutor dataCompanyPrimeStaffPositionExWarehouseCommandExecutor) {
		this.dataCompanyPrimeStaffPositionExWarehouseCommandExecutor = dataCompanyPrimeStaffPositionExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyPrimeStaffPositionCreateCommandExecutor(DataCompanyPrimeStaffPositionCreateCommandExecutor dataCompanyPrimeStaffPositionCreateCommandExecutor) {
		this.dataCompanyPrimeStaffPositionCreateCommandExecutor = dataCompanyPrimeStaffPositionCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyPrimeStaffPositionService(IDataCompanyPrimeStaffPositionService iDataCompanyPrimeStaffPositionService) {
		this.iDataCompanyPrimeStaffPositionService = iDataCompanyPrimeStaffPositionService;
	}
	@Autowired
	public void setDataCompanyPrimeStaffPositionUpdateCommandExecutor(DataCompanyPrimeStaffPositionUpdateCommandExecutor dataCompanyPrimeStaffPositionUpdateCommandExecutor) {
		this.dataCompanyPrimeStaffPositionUpdateCommandExecutor = dataCompanyPrimeStaffPositionUpdateCommandExecutor;
	}
}
