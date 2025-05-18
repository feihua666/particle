package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyDiscreditedJudgmentDebtorCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyDiscreditedJudgmentDebtorUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDiscreditedJudgmentDebtorWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyDiscreditedJudgmentDebtorVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyDiscreditedJudgmentDebtorService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业失信被执行人入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor;
	private DataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor dataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor;
	private IDataCompanyDiscreditedJudgmentDebtorService iDataCompanyDiscreditedJudgmentDebtorService;
	private DataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor dataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor;


	/**
	 * 企业失信被执行人入库
	 * @param dataCompanyDiscreditedJudgmentDebtorWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> warehouse(DataCompanyDiscreditedJudgmentDebtorWarehouseCommand dataCompanyDiscreditedJudgmentDebtorWarehouseCommand) {
		SingleResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> dataCompanyDiscreditedJudgmentDebtorExWarehouseVOSingleResponse = null;
		DataCompanyDiscreditedJudgmentDebtorExWarehouseVO dataCompanyDiscreditedJudgmentDebtorExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyDiscreditedJudgmentDebtorExWarehouseVOSingleResponse = dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor.exWarehouseByCompanyIdAndDataMd5(dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getDishonestExecutedPersonCompanyId(),obtainDataMd5);
		}
		dataCompanyDiscreditedJudgmentDebtorExWarehouseVO = dataCompanyDiscreditedJudgmentDebtorExWarehouseVOSingleResponse == null ? null : dataCompanyDiscreditedJudgmentDebtorExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyDiscreditedJudgmentDebtorExWarehouseVO == null) {
			DataCompanyDiscreditedJudgmentDebtorCreateCommand dataCompanyDiscreditedJudgmentDebtorCreateCommand = DataCompanyDiscreditedJudgmentDebtorCreateCommand.createByWarehouseCommand(dataCompanyDiscreditedJudgmentDebtorWarehouseCommand);
			SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> dataCompanyDiscreditedJudgmentDebtorVOSingleResponse = dataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor.execute(dataCompanyDiscreditedJudgmentDebtorCreateCommand);
			Long id = dataCompanyDiscreditedJudgmentDebtorVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyDiscreditedJudgmentDebtorExWarehouseVOSingleResponse = dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyDiscreditedJudgmentDebtorExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyDiscreditedJudgmentDebtorExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyDiscreditedJudgmentDebtorExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyDiscreditedJudgmentDebtorService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyDiscreditedJudgmentDebtorExWarehouseVOSingleResponse = dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyDiscreditedJudgmentDebtorExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyDiscreditedJudgmentDebtorUpdateCommand dataCompanyDiscreditedJudgmentDebtorUpdateCommand = DataCompanyDiscreditedJudgmentDebtorUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyDiscreditedJudgmentDebtorExWarehouseVO.getVersion(),
						dataCompanyDiscreditedJudgmentDebtorWarehouseCommand
				);
				dataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor.execute(dataCompanyDiscreditedJudgmentDebtorUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyDiscreditedJudgmentDebtorExWarehouseVOSingleResponse = dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyDiscreditedJudgmentDebtorExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor(DataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor) {
		this.dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor = dataCompanyDiscreditedJudgmentDebtorExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor(DataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor dataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor) {
		this.dataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor = dataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyDiscreditedJudgmentDebtorService(IDataCompanyDiscreditedJudgmentDebtorService iDataCompanyDiscreditedJudgmentDebtorService) {
		this.iDataCompanyDiscreditedJudgmentDebtorService = iDataCompanyDiscreditedJudgmentDebtorService;
	}
	@Autowired
	public void setDataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor(DataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor dataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor) {
		this.dataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor = dataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor;
	}
}
