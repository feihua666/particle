package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentContentCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentContentUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentContentCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentContentUpdateCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentContentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentContentService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业裁判文书内容入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentContentWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyJudgmentDocumentContentExWarehouseCommandExecutor dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor;
	private DataCompanyJudgmentDocumentContentCreateCommandExecutor dataCompanyJudgmentDocumentContentCreateCommandExecutor;
	private IDataCompanyJudgmentDocumentContentService iDataCompanyJudgmentDocumentContentService;
	private DataCompanyJudgmentDocumentContentUpdateCommandExecutor dataCompanyJudgmentDocumentContentUpdateCommandExecutor;


	/**
	 * 企业裁判文书内容入库
	 * @param dataCompanyJudgmentDocumentContentWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentContentExWarehouseVO> warehouse(DataCompanyJudgmentDocumentContentWarehouseCommand dataCompanyJudgmentDocumentContentWarehouseCommand) {
		DataCompanyJudgmentDocumentContentExWarehouseQueryCommand dataCompanyJudgmentDocumentContentExWarehouseQueryCommand = DataCompanyJudgmentDocumentContentExWarehouseQueryCommand.create(dataCompanyJudgmentDocumentContentWarehouseCommand.getCompanyJudgmentDocumentId());
		SingleResponse<DataCompanyJudgmentDocumentContentExWarehouseVO> dataCompanyJudgmentDocumentContentExWarehouseVOSingleResponse = dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor.exWarehouse(dataCompanyJudgmentDocumentContentExWarehouseQueryCommand);
		DataCompanyJudgmentDocumentContentExWarehouseVO dataCompanyJudgmentDocumentContentExWarehouseVO = dataCompanyJudgmentDocumentContentExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyJudgmentDocumentContentExWarehouseVO == null) {
			DataCompanyJudgmentDocumentContentCreateCommand dataCompanyJudgmentDocumentContentCreateCommand = DataCompanyJudgmentDocumentContentCreateCommand.createByWarehouseCommand(dataCompanyJudgmentDocumentContentWarehouseCommand);
			dataCompanyJudgmentDocumentContentCreateCommandExecutor.execute(dataCompanyJudgmentDocumentContentCreateCommand);
			// 新增后重新查询，返回最新数据
			dataCompanyJudgmentDocumentContentExWarehouseVOSingleResponse = dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor.exWarehouse(dataCompanyJudgmentDocumentContentExWarehouseQueryCommand);
			return dataCompanyJudgmentDocumentContentExWarehouseVOSingleResponse;
		}else {
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyJudgmentDocumentContentWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyJudgmentDocumentContentExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyJudgmentDocumentContentWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyJudgmentDocumentContentService.updateLatestHandleAt(dataCompanyJudgmentDocumentContentExWarehouseVO.getId());
				// 如果没有需要更新的字段，则直接返回
				return dataCompanyJudgmentDocumentContentExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyJudgmentDocumentContentUpdateCommand dataCompanyJudgmentDocumentContentUpdateCommand = DataCompanyJudgmentDocumentContentUpdateCommand.createByWarehouseCommand(
						dataCompanyJudgmentDocumentContentExWarehouseVO.getId(),
						dataCompanyJudgmentDocumentContentExWarehouseVO.getVersion(),
						dataCompanyJudgmentDocumentContentWarehouseCommand
				);
				dataCompanyJudgmentDocumentContentUpdateCommandExecutor.execute(dataCompanyJudgmentDocumentContentUpdateCommand);
			}

			// 更新完成后，新增的情况已经在新增逻辑里面直接返回了，查询返回
			dataCompanyJudgmentDocumentContentExWarehouseVOSingleResponse = dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor.exWarehouse(dataCompanyJudgmentDocumentContentExWarehouseQueryCommand);
			return dataCompanyJudgmentDocumentContentExWarehouseVOSingleResponse;
		}
	}

	@Autowired
	public void setDataCompanyJudgmentDocumentContentExWarehouseCommandExecutor(DataCompanyJudgmentDocumentContentExWarehouseCommandExecutor dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor) {
		this.dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor = dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyJudgmentDocumentContentCreateCommandExecutor(DataCompanyJudgmentDocumentContentCreateCommandExecutor dataCompanyJudgmentDocumentContentCreateCommandExecutor) {
		this.dataCompanyJudgmentDocumentContentCreateCommandExecutor = dataCompanyJudgmentDocumentContentCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyJudgmentDocumentContentService(IDataCompanyJudgmentDocumentContentService iDataCompanyJudgmentDocumentContentService) {
		this.iDataCompanyJudgmentDocumentContentService = iDataCompanyJudgmentDocumentContentService;
	}
	@Autowired
	public void setDataCompanyJudgmentDocumentContentUpdateCommandExecutor(DataCompanyJudgmentDocumentContentUpdateCommandExecutor dataCompanyJudgmentDocumentContentUpdateCommandExecutor) {
		this.dataCompanyJudgmentDocumentContentUpdateCommandExecutor = dataCompanyJudgmentDocumentContentUpdateCommandExecutor;
	}
}
