package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentPledgeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentPledgeUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentPledgeExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPledgeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPledgeUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentPledgeWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPledgeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPledgeExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPledgeService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权专利质押信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPatentPledgeWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPatentPledgeExWarehouseCommandExecutor dataCompanyIprPatentPledgeExWarehouseCommandExecutor;
	private DataCompanyIprPatentPledgeCreateCommandExecutor dataCompanyIprPatentPledgeCreateCommandExecutor;
	private IDataCompanyIprPatentPledgeService iDataCompanyIprPatentPledgeService;
	private DataCompanyIprPatentPledgeUpdateCommandExecutor dataCompanyIprPatentPledgeUpdateCommandExecutor;


	/**
	 * 企业知识产权专利质押信息入库
	 * @param dataCompanyIprPatentPledgeWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPledgeExWarehouseVO> warehouse(DataCompanyIprPatentPledgeWarehouseCommand dataCompanyIprPatentPledgeWarehouseCommand) {
		SingleResponse<DataCompanyIprPatentPledgeExWarehouseVO> dataCompanyIprPatentPledgeExWarehouseVOSingleResponse = null;
		DataCompanyIprPatentPledgeExWarehouseVO dataCompanyIprPatentPledgeExWarehouseVO = null;
		String pledgeNo = dataCompanyIprPatentPledgeWarehouseCommand.getPledgeNo();
		if (StrUtil.isNotEmpty(pledgeNo)) {
			dataCompanyIprPatentPledgeExWarehouseVOSingleResponse = dataCompanyIprPatentPledgeExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndPledgeNo(dataCompanyIprPatentPledgeWarehouseCommand.getCompanyIprPatentId(),pledgeNo);
		}

		dataCompanyIprPatentPledgeExWarehouseVO = dataCompanyIprPatentPledgeExWarehouseVOSingleResponse == null ? null : dataCompanyIprPatentPledgeExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPatentPledgeExWarehouseVO == null) {
			DataCompanyIprPatentPledgeCreateCommand dataCompanyIprPatentPledgeCreateCommand = DataCompanyIprPatentPledgeCreateCommand.createByWarehouseCommand(dataCompanyIprPatentPledgeWarehouseCommand);
			SingleResponse<DataCompanyIprPatentPledgeVO> dataCompanyIprPatentPledgeVOSingleResponse = dataCompanyIprPatentPledgeCreateCommandExecutor.execute(dataCompanyIprPatentPledgeCreateCommand);
			Long id = dataCompanyIprPatentPledgeVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprPatentPledgeExWarehouseVOSingleResponse = dataCompanyIprPatentPledgeExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprPatentPledgeExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprPatentPledgeExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPatentPledgeWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPatentPledgeExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPatentPledgeWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPatentPledgeService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprPatentPledgeExWarehouseVOSingleResponse = dataCompanyIprPatentPledgeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentPledgeExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPatentPledgeUpdateCommand dataCompanyIprPatentPledgeUpdateCommand = DataCompanyIprPatentPledgeUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprPatentPledgeExWarehouseVO.getVersion(),
						dataCompanyIprPatentPledgeWarehouseCommand
				);
				dataCompanyIprPatentPledgeUpdateCommandExecutor.execute(dataCompanyIprPatentPledgeUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprPatentPledgeExWarehouseVOSingleResponse = dataCompanyIprPatentPledgeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentPledgeExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprPatentPledgeExWarehouseCommandExecutor(DataCompanyIprPatentPledgeExWarehouseCommandExecutor dataCompanyIprPatentPledgeExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentPledgeExWarehouseCommandExecutor = dataCompanyIprPatentPledgeExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPatentPledgeCreateCommandExecutor(DataCompanyIprPatentPledgeCreateCommandExecutor dataCompanyIprPatentPledgeCreateCommandExecutor) {
		this.dataCompanyIprPatentPledgeCreateCommandExecutor = dataCompanyIprPatentPledgeCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPatentPledgeService(IDataCompanyIprPatentPledgeService iDataCompanyIprPatentPledgeService) {
		this.iDataCompanyIprPatentPledgeService = iDataCompanyIprPatentPledgeService;
	}
	@Autowired
	public void setDataCompanyIprPatentPledgeUpdateCommandExecutor(DataCompanyIprPatentPledgeUpdateCommandExecutor dataCompanyIprPatentPledgeUpdateCommandExecutor) {
		this.dataCompanyIprPatentPledgeUpdateCommandExecutor = dataCompanyIprPatentPledgeUpdateCommandExecutor;
	}
}
