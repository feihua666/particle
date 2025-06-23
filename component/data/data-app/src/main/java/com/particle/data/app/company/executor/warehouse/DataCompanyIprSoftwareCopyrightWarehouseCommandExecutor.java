package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprSoftwareCopyrightCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprSoftwareCopyrightUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprSoftwareCopyrightCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprSoftwareCopyrightUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprSoftwareCopyrightWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprSoftwareCopyrightVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprSoftwareCopyrightService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权软件著作入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprSoftwareCopyrightWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor;
	private DataCompanyIprSoftwareCopyrightCreateCommandExecutor dataCompanyIprSoftwareCopyrightCreateCommandExecutor;
	private IDataCompanyIprSoftwareCopyrightService iDataCompanyIprSoftwareCopyrightService;
	private DataCompanyIprSoftwareCopyrightUpdateCommandExecutor dataCompanyIprSoftwareCopyrightUpdateCommandExecutor;


	/**
	 * 企业知识产权软件著作入库
	 * @param dataCompanyIprSoftwareCopyrightWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> warehouse(DataCompanyIprSoftwareCopyrightWarehouseCommand dataCompanyIprSoftwareCopyrightWarehouseCommand) {
		SingleResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> dataCompanyIprSoftwareCopyrightExWarehouseVOSingleResponse = null;
		DataCompanyIprSoftwareCopyrightExWarehouseVO dataCompanyIprSoftwareCopyrightExWarehouseVO = null;
		String regNo = dataCompanyIprSoftwareCopyrightWarehouseCommand.getRegNo();
		if (StrUtil.isNotEmpty(regNo)) {
			dataCompanyIprSoftwareCopyrightExWarehouseVOSingleResponse = dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor.exWarehouseByRegNo(regNo);
		}

		dataCompanyIprSoftwareCopyrightExWarehouseVO = dataCompanyIprSoftwareCopyrightExWarehouseVOSingleResponse == null ? null : dataCompanyIprSoftwareCopyrightExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprSoftwareCopyrightExWarehouseVO == null) {
			DataCompanyIprSoftwareCopyrightCreateCommand dataCompanyIprSoftwareCopyrightCreateCommand = DataCompanyIprSoftwareCopyrightCreateCommand.createByWarehouseCommand(dataCompanyIprSoftwareCopyrightWarehouseCommand);
			SingleResponse<DataCompanyIprSoftwareCopyrightVO> dataCompanyIprSoftwareCopyrightVOSingleResponse = dataCompanyIprSoftwareCopyrightCreateCommandExecutor.execute(dataCompanyIprSoftwareCopyrightCreateCommand);
			Long id = dataCompanyIprSoftwareCopyrightVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprSoftwareCopyrightExWarehouseVOSingleResponse = dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprSoftwareCopyrightExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprSoftwareCopyrightExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprSoftwareCopyrightWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprSoftwareCopyrightExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprSoftwareCopyrightWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprSoftwareCopyrightService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprSoftwareCopyrightExWarehouseVOSingleResponse = dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprSoftwareCopyrightExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprSoftwareCopyrightUpdateCommand dataCompanyIprSoftwareCopyrightUpdateCommand = DataCompanyIprSoftwareCopyrightUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprSoftwareCopyrightExWarehouseVO.getVersion(),
						dataCompanyIprSoftwareCopyrightWarehouseCommand
				);
				dataCompanyIprSoftwareCopyrightUpdateCommandExecutor.execute(dataCompanyIprSoftwareCopyrightUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprSoftwareCopyrightExWarehouseVOSingleResponse = dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprSoftwareCopyrightExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor(DataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor) {
		this.dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor = dataCompanyIprSoftwareCopyrightExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprSoftwareCopyrightCreateCommandExecutor(DataCompanyIprSoftwareCopyrightCreateCommandExecutor dataCompanyIprSoftwareCopyrightCreateCommandExecutor) {
		this.dataCompanyIprSoftwareCopyrightCreateCommandExecutor = dataCompanyIprSoftwareCopyrightCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprSoftwareCopyrightService(IDataCompanyIprSoftwareCopyrightService iDataCompanyIprSoftwareCopyrightService) {
		this.iDataCompanyIprSoftwareCopyrightService = iDataCompanyIprSoftwareCopyrightService;
	}
	@Autowired
	public void setDataCompanyIprSoftwareCopyrightUpdateCommandExecutor(DataCompanyIprSoftwareCopyrightUpdateCommandExecutor dataCompanyIprSoftwareCopyrightUpdateCommandExecutor) {
		this.dataCompanyIprSoftwareCopyrightUpdateCommandExecutor = dataCompanyIprSoftwareCopyrightUpdateCommandExecutor;
	}
}
