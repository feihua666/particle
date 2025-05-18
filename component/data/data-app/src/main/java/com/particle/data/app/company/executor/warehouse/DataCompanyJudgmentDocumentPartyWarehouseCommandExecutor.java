package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentPartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentPartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentPartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentPartyUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentPartyVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentPartyService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业裁判文书当事人入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentPartyWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor;
	private DataCompanyJudgmentDocumentPartyCreateCommandExecutor dataCompanyJudgmentDocumentPartyCreateCommandExecutor;
	private IDataCompanyJudgmentDocumentPartyService iDataCompanyJudgmentDocumentPartyService;
	private DataCompanyJudgmentDocumentPartyUpdateCommandExecutor dataCompanyJudgmentDocumentPartyUpdateCommandExecutor;


	/**
	 * 企业裁判文书当事人入库
	 * @param dataCompanyJudgmentDocumentPartyWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentPartyExWarehouseVO> warehouse(DataCompanyJudgmentDocumentPartyWarehouseCommand dataCompanyJudgmentDocumentPartyWarehouseCommand) {
		SingleResponse<DataCompanyJudgmentDocumentPartyExWarehouseVO> dataCompanyJudgmentDocumentPartyExWarehouseVOSingleResponse = null;
		DataCompanyJudgmentDocumentPartyExWarehouseVO dataCompanyJudgmentDocumentPartyExWarehouseVO = null;
        if (dataCompanyJudgmentDocumentPartyWarehouseCommand.getCompanyJudgmentDocumentId() != null) {
			dataCompanyJudgmentDocumentPartyExWarehouseVOSingleResponse = dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor.exWarehouseByCompanyJudgmentDocumentIdAndDataMd5(dataCompanyJudgmentDocumentPartyWarehouseCommand.getCompanyJudgmentDocumentId(),dataCompanyJudgmentDocumentPartyWarehouseCommand.obtainDataMd5());
		}

		dataCompanyJudgmentDocumentPartyExWarehouseVO = dataCompanyJudgmentDocumentPartyExWarehouseVOSingleResponse == null ? null : dataCompanyJudgmentDocumentPartyExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyJudgmentDocumentPartyExWarehouseVO == null) {
			DataCompanyJudgmentDocumentPartyCreateCommand dataCompanyJudgmentDocumentPartyCreateCommand = DataCompanyJudgmentDocumentPartyCreateCommand.createByWarehouseCommand(dataCompanyJudgmentDocumentPartyWarehouseCommand);
			SingleResponse<DataCompanyJudgmentDocumentPartyVO> dataCompanyJudgmentDocumentPartyVOSingleResponse = dataCompanyJudgmentDocumentPartyCreateCommandExecutor.execute(dataCompanyJudgmentDocumentPartyCreateCommand);
			Long id = dataCompanyJudgmentDocumentPartyVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyJudgmentDocumentPartyExWarehouseVOSingleResponse = dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyJudgmentDocumentPartyExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyJudgmentDocumentPartyExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyJudgmentDocumentPartyWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyJudgmentDocumentPartyExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyJudgmentDocumentPartyWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyJudgmentDocumentPartyService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyJudgmentDocumentPartyExWarehouseVOSingleResponse = dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyJudgmentDocumentPartyExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyJudgmentDocumentPartyUpdateCommand dataCompanyJudgmentDocumentPartyUpdateCommand = DataCompanyJudgmentDocumentPartyUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyJudgmentDocumentPartyExWarehouseVO.getVersion(),
						dataCompanyJudgmentDocumentPartyWarehouseCommand
				);
				dataCompanyJudgmentDocumentPartyUpdateCommandExecutor.execute(dataCompanyJudgmentDocumentPartyUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyJudgmentDocumentPartyExWarehouseVOSingleResponse = dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyJudgmentDocumentPartyExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor(DataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor) {
		this.dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor = dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyJudgmentDocumentPartyCreateCommandExecutor(DataCompanyJudgmentDocumentPartyCreateCommandExecutor dataCompanyJudgmentDocumentPartyCreateCommandExecutor) {
		this.dataCompanyJudgmentDocumentPartyCreateCommandExecutor = dataCompanyJudgmentDocumentPartyCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyJudgmentDocumentPartyService(IDataCompanyJudgmentDocumentPartyService iDataCompanyJudgmentDocumentPartyService) {
		this.iDataCompanyJudgmentDocumentPartyService = iDataCompanyJudgmentDocumentPartyService;
	}
	@Autowired
	public void setDataCompanyJudgmentDocumentPartyUpdateCommandExecutor(DataCompanyJudgmentDocumentPartyUpdateCommandExecutor dataCompanyJudgmentDocumentPartyUpdateCommandExecutor) {
		this.dataCompanyJudgmentDocumentPartyUpdateCommandExecutor = dataCompanyJudgmentDocumentPartyUpdateCommandExecutor;
	}
}
