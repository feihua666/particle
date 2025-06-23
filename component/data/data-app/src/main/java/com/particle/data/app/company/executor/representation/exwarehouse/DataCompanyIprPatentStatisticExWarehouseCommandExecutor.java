package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentStatisticAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentStatisticExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentStatisticExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentStatisticDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentStatisticService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业知识产权专利统计出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentStatisticExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentStatisticService iDataCompanyIprPatentStatisticService;

	/**
	 * 企业知识产权专利统计出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentStatisticExWarehouseVO> exWarehouse(@Valid DataCompanyIprPatentStatisticExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		DataCompanyIprPatentStatisticDO dataCompanyIprPatentStatisticDO = iDataCompanyIprPatentStatisticService.getByCompanyIprPatentId(dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId());
        if (dataCompanyIprPatentStatisticDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		DataCompanyIprPatentStatisticExWarehouseVO dataCompanyIprPatentStatisticExWarehouseVO = DataCompanyIprPatentStatisticAppStructMapping.instance.dataCompanyIprPatentStatisticDOToDataCompanyIprPatentStatisticExWarehouseVO(dataCompanyIprPatentStatisticDO);
		return SingleResponse.of(dataCompanyIprPatentStatisticExWarehouseVO);
	}
	/**
	 * 企业知识产权专利统计出库
	 * @param companyIprPatentIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentStatisticExWarehouseVO> exWarehouseByCompanyIprPatentIds(List<Long> companyIprPatentIds) {
		List<DataCompanyIprPatentStatisticDO> dataCompanyIprPatentStatisticDOList = iDataCompanyIprPatentStatisticService.listByCompanyIprPatentIds(companyIprPatentIds);
		if (CollectionUtil.isEmpty(dataCompanyIprPatentStatisticDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprPatentStatisticExWarehouseVO> dataCompanyIprPatentStatisticExWarehouseVOS = DataCompanyIprPatentStatisticAppStructMapping.instance.dataCompanyIprPatentStatisticDOsToDataCompanyIprPatentStatisticExWarehouseVOs(dataCompanyIprPatentStatisticDOList);
		return MultiResponse.of(dataCompanyIprPatentStatisticExWarehouseVOS);
	}
	@Autowired
	public void setiDataCompanyIprPatentStatisticService(IDataCompanyIprPatentStatisticService iDataCompanyIprPatentStatisticService) {
		this.iDataCompanyIprPatentStatisticService = iDataCompanyIprPatentStatisticService;
	}
}
