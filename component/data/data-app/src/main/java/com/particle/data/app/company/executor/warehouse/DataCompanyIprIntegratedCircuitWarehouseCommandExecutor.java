package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprIntegratedCircuitCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprIntegratedCircuitUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprIntegratedCircuitCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprIntegratedCircuitUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprIntegratedCircuitWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprIntegratedCircuitVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprIntegratedCircuitService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权集成电路入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprIntegratedCircuitWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprIntegratedCircuitExWarehouseCommandExecutor dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor;
	private DataCompanyIprIntegratedCircuitCreateCommandExecutor dataCompanyIprIntegratedCircuitCreateCommandExecutor;
	private IDataCompanyIprIntegratedCircuitService iDataCompanyIprIntegratedCircuitService;
	private DataCompanyIprIntegratedCircuitUpdateCommandExecutor dataCompanyIprIntegratedCircuitUpdateCommandExecutor;


	/**
	 * 企业知识产权集成电路入库
	 * @param dataCompanyIprIntegratedCircuitWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> warehouse(DataCompanyIprIntegratedCircuitWarehouseCommand dataCompanyIprIntegratedCircuitWarehouseCommand) {
		SingleResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> dataCompanyIprIntegratedCircuitExWarehouseVOSingleResponse = null;
		DataCompanyIprIntegratedCircuitExWarehouseVO dataCompanyIprIntegratedCircuitExWarehouseVO = null;
		String publicNo = dataCompanyIprIntegratedCircuitWarehouseCommand.getPublicNo();
		if (StrUtil.isNotEmpty(publicNo)) {
			dataCompanyIprIntegratedCircuitExWarehouseVOSingleResponse = dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor.exWarehouseByPublicNo(publicNo);
		}

		dataCompanyIprIntegratedCircuitExWarehouseVO = dataCompanyIprIntegratedCircuitExWarehouseVOSingleResponse == null ? null : dataCompanyIprIntegratedCircuitExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprIntegratedCircuitExWarehouseVO == null) {
			DataCompanyIprIntegratedCircuitCreateCommand dataCompanyIprIntegratedCircuitCreateCommand = DataCompanyIprIntegratedCircuitCreateCommand.createByWarehouseCommand(dataCompanyIprIntegratedCircuitWarehouseCommand);
			SingleResponse<DataCompanyIprIntegratedCircuitVO> dataCompanyIprIntegratedCircuitVOSingleResponse = dataCompanyIprIntegratedCircuitCreateCommandExecutor.execute(dataCompanyIprIntegratedCircuitCreateCommand);
			Long id = dataCompanyIprIntegratedCircuitVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprIntegratedCircuitExWarehouseVOSingleResponse = dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprIntegratedCircuitExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprIntegratedCircuitExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprIntegratedCircuitWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprIntegratedCircuitExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprIntegratedCircuitWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprIntegratedCircuitService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprIntegratedCircuitExWarehouseVOSingleResponse = dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprIntegratedCircuitExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprIntegratedCircuitUpdateCommand dataCompanyIprIntegratedCircuitUpdateCommand = DataCompanyIprIntegratedCircuitUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprIntegratedCircuitExWarehouseVO.getVersion(),
						dataCompanyIprIntegratedCircuitWarehouseCommand
				);
				dataCompanyIprIntegratedCircuitUpdateCommandExecutor.execute(dataCompanyIprIntegratedCircuitUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprIntegratedCircuitExWarehouseVOSingleResponse = dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprIntegratedCircuitExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprIntegratedCircuitExWarehouseCommandExecutor(DataCompanyIprIntegratedCircuitExWarehouseCommandExecutor dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor) {
		this.dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor = dataCompanyIprIntegratedCircuitExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprIntegratedCircuitCreateCommandExecutor(DataCompanyIprIntegratedCircuitCreateCommandExecutor dataCompanyIprIntegratedCircuitCreateCommandExecutor) {
		this.dataCompanyIprIntegratedCircuitCreateCommandExecutor = dataCompanyIprIntegratedCircuitCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprIntegratedCircuitService(IDataCompanyIprIntegratedCircuitService iDataCompanyIprIntegratedCircuitService) {
		this.iDataCompanyIprIntegratedCircuitService = iDataCompanyIprIntegratedCircuitService;
	}
	@Autowired
	public void setDataCompanyIprIntegratedCircuitUpdateCommandExecutor(DataCompanyIprIntegratedCircuitUpdateCommandExecutor dataCompanyIprIntegratedCircuitUpdateCommandExecutor) {
		this.dataCompanyIprIntegratedCircuitUpdateCommandExecutor = dataCompanyIprIntegratedCircuitUpdateCommandExecutor;
	}
}
