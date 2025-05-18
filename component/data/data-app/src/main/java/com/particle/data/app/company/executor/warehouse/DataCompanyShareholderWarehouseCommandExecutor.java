package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyShareholderCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyShareholderUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyShareholderExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyShareholderCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyShareholderUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyShareholderWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyShareholderVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyShareholderExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyShareholderService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业股东入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyShareholderWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyShareholderExWarehouseCommandExecutor dataCompanyShareholderExWarehouseCommandExecutor;
	private DataCompanyShareholderCreateCommandExecutor dataCompanyShareholderCreateCommandExecutor;
	private IDataCompanyShareholderService iDataCompanyShareholderService;
	private DataCompanyShareholderUpdateCommandExecutor dataCompanyShareholderUpdateCommandExecutor;


	/**
	 * 企业股东入库
	 * @param dataCompanyShareholderWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyShareholderExWarehouseVO> warehouse(DataCompanyShareholderWarehouseCommand dataCompanyShareholderWarehouseCommand) {
		SingleResponse<DataCompanyShareholderExWarehouseVO> dataCompanyShareholderExWarehouseVOSingleResponse = null;
		DataCompanyShareholderExWarehouseVO dataCompanyShareholderExWarehouseVO = null;
        if (StrUtil.isNotEmpty(dataCompanyShareholderWarehouseCommand.getShareholderName())) {
			dataCompanyShareholderExWarehouseVOSingleResponse = dataCompanyShareholderExWarehouseCommandExecutor.exWarehouseByCompanyIdAndDataMd5(dataCompanyShareholderWarehouseCommand.getCompanyId(),dataCompanyShareholderWarehouseCommand.obtainDataMd5());
		}
		dataCompanyShareholderExWarehouseVO = dataCompanyShareholderExWarehouseVOSingleResponse == null ? null : dataCompanyShareholderExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyShareholderExWarehouseVO == null) {
			DataCompanyShareholderCreateCommand dataCompanyShareholderCreateCommand = DataCompanyShareholderCreateCommand.createByWarehouseCommand(dataCompanyShareholderWarehouseCommand);
			SingleResponse<DataCompanyShareholderVO> dataCompanyShareholderVOSingleResponse = dataCompanyShareholderCreateCommandExecutor.execute(dataCompanyShareholderCreateCommand);
			Long id = dataCompanyShareholderVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyShareholderExWarehouseVOSingleResponse = dataCompanyShareholderExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyShareholderExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyShareholderExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyShareholderWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyShareholderExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyShareholderWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyShareholderService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyShareholderExWarehouseVOSingleResponse = dataCompanyShareholderExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyShareholderExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyShareholderUpdateCommand dataCompanyShareholderUpdateCommand = DataCompanyShareholderUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyShareholderExWarehouseVO.getVersion(),
						dataCompanyShareholderWarehouseCommand
				);
				dataCompanyShareholderUpdateCommandExecutor.execute(dataCompanyShareholderUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyShareholderExWarehouseVOSingleResponse = dataCompanyShareholderExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyShareholderExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyShareholderExWarehouseCommandExecutor(DataCompanyShareholderExWarehouseCommandExecutor dataCompanyShareholderExWarehouseCommandExecutor) {
		this.dataCompanyShareholderExWarehouseCommandExecutor = dataCompanyShareholderExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyShareholderCreateCommandExecutor(DataCompanyShareholderCreateCommandExecutor dataCompanyShareholderCreateCommandExecutor) {
		this.dataCompanyShareholderCreateCommandExecutor = dataCompanyShareholderCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyShareholderService(IDataCompanyShareholderService iDataCompanyShareholderService) {
		this.iDataCompanyShareholderService = iDataCompanyShareholderService;
	}
	@Autowired
	public void setDataCompanyShareholderUpdateCommandExecutor(DataCompanyShareholderUpdateCommandExecutor dataCompanyShareholderUpdateCommandExecutor) {
		this.dataCompanyShareholderUpdateCommandExecutor = dataCompanyShareholderUpdateCommandExecutor;
	}
}
