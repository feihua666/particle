package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyAdministrativeLicenseCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAdministrativeLicenseUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyAdministrativeLicenseCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyAdministrativeLicenseUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAdministrativeLicenseWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyAdministrativeLicenseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyAdministrativeLicenseService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业行政许可入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyAdministrativeLicenseWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyAdministrativeLicenseExWarehouseCommandExecutor dataCompanyAdministrativeLicenseExWarehouseCommandExecutor;
	private DataCompanyAdministrativeLicenseCreateCommandExecutor dataCompanyAdministrativeLicenseCreateCommandExecutor;
	private IDataCompanyAdministrativeLicenseService iDataCompanyAdministrativeLicenseService;
	private DataCompanyAdministrativeLicenseUpdateCommandExecutor dataCompanyAdministrativeLicenseUpdateCommandExecutor;


	/**
	 * 企业行政许可入库
	 * @param dataCompanyAdministrativeLicenseWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAdministrativeLicenseExWarehouseVO> warehouse(DataCompanyAdministrativeLicenseWarehouseCommand dataCompanyAdministrativeLicenseWarehouseCommand) {
		SingleResponse<DataCompanyAdministrativeLicenseExWarehouseVO> dataCompanyAdministrativeLicenseExWarehouseVOSingleResponse = null;
		DataCompanyAdministrativeLicenseExWarehouseVO dataCompanyAdministrativeLicenseExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyAdministrativeLicenseWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyAdministrativeLicenseExWarehouseVOSingleResponse = dataCompanyAdministrativeLicenseExWarehouseCommandExecutor.exWarehouseByCompanyIdAndDataMd5(dataCompanyAdministrativeLicenseWarehouseCommand.getCompanyId(),obtainDataMd5);
		}

		dataCompanyAdministrativeLicenseExWarehouseVO = dataCompanyAdministrativeLicenseExWarehouseVOSingleResponse == null ? null : dataCompanyAdministrativeLicenseExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyAdministrativeLicenseExWarehouseVO == null) {
			DataCompanyAdministrativeLicenseCreateCommand dataCompanyAdministrativeLicenseCreateCommand = DataCompanyAdministrativeLicenseCreateCommand.createByWarehouseCommand(dataCompanyAdministrativeLicenseWarehouseCommand);
			SingleResponse<DataCompanyAdministrativeLicenseVO> dataCompanyAdministrativeLicenseVOSingleResponse = dataCompanyAdministrativeLicenseCreateCommandExecutor.execute(dataCompanyAdministrativeLicenseCreateCommand);
			Long id = dataCompanyAdministrativeLicenseVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyAdministrativeLicenseExWarehouseVOSingleResponse = dataCompanyAdministrativeLicenseExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyAdministrativeLicenseExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyAdministrativeLicenseExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyAdministrativeLicenseWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyAdministrativeLicenseExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyAdministrativeLicenseWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyAdministrativeLicenseService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyAdministrativeLicenseExWarehouseVOSingleResponse = dataCompanyAdministrativeLicenseExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAdministrativeLicenseExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyAdministrativeLicenseUpdateCommand dataCompanyAdministrativeLicenseUpdateCommand = DataCompanyAdministrativeLicenseUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyAdministrativeLicenseExWarehouseVO.getVersion(),
						dataCompanyAdministrativeLicenseWarehouseCommand
				);
				dataCompanyAdministrativeLicenseUpdateCommandExecutor.execute(dataCompanyAdministrativeLicenseUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyAdministrativeLicenseExWarehouseVOSingleResponse = dataCompanyAdministrativeLicenseExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyAdministrativeLicenseExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyAdministrativeLicenseExWarehouseCommandExecutor(DataCompanyAdministrativeLicenseExWarehouseCommandExecutor dataCompanyAdministrativeLicenseExWarehouseCommandExecutor) {
		this.dataCompanyAdministrativeLicenseExWarehouseCommandExecutor = dataCompanyAdministrativeLicenseExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAdministrativeLicenseCreateCommandExecutor(DataCompanyAdministrativeLicenseCreateCommandExecutor dataCompanyAdministrativeLicenseCreateCommandExecutor) {
		this.dataCompanyAdministrativeLicenseCreateCommandExecutor = dataCompanyAdministrativeLicenseCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyAdministrativeLicenseService(IDataCompanyAdministrativeLicenseService iDataCompanyAdministrativeLicenseService) {
		this.iDataCompanyAdministrativeLicenseService = iDataCompanyAdministrativeLicenseService;
	}
	@Autowired
	public void setDataCompanyAdministrativeLicenseUpdateCommandExecutor(DataCompanyAdministrativeLicenseUpdateCommandExecutor dataCompanyAdministrativeLicenseUpdateCommandExecutor) {
		this.dataCompanyAdministrativeLicenseUpdateCommandExecutor = dataCompanyAdministrativeLicenseUpdateCommandExecutor;
	}
}
