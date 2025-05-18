package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyVcProductCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcProductUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyVcProductExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyVcProductCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcProductUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcProductWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyVcProductService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业融资产品入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyVcProductWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyVcProductExWarehouseCommandExecutor dataCompanyVcProductExWarehouseCommandExecutor;
	private DataCompanyVcProductCreateCommandExecutor dataCompanyVcProductCreateCommandExecutor;
	private IDataCompanyVcProductService iDataCompanyVcProductService;
	private DataCompanyVcProductUpdateCommandExecutor dataCompanyVcProductUpdateCommandExecutor;


	/**
	 * 企业融资产品入库
	 * @param dataCompanyVcProductWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductExWarehouseVO> warehouse(DataCompanyVcProductWarehouseCommand dataCompanyVcProductWarehouseCommand) {
		SingleResponse<DataCompanyVcProductExWarehouseVO> dataCompanyVcProductExWarehouseVOSingleResponse = null;
		DataCompanyVcProductExWarehouseVO dataCompanyVcProductExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyVcProductWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyVcProductExWarehouseVOSingleResponse = dataCompanyVcProductExWarehouseCommandExecutor.exWarehouseByCompanyIdAndDataMd5(dataCompanyVcProductWarehouseCommand.getCompanyId(),obtainDataMd5);
		}

		dataCompanyVcProductExWarehouseVO = dataCompanyVcProductExWarehouseVOSingleResponse == null ? null : dataCompanyVcProductExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyVcProductExWarehouseVO == null) {
			DataCompanyVcProductCreateCommand dataCompanyVcProductCreateCommand = DataCompanyVcProductCreateCommand.createByWarehouseCommand(dataCompanyVcProductWarehouseCommand);
			SingleResponse<DataCompanyVcProductVO> dataCompanyVcProductVOSingleResponse = dataCompanyVcProductCreateCommandExecutor.execute(dataCompanyVcProductCreateCommand);
			Long id = dataCompanyVcProductVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyVcProductExWarehouseVOSingleResponse = dataCompanyVcProductExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyVcProductExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyVcProductExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyVcProductWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyVcProductExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyVcProductWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyVcProductService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyVcProductExWarehouseVOSingleResponse = dataCompanyVcProductExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyVcProductExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyVcProductUpdateCommand dataCompanyVcProductUpdateCommand = DataCompanyVcProductUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyVcProductExWarehouseVO.getVersion(),
						dataCompanyVcProductWarehouseCommand
				);
				dataCompanyVcProductUpdateCommandExecutor.execute(dataCompanyVcProductUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyVcProductExWarehouseVOSingleResponse = dataCompanyVcProductExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyVcProductExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyVcProductExWarehouseCommandExecutor(DataCompanyVcProductExWarehouseCommandExecutor dataCompanyVcProductExWarehouseCommandExecutor) {
		this.dataCompanyVcProductExWarehouseCommandExecutor = dataCompanyVcProductExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyVcProductCreateCommandExecutor(DataCompanyVcProductCreateCommandExecutor dataCompanyVcProductCreateCommandExecutor) {
		this.dataCompanyVcProductCreateCommandExecutor = dataCompanyVcProductCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyVcProductService(IDataCompanyVcProductService iDataCompanyVcProductService) {
		this.iDataCompanyVcProductService = iDataCompanyVcProductService;
	}
	@Autowired
	public void setDataCompanyVcProductUpdateCommandExecutor(DataCompanyVcProductUpdateCommandExecutor dataCompanyVcProductUpdateCommandExecutor) {
		this.dataCompanyVcProductUpdateCommandExecutor = dataCompanyVcProductUpdateCommandExecutor;
	}
}
