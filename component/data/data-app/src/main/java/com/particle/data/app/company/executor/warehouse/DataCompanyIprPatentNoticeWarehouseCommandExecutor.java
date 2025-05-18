package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentNoticeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentNoticeUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentNoticeExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentNoticeCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentNoticeUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentNoticeWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentNoticeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentNoticeExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentNoticeService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权专利通知书信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPatentNoticeWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPatentNoticeExWarehouseCommandExecutor dataCompanyIprPatentNoticeExWarehouseCommandExecutor;
	private DataCompanyIprPatentNoticeCreateCommandExecutor dataCompanyIprPatentNoticeCreateCommandExecutor;
	private IDataCompanyIprPatentNoticeService iDataCompanyIprPatentNoticeService;
	private DataCompanyIprPatentNoticeUpdateCommandExecutor dataCompanyIprPatentNoticeUpdateCommandExecutor;


	/**
	 * 企业知识产权专利通知书信息入库
	 * @param dataCompanyIprPatentNoticeWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentNoticeExWarehouseVO> warehouse(DataCompanyIprPatentNoticeWarehouseCommand dataCompanyIprPatentNoticeWarehouseCommand) {
		SingleResponse<DataCompanyIprPatentNoticeExWarehouseVO> dataCompanyIprPatentNoticeExWarehouseVOSingleResponse = null;
		DataCompanyIprPatentNoticeExWarehouseVO dataCompanyIprPatentNoticeExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyIprPatentNoticeWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyIprPatentNoticeExWarehouseVOSingleResponse = dataCompanyIprPatentNoticeExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndDataMd5(dataCompanyIprPatentNoticeWarehouseCommand.getCompanyIprPatentId(),obtainDataMd5);
		}

		dataCompanyIprPatentNoticeExWarehouseVO = dataCompanyIprPatentNoticeExWarehouseVOSingleResponse == null ? null : dataCompanyIprPatentNoticeExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPatentNoticeExWarehouseVO == null) {
			DataCompanyIprPatentNoticeCreateCommand dataCompanyIprPatentNoticeCreateCommand = DataCompanyIprPatentNoticeCreateCommand.createByWarehouseCommand(dataCompanyIprPatentNoticeWarehouseCommand);
			SingleResponse<DataCompanyIprPatentNoticeVO> dataCompanyIprPatentNoticeVOSingleResponse = dataCompanyIprPatentNoticeCreateCommandExecutor.execute(dataCompanyIprPatentNoticeCreateCommand);
			Long id = dataCompanyIprPatentNoticeVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprPatentNoticeExWarehouseVOSingleResponse = dataCompanyIprPatentNoticeExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprPatentNoticeExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprPatentNoticeExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPatentNoticeWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPatentNoticeExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPatentNoticeWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPatentNoticeService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprPatentNoticeExWarehouseVOSingleResponse = dataCompanyIprPatentNoticeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentNoticeExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPatentNoticeUpdateCommand dataCompanyIprPatentNoticeUpdateCommand = DataCompanyIprPatentNoticeUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprPatentNoticeExWarehouseVO.getVersion(),
						dataCompanyIprPatentNoticeWarehouseCommand
				);
				dataCompanyIprPatentNoticeUpdateCommandExecutor.execute(dataCompanyIprPatentNoticeUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprPatentNoticeExWarehouseVOSingleResponse = dataCompanyIprPatentNoticeExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentNoticeExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprPatentNoticeExWarehouseCommandExecutor(DataCompanyIprPatentNoticeExWarehouseCommandExecutor dataCompanyIprPatentNoticeExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentNoticeExWarehouseCommandExecutor = dataCompanyIprPatentNoticeExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPatentNoticeCreateCommandExecutor(DataCompanyIprPatentNoticeCreateCommandExecutor dataCompanyIprPatentNoticeCreateCommandExecutor) {
		this.dataCompanyIprPatentNoticeCreateCommandExecutor = dataCompanyIprPatentNoticeCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPatentNoticeService(IDataCompanyIprPatentNoticeService iDataCompanyIprPatentNoticeService) {
		this.iDataCompanyIprPatentNoticeService = iDataCompanyIprPatentNoticeService;
	}
	@Autowired
	public void setDataCompanyIprPatentNoticeUpdateCommandExecutor(DataCompanyIprPatentNoticeUpdateCommandExecutor dataCompanyIprPatentNoticeUpdateCommandExecutor) {
		this.dataCompanyIprPatentNoticeUpdateCommandExecutor = dataCompanyIprPatentNoticeUpdateCommandExecutor;
	}
}
