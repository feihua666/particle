package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkTransferAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkTransferService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferPageQueryCommand;
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
 * 企业知识产权商标转让信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-16 11:16:04
 */
@Component
@Validated
public class DataCompanyIprTrademarkTransferQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprTrademarkTransferService iDataCompanyIprTrademarkTransferService;

	/**
	 * 执行 企业知识产权商标转让信息 列表查询指令
	 * @param dataCompanyIprTrademarkTransferQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprTrademarkTransferVO> execute(@Valid DataCompanyIprTrademarkTransferQueryListCommand dataCompanyIprTrademarkTransferQueryListCommand) {
		List<DataCompanyIprTrademarkTransferDO> dataCompanyIprTrademarkTransferDO = iDataCompanyIprTrademarkTransferService.list(dataCompanyIprTrademarkTransferQueryListCommand);
		List<DataCompanyIprTrademarkTransferVO> dataCompanyIprTrademarkTransferVOs = DataCompanyIprTrademarkTransferAppStructMapping.instance.dataCompanyIprTrademarkTransferDOsToDataCompanyIprTrademarkTransferVOs(dataCompanyIprTrademarkTransferDO);
		return MultiResponse.of(dataCompanyIprTrademarkTransferVOs);
	}
	/**
	 * 执行 企业知识产权商标转让信息 分页查询指令
	 * @param dataCompanyIprTrademarkTransferPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkTransferVO> execute(@Valid DataCompanyIprTrademarkTransferPageQueryCommand dataCompanyIprTrademarkTransferPageQueryCommand) {
		Page<DataCompanyIprTrademarkTransferDO> page = iDataCompanyIprTrademarkTransferService.listPage(dataCompanyIprTrademarkTransferPageQueryCommand);
		return DataCompanyIprTrademarkTransferAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权商标转让信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprTrademarkTransferDO byId = iDataCompanyIprTrademarkTransferService.getById(detailCommand.getId());
		DataCompanyIprTrademarkTransferVO dataCompanyIprTrademarkTransferVO = DataCompanyIprTrademarkTransferAppStructMapping.instance.dataCompanyIprTrademarkTransferDOToDataCompanyIprTrademarkTransferVO(byId);
		return SingleResponse.of(dataCompanyIprTrademarkTransferVO);
	}
	/**
	 * 执行 企业知识产权商标转让信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkTransferVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprTrademarkTransferDO byId = iDataCompanyIprTrademarkTransferService.getById(detailForUpdateCommand.getId());
		DataCompanyIprTrademarkTransferVO dataCompanyIprTrademarkTransferVO = DataCompanyIprTrademarkTransferAppStructMapping.instance.dataCompanyIprTrademarkTransferDOToDataCompanyIprTrademarkTransferVO(byId);
		return SingleResponse.of(dataCompanyIprTrademarkTransferVO);
	}


	@Autowired
	public void setIDataCompanyIprTrademarkTransferService(IDataCompanyIprTrademarkTransferService iDataCompanyIprTrademarkTransferService) {
		this.iDataCompanyIprTrademarkTransferService = iDataCompanyIprTrademarkTransferService;
	}
}
