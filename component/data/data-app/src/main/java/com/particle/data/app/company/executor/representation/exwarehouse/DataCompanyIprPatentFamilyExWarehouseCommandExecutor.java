package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentFamilyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentFamilyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentFamilyExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentFamilyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentFamilyService;
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
 * 企业知识产权专利同族信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentFamilyExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentFamilyService iDataCompanyIprPatentFamilyService;

	/**
	 * 企业知识产权专利同族信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentFamilyExWarehouseVO> exWarehouse(@Valid DataCompanyIprPatentFamilyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprPatentFamilyDO> dataCompanyIprPatentFamilyDOPage = null;
		dataCompanyIprPatentFamilyDOPage = iDataCompanyIprPatentFamilyService.listPageByCompanyIprPatentId(dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId(),
				dataCompanyExWarehouseQueryCommand);

		if (dataCompanyIprPatentFamilyDOPage == null || dataCompanyIprPatentFamilyDOPage.getRecords() == null || dataCompanyIprPatentFamilyDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprPatentFamilyAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprPatentFamilyDOPage);
	}
	/**
	 * 企业知识产权专利同族信息出库
	 * @param companyCaseFilingIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentFamilyExWarehouseVO> exWarehouseByCompanyIprPatentIds(List<Long> companyCaseFilingIds) {
		List<DataCompanyIprPatentFamilyDO> dataCompanyIprPatentFamilyDOList = iDataCompanyIprPatentFamilyService.listByCompanyIprPatentIds(companyCaseFilingIds);
		if (CollectionUtil.isEmpty(dataCompanyIprPatentFamilyDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprPatentFamilyExWarehouseVO> dataCompanyIprPatentFamilyExWarehouseVOS = DataCompanyIprPatentFamilyAppStructMapping.instance.dataCompanyIprPatentFamilyDOsToDataCompanyIprPatentFamilyExWarehouseVOs(dataCompanyIprPatentFamilyDOList);
		return MultiResponse.of(dataCompanyIprPatentFamilyExWarehouseVOS);
	}
	/**
	 * 企业知识产权专利同族信息出库
	 * @param applyNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentFamilyExWarehouseVO> exWarehouseByCompanyIprPatentIdAndApplyNo(Long companyIprPatentId,String applyNo) {
		DataCompanyIprPatentFamilyDO dataCompanyIprPatentFamilyDO = iDataCompanyIprPatentFamilyService.getByCompanyIprPatentIdAndApplyNo(companyIprPatentId,applyNo);
		if (dataCompanyIprPatentFamilyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentFamilyExWarehouseVO dataCompanyIprPatentFamilyExWarehouseVO = DataCompanyIprPatentFamilyAppStructMapping.instance.dataCompanyIprPatentFamilyDOToDataCompanyIprPatentFamilyExWarehouseVO(dataCompanyIprPatentFamilyDO);
		return SingleResponse.of(dataCompanyIprPatentFamilyExWarehouseVO);
	}
	/**
	 * 企业知识产权专利同族信息出库
	 * @param standardApplyNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentFamilyExWarehouseVO> exWarehouseByCompanyIprPatentIdAndStandardApplyNo(Long companyIprPatentId,String standardApplyNo) {
		DataCompanyIprPatentFamilyDO dataCompanyIprPatentFamilyDO = iDataCompanyIprPatentFamilyService.getByCompanyIprPatentIdAndStandardApplyNo(companyIprPatentId,standardApplyNo);
		if (dataCompanyIprPatentFamilyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentFamilyExWarehouseVO dataCompanyIprPatentFamilyExWarehouseVO = DataCompanyIprPatentFamilyAppStructMapping.instance.dataCompanyIprPatentFamilyDOToDataCompanyIprPatentFamilyExWarehouseVO(dataCompanyIprPatentFamilyDO);
		return SingleResponse.of(dataCompanyIprPatentFamilyExWarehouseVO);
	}
	/**
	 * 企业知识产权专利同族信息出库
	 * @param publicNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentFamilyExWarehouseVO> exWarehouseByCompanyIprPatentIdAndPublicNo(Long companyIprPatentId,String publicNo) {
		DataCompanyIprPatentFamilyDO dataCompanyIprPatentFamilyDO = iDataCompanyIprPatentFamilyService.getByCompanyIprPatentIdAndPublicNo(companyIprPatentId,publicNo);
		if (dataCompanyIprPatentFamilyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentFamilyExWarehouseVO dataCompanyIprPatentFamilyExWarehouseVO = DataCompanyIprPatentFamilyAppStructMapping.instance.dataCompanyIprPatentFamilyDOToDataCompanyIprPatentFamilyExWarehouseVO(dataCompanyIprPatentFamilyDO);
		return SingleResponse.of(dataCompanyIprPatentFamilyExWarehouseVO);
	}
	/**
	 * 企业知识产权专利同族信息出库
	 * @param standardPublicNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentFamilyExWarehouseVO> exWarehouseByCompanyIprPatentIdAndStandardPublicNo(Long companyIprPatentId,String standardPublicNo) {
		DataCompanyIprPatentFamilyDO dataCompanyIprPatentFamilyDO = iDataCompanyIprPatentFamilyService.getByCompanyIprPatentIdAndStandardPublicNo(companyIprPatentId,standardPublicNo);
		if (dataCompanyIprPatentFamilyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentFamilyExWarehouseVO dataCompanyIprPatentFamilyExWarehouseVO = DataCompanyIprPatentFamilyAppStructMapping.instance.dataCompanyIprPatentFamilyDOToDataCompanyIprPatentFamilyExWarehouseVO(dataCompanyIprPatentFamilyDO);
		return SingleResponse.of(dataCompanyIprPatentFamilyExWarehouseVO);
	}
	/**
	 * 企业知识产权专利同族信息出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentFamilyExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprPatentFamilyDO dataCompanyIprPatentFamilyDO = iDataCompanyIprPatentFamilyService.getById(id);
		if (dataCompanyIprPatentFamilyDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentFamilyExWarehouseVO dataCompanyIprPatentFamilyExWarehouseVO = DataCompanyIprPatentFamilyAppStructMapping.instance.dataCompanyIprPatentFamilyDOToDataCompanyIprPatentFamilyExWarehouseVO(dataCompanyIprPatentFamilyDO);
		return SingleResponse.of(dataCompanyIprPatentFamilyExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprPatentFamilyService(IDataCompanyIprPatentFamilyService iDataCompanyIprPatentFamilyService) {
		this.iDataCompanyIprPatentFamilyService = iDataCompanyIprPatentFamilyService;
	}
}
