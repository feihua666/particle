package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyVcFinancingCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcFinancingUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyVcFinancingExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcFinancingWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyVcFinancingService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业融资入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyVcFinancingWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyVcFinancingExWarehouseCommandExecutor dataCompanyVcFinancingExWarehouseCommandExecutor;
	private DataCompanyVcFinancingCreateCommandExecutor dataCompanyVcFinancingCreateCommandExecutor;
	private IDataCompanyVcFinancingService iDataCompanyVcFinancingService;
	private DataCompanyVcFinancingUpdateCommandExecutor dataCompanyVcFinancingUpdateCommandExecutor;


	/**
	 * 企业融资入库
	 * @param dataCompanyVcFinancingWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingExWarehouseVO> warehouse(DataCompanyVcFinancingWarehouseCommand dataCompanyVcFinancingWarehouseCommand) {
		SingleResponse<DataCompanyVcFinancingExWarehouseVO> dataCompanyVcFinancingExWarehouseVOSingleResponse = null;
		DataCompanyVcFinancingExWarehouseVO dataCompanyVcFinancingExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyVcFinancingWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyVcFinancingExWarehouseVOSingleResponse = dataCompanyVcFinancingExWarehouseCommandExecutor.exWarehouseByCompanyIdAndDataMd5(dataCompanyVcFinancingWarehouseCommand.getCompanyId(), obtainDataMd5);
		}

		dataCompanyVcFinancingExWarehouseVO = dataCompanyVcFinancingExWarehouseVOSingleResponse == null ? null : dataCompanyVcFinancingExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyVcFinancingExWarehouseVO == null) {
			DataCompanyVcFinancingCreateCommand dataCompanyVcFinancingCreateCommand = DataCompanyVcFinancingCreateCommand.createByWarehouseCommand(dataCompanyVcFinancingWarehouseCommand);
			SingleResponse<DataCompanyVcFinancingVO> dataCompanyVcFinancingVOSingleResponse = dataCompanyVcFinancingCreateCommandExecutor.execute(dataCompanyVcFinancingCreateCommand);
			Long id = dataCompanyVcFinancingVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyVcFinancingExWarehouseVOSingleResponse = dataCompanyVcFinancingExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyVcFinancingExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyVcFinancingExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyVcFinancingWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyVcFinancingExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyVcFinancingWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyVcFinancingService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyVcFinancingExWarehouseVOSingleResponse = dataCompanyVcFinancingExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyVcFinancingExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyVcFinancingUpdateCommand dataCompanyVcFinancingUpdateCommand = DataCompanyVcFinancingUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyVcFinancingExWarehouseVO.getVersion(),
						dataCompanyVcFinancingWarehouseCommand
				);
				dataCompanyVcFinancingUpdateCommandExecutor.execute(dataCompanyVcFinancingUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyVcFinancingExWarehouseVOSingleResponse = dataCompanyVcFinancingExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyVcFinancingExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyVcFinancingExWarehouseCommandExecutor(DataCompanyVcFinancingExWarehouseCommandExecutor dataCompanyVcFinancingExWarehouseCommandExecutor) {
		this.dataCompanyVcFinancingExWarehouseCommandExecutor = dataCompanyVcFinancingExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyVcFinancingCreateCommandExecutor(DataCompanyVcFinancingCreateCommandExecutor dataCompanyVcFinancingCreateCommandExecutor) {
		this.dataCompanyVcFinancingCreateCommandExecutor = dataCompanyVcFinancingCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyVcFinancingService(IDataCompanyVcFinancingService iDataCompanyVcFinancingService) {
		this.iDataCompanyVcFinancingService = iDataCompanyVcFinancingService;
	}
	@Autowired
	public void setDataCompanyVcFinancingUpdateCommandExecutor(DataCompanyVcFinancingUpdateCommandExecutor dataCompanyVcFinancingUpdateCommandExecutor) {
		this.dataCompanyVcFinancingUpdateCommandExecutor = dataCompanyVcFinancingUpdateCommandExecutor;
	}
}
