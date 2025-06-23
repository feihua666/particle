package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkPartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPartyVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkPartyService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPartyPageQueryCommand;
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
 * 企业知识产权商标当事人 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-16 11:15:34
 */
@Component
@Validated
public class DataCompanyIprTrademarkPartyQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprTrademarkPartyService iDataCompanyIprTrademarkPartyService;

	/**
	 * 执行 企业知识产权商标当事人 列表查询指令
	 * @param dataCompanyIprTrademarkPartyQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprTrademarkPartyVO> execute(@Valid DataCompanyIprTrademarkPartyQueryListCommand dataCompanyIprTrademarkPartyQueryListCommand) {
		List<DataCompanyIprTrademarkPartyDO> dataCompanyIprTrademarkPartyDO = iDataCompanyIprTrademarkPartyService.list(dataCompanyIprTrademarkPartyQueryListCommand);
		List<DataCompanyIprTrademarkPartyVO> dataCompanyIprTrademarkPartyVOs = DataCompanyIprTrademarkPartyAppStructMapping.instance.dataCompanyIprTrademarkPartyDOsToDataCompanyIprTrademarkPartyVOs(dataCompanyIprTrademarkPartyDO);
		return MultiResponse.of(dataCompanyIprTrademarkPartyVOs);
	}
	/**
	 * 执行 企业知识产权商标当事人 分页查询指令
	 * @param dataCompanyIprTrademarkPartyPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkPartyVO> execute(@Valid DataCompanyIprTrademarkPartyPageQueryCommand dataCompanyIprTrademarkPartyPageQueryCommand) {
		Page<DataCompanyIprTrademarkPartyDO> page = iDataCompanyIprTrademarkPartyService.listPage(dataCompanyIprTrademarkPartyPageQueryCommand);
		return DataCompanyIprTrademarkPartyAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权商标当事人 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPartyVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprTrademarkPartyDO byId = iDataCompanyIprTrademarkPartyService.getById(detailCommand.getId());
		DataCompanyIprTrademarkPartyVO dataCompanyIprTrademarkPartyVO = DataCompanyIprTrademarkPartyAppStructMapping.instance.dataCompanyIprTrademarkPartyDOToDataCompanyIprTrademarkPartyVO(byId);
		return SingleResponse.of(dataCompanyIprTrademarkPartyVO);
	}
	/**
	 * 执行 企业知识产权商标当事人 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPartyVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprTrademarkPartyDO byId = iDataCompanyIprTrademarkPartyService.getById(detailForUpdateCommand.getId());
		DataCompanyIprTrademarkPartyVO dataCompanyIprTrademarkPartyVO = DataCompanyIprTrademarkPartyAppStructMapping.instance.dataCompanyIprTrademarkPartyDOToDataCompanyIprTrademarkPartyVO(byId);
		return SingleResponse.of(dataCompanyIprTrademarkPartyVO);
	}


	@Autowired
	public void setIDataCompanyIprTrademarkPartyService(IDataCompanyIprTrademarkPartyService iDataCompanyIprTrademarkPartyService) {
		this.iDataCompanyIprTrademarkPartyService = iDataCompanyIprTrademarkPartyService;
	}
}
