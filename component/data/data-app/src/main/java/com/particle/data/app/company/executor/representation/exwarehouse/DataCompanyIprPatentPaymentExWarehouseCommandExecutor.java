package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentPaymentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentPaymentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPaymentExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPaymentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPaymentService;
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
 * 企业知识产权专利缴费信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentPaymentExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentPaymentService iDataCompanyIprPatentPaymentService;

	/**
	 * 企业知识产权专利缴费信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentPaymentExWarehouseVO> exWarehouse(@Valid DataCompanyIprPatentPaymentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprPatentPaymentDO> dataCompanyIprPatentPaymentDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId() != null) {
			dataCompanyIprPatentPaymentDOPage = iDataCompanyIprPatentPaymentService.listPageByCompanyIprPatentId(dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyIprPatentPaymentDOPage == null || dataCompanyIprPatentPaymentDOPage.getRecords() == null || dataCompanyIprPatentPaymentDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprPatentPaymentAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprPatentPaymentDOPage);
	}
	/**
	 * 企业知识产权专利缴费信息出库
	 * @param companyCaseFilingIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentPaymentExWarehouseVO> exWarehouseByCompanyIprPatentIds(List<Long> companyCaseFilingIds) {
		List<DataCompanyIprPatentPaymentDO> dataCompanyIprPatentPaymentDOList = iDataCompanyIprPatentPaymentService.listByCompanyIprPatentIds(companyCaseFilingIds);
		if (CollectionUtil.isEmpty(dataCompanyIprPatentPaymentDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprPatentPaymentExWarehouseVO> dataCompanyIprPatentPaymentExWarehouseVOS = DataCompanyIprPatentPaymentAppStructMapping.instance.dataCompanyIprPatentPaymentDOsToDataCompanyIprPatentPaymentExWarehouseVOs(dataCompanyIprPatentPaymentDOList);
		return MultiResponse.of(dataCompanyIprPatentPaymentExWarehouseVOS);
	}
	/**
	 * 企业知识产权专利缴费信息出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPaymentExWarehouseVO> exWarehouseByCompanyIprPatentIdAndDataMd5(Long companyIprPatentId,String dataMd5) {
		DataCompanyIprPatentPaymentDO dataCompanyIprPatentPaymentDO = iDataCompanyIprPatentPaymentService.getByCompanyIprPatentIdAndDataMd5(companyIprPatentId,dataMd5);
		if (dataCompanyIprPatentPaymentDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentPaymentExWarehouseVO dataCompanyIprPatentPaymentExWarehouseVO = DataCompanyIprPatentPaymentAppStructMapping.instance.dataCompanyIprPatentPaymentDOToDataCompanyIprPatentPaymentExWarehouseVO(dataCompanyIprPatentPaymentDO);
		return SingleResponse.of(dataCompanyIprPatentPaymentExWarehouseVO);
	}
	/**
	 * 企业知识产权专利缴费信息出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPaymentExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprPatentPaymentDO dataCompanyIprPatentPaymentDO = iDataCompanyIprPatentPaymentService.getById(id);
		if (dataCompanyIprPatentPaymentDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentPaymentExWarehouseVO dataCompanyIprPatentPaymentExWarehouseVO = DataCompanyIprPatentPaymentAppStructMapping.instance.dataCompanyIprPatentPaymentDOToDataCompanyIprPatentPaymentExWarehouseVO(dataCompanyIprPatentPaymentDO);
		return SingleResponse.of(dataCompanyIprPatentPaymentExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprPatentPaymentService(IDataCompanyIprPatentPaymentService iDataCompanyIprPatentPaymentService) {
		this.iDataCompanyIprPatentPaymentService = iDataCompanyIprPatentPaymentService;
	}
}
