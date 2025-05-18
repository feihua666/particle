package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentTransferAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentTransferExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentTransferExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentTransferDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentTransferService;
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
 * 企业知识产权专利转让信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentTransferExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentTransferService iDataCompanyIprPatentTransferService;

	/**
	 * 企业知识产权专利转让信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentTransferExWarehouseVO> exWarehouse(@Valid DataCompanyIprPatentTransferExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprPatentTransferDO> dataCompanyIprPatentTransferDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId() != null) {
			dataCompanyIprPatentTransferDOPage = iDataCompanyIprPatentTransferService.listPageByCompanyIprPatentId(dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyIprPatentTransferDOPage == null || dataCompanyIprPatentTransferDOPage.getRecords() == null || dataCompanyIprPatentTransferDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprPatentTransferAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprPatentTransferDOPage);
	}
	/**
	 * 企业知识产权专利转让信息出库
	 * @param companyCaseFilingIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentTransferExWarehouseVO> exWarehouseByCompanyIprPatentIds(List<Long> companyCaseFilingIds) {
		List<DataCompanyIprPatentTransferDO> dataCompanyIprPatentTransferDOList = iDataCompanyIprPatentTransferService.listByCompanyIprPatentIds(companyCaseFilingIds);
		if (CollectionUtil.isEmpty(dataCompanyIprPatentTransferDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprPatentTransferExWarehouseVO> dataCompanyIprPatentTransferExWarehouseVOS = DataCompanyIprPatentTransferAppStructMapping.instance.dataCompanyIprPatentTransferDOsToDataCompanyIprPatentTransferExWarehouseVOs(dataCompanyIprPatentTransferDOList);
		return MultiResponse.of(dataCompanyIprPatentTransferExWarehouseVOS);
	}
	/**
	 * 企业知识产权专利转让信息出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentTransferExWarehouseVO> exWarehouseByCompanyIprPatentIdAndDataMd5(Long companyIprPatentId,String dataMd5) {
		DataCompanyIprPatentTransferDO dataCompanyIprPatentTransferDO = iDataCompanyIprPatentTransferService.getByCompanyIprPatentIdAndDataMd5(companyIprPatentId,dataMd5);
		if (dataCompanyIprPatentTransferDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentTransferExWarehouseVO dataCompanyIprPatentTransferExWarehouseVO = DataCompanyIprPatentTransferAppStructMapping.instance.dataCompanyIprPatentTransferDOToDataCompanyIprPatentTransferExWarehouseVO(dataCompanyIprPatentTransferDO);
		return SingleResponse.of(dataCompanyIprPatentTransferExWarehouseVO);
	}
	/**
	 * 企业知识产权专利转让信息出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentTransferExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprPatentTransferDO dataCompanyIprPatentTransferDO = iDataCompanyIprPatentTransferService.getById(id);
		if (dataCompanyIprPatentTransferDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentTransferExWarehouseVO dataCompanyIprPatentTransferExWarehouseVO = DataCompanyIprPatentTransferAppStructMapping.instance.dataCompanyIprPatentTransferDOToDataCompanyIprPatentTransferExWarehouseVO(dataCompanyIprPatentTransferDO);
		return SingleResponse.of(dataCompanyIprPatentTransferExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprPatentTransferService(IDataCompanyIprPatentTransferService iDataCompanyIprPatentTransferService) {
		this.iDataCompanyIprPatentTransferService = iDataCompanyIprPatentTransferService;
	}
}
