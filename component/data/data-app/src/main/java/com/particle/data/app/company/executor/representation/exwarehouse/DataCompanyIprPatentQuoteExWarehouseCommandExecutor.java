package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentQuoteAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentQuoteExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentQuoteExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentQuoteDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentQuoteService;
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
 * 企业知识产权专利引证信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentQuoteExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentQuoteService iDataCompanyIprPatentQuoteService;

	/**
	 * 企业知识产权专利引证信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentQuoteExWarehouseVO> exWarehouse(@Valid DataCompanyIprPatentQuoteExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprPatentQuoteDO> dataCompanyIprPatentQuoteDOPage = null;
		dataCompanyIprPatentQuoteDOPage = iDataCompanyIprPatentQuoteService.listPageByCompanyIprPatentId(dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId(),
				dataCompanyExWarehouseQueryCommand);

		if (dataCompanyIprPatentQuoteDOPage == null || dataCompanyIprPatentQuoteDOPage.getRecords() == null || dataCompanyIprPatentQuoteDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprPatentQuoteAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprPatentQuoteDOPage);
	}
	/**
	 * 企业知识产权专利引证信息出库
	 * @param companyIprPatentIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentQuoteExWarehouseVO> exWarehouseByCompanyIprPatentIds(List<Long> companyIprPatentIds) {
		List<DataCompanyIprPatentQuoteDO> dataCompanyIprPatentQuoteDOList = iDataCompanyIprPatentQuoteService.listByCompanyIprPatentIds(companyIprPatentIds);
		if (CollectionUtil.isEmpty(dataCompanyIprPatentQuoteDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprPatentQuoteExWarehouseVO> dataCompanyIprPatentQuoteExWarehouseVOS = DataCompanyIprPatentQuoteAppStructMapping.instance.dataCompanyIprPatentQuoteDOsToDataCompanyIprPatentQuoteExWarehouseVOs(dataCompanyIprPatentQuoteDOList);
		return MultiResponse.of(dataCompanyIprPatentQuoteExWarehouseVOS);
	}
	/**
	 * 企业知识产权专利引证信息出库
	 * @param applyNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentQuoteExWarehouseVO> exWarehouseByCompanyIprPatentIdAndApplyNo(Long companyIprPatentId,String applyNo) {
		DataCompanyIprPatentQuoteDO dataCompanyIprPatentQuoteDO = iDataCompanyIprPatentQuoteService.getByCompanyIprPatentIdAndApplyNo(companyIprPatentId,applyNo);
		if (dataCompanyIprPatentQuoteDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentQuoteExWarehouseVO dataCompanyIprPatentQuoteExWarehouseVO = DataCompanyIprPatentQuoteAppStructMapping.instance.dataCompanyIprPatentQuoteDOToDataCompanyIprPatentQuoteExWarehouseVO(dataCompanyIprPatentQuoteDO);
		return SingleResponse.of(dataCompanyIprPatentQuoteExWarehouseVO);
	}
	/**
	 * 企业知识产权专利引证信息出库
	 * @param standardApplyNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentQuoteExWarehouseVO> exWarehouseByCompanyIprPatentIdAndStandardApplyNo(Long companyIprPatentId,String standardApplyNo) {
		DataCompanyIprPatentQuoteDO dataCompanyIprPatentQuoteDO = iDataCompanyIprPatentQuoteService.getByCompanyIprPatentIdAndStandardApplyNo(companyIprPatentId,standardApplyNo);
		if (dataCompanyIprPatentQuoteDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentQuoteExWarehouseVO dataCompanyIprPatentQuoteExWarehouseVO = DataCompanyIprPatentQuoteAppStructMapping.instance.dataCompanyIprPatentQuoteDOToDataCompanyIprPatentQuoteExWarehouseVO(dataCompanyIprPatentQuoteDO);
		return SingleResponse.of(dataCompanyIprPatentQuoteExWarehouseVO);
	}
	/**
	 * 企业知识产权专利引证信息出库
	 * @param publicNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentQuoteExWarehouseVO> exWarehouseByCompanyIprPatentIdAndPublicNo(Long companyIprPatentId,String publicNo) {
		DataCompanyIprPatentQuoteDO dataCompanyIprPatentQuoteDO = iDataCompanyIprPatentQuoteService.getByCompanyIprPatentIdAndPublicNo(companyIprPatentId,publicNo);
		if (dataCompanyIprPatentQuoteDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentQuoteExWarehouseVO dataCompanyIprPatentQuoteExWarehouseVO = DataCompanyIprPatentQuoteAppStructMapping.instance.dataCompanyIprPatentQuoteDOToDataCompanyIprPatentQuoteExWarehouseVO(dataCompanyIprPatentQuoteDO);
		return SingleResponse.of(dataCompanyIprPatentQuoteExWarehouseVO);
	}

	/**
	 * 企业知识产权专利引证信息出库
	 * @param standardPublicNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentQuoteExWarehouseVO> exWarehouseByCompanyIprPatentIdAndStandardPublicNo(Long companyIprPatentId,String standardPublicNo) {
		DataCompanyIprPatentQuoteDO dataCompanyIprPatentQuoteDO = iDataCompanyIprPatentQuoteService.getByCompanyIprPatentIdAndStandardPublicNo(companyIprPatentId,standardPublicNo);
		if (dataCompanyIprPatentQuoteDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentQuoteExWarehouseVO dataCompanyIprPatentQuoteExWarehouseVO = DataCompanyIprPatentQuoteAppStructMapping.instance.dataCompanyIprPatentQuoteDOToDataCompanyIprPatentQuoteExWarehouseVO(dataCompanyIprPatentQuoteDO);
		return SingleResponse.of(dataCompanyIprPatentQuoteExWarehouseVO);
	}
	/**
	 * 企业知识产权专利引证信息出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentQuoteExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprPatentQuoteDO dataCompanyIprPatentQuoteDO = iDataCompanyIprPatentQuoteService.getById(id);
		if (dataCompanyIprPatentQuoteDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentQuoteExWarehouseVO dataCompanyIprPatentQuoteExWarehouseVO = DataCompanyIprPatentQuoteAppStructMapping.instance.dataCompanyIprPatentQuoteDOToDataCompanyIprPatentQuoteExWarehouseVO(dataCompanyIprPatentQuoteDO);
		return SingleResponse.of(dataCompanyIprPatentQuoteExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprPatentQuoteService(IDataCompanyIprPatentQuoteService iDataCompanyIprPatentQuoteService) {
		this.iDataCompanyIprPatentQuoteService = iDataCompanyIprPatentQuoteService;
	}
}
