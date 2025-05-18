package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentQuoteAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentQuoteQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentQuoteVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentQuoteDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentQuoteService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentQuotePageQueryCommand;
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
 * 企业知识产权专利引证信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:42:24
 */
@Component
@Validated
public class DataCompanyIprPatentQuoteQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentQuoteService iDataCompanyIprPatentQuoteService;

	/**
	 * 执行 企业知识产权专利引证信息 列表查询指令
	 * @param dataCompanyIprPatentQuoteQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentQuoteVO> execute(@Valid DataCompanyIprPatentQuoteQueryListCommand dataCompanyIprPatentQuoteQueryListCommand) {
		List<DataCompanyIprPatentQuoteDO> dataCompanyIprPatentQuoteDO = iDataCompanyIprPatentQuoteService.list(dataCompanyIprPatentQuoteQueryListCommand);
		List<DataCompanyIprPatentQuoteVO> dataCompanyIprPatentQuoteVOs = DataCompanyIprPatentQuoteAppStructMapping.instance.dataCompanyIprPatentQuoteDOsToDataCompanyIprPatentQuoteVOs(dataCompanyIprPatentQuoteDO);
		return MultiResponse.of(dataCompanyIprPatentQuoteVOs);
	}
	/**
	 * 执行 企业知识产权专利引证信息 分页查询指令
	 * @param dataCompanyIprPatentQuotePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentQuoteVO> execute(@Valid DataCompanyIprPatentQuotePageQueryCommand dataCompanyIprPatentQuotePageQueryCommand) {
		Page<DataCompanyIprPatentQuoteDO> page = iDataCompanyIprPatentQuoteService.listPage(dataCompanyIprPatentQuotePageQueryCommand);
		return DataCompanyIprPatentQuoteAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权专利引证信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentQuoteVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPatentQuoteDO byId = iDataCompanyIprPatentQuoteService.getById(detailCommand.getId());
		DataCompanyIprPatentQuoteVO dataCompanyIprPatentQuoteVO = DataCompanyIprPatentQuoteAppStructMapping.instance.dataCompanyIprPatentQuoteDOToDataCompanyIprPatentQuoteVO(byId);
		return SingleResponse.of(dataCompanyIprPatentQuoteVO);
	}
	/**
	 * 执行 企业知识产权专利引证信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentQuoteVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPatentQuoteDO byId = iDataCompanyIprPatentQuoteService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPatentQuoteVO dataCompanyIprPatentQuoteVO = DataCompanyIprPatentQuoteAppStructMapping.instance.dataCompanyIprPatentQuoteDOToDataCompanyIprPatentQuoteVO(byId);
		return SingleResponse.of(dataCompanyIprPatentQuoteVO);
	}


	@Autowired
	public void setIDataCompanyIprPatentQuoteService(IDataCompanyIprPatentQuoteService iDataCompanyIprPatentQuoteService) {
		this.iDataCompanyIprPatentQuoteService = iDataCompanyIprPatentQuoteService;
	}
}
