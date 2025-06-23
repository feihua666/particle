package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprGeograCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprGeograUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprGeograExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprGeograWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprGeograService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权地理标识入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprGeograWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprGeograExWarehouseCommandExecutor dataCompanyIprGeograExWarehouseCommandExecutor;
	private DataCompanyIprGeograCreateCommandExecutor dataCompanyIprGeograCreateCommandExecutor;
	private IDataCompanyIprGeograService iDataCompanyIprGeograService;
	private DataCompanyIprGeograUpdateCommandExecutor dataCompanyIprGeograUpdateCommandExecutor;


	/**
	 * 企业知识产权地理标识入库
	 * @param dataCompanyIprGeograWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograExWarehouseVO> warehouse(DataCompanyIprGeograWarehouseCommand dataCompanyIprGeograWarehouseCommand) {
		SingleResponse<DataCompanyIprGeograExWarehouseVO> dataCompanyIprGeograExWarehouseVOSingleResponse = null;
		DataCompanyIprGeograExWarehouseVO dataCompanyIprGeograExWarehouseVO = null;
		String publicNo = dataCompanyIprGeograWarehouseCommand.getPublicNo();
		if (StrUtil.isNotEmpty(publicNo)) {
			dataCompanyIprGeograExWarehouseVOSingleResponse = dataCompanyIprGeograExWarehouseCommandExecutor.exWarehouseByPublicNo(publicNo);
		}

		dataCompanyIprGeograExWarehouseVO = dataCompanyIprGeograExWarehouseVOSingleResponse == null ? null : dataCompanyIprGeograExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprGeograExWarehouseVO == null) {
			DataCompanyIprGeograCreateCommand dataCompanyIprGeograCreateCommand = DataCompanyIprGeograCreateCommand.createByWarehouseCommand(dataCompanyIprGeograWarehouseCommand);
			SingleResponse<DataCompanyIprGeograVO> dataCompanyIprGeograVOSingleResponse = dataCompanyIprGeograCreateCommandExecutor.execute(dataCompanyIprGeograCreateCommand);
			Long id = dataCompanyIprGeograVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprGeograExWarehouseVOSingleResponse = dataCompanyIprGeograExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprGeograExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprGeograExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprGeograWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprGeograExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprGeograWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprGeograService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprGeograExWarehouseVOSingleResponse = dataCompanyIprGeograExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprGeograExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprGeograUpdateCommand dataCompanyIprGeograUpdateCommand = DataCompanyIprGeograUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprGeograExWarehouseVO.getVersion(),
						dataCompanyIprGeograWarehouseCommand
				);
				dataCompanyIprGeograUpdateCommandExecutor.execute(dataCompanyIprGeograUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprGeograExWarehouseVOSingleResponse = dataCompanyIprGeograExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprGeograExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprGeograExWarehouseCommandExecutor(DataCompanyIprGeograExWarehouseCommandExecutor dataCompanyIprGeograExWarehouseCommandExecutor) {
		this.dataCompanyIprGeograExWarehouseCommandExecutor = dataCompanyIprGeograExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprGeograCreateCommandExecutor(DataCompanyIprGeograCreateCommandExecutor dataCompanyIprGeograCreateCommandExecutor) {
		this.dataCompanyIprGeograCreateCommandExecutor = dataCompanyIprGeograCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprGeograService(IDataCompanyIprGeograService iDataCompanyIprGeograService) {
		this.iDataCompanyIprGeograService = iDataCompanyIprGeograService;
	}
	@Autowired
	public void setDataCompanyIprGeograUpdateCommandExecutor(DataCompanyIprGeograUpdateCommandExecutor dataCompanyIprGeograUpdateCommandExecutor) {
		this.dataCompanyIprGeograUpdateCommandExecutor = dataCompanyIprGeograUpdateCommandExecutor;
	}
}
