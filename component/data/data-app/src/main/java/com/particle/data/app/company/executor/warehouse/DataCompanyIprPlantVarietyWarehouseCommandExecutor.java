package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPlantVarietyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPlantVarietyUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPlantVarietyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPlantVarietyWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPlantVarietyService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权植物新品种入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPlantVarietyExWarehouseCommandExecutor dataCompanyIprPlantVarietyExWarehouseCommandExecutor;
	private DataCompanyIprPlantVarietyCreateCommandExecutor dataCompanyIprPlantVarietyCreateCommandExecutor;
	private IDataCompanyIprPlantVarietyService iDataCompanyIprPlantVarietyService;
	private DataCompanyIprPlantVarietyUpdateCommandExecutor dataCompanyIprPlantVarietyUpdateCommandExecutor;


	/**
	 * 企业知识产权植物新品种入库
	 * @param dataCompanyIprPlantVarietyWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPlantVarietyExWarehouseVO> warehouse(DataCompanyIprPlantVarietyWarehouseCommand dataCompanyIprPlantVarietyWarehouseCommand) {
		SingleResponse<DataCompanyIprPlantVarietyExWarehouseVO> dataCompanyIprPlantVarietyExWarehouseVOSingleResponse = null;
		DataCompanyIprPlantVarietyExWarehouseVO dataCompanyIprPlantVarietyExWarehouseVO = null;
		String publicNo = dataCompanyIprPlantVarietyWarehouseCommand.getPublicNo();
		if (StrUtil.isNotEmpty(publicNo)) {
			dataCompanyIprPlantVarietyExWarehouseVOSingleResponse = dataCompanyIprPlantVarietyExWarehouseCommandExecutor.exWarehouseByPublicNo(publicNo);
		}
		if (dataCompanyIprPlantVarietyExWarehouseVOSingleResponse == null || dataCompanyIprPlantVarietyExWarehouseVOSingleResponse.getData() == null) {
			if (StrUtil.isNotEmpty(dataCompanyIprPlantVarietyWarehouseCommand.getApplyNo())) {
				dataCompanyIprPlantVarietyExWarehouseVOSingleResponse = dataCompanyIprPlantVarietyExWarehouseCommandExecutor.exWarehouseByApplyNo(dataCompanyIprPlantVarietyWarehouseCommand.getApplyNo());
			}
		}
		dataCompanyIprPlantVarietyExWarehouseVO = dataCompanyIprPlantVarietyExWarehouseVOSingleResponse == null ? null : dataCompanyIprPlantVarietyExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPlantVarietyExWarehouseVO == null) {
			DataCompanyIprPlantVarietyCreateCommand dataCompanyIprPlantVarietyCreateCommand = DataCompanyIprPlantVarietyCreateCommand.createByWarehouseCommand(dataCompanyIprPlantVarietyWarehouseCommand);
			SingleResponse<DataCompanyIprPlantVarietyVO> dataCompanyIprPlantVarietyVOSingleResponse = dataCompanyIprPlantVarietyCreateCommandExecutor.execute(dataCompanyIprPlantVarietyCreateCommand);
			Long id = dataCompanyIprPlantVarietyVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprPlantVarietyExWarehouseVOSingleResponse = dataCompanyIprPlantVarietyExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprPlantVarietyExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprPlantVarietyExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPlantVarietyWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPlantVarietyExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPlantVarietyWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPlantVarietyService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprPlantVarietyExWarehouseVOSingleResponse = dataCompanyIprPlantVarietyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPlantVarietyExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPlantVarietyUpdateCommand dataCompanyIprPlantVarietyUpdateCommand = DataCompanyIprPlantVarietyUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprPlantVarietyExWarehouseVO.getVersion(),
						dataCompanyIprPlantVarietyWarehouseCommand
				);
				dataCompanyIprPlantVarietyUpdateCommandExecutor.execute(dataCompanyIprPlantVarietyUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprPlantVarietyExWarehouseVOSingleResponse = dataCompanyIprPlantVarietyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPlantVarietyExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprPlantVarietyExWarehouseCommandExecutor(DataCompanyIprPlantVarietyExWarehouseCommandExecutor dataCompanyIprPlantVarietyExWarehouseCommandExecutor) {
		this.dataCompanyIprPlantVarietyExWarehouseCommandExecutor = dataCompanyIprPlantVarietyExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPlantVarietyCreateCommandExecutor(DataCompanyIprPlantVarietyCreateCommandExecutor dataCompanyIprPlantVarietyCreateCommandExecutor) {
		this.dataCompanyIprPlantVarietyCreateCommandExecutor = dataCompanyIprPlantVarietyCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPlantVarietyService(IDataCompanyIprPlantVarietyService iDataCompanyIprPlantVarietyService) {
		this.iDataCompanyIprPlantVarietyService = iDataCompanyIprPlantVarietyService;
	}
	@Autowired
	public void setDataCompanyIprPlantVarietyUpdateCommandExecutor(DataCompanyIprPlantVarietyUpdateCommandExecutor dataCompanyIprPlantVarietyUpdateCommandExecutor) {
		this.dataCompanyIprPlantVarietyUpdateCommandExecutor = dataCompanyIprPlantVarietyUpdateCommandExecutor;
	}
}
