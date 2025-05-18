package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyPunishmentCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPunishmentUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyPunishmentExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyPunishmentCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyPunishmentUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPunishmentWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyPunishmentVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPunishmentExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyPunishmentService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业行政处罚入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyPunishmentWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyPunishmentExWarehouseCommandExecutor dataCompanyPunishmentExWarehouseCommandExecutor;
	private DataCompanyPunishmentCreateCommandExecutor dataCompanyPunishmentCreateCommandExecutor;
	private IDataCompanyPunishmentService iDataCompanyPunishmentService;
	private DataCompanyPunishmentUpdateCommandExecutor dataCompanyPunishmentUpdateCommandExecutor;


	/**
	 * 企业行政处罚入库
	 * @param dataCompanyPunishmentWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPunishmentExWarehouseVO> warehouse(DataCompanyPunishmentWarehouseCommand dataCompanyPunishmentWarehouseCommand) {
		SingleResponse<DataCompanyPunishmentExWarehouseVO> dataCompanyPunishmentExWarehouseVOSingleResponse = null;
		DataCompanyPunishmentExWarehouseVO dataCompanyPunishmentExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyPunishmentWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyPunishmentExWarehouseVOSingleResponse = dataCompanyPunishmentExWarehouseCommandExecutor.exWarehouseByCompanyIdAndDataMd5(dataCompanyPunishmentWarehouseCommand.getCompanyId(),obtainDataMd5);
		}

		dataCompanyPunishmentExWarehouseVO = dataCompanyPunishmentExWarehouseVOSingleResponse == null ? null : dataCompanyPunishmentExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyPunishmentExWarehouseVO == null) {
			DataCompanyPunishmentCreateCommand dataCompanyPunishmentCreateCommand = DataCompanyPunishmentCreateCommand.createByWarehouseCommand(dataCompanyPunishmentWarehouseCommand);
			SingleResponse<DataCompanyPunishmentVO> dataCompanyPunishmentVOSingleResponse = dataCompanyPunishmentCreateCommandExecutor.execute(dataCompanyPunishmentCreateCommand);
			Long id = dataCompanyPunishmentVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyPunishmentExWarehouseVOSingleResponse = dataCompanyPunishmentExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyPunishmentExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyPunishmentExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyPunishmentWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyPunishmentExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyPunishmentWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyPunishmentService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyPunishmentExWarehouseVOSingleResponse = dataCompanyPunishmentExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyPunishmentExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyPunishmentUpdateCommand dataCompanyPunishmentUpdateCommand = DataCompanyPunishmentUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyPunishmentExWarehouseVO.getVersion(),
						dataCompanyPunishmentWarehouseCommand
				);
				dataCompanyPunishmentUpdateCommandExecutor.execute(dataCompanyPunishmentUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyPunishmentExWarehouseVOSingleResponse = dataCompanyPunishmentExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyPunishmentExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyPunishmentExWarehouseCommandExecutor(DataCompanyPunishmentExWarehouseCommandExecutor dataCompanyPunishmentExWarehouseCommandExecutor) {
		this.dataCompanyPunishmentExWarehouseCommandExecutor = dataCompanyPunishmentExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyPunishmentCreateCommandExecutor(DataCompanyPunishmentCreateCommandExecutor dataCompanyPunishmentCreateCommandExecutor) {
		this.dataCompanyPunishmentCreateCommandExecutor = dataCompanyPunishmentCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyPunishmentService(IDataCompanyPunishmentService iDataCompanyPunishmentService) {
		this.iDataCompanyPunishmentService = iDataCompanyPunishmentService;
	}
	@Autowired
	public void setDataCompanyPunishmentUpdateCommandExecutor(DataCompanyPunishmentUpdateCommandExecutor dataCompanyPunishmentUpdateCommandExecutor) {
		this.dataCompanyPunishmentUpdateCommandExecutor = dataCompanyPunishmentUpdateCommandExecutor;
	}
}
