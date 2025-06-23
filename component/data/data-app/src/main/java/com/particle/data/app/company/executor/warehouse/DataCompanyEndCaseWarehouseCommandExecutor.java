package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyEndCaseCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyEndCaseUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyEndCaseExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyEndCaseCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyEndCaseUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyEndCaseWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyEndCaseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEndCaseExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyEndCaseService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业终本案件入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyEndCaseWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyEndCaseExWarehouseCommandExecutor dataCompanyEndCaseExWarehouseCommandExecutor;
	private DataCompanyEndCaseCreateCommandExecutor dataCompanyEndCaseCreateCommandExecutor;
	private IDataCompanyEndCaseService iDataCompanyEndCaseService;
	private DataCompanyEndCaseUpdateCommandExecutor dataCompanyEndCaseUpdateCommandExecutor;


	/**
	 * 企业终本案件入库
	 * @param dataCompanyEndCaseWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyEndCaseExWarehouseVO> warehouse(DataCompanyEndCaseWarehouseCommand dataCompanyEndCaseWarehouseCommand) {
		SingleResponse<DataCompanyEndCaseExWarehouseVO> dataCompanyEndCaseExWarehouseVOSingleResponse = null;
		DataCompanyEndCaseExWarehouseVO dataCompanyEndCaseExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyEndCaseWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyEndCaseExWarehouseVOSingleResponse = dataCompanyEndCaseExWarehouseCommandExecutor.exWarehouseByCompanyIdAndDataMd5(dataCompanyEndCaseWarehouseCommand.getExecutedPersonCompanyId(),obtainDataMd5);
		}

		dataCompanyEndCaseExWarehouseVO = dataCompanyEndCaseExWarehouseVOSingleResponse == null ? null : dataCompanyEndCaseExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyEndCaseExWarehouseVO == null) {
			DataCompanyEndCaseCreateCommand dataCompanyEndCaseCreateCommand = DataCompanyEndCaseCreateCommand.createByWarehouseCommand(dataCompanyEndCaseWarehouseCommand);
			SingleResponse<DataCompanyEndCaseVO> dataCompanyEndCaseVOSingleResponse = dataCompanyEndCaseCreateCommandExecutor.execute(dataCompanyEndCaseCreateCommand);
			Long id = dataCompanyEndCaseVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyEndCaseExWarehouseVOSingleResponse = dataCompanyEndCaseExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyEndCaseExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyEndCaseExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyEndCaseWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyEndCaseExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyEndCaseWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyEndCaseService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyEndCaseExWarehouseVOSingleResponse = dataCompanyEndCaseExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyEndCaseExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyEndCaseUpdateCommand dataCompanyEndCaseUpdateCommand = DataCompanyEndCaseUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyEndCaseExWarehouseVO.getVersion(),
						dataCompanyEndCaseWarehouseCommand
				);
				dataCompanyEndCaseUpdateCommandExecutor.execute(dataCompanyEndCaseUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyEndCaseExWarehouseVOSingleResponse = dataCompanyEndCaseExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyEndCaseExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyEndCaseExWarehouseCommandExecutor(DataCompanyEndCaseExWarehouseCommandExecutor dataCompanyEndCaseExWarehouseCommandExecutor) {
		this.dataCompanyEndCaseExWarehouseCommandExecutor = dataCompanyEndCaseExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyEndCaseCreateCommandExecutor(DataCompanyEndCaseCreateCommandExecutor dataCompanyEndCaseCreateCommandExecutor) {
		this.dataCompanyEndCaseCreateCommandExecutor = dataCompanyEndCaseCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyEndCaseService(IDataCompanyEndCaseService iDataCompanyEndCaseService) {
		this.iDataCompanyEndCaseService = iDataCompanyEndCaseService;
	}
	@Autowired
	public void setDataCompanyEndCaseUpdateCommandExecutor(DataCompanyEndCaseUpdateCommandExecutor dataCompanyEndCaseUpdateCommandExecutor) {
		this.dataCompanyEndCaseUpdateCommandExecutor = dataCompanyEndCaseUpdateCommandExecutor;
	}
}
