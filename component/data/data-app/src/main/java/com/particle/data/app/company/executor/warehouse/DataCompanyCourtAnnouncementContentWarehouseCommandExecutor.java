package com.particle.data.app.company.executor.warehouse;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementContentCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementContentUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementContentCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementContentUpdateCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementContentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementContentService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业法院公告内容入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementContentWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyCourtAnnouncementContentExWarehouseCommandExecutor dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor;
	private DataCompanyCourtAnnouncementContentCreateCommandExecutor dataCompanyCourtAnnouncementContentCreateCommandExecutor;
	private IDataCompanyCourtAnnouncementContentService iDataCompanyCourtAnnouncementContentService;
	private DataCompanyCourtAnnouncementContentUpdateCommandExecutor dataCompanyCourtAnnouncementContentUpdateCommandExecutor;


	/**
	 * 企业法院公告内容入库
	 * @param dataCompanyCourtAnnouncementContentWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementContentExWarehouseVO> warehouse(DataCompanyCourtAnnouncementContentWarehouseCommand dataCompanyCourtAnnouncementContentWarehouseCommand) {
		DataCompanyCourtAnnouncementContentExWarehouseQueryCommand dataCompanyCourtAnnouncementContentExWarehouseQueryCommand = DataCompanyCourtAnnouncementContentExWarehouseQueryCommand.create(dataCompanyCourtAnnouncementContentWarehouseCommand.getCompanyCourtAnnouncementId());
		SingleResponse<DataCompanyCourtAnnouncementContentExWarehouseVO> dataCompanyCourtAnnouncementContentExWarehouseVOSingleResponse = dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor.exWarehouse(dataCompanyCourtAnnouncementContentExWarehouseQueryCommand);
		DataCompanyCourtAnnouncementContentExWarehouseVO dataCompanyCourtAnnouncementContentExWarehouseVO = dataCompanyCourtAnnouncementContentExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyCourtAnnouncementContentExWarehouseVO == null) {
			DataCompanyCourtAnnouncementContentCreateCommand dataCompanyCourtAnnouncementContentCreateCommand = DataCompanyCourtAnnouncementContentCreateCommand.createByWarehouseCommand(dataCompanyCourtAnnouncementContentWarehouseCommand);
			dataCompanyCourtAnnouncementContentCreateCommandExecutor.execute(dataCompanyCourtAnnouncementContentCreateCommand);
			// 新增后重新查询，返回最新数据
			dataCompanyCourtAnnouncementContentExWarehouseVOSingleResponse = dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor.exWarehouse(dataCompanyCourtAnnouncementContentExWarehouseQueryCommand);
			return dataCompanyCourtAnnouncementContentExWarehouseVOSingleResponse;
		}else {
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyCourtAnnouncementContentWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyCourtAnnouncementContentExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyCourtAnnouncementContentWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyCourtAnnouncementContentService.updateLatestHandleAt(dataCompanyCourtAnnouncementContentExWarehouseVO.getId());
				// 如果没有需要更新的字段，则直接返回
				return dataCompanyCourtAnnouncementContentExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyCourtAnnouncementContentUpdateCommand dataCompanyCourtAnnouncementContentUpdateCommand = DataCompanyCourtAnnouncementContentUpdateCommand.createByWarehouseCommand(
						dataCompanyCourtAnnouncementContentExWarehouseVO.getId(),
						dataCompanyCourtAnnouncementContentExWarehouseVO.getVersion(),
						dataCompanyCourtAnnouncementContentWarehouseCommand
				);
				dataCompanyCourtAnnouncementContentUpdateCommandExecutor.execute(dataCompanyCourtAnnouncementContentUpdateCommand);
			}

			// 更新完成后，新增的情况已经在新增逻辑里面直接返回了，查询返回
			dataCompanyCourtAnnouncementContentExWarehouseVOSingleResponse = dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor.exWarehouse(dataCompanyCourtAnnouncementContentExWarehouseQueryCommand);
			return dataCompanyCourtAnnouncementContentExWarehouseVOSingleResponse;
		}
	}

	@Autowired
	public void setDataCompanyCourtAnnouncementContentExWarehouseCommandExecutor(DataCompanyCourtAnnouncementContentExWarehouseCommandExecutor dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor) {
		this.dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor = dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyCourtAnnouncementContentCreateCommandExecutor(DataCompanyCourtAnnouncementContentCreateCommandExecutor dataCompanyCourtAnnouncementContentCreateCommandExecutor) {
		this.dataCompanyCourtAnnouncementContentCreateCommandExecutor = dataCompanyCourtAnnouncementContentCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyCourtAnnouncementContentService(IDataCompanyCourtAnnouncementContentService iDataCompanyCourtAnnouncementContentService) {
		this.iDataCompanyCourtAnnouncementContentService = iDataCompanyCourtAnnouncementContentService;
	}
	@Autowired
	public void setDataCompanyCourtAnnouncementContentUpdateCommandExecutor(DataCompanyCourtAnnouncementContentUpdateCommandExecutor dataCompanyCourtAnnouncementContentUpdateCommandExecutor) {
		this.dataCompanyCourtAnnouncementContentUpdateCommandExecutor = dataCompanyCourtAnnouncementContentUpdateCommandExecutor;
	}
}
