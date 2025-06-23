package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprWorkCopyrightCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprWorkCopyrightUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprWorkCopyrightCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprWorkCopyrightUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprWorkCopyrightWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprWorkCopyrightVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprWorkCopyrightService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权作品著作入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprWorkCopyrightWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprWorkCopyrightExWarehouseCommandExecutor dataCompanyIprWorkCopyrightExWarehouseCommandExecutor;
	private DataCompanyIprWorkCopyrightCreateCommandExecutor dataCompanyIprWorkCopyrightCreateCommandExecutor;
	private IDataCompanyIprWorkCopyrightService iDataCompanyIprWorkCopyrightService;
	private DataCompanyIprWorkCopyrightUpdateCommandExecutor dataCompanyIprWorkCopyrightUpdateCommandExecutor;


	/**
	 * 企业知识产权作品著作入库
	 * @param dataCompanyIprWorkCopyrightWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprWorkCopyrightExWarehouseVO> warehouse(DataCompanyIprWorkCopyrightWarehouseCommand dataCompanyIprWorkCopyrightWarehouseCommand) {
		SingleResponse<DataCompanyIprWorkCopyrightExWarehouseVO> dataCompanyIprWorkCopyrightExWarehouseVOSingleResponse = null;
		DataCompanyIprWorkCopyrightExWarehouseVO dataCompanyIprWorkCopyrightExWarehouseVO = null;
		String regNo = dataCompanyIprWorkCopyrightWarehouseCommand.getRegNo();
		if (StrUtil.isNotEmpty(regNo)) {
			dataCompanyIprWorkCopyrightExWarehouseVOSingleResponse = dataCompanyIprWorkCopyrightExWarehouseCommandExecutor.exWarehouseByRegNo(regNo);
		}

		dataCompanyIprWorkCopyrightExWarehouseVO = dataCompanyIprWorkCopyrightExWarehouseVOSingleResponse == null ? null : dataCompanyIprWorkCopyrightExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprWorkCopyrightExWarehouseVO == null) {
			DataCompanyIprWorkCopyrightCreateCommand dataCompanyIprWorkCopyrightCreateCommand = DataCompanyIprWorkCopyrightCreateCommand.createByWarehouseCommand(dataCompanyIprWorkCopyrightWarehouseCommand);
			SingleResponse<DataCompanyIprWorkCopyrightVO> dataCompanyIprWorkCopyrightVOSingleResponse = dataCompanyIprWorkCopyrightCreateCommandExecutor.execute(dataCompanyIprWorkCopyrightCreateCommand);
			Long id = dataCompanyIprWorkCopyrightVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprWorkCopyrightExWarehouseVOSingleResponse = dataCompanyIprWorkCopyrightExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprWorkCopyrightExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprWorkCopyrightExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprWorkCopyrightWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprWorkCopyrightExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprWorkCopyrightWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprWorkCopyrightService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprWorkCopyrightExWarehouseVOSingleResponse = dataCompanyIprWorkCopyrightExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprWorkCopyrightExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprWorkCopyrightUpdateCommand dataCompanyIprWorkCopyrightUpdateCommand = DataCompanyIprWorkCopyrightUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprWorkCopyrightExWarehouseVO.getVersion(),
						dataCompanyIprWorkCopyrightWarehouseCommand
				);
				dataCompanyIprWorkCopyrightUpdateCommandExecutor.execute(dataCompanyIprWorkCopyrightUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprWorkCopyrightExWarehouseVOSingleResponse = dataCompanyIprWorkCopyrightExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprWorkCopyrightExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprWorkCopyrightExWarehouseCommandExecutor(DataCompanyIprWorkCopyrightExWarehouseCommandExecutor dataCompanyIprWorkCopyrightExWarehouseCommandExecutor) {
		this.dataCompanyIprWorkCopyrightExWarehouseCommandExecutor = dataCompanyIprWorkCopyrightExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprWorkCopyrightCreateCommandExecutor(DataCompanyIprWorkCopyrightCreateCommandExecutor dataCompanyIprWorkCopyrightCreateCommandExecutor) {
		this.dataCompanyIprWorkCopyrightCreateCommandExecutor = dataCompanyIprWorkCopyrightCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprWorkCopyrightService(IDataCompanyIprWorkCopyrightService iDataCompanyIprWorkCopyrightService) {
		this.iDataCompanyIprWorkCopyrightService = iDataCompanyIprWorkCopyrightService;
	}
	@Autowired
	public void setDataCompanyIprWorkCopyrightUpdateCommandExecutor(DataCompanyIprWorkCopyrightUpdateCommandExecutor dataCompanyIprWorkCopyrightUpdateCommandExecutor) {
		this.dataCompanyIprWorkCopyrightUpdateCommandExecutor = dataCompanyIprWorkCopyrightUpdateCommandExecutor;
	}
}
