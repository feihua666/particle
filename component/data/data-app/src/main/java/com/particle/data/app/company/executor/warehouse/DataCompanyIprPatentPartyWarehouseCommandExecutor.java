package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentPartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentPartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentPartyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPartyUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPartyExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPartyService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权当事人入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPatentPartyWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPatentPartyExWarehouseCommandExecutor dataCompanyIprPatentPartyExWarehouseCommandExecutor;
	private DataCompanyIprPatentPartyCreateCommandExecutor dataCompanyIprPatentPartyCreateCommandExecutor;
	private IDataCompanyIprPatentPartyService iDataCompanyIprPatentPartyService;
	private DataCompanyIprPatentPartyUpdateCommandExecutor dataCompanyIprPatentPartyUpdateCommandExecutor;


	/**
	 * 企业知识产权当事人入库
	 * @param dataCompanyIprPatentPartyWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPartyExWarehouseVO> warehouse(DataCompanyIprPatentPartyWarehouseCommand dataCompanyIprPatentPartyWarehouseCommand) {
		SingleResponse<DataCompanyIprPatentPartyExWarehouseVO> dataCompanyIprPatentPartyExWarehouseVOSingleResponse = null;
		DataCompanyIprPatentPartyExWarehouseVO dataCompanyIprPatentPartyExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyIprPatentPartyWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyIprPatentPartyExWarehouseVOSingleResponse = dataCompanyIprPatentPartyExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndDataMd5(dataCompanyIprPatentPartyWarehouseCommand.getCompanyIprPatentId(),dataCompanyIprPatentPartyWarehouseCommand.obtainDataMd5());
		}

		dataCompanyIprPatentPartyExWarehouseVO = dataCompanyIprPatentPartyExWarehouseVOSingleResponse == null ? null : dataCompanyIprPatentPartyExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPatentPartyExWarehouseVO == null) {
			DataCompanyIprPatentPartyCreateCommand dataCompanyIprPatentPartyCreateCommand = DataCompanyIprPatentPartyCreateCommand.createByWarehouseCommand(dataCompanyIprPatentPartyWarehouseCommand);
			SingleResponse<DataCompanyIprPatentPartyVO> dataCompanyIprPatentPartyVOSingleResponse = dataCompanyIprPatentPartyCreateCommandExecutor.execute(dataCompanyIprPatentPartyCreateCommand);
			Long id = dataCompanyIprPatentPartyVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprPatentPartyExWarehouseVOSingleResponse = dataCompanyIprPatentPartyExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprPatentPartyExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprPatentPartyExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPatentPartyWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPatentPartyExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPatentPartyWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPatentPartyService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprPatentPartyExWarehouseVOSingleResponse = dataCompanyIprPatentPartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentPartyExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPatentPartyUpdateCommand dataCompanyIprPatentPartyUpdateCommand = DataCompanyIprPatentPartyUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprPatentPartyExWarehouseVO.getVersion(),
						dataCompanyIprPatentPartyWarehouseCommand
				);
				dataCompanyIprPatentPartyUpdateCommandExecutor.execute(dataCompanyIprPatentPartyUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprPatentPartyExWarehouseVOSingleResponse = dataCompanyIprPatentPartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentPartyExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprPatentPartyExWarehouseCommandExecutor(DataCompanyIprPatentPartyExWarehouseCommandExecutor dataCompanyIprPatentPartyExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentPartyExWarehouseCommandExecutor = dataCompanyIprPatentPartyExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPatentPartyCreateCommandExecutor(DataCompanyIprPatentPartyCreateCommandExecutor dataCompanyIprPatentPartyCreateCommandExecutor) {
		this.dataCompanyIprPatentPartyCreateCommandExecutor = dataCompanyIprPatentPartyCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPatentPartyService(IDataCompanyIprPatentPartyService iDataCompanyIprPatentPartyService) {
		this.iDataCompanyIprPatentPartyService = iDataCompanyIprPatentPartyService;
	}
	@Autowired
	public void setDataCompanyIprPatentPartyUpdateCommandExecutor(DataCompanyIprPatentPartyUpdateCommandExecutor dataCompanyIprPatentPartyUpdateCommandExecutor) {
		this.dataCompanyIprPatentPartyUpdateCommandExecutor = dataCompanyIprPatentPartyUpdateCommandExecutor;
	}
}
