package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprGeograApproveAnnouncementCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograApproveAnnouncementCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograApproveAnnouncementUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprGeograApproveAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograApproveAnnouncementVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprGeograApproveAnnouncementService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权地理标识核准公告入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprGeograApproveAnnouncementWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor;
	private DataCompanyIprGeograApproveAnnouncementCreateCommandExecutor dataCompanyIprGeograApproveAnnouncementCreateCommandExecutor;
	private IDataCompanyIprGeograApproveAnnouncementService iDataCompanyIprGeograApproveAnnouncementService;
	private DataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor dataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor;


	/**
	 * 企业知识产权地理标识核准公告入库
	 * @param dataCompanyIprGeograApproveAnnouncementWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> warehouse(DataCompanyIprGeograApproveAnnouncementWarehouseCommand dataCompanyIprGeograApproveAnnouncementWarehouseCommand) {
		SingleResponse<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> dataCompanyIprGeograApproveAnnouncementExWarehouseVOSingleResponse = null;
		DataCompanyIprGeograApproveAnnouncementExWarehouseVO dataCompanyIprGeograApproveAnnouncementExWarehouseVO = null;
		String approvePublicNo = dataCompanyIprGeograApproveAnnouncementWarehouseCommand.getApprovePublicNo();
		if (StrUtil.isNotEmpty(approvePublicNo)) {
			dataCompanyIprGeograApproveAnnouncementExWarehouseVOSingleResponse = dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor.exWarehouseByCompanyIprGeograIdAndApprovePublicNo(dataCompanyIprGeograApproveAnnouncementWarehouseCommand.getCompanyIprGeograId(),approvePublicNo);
		}

		dataCompanyIprGeograApproveAnnouncementExWarehouseVO = dataCompanyIprGeograApproveAnnouncementExWarehouseVOSingleResponse == null ? null : dataCompanyIprGeograApproveAnnouncementExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprGeograApproveAnnouncementExWarehouseVO == null) {
			DataCompanyIprGeograApproveAnnouncementCreateCommand dataCompanyIprGeograApproveAnnouncementCreateCommand = DataCompanyIprGeograApproveAnnouncementCreateCommand.createByWarehouseCommand(dataCompanyIprGeograApproveAnnouncementWarehouseCommand);
			SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> dataCompanyIprGeograApproveAnnouncementVOSingleResponse = dataCompanyIprGeograApproveAnnouncementCreateCommandExecutor.execute(dataCompanyIprGeograApproveAnnouncementCreateCommand);
			Long id = dataCompanyIprGeograApproveAnnouncementVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprGeograApproveAnnouncementExWarehouseVOSingleResponse = dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprGeograApproveAnnouncementExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprGeograApproveAnnouncementExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprGeograApproveAnnouncementWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprGeograApproveAnnouncementExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprGeograApproveAnnouncementWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprGeograApproveAnnouncementService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprGeograApproveAnnouncementExWarehouseVOSingleResponse = dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprGeograApproveAnnouncementExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprGeograApproveAnnouncementUpdateCommand dataCompanyIprGeograApproveAnnouncementUpdateCommand = DataCompanyIprGeograApproveAnnouncementUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprGeograApproveAnnouncementExWarehouseVO.getVersion(),
						dataCompanyIprGeograApproveAnnouncementWarehouseCommand
				);
				dataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor.execute(dataCompanyIprGeograApproveAnnouncementUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprGeograApproveAnnouncementExWarehouseVOSingleResponse = dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprGeograApproveAnnouncementExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor(DataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor) {
		this.dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor = dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprGeograApproveAnnouncementCreateCommandExecutor(DataCompanyIprGeograApproveAnnouncementCreateCommandExecutor dataCompanyIprGeograApproveAnnouncementCreateCommandExecutor) {
		this.dataCompanyIprGeograApproveAnnouncementCreateCommandExecutor = dataCompanyIprGeograApproveAnnouncementCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprGeograApproveAnnouncementService(IDataCompanyIprGeograApproveAnnouncementService iDataCompanyIprGeograApproveAnnouncementService) {
		this.iDataCompanyIprGeograApproveAnnouncementService = iDataCompanyIprGeograApproveAnnouncementService;
	}
	@Autowired
	public void setDataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor(DataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor dataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor) {
		this.dataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor = dataCompanyIprGeograApproveAnnouncementUpdateCommandExecutor;
	}
}
