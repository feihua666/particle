package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanySpotCheckCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanySpotCheckUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanySpotCheckExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanySpotCheckCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanySpotCheckUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanySpotCheckWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanySpotCheckVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySpotCheckExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanySpotCheckService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业抽查检查入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanySpotCheckWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanySpotCheckExWarehouseCommandExecutor dataCompanySpotCheckExWarehouseCommandExecutor;
	private DataCompanySpotCheckCreateCommandExecutor dataCompanySpotCheckCreateCommandExecutor;
	private IDataCompanySpotCheckService iDataCompanySpotCheckService;
	private DataCompanySpotCheckUpdateCommandExecutor dataCompanySpotCheckUpdateCommandExecutor;


	/**
	 * 企业抽查检查入库
	 * @param dataCompanySpotCheckWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanySpotCheckExWarehouseVO> warehouse(DataCompanySpotCheckWarehouseCommand dataCompanySpotCheckWarehouseCommand) {
		SingleResponse<DataCompanySpotCheckExWarehouseVO> dataCompanySpotCheckExWarehouseVOSingleResponse = null;
		DataCompanySpotCheckExWarehouseVO dataCompanySpotCheckExWarehouseVO = null;
		String obtainDataMd5 = dataCompanySpotCheckWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanySpotCheckExWarehouseVOSingleResponse = dataCompanySpotCheckExWarehouseCommandExecutor.exWarehouseByCompanyIdAndDataMd5(dataCompanySpotCheckWarehouseCommand.getCompanyId(),obtainDataMd5);
		}

		dataCompanySpotCheckExWarehouseVO = dataCompanySpotCheckExWarehouseVOSingleResponse == null ? null : dataCompanySpotCheckExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanySpotCheckExWarehouseVO == null) {
			DataCompanySpotCheckCreateCommand dataCompanySpotCheckCreateCommand = DataCompanySpotCheckCreateCommand.createByWarehouseCommand(dataCompanySpotCheckWarehouseCommand);
			SingleResponse<DataCompanySpotCheckVO> dataCompanySpotCheckVOSingleResponse = dataCompanySpotCheckCreateCommandExecutor.execute(dataCompanySpotCheckCreateCommand);
			Long id = dataCompanySpotCheckVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanySpotCheckExWarehouseVOSingleResponse = dataCompanySpotCheckExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanySpotCheckExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanySpotCheckExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanySpotCheckWarehouseCommand.compareAndSetNullWhenEquals(dataCompanySpotCheckExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanySpotCheckWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanySpotCheckService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanySpotCheckExWarehouseVOSingleResponse = dataCompanySpotCheckExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanySpotCheckExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanySpotCheckUpdateCommand dataCompanySpotCheckUpdateCommand = DataCompanySpotCheckUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanySpotCheckExWarehouseVO.getVersion(),
						dataCompanySpotCheckWarehouseCommand
				);
				dataCompanySpotCheckUpdateCommandExecutor.execute(dataCompanySpotCheckUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanySpotCheckExWarehouseVOSingleResponse = dataCompanySpotCheckExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanySpotCheckExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanySpotCheckExWarehouseCommandExecutor(DataCompanySpotCheckExWarehouseCommandExecutor dataCompanySpotCheckExWarehouseCommandExecutor) {
		this.dataCompanySpotCheckExWarehouseCommandExecutor = dataCompanySpotCheckExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanySpotCheckCreateCommandExecutor(DataCompanySpotCheckCreateCommandExecutor dataCompanySpotCheckCreateCommandExecutor) {
		this.dataCompanySpotCheckCreateCommandExecutor = dataCompanySpotCheckCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanySpotCheckService(IDataCompanySpotCheckService iDataCompanySpotCheckService) {
		this.iDataCompanySpotCheckService = iDataCompanySpotCheckService;
	}
	@Autowired
	public void setDataCompanySpotCheckUpdateCommandExecutor(DataCompanySpotCheckUpdateCommandExecutor dataCompanySpotCheckUpdateCommandExecutor) {
		this.dataCompanySpotCheckUpdateCommandExecutor = dataCompanySpotCheckUpdateCommandExecutor;
	}
}
