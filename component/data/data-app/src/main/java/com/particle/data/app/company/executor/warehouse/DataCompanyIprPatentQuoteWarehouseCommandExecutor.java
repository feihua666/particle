package com.particle.data.app.company.executor.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentQuoteCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentQuoteUpdateCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentQuoteExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentQuoteCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentQuoteUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentQuoteWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentQuoteVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentQuoteExWarehouseVO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentQuoteService;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业知识产权专利引证信息入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyIprPatentQuoteWarehouseCommandExecutor extends AbstractBaseExecutor {

	private DataCompanyIprPatentQuoteExWarehouseCommandExecutor dataCompanyIprPatentQuoteExWarehouseCommandExecutor;
	private DataCompanyIprPatentQuoteCreateCommandExecutor dataCompanyIprPatentQuoteCreateCommandExecutor;
	private IDataCompanyIprPatentQuoteService iDataCompanyIprPatentQuoteService;
	private DataCompanyIprPatentQuoteUpdateCommandExecutor dataCompanyIprPatentQuoteUpdateCommandExecutor;


	/**
	 * 企业知识产权专利引证信息入库
	 * @param dataCompanyIprPatentQuoteWarehouseCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentQuoteExWarehouseVO> warehouse(DataCompanyIprPatentQuoteWarehouseCommand dataCompanyIprPatentQuoteWarehouseCommand) {
		SingleResponse<DataCompanyIprPatentQuoteExWarehouseVO> dataCompanyIprPatentQuoteExWarehouseVOSingleResponse = null;
		DataCompanyIprPatentQuoteExWarehouseVO dataCompanyIprPatentQuoteExWarehouseVO = null;
		String applyNo = dataCompanyIprPatentQuoteWarehouseCommand.getApplyNo();
		if (StrUtil.isNotEmpty(applyNo)) {
			dataCompanyIprPatentQuoteExWarehouseVOSingleResponse = dataCompanyIprPatentQuoteExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndApplyNo(dataCompanyIprPatentQuoteWarehouseCommand.getCompanyIprPatentId(),applyNo);
		}
		if (dataCompanyIprPatentQuoteExWarehouseVOSingleResponse == null || dataCompanyIprPatentQuoteExWarehouseVOSingleResponse.getData() == null) {
			String standardApplyNo = dataCompanyIprPatentQuoteWarehouseCommand.getStandardApplyNo();
			if (StrUtil.isNotEmpty(standardApplyNo)) {
				dataCompanyIprPatentQuoteExWarehouseVOSingleResponse = dataCompanyIprPatentQuoteExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndStandardApplyNo(dataCompanyIprPatentQuoteWarehouseCommand.getCompanyIprPatentId(),standardApplyNo);
			}
		}
		if (dataCompanyIprPatentQuoteExWarehouseVOSingleResponse == null || dataCompanyIprPatentQuoteExWarehouseVOSingleResponse.getData() == null) {
			String publicNo = dataCompanyIprPatentQuoteWarehouseCommand.getPublicNo();
			if (StrUtil.isNotEmpty(publicNo)) {
				dataCompanyIprPatentQuoteExWarehouseVOSingleResponse = dataCompanyIprPatentQuoteExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndPublicNo(dataCompanyIprPatentQuoteWarehouseCommand.getCompanyIprPatentId(),publicNo);
			}
		}
		if (dataCompanyIprPatentQuoteExWarehouseVOSingleResponse == null || dataCompanyIprPatentQuoteExWarehouseVOSingleResponse.getData() == null) {
			String standardPublicNo = dataCompanyIprPatentQuoteWarehouseCommand.getStandardPublicNo();
			if (StrUtil.isNotEmpty(standardPublicNo)) {
				dataCompanyIprPatentQuoteExWarehouseVOSingleResponse = dataCompanyIprPatentQuoteExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIdAndStandardPublicNo(dataCompanyIprPatentQuoteWarehouseCommand.getCompanyIprPatentId(),standardPublicNo);
			}
		}
		dataCompanyIprPatentQuoteExWarehouseVO = dataCompanyIprPatentQuoteExWarehouseVOSingleResponse == null ? null : dataCompanyIprPatentQuoteExWarehouseVOSingleResponse.getData();
		// 不存在，添加
        if (dataCompanyIprPatentQuoteExWarehouseVO == null) {
			DataCompanyIprPatentQuoteCreateCommand dataCompanyIprPatentQuoteCreateCommand = DataCompanyIprPatentQuoteCreateCommand.createByWarehouseCommand(dataCompanyIprPatentQuoteWarehouseCommand);
			SingleResponse<DataCompanyIprPatentQuoteVO> dataCompanyIprPatentQuoteVOSingleResponse = dataCompanyIprPatentQuoteCreateCommandExecutor.execute(dataCompanyIprPatentQuoteCreateCommand);
			Long id = dataCompanyIprPatentQuoteVOSingleResponse.getData().getId();
			// 新增后重新查询，返回最新数据
			dataCompanyIprPatentQuoteExWarehouseVOSingleResponse = dataCompanyIprPatentQuoteExWarehouseCommandExecutor.exWarehouseById(id);
			return dataCompanyIprPatentQuoteExWarehouseVOSingleResponse;
		}else {
			Long id = dataCompanyIprPatentQuoteExWarehouseVO.getId();
			// 	存在，尝试入库
			// 仅更新有变化的字段，将相同的字段设置为null不更新
			dataCompanyIprPatentQuoteWarehouseCommand.compareAndSetNullWhenEquals(dataCompanyIprPatentQuoteExWarehouseVO);

			// 判断是否所有字段都为空，所有字段都没有变化，不需要更新
			if (dataCompanyIprPatentQuoteWarehouseCommand.allFieldEmpty()) {
				// 更新最后处理时间
				iDataCompanyIprPatentQuoteService.updateLatestHandleAt(id);
				// 重新查询，返回最新数据
				dataCompanyIprPatentQuoteExWarehouseVOSingleResponse = dataCompanyIprPatentQuoteExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentQuoteExWarehouseVOSingleResponse;
			} else {
				// 更新处理
				DataCompanyIprPatentQuoteUpdateCommand dataCompanyIprPatentQuoteUpdateCommand = DataCompanyIprPatentQuoteUpdateCommand.createByWarehouseCommand(
						id,
						dataCompanyIprPatentQuoteExWarehouseVO.getVersion(),
						dataCompanyIprPatentQuoteWarehouseCommand
				);
				dataCompanyIprPatentQuoteUpdateCommandExecutor.execute(dataCompanyIprPatentQuoteUpdateCommand);
				// 重新查询，返回最新数据
				dataCompanyIprPatentQuoteExWarehouseVOSingleResponse = dataCompanyIprPatentQuoteExWarehouseCommandExecutor.exWarehouseById(id);
				return dataCompanyIprPatentQuoteExWarehouseVOSingleResponse;
			}
		}
	}

	@Autowired
	public void setDataCompanyIprPatentQuoteExWarehouseCommandExecutor(DataCompanyIprPatentQuoteExWarehouseCommandExecutor dataCompanyIprPatentQuoteExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentQuoteExWarehouseCommandExecutor = dataCompanyIprPatentQuoteExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPatentQuoteCreateCommandExecutor(DataCompanyIprPatentQuoteCreateCommandExecutor dataCompanyIprPatentQuoteCreateCommandExecutor) {
		this.dataCompanyIprPatentQuoteCreateCommandExecutor = dataCompanyIprPatentQuoteCreateCommandExecutor;
	}
	@Autowired
	public void setiDataCompanyIprPatentQuoteService(IDataCompanyIprPatentQuoteService iDataCompanyIprPatentQuoteService) {
		this.iDataCompanyIprPatentQuoteService = iDataCompanyIprPatentQuoteService;
	}
	@Autowired
	public void setDataCompanyIprPatentQuoteUpdateCommandExecutor(DataCompanyIprPatentQuoteUpdateCommandExecutor dataCompanyIprPatentQuoteUpdateCommandExecutor) {
		this.dataCompanyIprPatentQuoteUpdateCommandExecutor = dataCompanyIprPatentQuoteUpdateCommandExecutor;
	}
}
