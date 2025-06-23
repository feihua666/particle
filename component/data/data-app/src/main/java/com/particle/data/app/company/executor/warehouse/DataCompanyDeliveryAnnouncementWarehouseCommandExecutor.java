package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyDeliveryAnnouncementUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyDeliveryAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDeliveryAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业送达公告入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyDeliveryAnnouncementExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor;
	private DataCompanyDeliveryAnnouncementCreateCommandExecutor dataCompanyDeliveryAnnouncementCreateCommandExecutor;
	private IDataCompanyDeliveryAnnouncementService iDataCompanyDeliveryAnnouncementService;
	private DataCompanyDeliveryAnnouncementUpdateCommandExecutor dataCompanyDeliveryAnnouncementUpdateCommandExecutor;


	/**
	 * 企业送达公告入库
	 * @param dataCompanyDeliveryAnnouncementWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> warehouse(DataCompanyDeliveryAnnouncementWarehouseCommand dataCompanyDeliveryAnnouncementWarehouseCommand) {
		SingleResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> dataCompanyDeliveryAnnouncementExWarehouseVOSingleResponse = null;
		DataCompanyDeliveryAnnouncementExWarehouseVO dataCompanyDeliveryAnnouncementExWarehouseVO = null;
		String obtainDataMd5 = dataCompanyDeliveryAnnouncementWarehouseCommand.obtainDataMd5();
		if (StrUtil.isNotEmpty(obtainDataMd5)) {
			dataCompanyDeliveryAnnouncementExWarehouseVOSingleResponse = dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor.exWarehouseByDataMd5(obtainDataMd5);
		}

		dataCompanyDeliveryAnnouncementExWarehouseVO = dataCompanyDeliveryAnnouncementExWarehouseVOSingleResponse == null ? null : dataCompanyDeliveryAnnouncementExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyDeliveryAnnouncementExWarehouseVO == null) {
			DataCompanyDeliveryAnnouncementCreateCommand dataCompanyDeliveryAnnouncementCreateCommand = DataCompanyDeliveryAnnouncementCreateCommand.createByWarehouseCommand(dataCompanyDeliveryAnnouncementWarehouseCommand);
			SingleResponse<DataCompanyDeliveryAnnouncementVO> dataCompanyDeliveryAnnouncementVOSingleResponse = dataCompanyDeliveryAnnouncementCreateCommandExecutor.execute(dataCompanyDeliveryAnnouncementCreateCommand);
			Long id = dataCompanyDeliveryAnnouncementVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyDeliveryAnnouncementExWarehouseVOSingleResponse = dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyDeliveryAnnouncementExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyDeliveryAnnouncementExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyDeliveryAnnouncementWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyDeliveryAnnouncementExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyDeliveryAnnouncementWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyDeliveryAnnouncementService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyDeliveryAnnouncementExWarehouseVOSingleResponse = dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyDeliveryAnnouncementExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyDeliveryAnnouncementUpdateCommand dataCompanyDeliveryAnnouncementUpdateCommand = DataCompanyDeliveryAnnouncementUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyDeliveryAnnouncementExWarehouseVO.getVersion(),
						dataCompanyDeliveryAnnouncementWarehouseCommand
				);
				dataCompanyDeliveryAnnouncementUpdateCommandExecutor.execute(dataCompanyDeliveryAnnouncementUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyDeliveryAnnouncementExWarehouseVOSingleResponse = dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyDeliveryAnnouncementExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyDeliveryAnnouncementExWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor) {
		this.dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor = dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyDeliveryAnnouncementCreateCommandExecutor(DataCompanyDeliveryAnnouncementCreateCommandExecutor dataCompanyDeliveryAnnouncementCreateCommandExecutor) {
		this.dataCompanyDeliveryAnnouncementCreateCommandExecutor = dataCompanyDeliveryAnnouncementCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyDeliveryAnnouncementService(IDataCompanyDeliveryAnnouncementService iDataCompanyDeliveryAnnouncementService) {
		this.iDataCompanyDeliveryAnnouncementService = iDataCompanyDeliveryAnnouncementService;
	}
	@Autowired
	public void setDataCompanyDeliveryAnnouncementUpdateCommandExecutor(DataCompanyDeliveryAnnouncementUpdateCommandExecutor dataCompanyDeliveryAnnouncementUpdateCommandExecutor) {
		this.dataCompanyDeliveryAnnouncementUpdateCommandExecutor = dataCompanyDeliveryAnnouncementUpdateCommandExecutor;
	}
}
