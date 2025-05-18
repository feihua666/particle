package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权专利入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPatentWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPatentExWarehouseCommandExecutor dataCompanyIprPatentExWarehouseCommandExecutor;
	private DataCompanyIprPatentCreateCommandExecutor dataCompanyIprPatentCreateCommandExecutor;
	private IDataCompanyIprPatentService iDataCompanyIprPatentService;
	private DataCompanyIprPatentUpdateCommandExecutor dataCompanyIprPatentUpdateCommandExecutor;


	/**
	 * 企业知识产权专利入库
	 * @param dataCompanyIprPatentWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentExWarehouseVO> warehouse(DataCompanyIprPatentWarehouseCommand dataCompanyIprPatentWarehouseCommand) {
		SingleResponse<DataCompanyIprPatentExWarehouseVO> dataCompanyIprPatentExWarehouseVOSingleResponse = null;
		DataCompanyIprPatentExWarehouseVO dataCompanyIprPatentExWarehouseVO = null;
		if (StrUtil.isNotEmpty(dataCompanyIprPatentWarehouseCommand.getApplyNo())) {
			dataCompanyIprPatentExWarehouseVOSingleResponse = dataCompanyIprPatentExWarehouseCommandExecutor.exWarehouseByApplyNo(dataCompanyIprPatentWarehouseCommand.getApplyNo());
		}
		if (dataCompanyIprPatentExWarehouseVOSingleResponse == null || dataCompanyIprPatentExWarehouseVOSingleResponse.getData() == null) {
			if (StrUtil.isNotEmpty(dataCompanyIprPatentWarehouseCommand.getPublicNo())) {
				dataCompanyIprPatentExWarehouseVOSingleResponse = dataCompanyIprPatentExWarehouseCommandExecutor.exWarehouseByPublicNo(dataCompanyIprPatentWarehouseCommand.getPublicNo());
			}
		}

		dataCompanyIprPatentExWarehouseVO = dataCompanyIprPatentExWarehouseVOSingleResponse == null ? null : dataCompanyIprPatentExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPatentExWarehouseVO == null) {
			DataCompanyIprPatentCreateCommand dataCompanyIprPatentCreateCommand = DataCompanyIprPatentCreateCommand.createByWarehouseCommand(dataCompanyIprPatentWarehouseCommand);
			SingleResponse<DataCompanyIprPatentVO> dataCompanyIprPatentVOSingleResponse = dataCompanyIprPatentCreateCommandExecutor.execute(dataCompanyIprPatentCreateCommand);
			Long id = dataCompanyIprPatentVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprPatentExWarehouseVOSingleResponse = dataCompanyIprPatentExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprPatentExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprPatentExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPatentWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPatentExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPatentWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPatentService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprPatentExWarehouseVOSingleResponse = dataCompanyIprPatentExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPatentUpdateCommand dataCompanyIprPatentUpdateCommand = DataCompanyIprPatentUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprPatentExWarehouseVO.getVersion(),
						dataCompanyIprPatentWarehouseCommand
				);
				dataCompanyIprPatentUpdateCommandExecutor.execute(dataCompanyIprPatentUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprPatentExWarehouseVOSingleResponse = dataCompanyIprPatentExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprPatentExWarehouseCommandExecutor(DataCompanyIprPatentExWarehouseCommandExecutor dataCompanyIprPatentExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentExWarehouseCommandExecutor = dataCompanyIprPatentExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPatentCreateCommandExecutor(DataCompanyIprPatentCreateCommandExecutor dataCompanyIprPatentCreateCommandExecutor) {
		this.dataCompanyIprPatentCreateCommandExecutor = dataCompanyIprPatentCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPatentService(IDataCompanyIprPatentService iDataCompanyIprPatentService) {
		this.iDataCompanyIprPatentService = iDataCompanyIprPatentService;
	}
	@Autowired
	public void setDataCompanyIprPatentUpdateCommandExecutor(DataCompanyIprPatentUpdateCommandExecutor dataCompanyIprPatentUpdateCommandExecutor) {
		this.dataCompanyIprPatentUpdateCommandExecutor = dataCompanyIprPatentUpdateCommandExecutor;
	}
}
