package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprTrademarkExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权商标入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprTrademarkWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkExWarehouseCommandExecutor dataCompanyIprTrademarkExWarehouseCommandExecutor;
	private DataCompanyIprTrademarkCreateCommandExecutor dataCompanyIprTrademarkCreateCommandExecutor;
	private IDataCompanyIprTrademarkService iDataCompanyIprTrademarkService;
	private DataCompanyIprTrademarkUpdateCommandExecutor dataCompanyIprTrademarkUpdateCommandExecutor;


	/**
	 * 企业知识产权商标入库
	 * @param dataCompanyIprTrademarkWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkExWarehouseVO> warehouse(DataCompanyIprTrademarkWarehouseCommand dataCompanyIprTrademarkWarehouseCommand) {
		SingleResponse<DataCompanyIprTrademarkExWarehouseVO> dataCompanyIprTrademarkExWarehouseVOSingleResponse = null;
		DataCompanyIprTrademarkExWarehouseVO dataCompanyIprTrademarkExWarehouseVO = null;
		String regNo = dataCompanyIprTrademarkWarehouseCommand.getRegNo();
		if (StrUtil.isNotEmpty(regNo)) {
			dataCompanyIprTrademarkExWarehouseVOSingleResponse = dataCompanyIprTrademarkExWarehouseCommandExecutor.exWarehouseByRegNo(regNo);
		}
		if (dataCompanyIprTrademarkExWarehouseVOSingleResponse == null || dataCompanyIprTrademarkExWarehouseVOSingleResponse.getData() == null) {
			if (StrUtil.isNotEmpty(dataCompanyIprTrademarkWarehouseCommand.getApplyNo())) {
				dataCompanyIprTrademarkExWarehouseVOSingleResponse = dataCompanyIprTrademarkExWarehouseCommandExecutor.exWarehouseByApplyNo(dataCompanyIprTrademarkWarehouseCommand.getApplyNo());
			}
		}

		dataCompanyIprTrademarkExWarehouseVO = dataCompanyIprTrademarkExWarehouseVOSingleResponse == null ? null : dataCompanyIprTrademarkExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprTrademarkExWarehouseVO == null) {
			DataCompanyIprTrademarkCreateCommand dataCompanyIprTrademarkCreateCommand = DataCompanyIprTrademarkCreateCommand.createByWarehouseCommand(dataCompanyIprTrademarkWarehouseCommand);
			SingleResponse<DataCompanyIprTrademarkVO> dataCompanyIprTrademarkVOSingleResponse = dataCompanyIprTrademarkCreateCommandExecutor.execute(dataCompanyIprTrademarkCreateCommand);
			Long id = dataCompanyIprTrademarkVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprTrademarkExWarehouseVOSingleResponse = dataCompanyIprTrademarkExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprTrademarkExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprTrademarkExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprTrademarkWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprTrademarkExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprTrademarkWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprTrademarkService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprTrademarkExWarehouseVOSingleResponse = dataCompanyIprTrademarkExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprTrademarkExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprTrademarkUpdateCommand dataCompanyIprTrademarkUpdateCommand = DataCompanyIprTrademarkUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprTrademarkExWarehouseVO.getVersion(),
						dataCompanyIprTrademarkWarehouseCommand
				);
				dataCompanyIprTrademarkUpdateCommandExecutor.execute(dataCompanyIprTrademarkUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprTrademarkExWarehouseVOSingleResponse = dataCompanyIprTrademarkExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprTrademarkExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprTrademarkExWarehouseCommandExecutor(DataCompanyIprTrademarkExWarehouseCommandExecutor dataCompanyIprTrademarkExWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkExWarehouseCommandExecutor = dataCompanyIprTrademarkExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprTrademarkCreateCommandExecutor(DataCompanyIprTrademarkCreateCommandExecutor dataCompanyIprTrademarkCreateCommandExecutor) {
		this.dataCompanyIprTrademarkCreateCommandExecutor = dataCompanyIprTrademarkCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprTrademarkService(IDataCompanyIprTrademarkService iDataCompanyIprTrademarkService) {
		this.iDataCompanyIprTrademarkService = iDataCompanyIprTrademarkService;
	}
	@Autowired
	public void setDataCompanyIprTrademarkUpdateCommandExecutor(DataCompanyIprTrademarkUpdateCommandExecutor dataCompanyIprTrademarkUpdateCommandExecutor) {
		this.dataCompanyIprTrademarkUpdateCommandExecutor = dataCompanyIprTrademarkUpdateCommandExecutor;
	}
}
