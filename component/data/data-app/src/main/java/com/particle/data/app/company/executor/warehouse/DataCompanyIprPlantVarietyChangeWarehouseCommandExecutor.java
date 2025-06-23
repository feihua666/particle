package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPlantVarietyChangeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPlantVarietyChangeUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyChangeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyChangeUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPlantVarietyChangeWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyChangeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPlantVarietyChangeService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权植物新品种变更信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyChangeWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor;
	private DataCompanyIprPlantVarietyChangeCreateCommandExecutor dataCompanyIprPlantVarietyChangeCreateCommandExecutor;
	private IDataCompanyIprPlantVarietyChangeService iDataCompanyIprPlantVarietyChangeService;
	private DataCompanyIprPlantVarietyChangeUpdateCommandExecutor dataCompanyIprPlantVarietyChangeUpdateCommandExecutor;


	/**
	 * 企业知识产权植物新品种变更信息入库
	 * @param dataCompanyIprPlantVarietyChangeWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyChangeExWarehouseVO> warehouse(DataCompanyIprPlantVarietyChangeWarehouseCommand dataCompanyIprPlantVarietyChangeWarehouseCommand) {
		SingleResponse<DataCompanyIprPlantVarietyChangeExWarehouseVO> dataCompanyIprPlantVarietyChangeExWarehouseVOSingleResponse = null;
		DataCompanyIprPlantVarietyChangeExWarehouseVO dataCompanyIprPlantVarietyChangeExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyIprPlantVarietyChangeWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyIprPlantVarietyChangeExWarehouseVOSingleResponse = dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor.exWarehouseByCompanyIprPlantVarietyIdAndDataMd5(dataCompanyIprPlantVarietyChangeWarehouseCommand.getCompanyIprPlantVarietyId(),obtainDataMd5);
		}

		dataCompanyIprPlantVarietyChangeExWarehouseVO = dataCompanyIprPlantVarietyChangeExWarehouseVOSingleResponse == null ? null : dataCompanyIprPlantVarietyChangeExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPlantVarietyChangeExWarehouseVO == null) {
			DataCompanyIprPlantVarietyChangeCreateCommand dataCompanyIprPlantVarietyChangeCreateCommand = DataCompanyIprPlantVarietyChangeCreateCommand.createByWarehouseCommand(dataCompanyIprPlantVarietyChangeWarehouseCommand);
			SingleResponse<DataCompanyIprPlantVarietyChangeVO> dataCompanyIprPlantVarietyChangeVOSingleResponse = dataCompanyIprPlantVarietyChangeCreateCommandExecutor.execute(dataCompanyIprPlantVarietyChangeCreateCommand);
			Long id = dataCompanyIprPlantVarietyChangeVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprPlantVarietyChangeExWarehouseVOSingleResponse = dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprPlantVarietyChangeExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprPlantVarietyChangeExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPlantVarietyChangeWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPlantVarietyChangeExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPlantVarietyChangeWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPlantVarietyChangeService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprPlantVarietyChangeExWarehouseVOSingleResponse = dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPlantVarietyChangeExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPlantVarietyChangeUpdateCommand dataCompanyIprPlantVarietyChangeUpdateCommand = DataCompanyIprPlantVarietyChangeUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprPlantVarietyChangeExWarehouseVO.getVersion(),
						dataCompanyIprPlantVarietyChangeWarehouseCommand
				);
				dataCompanyIprPlantVarietyChangeUpdateCommandExecutor.execute(dataCompanyIprPlantVarietyChangeUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprPlantVarietyChangeExWarehouseVOSingleResponse = dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPlantVarietyChangeExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor(DataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor) {
		this.dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor = dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPlantVarietyChangeCreateCommandExecutor(DataCompanyIprPlantVarietyChangeCreateCommandExecutor dataCompanyIprPlantVarietyChangeCreateCommandExecutor) {
		this.dataCompanyIprPlantVarietyChangeCreateCommandExecutor = dataCompanyIprPlantVarietyChangeCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPlantVarietyChangeService(IDataCompanyIprPlantVarietyChangeService iDataCompanyIprPlantVarietyChangeService) {
		this.iDataCompanyIprPlantVarietyChangeService = iDataCompanyIprPlantVarietyChangeService;
	}
	@Autowired
	public void setDataCompanyIprPlantVarietyChangeUpdateCommandExecutor(DataCompanyIprPlantVarietyChangeUpdateCommandExecutor dataCompanyIprPlantVarietyChangeUpdateCommandExecutor) {
		this.dataCompanyIprPlantVarietyChangeUpdateCommandExecutor = dataCompanyIprPlantVarietyChangeUpdateCommandExecutor;
	}
}
