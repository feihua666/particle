package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentContentAppStructMapping;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentContentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentContentExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentContentExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentContentDO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentContentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentContentService;
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
 * 企业知识产权专利内容出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentContentExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentContentService iDataCompanyIprPatentContentService;

	/**
	 * 企业知识产权专利内容出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentContentExWarehouseVO> exWarehouse(@Valid DataCompanyIprPatentContentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		DataCompanyIprPatentContentDO dataCompanyIprPatentContentDO = iDataCompanyIprPatentContentService.getByCompanyIprPatentId(dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId());
        if (dataCompanyIprPatentContentDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		DataCompanyIprPatentContentExWarehouseVO dataCompanyIprPatentContentExWarehouseVO = DataCompanyIprPatentContentAppStructMapping.instance.dataCompanyIprPatentContentDOToDataCompanyIprPatentContentExWarehouseVO(dataCompanyIprPatentContentDO);
		return SingleResponse.of(dataCompanyIprPatentContentExWarehouseVO);
	}
	/**
	 * 企业知识产权专利内容出库
	 * @param companyCaseFilingIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentContentExWarehouseVO> exWarehouseByCompanyIprPatentIds(List<Long> companyCaseFilingIds) {
		List<DataCompanyIprPatentContentDO> dataCompanyIprPatentContentDOList = iDataCompanyIprPatentContentService.listByCompanyIprPatentIds(companyCaseFilingIds);
		if (CollectionUtil.isEmpty(dataCompanyIprPatentContentDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprPatentContentExWarehouseVO> dataCompanyIprPatentContentExWarehouseVOS = DataCompanyIprPatentContentAppStructMapping.instance.dataCompanyIprPatentContentDOsToDataCompanyIprPatentContentExWarehouseVOs(dataCompanyIprPatentContentDOList);
		return MultiResponse.of(dataCompanyIprPatentContentExWarehouseVOS);
	}
	@Autowired
	public void setiDataCompanyIprPatentContentService(IDataCompanyIprPatentContentService iDataCompanyIprPatentContentService) {
		this.iDataCompanyIprPatentContentService = iDataCompanyIprPatentContentService;
	}
}
