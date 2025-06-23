package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentLegalStatusAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentLegalStatusExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLegalStatusExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLegalStatusDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentLegalStatusService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业知识产权专利法律状态出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentLegalStatusExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentLegalStatusService iDataCompanyIprPatentLegalStatusService;

	/**
	 * 企业知识产权专利法律状态出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentLegalStatusExWarehouseVO> exWarehouse(@Valid DataCompanyIprPatentLegalStatusExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprPatentLegalStatusDO> dataCompanyIprPatentLegalStatusDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId() != null) {
			dataCompanyIprPatentLegalStatusDOPage = iDataCompanyIprPatentLegalStatusService.listPageByCompanyIprPatentId(dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyIprPatentLegalStatusDOPage == null || dataCompanyIprPatentLegalStatusDOPage.getRecords() == null || dataCompanyIprPatentLegalStatusDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprPatentLegalStatusAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprPatentLegalStatusDOPage);
	}
	/**
	 * 企业知识产权专利法律状态出库
	 * @param companyIprPatentIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentLegalStatusExWarehouseVO> exWarehouseByCompanyIprPatentIds(List<Long> companyIprPatentIds) {
		List<DataCompanyIprPatentLegalStatusDO> dataCompanyIprPatentLegalStatusDOList = iDataCompanyIprPatentLegalStatusService.listByCompanyIprPatentIds(companyIprPatentIds);
		if (CollectionUtil.isEmpty(dataCompanyIprPatentLegalStatusDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprPatentLegalStatusExWarehouseVO> dataCompanyIprPatentLegalStatusExWarehouseVOS = DataCompanyIprPatentLegalStatusAppStructMapping.instance.dataCompanyIprPatentLegalStatusDOsToDataCompanyIprPatentLegalStatusExWarehouseVOs(dataCompanyIprPatentLegalStatusDOList);
		return MultiResponse.of(dataCompanyIprPatentLegalStatusExWarehouseVOS);
	}
	/**
	 * 企业知识产权专利法律状态出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLegalStatusExWarehouseVO> exWarehouseByCompanyIprPatentIdAndDataMd5(Long companyIprPatentId,String dataMd5) {
		DataCompanyIprPatentLegalStatusDO dataCompanyIprPatentLegalStatusDO = iDataCompanyIprPatentLegalStatusService.getByCompanyIprPatentIdAndDataMd5(companyIprPatentId,dataMd5);
		if (dataCompanyIprPatentLegalStatusDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentLegalStatusExWarehouseVO dataCompanyIprPatentLegalStatusExWarehouseVO = DataCompanyIprPatentLegalStatusAppStructMapping.instance.dataCompanyIprPatentLegalStatusDOToDataCompanyIprPatentLegalStatusExWarehouseVO(dataCompanyIprPatentLegalStatusDO);
		return SingleResponse.of(dataCompanyIprPatentLegalStatusExWarehouseVO);
	}
	/**
	 * 企业知识产权专利法律状态出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLegalStatusExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprPatentLegalStatusDO dataCompanyIprPatentLegalStatusDO = iDataCompanyIprPatentLegalStatusService.getById(id);
		if (dataCompanyIprPatentLegalStatusDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentLegalStatusExWarehouseVO dataCompanyIprPatentLegalStatusExWarehouseVO = DataCompanyIprPatentLegalStatusAppStructMapping.instance.dataCompanyIprPatentLegalStatusDOToDataCompanyIprPatentLegalStatusExWarehouseVO(dataCompanyIprPatentLegalStatusDO);
		return SingleResponse.of(dataCompanyIprPatentLegalStatusExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprPatentLegalStatusService(IDataCompanyIprPatentLegalStatusService iDataCompanyIprPatentLegalStatusService) {
		this.iDataCompanyIprPatentLegalStatusService = iDataCompanyIprPatentLegalStatusService;
	}
}
