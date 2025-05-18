package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDebtorCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDebtorUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyJudgmentDebtorExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDebtorCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDebtorUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDebtorWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDebtorVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDebtorExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDebtorService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业被执行人入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyJudgmentDebtorWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyJudgmentDebtorExWarehouseCommandExecutor dataCompanyJudgmentDebtorExWarehouseCommandExecutor;
	private DataCompanyJudgmentDebtorCreateCommandExecutor dataCompanyJudgmentDebtorCreateCommandExecutor;
	private IDataCompanyJudgmentDebtorService iDataCompanyJudgmentDebtorService;
	private DataCompanyJudgmentDebtorUpdateCommandExecutor dataCompanyJudgmentDebtorUpdateCommandExecutor;


	/**
	 * 企业被执行人入库
	 * @param dataCompanyJudgmentDebtorWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDebtorExWarehouseVO> warehouse(DataCompanyJudgmentDebtorWarehouseCommand dataCompanyJudgmentDebtorWarehouseCommand) {
		SingleResponse<DataCompanyJudgmentDebtorExWarehouseVO> dataCompanyJudgmentDebtorExWarehouseVOSingleResponse = null;
		DataCompanyJudgmentDebtorExWarehouseVO dataCompanyJudgmentDebtorExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyJudgmentDebtorWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyJudgmentDebtorExWarehouseVOSingleResponse = dataCompanyJudgmentDebtorExWarehouseCommandExecutor.exWarehouseByCompanyIdAndDataMd5(dataCompanyJudgmentDebtorWarehouseCommand.getExecutedPersonCompanyId(),obtainDataMd5);
		}

		dataCompanyJudgmentDebtorExWarehouseVO = dataCompanyJudgmentDebtorExWarehouseVOSingleResponse == null ? null : dataCompanyJudgmentDebtorExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyJudgmentDebtorExWarehouseVO == null) {
			DataCompanyJudgmentDebtorCreateCommand dataCompanyJudgmentDebtorCreateCommand = DataCompanyJudgmentDebtorCreateCommand.createByWarehouseCommand(dataCompanyJudgmentDebtorWarehouseCommand);
			SingleResponse<DataCompanyJudgmentDebtorVO> dataCompanyJudgmentDebtorVOSingleResponse = dataCompanyJudgmentDebtorCreateCommandExecutor.execute(dataCompanyJudgmentDebtorCreateCommand);
			Long id = dataCompanyJudgmentDebtorVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyJudgmentDebtorExWarehouseVOSingleResponse = dataCompanyJudgmentDebtorExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyJudgmentDebtorExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyJudgmentDebtorExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyJudgmentDebtorWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyJudgmentDebtorExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyJudgmentDebtorWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyJudgmentDebtorService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyJudgmentDebtorExWarehouseVOSingleResponse = dataCompanyJudgmentDebtorExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyJudgmentDebtorExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyJudgmentDebtorUpdateCommand dataCompanyJudgmentDebtorUpdateCommand = DataCompanyJudgmentDebtorUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyJudgmentDebtorExWarehouseVO.getVersion(),
						dataCompanyJudgmentDebtorWarehouseCommand
				);
				dataCompanyJudgmentDebtorUpdateCommandExecutor.execute(dataCompanyJudgmentDebtorUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyJudgmentDebtorExWarehouseVOSingleResponse = dataCompanyJudgmentDebtorExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyJudgmentDebtorExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyJudgmentDebtorExWarehouseCommandExecutor(DataCompanyJudgmentDebtorExWarehouseCommandExecutor dataCompanyJudgmentDebtorExWarehouseCommandExecutor) {
		this.dataCompanyJudgmentDebtorExWarehouseCommandExecutor = dataCompanyJudgmentDebtorExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyJudgmentDebtorCreateCommandExecutor(DataCompanyJudgmentDebtorCreateCommandExecutor dataCompanyJudgmentDebtorCreateCommandExecutor) {
		this.dataCompanyJudgmentDebtorCreateCommandExecutor = dataCompanyJudgmentDebtorCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyJudgmentDebtorService(IDataCompanyJudgmentDebtorService iDataCompanyJudgmentDebtorService) {
		this.iDataCompanyJudgmentDebtorService = iDataCompanyJudgmentDebtorService;
	}
	@Autowired
	public void setDataCompanyJudgmentDebtorUpdateCommandExecutor(DataCompanyJudgmentDebtorUpdateCommandExecutor dataCompanyJudgmentDebtorUpdateCommandExecutor) {
		this.dataCompanyJudgmentDebtorUpdateCommandExecutor = dataCompanyJudgmentDebtorUpdateCommandExecutor;
	}
}
