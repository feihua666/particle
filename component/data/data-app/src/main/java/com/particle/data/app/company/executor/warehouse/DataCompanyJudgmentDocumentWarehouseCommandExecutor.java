package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyJudgmentDocumentUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyJudgmentDocumentExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDocumentUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业裁判文书入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyJudgmentDocumentExWarehouseCommandExecutor dataCompanyJudgmentDocumentExWarehouseCommandExecutor;
	private DataCompanyJudgmentDocumentCreateCommandExecutor dataCompanyJudgmentDocumentCreateCommandExecutor;
	private IDataCompanyJudgmentDocumentService iDataCompanyJudgmentDocumentService;
	private DataCompanyJudgmentDocumentUpdateCommandExecutor dataCompanyJudgmentDocumentUpdateCommandExecutor;


	/**
	 * 企业裁判文书入库
	 * @param dataCompanyJudgmentDocumentWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentExWarehouseVO> warehouse(DataCompanyJudgmentDocumentWarehouseCommand dataCompanyJudgmentDocumentWarehouseCommand) {
		SingleResponse<DataCompanyJudgmentDocumentExWarehouseVO> dataCompanyJudgmentDocumentExWarehouseVOSingleResponse = null;
		DataCompanyJudgmentDocumentExWarehouseVO dataCompanyJudgmentDocumentExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyJudgmentDocumentWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyJudgmentDocumentExWarehouseVOSingleResponse = dataCompanyJudgmentDocumentExWarehouseCommandExecutor.exWarehouseByDataMd5(obtainDataMd5);
		}

		dataCompanyJudgmentDocumentExWarehouseVO = dataCompanyJudgmentDocumentExWarehouseVOSingleResponse == null ? null : dataCompanyJudgmentDocumentExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyJudgmentDocumentExWarehouseVO == null) {
			DataCompanyJudgmentDocumentCreateCommand dataCompanyJudgmentDocumentCreateCommand = DataCompanyJudgmentDocumentCreateCommand.createByWarehouseCommand(dataCompanyJudgmentDocumentWarehouseCommand);
			SingleResponse<DataCompanyJudgmentDocumentVO> dataCompanyJudgmentDocumentVOSingleResponse = dataCompanyJudgmentDocumentCreateCommandExecutor.execute(dataCompanyJudgmentDocumentCreateCommand);
			Long id = dataCompanyJudgmentDocumentVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyJudgmentDocumentExWarehouseVOSingleResponse = dataCompanyJudgmentDocumentExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyJudgmentDocumentExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyJudgmentDocumentExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyJudgmentDocumentWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyJudgmentDocumentExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyJudgmentDocumentWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyJudgmentDocumentService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyJudgmentDocumentExWarehouseVOSingleResponse = dataCompanyJudgmentDocumentExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyJudgmentDocumentExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyJudgmentDocumentUpdateCommand dataCompanyJudgmentDocumentUpdateCommand = DataCompanyJudgmentDocumentUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyJudgmentDocumentExWarehouseVO.getVersion(),
						dataCompanyJudgmentDocumentWarehouseCommand
				);
				dataCompanyJudgmentDocumentUpdateCommandExecutor.execute(dataCompanyJudgmentDocumentUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyJudgmentDocumentExWarehouseVOSingleResponse = dataCompanyJudgmentDocumentExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyJudgmentDocumentExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyJudgmentDocumentExWarehouseCommandExecutor(DataCompanyJudgmentDocumentExWarehouseCommandExecutor dataCompanyJudgmentDocumentExWarehouseCommandExecutor) {
		this.dataCompanyJudgmentDocumentExWarehouseCommandExecutor = dataCompanyJudgmentDocumentExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyJudgmentDocumentCreateCommandExecutor(DataCompanyJudgmentDocumentCreateCommandExecutor dataCompanyJudgmentDocumentCreateCommandExecutor) {
		this.dataCompanyJudgmentDocumentCreateCommandExecutor = dataCompanyJudgmentDocumentCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyJudgmentDocumentService(IDataCompanyJudgmentDocumentService iDataCompanyJudgmentDocumentService) {
		this.iDataCompanyJudgmentDocumentService = iDataCompanyJudgmentDocumentService;
	}
	@Autowired
	public void setDataCompanyJudgmentDocumentUpdateCommandExecutor(DataCompanyJudgmentDocumentUpdateCommandExecutor dataCompanyJudgmentDocumentUpdateCommandExecutor) {
		this.dataCompanyJudgmentDocumentUpdateCommandExecutor = dataCompanyJudgmentDocumentUpdateCommandExecutor;
	}
}
