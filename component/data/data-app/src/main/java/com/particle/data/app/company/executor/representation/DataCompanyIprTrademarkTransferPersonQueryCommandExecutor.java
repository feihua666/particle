package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkTransferPersonAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferPersonQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferPersonVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferPersonDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkTransferPersonService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferPersonPageQueryCommand;
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
 * 企业知识产权商标转让人 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Component
@Validated
public class DataCompanyIprTrademarkTransferPersonQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprTrademarkTransferPersonService iDataCompanyIprTrademarkTransferPersonService;

	/**
	 * 执行 企业知识产权商标转让人 列表查询指令
	 * @param dataCompanyIprTrademarkTransferPersonQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprTrademarkTransferPersonVO> execute(@Valid DataCompanyIprTrademarkTransferPersonQueryListCommand dataCompanyIprTrademarkTransferPersonQueryListCommand) {
		List<DataCompanyIprTrademarkTransferPersonDO> dataCompanyIprTrademarkTransferPersonDO = iDataCompanyIprTrademarkTransferPersonService.list(dataCompanyIprTrademarkTransferPersonQueryListCommand);
		List<DataCompanyIprTrademarkTransferPersonVO> dataCompanyIprTrademarkTransferPersonVOs = DataCompanyIprTrademarkTransferPersonAppStructMapping.instance.dataCompanyIprTrademarkTransferPersonDOsToDataCompanyIprTrademarkTransferPersonVOs(dataCompanyIprTrademarkTransferPersonDO);
		return MultiResponse.of(dataCompanyIprTrademarkTransferPersonVOs);
	}
	/**
	 * 执行 企业知识产权商标转让人 分页查询指令
	 * @param dataCompanyIprTrademarkTransferPersonPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkTransferPersonVO> execute(@Valid DataCompanyIprTrademarkTransferPersonPageQueryCommand dataCompanyIprTrademarkTransferPersonPageQueryCommand) {
		Page<DataCompanyIprTrademarkTransferPersonDO> page = iDataCompanyIprTrademarkTransferPersonService.listPage(dataCompanyIprTrademarkTransferPersonPageQueryCommand);
		return DataCompanyIprTrademarkTransferPersonAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权商标转让人 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferPersonVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprTrademarkTransferPersonDO byId = iDataCompanyIprTrademarkTransferPersonService.getById(detailCommand.getId());
		DataCompanyIprTrademarkTransferPersonVO dataCompanyIprTrademarkTransferPersonVO = DataCompanyIprTrademarkTransferPersonAppStructMapping.instance.dataCompanyIprTrademarkTransferPersonDOToDataCompanyIprTrademarkTransferPersonVO(byId);
		return SingleResponse.of(dataCompanyIprTrademarkTransferPersonVO);
	}
	/**
	 * 执行 企业知识产权商标转让人 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferPersonVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprTrademarkTransferPersonDO byId = iDataCompanyIprTrademarkTransferPersonService.getById(detailForUpdateCommand.getId());
		DataCompanyIprTrademarkTransferPersonVO dataCompanyIprTrademarkTransferPersonVO = DataCompanyIprTrademarkTransferPersonAppStructMapping.instance.dataCompanyIprTrademarkTransferPersonDOToDataCompanyIprTrademarkTransferPersonVO(byId);
		return SingleResponse.of(dataCompanyIprTrademarkTransferPersonVO);
	}


	@Autowired
	public void setIDataCompanyIprTrademarkTransferPersonService(IDataCompanyIprTrademarkTransferPersonService iDataCompanyIprTrademarkTransferPersonService) {
		this.iDataCompanyIprTrademarkTransferPersonService = iDataCompanyIprTrademarkTransferPersonService;
	}
}
