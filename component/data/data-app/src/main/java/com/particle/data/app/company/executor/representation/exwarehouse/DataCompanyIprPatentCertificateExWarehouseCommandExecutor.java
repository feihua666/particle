package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentCertificateAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentCertificateExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentCertificateExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCertificateDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentCertificateService;
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
 * 企业知识产权专利证书信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentCertificateExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentCertificateService iDataCompanyIprPatentCertificateService;

	/**
	 * 企业知识产权专利证书信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentCertificateExWarehouseVO> exWarehouse(@Valid DataCompanyIprPatentCertificateExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprPatentCertificateDO> dataCompanyIprPatentCertificateDOPage = null;
		dataCompanyIprPatentCertificateDOPage = iDataCompanyIprPatentCertificateService.listPageByCompanyIprPatentId(dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId(),
				dataCompanyExWarehouseQueryCommand);

		if (dataCompanyIprPatentCertificateDOPage == null || dataCompanyIprPatentCertificateDOPage.getRecords() == null || dataCompanyIprPatentCertificateDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprPatentCertificateAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprPatentCertificateDOPage);
	}
	/**
	 * 企业知识产权专利证书信息出库
	 * @param companyCaseFilingIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentCertificateExWarehouseVO> exWarehouseByCompanyIprPatentIds(List<Long> companyCaseFilingIds) {
		List<DataCompanyIprPatentCertificateDO> dataCompanyIprPatentCertificateDOList = iDataCompanyIprPatentCertificateService.listByCompanyIprPatentIds(companyCaseFilingIds);
		if (CollectionUtil.isEmpty(dataCompanyIprPatentCertificateDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprPatentCertificateExWarehouseVO> dataCompanyIprPatentCertificateExWarehouseVOS = DataCompanyIprPatentCertificateAppStructMapping.instance.dataCompanyIprPatentCertificateDOsToDataCompanyIprPatentCertificateExWarehouseVOs(dataCompanyIprPatentCertificateDOList);
		return MultiResponse.of(dataCompanyIprPatentCertificateExWarehouseVOS);
	}
	/**
	 * 企业知识产权专利证书信息出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCertificateExWarehouseVO> exWarehouseByCompanyIprPatentIdAndDataMd5(Long companyIprPatentId,String dataMd5) {
		DataCompanyIprPatentCertificateDO dataCompanyIprPatentCertificateDO = iDataCompanyIprPatentCertificateService.getByCompanyIprPatentIdAndDataMd5(companyIprPatentId,dataMd5);
		if (dataCompanyIprPatentCertificateDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentCertificateExWarehouseVO dataCompanyIprPatentCertificateExWarehouseVO = DataCompanyIprPatentCertificateAppStructMapping.instance.dataCompanyIprPatentCertificateDOToDataCompanyIprPatentCertificateExWarehouseVO(dataCompanyIprPatentCertificateDO);
		return SingleResponse.of(dataCompanyIprPatentCertificateExWarehouseVO);
	}
	/**
	 * 企业知识产权专利证书信息出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCertificateExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprPatentCertificateDO dataCompanyIprPatentCertificateDO = iDataCompanyIprPatentCertificateService.getById(id);
		if (dataCompanyIprPatentCertificateDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentCertificateExWarehouseVO dataCompanyIprPatentCertificateExWarehouseVO = DataCompanyIprPatentCertificateAppStructMapping.instance.dataCompanyIprPatentCertificateDOToDataCompanyIprPatentCertificateExWarehouseVO(dataCompanyIprPatentCertificateDO);
		return SingleResponse.of(dataCompanyIprPatentCertificateExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprPatentCertificateService(IDataCompanyIprPatentCertificateService iDataCompanyIprPatentCertificateService) {
		this.iDataCompanyIprPatentCertificateService = iDataCompanyIprPatentCertificateService;
	}
}
