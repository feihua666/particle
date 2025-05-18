package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyRestrictHighConsumeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyRestrictHighConsumeUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumeUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyRestrictHighConsumeWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyRestrictHighConsumeService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业限制高消费入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumeWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyRestrictHighConsumeExWarehouseCommandExecutor dataCompanyRestrictHighConsumeExWarehouseCommandExecutor;
	private DataCompanyRestrictHighConsumeCreateCommandExecutor dataCompanyRestrictHighConsumeCreateCommandExecutor;
	private IDataCompanyRestrictHighConsumeService iDataCompanyRestrictHighConsumeService;
	private DataCompanyRestrictHighConsumeUpdateCommandExecutor dataCompanyRestrictHighConsumeUpdateCommandExecutor;


	/**
	 * 企业限制高消费入库
	 * @param dataCompanyRestrictHighConsumeWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumeExWarehouseVO> warehouse(DataCompanyRestrictHighConsumeWarehouseCommand dataCompanyRestrictHighConsumeWarehouseCommand) {
		SingleResponse<DataCompanyRestrictHighConsumeExWarehouseVO> dataCompanyRestrictHighConsumeExWarehouseVOSingleResponse = null;
		DataCompanyRestrictHighConsumeExWarehouseVO dataCompanyRestrictHighConsumeExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyRestrictHighConsumeWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyRestrictHighConsumeExWarehouseVOSingleResponse = dataCompanyRestrictHighConsumeExWarehouseCommandExecutor.exWarehouseByDataMd5(obtainDataMd5);
		}

		dataCompanyRestrictHighConsumeExWarehouseVO = dataCompanyRestrictHighConsumeExWarehouseVOSingleResponse == null ? null : dataCompanyRestrictHighConsumeExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyRestrictHighConsumeExWarehouseVO == null) {
			DataCompanyRestrictHighConsumeCreateCommand dataCompanyRestrictHighConsumeCreateCommand = DataCompanyRestrictHighConsumeCreateCommand.createByWarehouseCommand(dataCompanyRestrictHighConsumeWarehouseCommand);
			SingleResponse<DataCompanyRestrictHighConsumeVO> dataCompanyRestrictHighConsumeVOSingleResponse = dataCompanyRestrictHighConsumeCreateCommandExecutor.execute(dataCompanyRestrictHighConsumeCreateCommand);
			Long id = dataCompanyRestrictHighConsumeVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyRestrictHighConsumeExWarehouseVOSingleResponse = dataCompanyRestrictHighConsumeExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyRestrictHighConsumeExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyRestrictHighConsumeExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyRestrictHighConsumeWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyRestrictHighConsumeExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyRestrictHighConsumeWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyRestrictHighConsumeService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyRestrictHighConsumeExWarehouseVOSingleResponse = dataCompanyRestrictHighConsumeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyRestrictHighConsumeExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyRestrictHighConsumeUpdateCommand dataCompanyRestrictHighConsumeUpdateCommand = DataCompanyRestrictHighConsumeUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyRestrictHighConsumeExWarehouseVO.getVersion(),
						dataCompanyRestrictHighConsumeWarehouseCommand
				);
				dataCompanyRestrictHighConsumeUpdateCommandExecutor.execute(dataCompanyRestrictHighConsumeUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyRestrictHighConsumeExWarehouseVOSingleResponse = dataCompanyRestrictHighConsumeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyRestrictHighConsumeExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyRestrictHighConsumeExWarehouseCommandExecutor(DataCompanyRestrictHighConsumeExWarehouseCommandExecutor dataCompanyRestrictHighConsumeExWarehouseCommandExecutor) {
		this.dataCompanyRestrictHighConsumeExWarehouseCommandExecutor = dataCompanyRestrictHighConsumeExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyRestrictHighConsumeCreateCommandExecutor(DataCompanyRestrictHighConsumeCreateCommandExecutor dataCompanyRestrictHighConsumeCreateCommandExecutor) {
		this.dataCompanyRestrictHighConsumeCreateCommandExecutor = dataCompanyRestrictHighConsumeCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyRestrictHighConsumeService(IDataCompanyRestrictHighConsumeService iDataCompanyRestrictHighConsumeService) {
		this.iDataCompanyRestrictHighConsumeService = iDataCompanyRestrictHighConsumeService;
	}
	@Autowired
	public void setDataCompanyRestrictHighConsumeUpdateCommandExecutor(DataCompanyRestrictHighConsumeUpdateCommandExecutor dataCompanyRestrictHighConsumeUpdateCommandExecutor) {
		this.dataCompanyRestrictHighConsumeUpdateCommandExecutor = dataCompanyRestrictHighConsumeUpdateCommandExecutor;
	}
}
