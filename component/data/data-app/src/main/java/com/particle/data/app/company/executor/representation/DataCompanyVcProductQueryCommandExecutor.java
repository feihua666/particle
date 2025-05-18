package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyVcProductAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductVO;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductDO;
import com.particle.data.infrastructure.company.service.IDataCompanyVcProductService;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业融资产品 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Component
@Validated
public class DataCompanyVcProductQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyVcProductService iDataCompanyVcProductService;

	/**
	 * 执行 企业融资产品 列表查询指令
	 * @param dataCompanyVcProductQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyVcProductVO> execute(@Valid DataCompanyVcProductQueryListCommand dataCompanyVcProductQueryListCommand) {
		List<DataCompanyVcProductDO> dataCompanyVcProductDO = iDataCompanyVcProductService.list(dataCompanyVcProductQueryListCommand);
		List<DataCompanyVcProductVO> dataCompanyVcProductVOs = DataCompanyVcProductAppStructMapping.instance.dataCompanyVcProductDOsToDataCompanyVcProductVOs(dataCompanyVcProductDO);
		return MultiResponse.of(dataCompanyVcProductVOs);
	}
	/**
	 * 执行 企业融资产品 分页查询指令
	 * @param dataCompanyVcProductPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyVcProductVO> execute(@Valid DataCompanyVcProductPageQueryCommand dataCompanyVcProductPageQueryCommand) {
		Page<DataCompanyVcProductDO> page = iDataCompanyVcProductService.listPage(dataCompanyVcProductPageQueryCommand);
		return DataCompanyVcProductAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业融资产品 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductVO> executeDetail(IdCommand detailCommand) {
		DataCompanyVcProductDO byId = iDataCompanyVcProductService.getById(detailCommand.getId());
		DataCompanyVcProductVO dataCompanyVcProductVO = DataCompanyVcProductAppStructMapping.instance.dataCompanyVcProductDOToDataCompanyVcProductVO(byId);
		return SingleResponse.of(dataCompanyVcProductVO);
	}
	/**
	 * 执行 企业融资产品 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyVcProductDO byId = iDataCompanyVcProductService.getById(detailForUpdateCommand.getId());
		DataCompanyVcProductVO dataCompanyVcProductVO = DataCompanyVcProductAppStructMapping.instance.dataCompanyVcProductDOToDataCompanyVcProductVO(byId);
		return SingleResponse.of(dataCompanyVcProductVO);
	}


	@Autowired
	public void setIDataCompanyVcProductService(IDataCompanyVcProductService iDataCompanyVcProductService) {
		this.iDataCompanyVcProductService = iDataCompanyVcProductService;
	}
}
