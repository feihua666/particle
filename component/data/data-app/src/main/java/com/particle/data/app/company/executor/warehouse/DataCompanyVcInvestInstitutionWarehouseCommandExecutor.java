package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyVcInvestInstitutionCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcInvestInstitutionUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyVcInvestInstitutionCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcInvestInstitutionUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcInvestInstitutionWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcInvestInstitutionVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyVcInvestInstitutionService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业投资机构入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyVcInvestInstitutionWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyVcInvestInstitutionExWarehouseCommandExecutor dataCompanyVcInvestInstitutionExWarehouseCommandExecutor;
	private DataCompanyVcInvestInstitutionCreateCommandExecutor dataCompanyVcInvestInstitutionCreateCommandExecutor;
	private IDataCompanyVcInvestInstitutionService iDataCompanyVcInvestInstitutionService;
	private DataCompanyVcInvestInstitutionUpdateCommandExecutor dataCompanyVcInvestInstitutionUpdateCommandExecutor;


	/**
	 * 企业投资机构入库
	 * @param dataCompanyVcInvestInstitutionWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcInvestInstitutionExWarehouseVO> warehouse(DataCompanyVcInvestInstitutionWarehouseCommand dataCompanyVcInvestInstitutionWarehouseCommand) {
		SingleResponse<DataCompanyVcInvestInstitutionExWarehouseVO> dataCompanyVcInvestInstitutionExWarehouseVOSingleResponse = null;
		DataCompanyVcInvestInstitutionExWarehouseVO dataCompanyVcInvestInstitutionExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyVcInvestInstitutionWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyVcInvestInstitutionExWarehouseVOSingleResponse = dataCompanyVcInvestInstitutionExWarehouseCommandExecutor.exWarehouseByDataMd5(obtainDataMd5);
		}

		dataCompanyVcInvestInstitutionExWarehouseVO = dataCompanyVcInvestInstitutionExWarehouseVOSingleResponse == null ? null : dataCompanyVcInvestInstitutionExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyVcInvestInstitutionExWarehouseVO == null) {
			DataCompanyVcInvestInstitutionCreateCommand dataCompanyVcInvestInstitutionCreateCommand = DataCompanyVcInvestInstitutionCreateCommand.createByWarehouseCommand(dataCompanyVcInvestInstitutionWarehouseCommand);
			SingleResponse<DataCompanyVcInvestInstitutionVO> dataCompanyVcInvestInstitutionVOSingleResponse = dataCompanyVcInvestInstitutionCreateCommandExecutor.execute(dataCompanyVcInvestInstitutionCreateCommand);
			Long id = dataCompanyVcInvestInstitutionVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyVcInvestInstitutionExWarehouseVOSingleResponse = dataCompanyVcInvestInstitutionExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyVcInvestInstitutionExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyVcInvestInstitutionExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyVcInvestInstitutionWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyVcInvestInstitutionExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyVcInvestInstitutionWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyVcInvestInstitutionService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyVcInvestInstitutionExWarehouseVOSingleResponse = dataCompanyVcInvestInstitutionExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyVcInvestInstitutionExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyVcInvestInstitutionUpdateCommand dataCompanyVcInvestInstitutionUpdateCommand = DataCompanyVcInvestInstitutionUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyVcInvestInstitutionExWarehouseVO.getVersion(),
						dataCompanyVcInvestInstitutionWarehouseCommand
				);
				dataCompanyVcInvestInstitutionUpdateCommandExecutor.execute(dataCompanyVcInvestInstitutionUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyVcInvestInstitutionExWarehouseVOSingleResponse = dataCompanyVcInvestInstitutionExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyVcInvestInstitutionExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyVcInvestInstitutionExWarehouseCommandExecutor(DataCompanyVcInvestInstitutionExWarehouseCommandExecutor dataCompanyVcInvestInstitutionExWarehouseCommandExecutor) {
		this.dataCompanyVcInvestInstitutionExWarehouseCommandExecutor = dataCompanyVcInvestInstitutionExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyVcInvestInstitutionCreateCommandExecutor(DataCompanyVcInvestInstitutionCreateCommandExecutor dataCompanyVcInvestInstitutionCreateCommandExecutor) {
		this.dataCompanyVcInvestInstitutionCreateCommandExecutor = dataCompanyVcInvestInstitutionCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyVcInvestInstitutionService(IDataCompanyVcInvestInstitutionService iDataCompanyVcInvestInstitutionService) {
		this.iDataCompanyVcInvestInstitutionService = iDataCompanyVcInvestInstitutionService;
	}
	@Autowired
	public void setDataCompanyVcInvestInstitutionUpdateCommandExecutor(DataCompanyVcInvestInstitutionUpdateCommandExecutor dataCompanyVcInvestInstitutionUpdateCommandExecutor) {
		this.dataCompanyVcInvestInstitutionUpdateCommandExecutor = dataCompanyVcInvestInstitutionUpdateCommandExecutor;
	}
}
