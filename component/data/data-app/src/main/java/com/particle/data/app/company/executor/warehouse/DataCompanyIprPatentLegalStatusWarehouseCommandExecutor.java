package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentLegalStatusCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentLegalStatusUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentLegalStatusExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLegalStatusCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLegalStatusUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentLegalStatusWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLegalStatusVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLegalStatusExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentLegalStatusService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权专利法律状态入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPatentLegalStatusWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPatentLegalStatusExWarehouseCommandExecutor dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor;
	private DataCompanyIprPatentLegalStatusCreateCommandExecutor dataCompanyIprPatentLegalStatusCreateCommandExecutor;
	private IDataCompanyIprPatentLegalStatusService iDataCompanyIprPatentLegalStatusService;
	private DataCompanyIprPatentLegalStatusUpdateCommandExecutor dataCompanyIprPatentLegalStatusUpdateCommandExecutor;


	/**
	 * 企业知识产权专利法律状态入库
	 * @param dataCompanyIprPatentLegalStatusWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLegalStatusExWarehouseVO> warehouse(DataCompanyIprPatentLegalStatusWarehouseCommand dataCompanyIprPatentLegalStatusWarehouseCommand) {
		SingleResponse<DataCompanyIprPatentLegalStatusExWarehouseVO> dataCompanyIprPatentLegalStatusExWarehouseVOSingleResponse = null;
		DataCompanyIprPatentLegalStatusExWarehouseVO dataCompanyIprPatentLegalStatusExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyIprPatentLegalStatusWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyIprPatentLegalStatusExWarehouseVOSingleResponse = dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndDataMd5(dataCompanyIprPatentLegalStatusWarehouseCommand.getCompanyIprPatentId(),obtainDataMd5);
		}

		dataCompanyIprPatentLegalStatusExWarehouseVO = dataCompanyIprPatentLegalStatusExWarehouseVOSingleResponse == null ? null : dataCompanyIprPatentLegalStatusExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPatentLegalStatusExWarehouseVO == null) {
			DataCompanyIprPatentLegalStatusCreateCommand dataCompanyIprPatentLegalStatusCreateCommand = DataCompanyIprPatentLegalStatusCreateCommand.createByWarehouseCommand(dataCompanyIprPatentLegalStatusWarehouseCommand);
			SingleResponse<DataCompanyIprPatentLegalStatusVO> dataCompanyIprPatentLegalStatusVOSingleResponse = dataCompanyIprPatentLegalStatusCreateCommandExecutor.execute(dataCompanyIprPatentLegalStatusCreateCommand);
			Long id = dataCompanyIprPatentLegalStatusVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprPatentLegalStatusExWarehouseVOSingleResponse = dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprPatentLegalStatusExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprPatentLegalStatusExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPatentLegalStatusWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPatentLegalStatusExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPatentLegalStatusWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPatentLegalStatusService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprPatentLegalStatusExWarehouseVOSingleResponse = dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentLegalStatusExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPatentLegalStatusUpdateCommand dataCompanyIprPatentLegalStatusUpdateCommand = DataCompanyIprPatentLegalStatusUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprPatentLegalStatusExWarehouseVO.getVersion(),
						dataCompanyIprPatentLegalStatusWarehouseCommand
				);
				dataCompanyIprPatentLegalStatusUpdateCommandExecutor.execute(dataCompanyIprPatentLegalStatusUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprPatentLegalStatusExWarehouseVOSingleResponse = dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentLegalStatusExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprPatentLegalStatusExWarehouseCommandExecutor(DataCompanyIprPatentLegalStatusExWarehouseCommandExecutor dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor = dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPatentLegalStatusCreateCommandExecutor(DataCompanyIprPatentLegalStatusCreateCommandExecutor dataCompanyIprPatentLegalStatusCreateCommandExecutor) {
		this.dataCompanyIprPatentLegalStatusCreateCommandExecutor = dataCompanyIprPatentLegalStatusCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPatentLegalStatusService(IDataCompanyIprPatentLegalStatusService iDataCompanyIprPatentLegalStatusService) {
		this.iDataCompanyIprPatentLegalStatusService = iDataCompanyIprPatentLegalStatusService;
	}
	@Autowired
	public void setDataCompanyIprPatentLegalStatusUpdateCommandExecutor(DataCompanyIprPatentLegalStatusUpdateCommandExecutor dataCompanyIprPatentLegalStatusUpdateCommandExecutor) {
		this.dataCompanyIprPatentLegalStatusUpdateCommandExecutor = dataCompanyIprPatentLegalStatusUpdateCommandExecutor;
	}
}
