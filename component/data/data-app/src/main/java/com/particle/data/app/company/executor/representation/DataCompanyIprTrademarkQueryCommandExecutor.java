package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPageQueryCommand;
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
 * 企业知识产权商标 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@Component
@Validated
public class DataCompanyIprTrademarkQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprTrademarkService iDataCompanyIprTrademarkService;

	/**
	 * 执行 企业知识产权商标 列表查询指令
	 * @param dataCompanyIprTrademarkQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprTrademarkVO> execute(@Valid DataCompanyIprTrademarkQueryListCommand dataCompanyIprTrademarkQueryListCommand) {
		List<DataCompanyIprTrademarkDO> dataCompanyIprTrademarkDO = iDataCompanyIprTrademarkService.list(dataCompanyIprTrademarkQueryListCommand);
		List<DataCompanyIprTrademarkVO> dataCompanyIprTrademarkVOs = DataCompanyIprTrademarkAppStructMapping.instance.dataCompanyIprTrademarkDOsToDataCompanyIprTrademarkVOs(dataCompanyIprTrademarkDO);
		return MultiResponse.of(dataCompanyIprTrademarkVOs);
	}
	/**
	 * 执行 企业知识产权商标 分页查询指令
	 * @param dataCompanyIprTrademarkPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkVO> execute(@Valid DataCompanyIprTrademarkPageQueryCommand dataCompanyIprTrademarkPageQueryCommand) {
		Page<DataCompanyIprTrademarkDO> page = iDataCompanyIprTrademarkService.listPage(dataCompanyIprTrademarkPageQueryCommand);
		return DataCompanyIprTrademarkAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权商标 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprTrademarkDO byId = iDataCompanyIprTrademarkService.getById(detailCommand.getId());
		DataCompanyIprTrademarkVO dataCompanyIprTrademarkVO = DataCompanyIprTrademarkAppStructMapping.instance.dataCompanyIprTrademarkDOToDataCompanyIprTrademarkVO(byId);
		return SingleResponse.of(dataCompanyIprTrademarkVO);
	}
	/**
	 * 执行 企业知识产权商标 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprTrademarkDO byId = iDataCompanyIprTrademarkService.getById(detailForUpdateCommand.getId());
		DataCompanyIprTrademarkVO dataCompanyIprTrademarkVO = DataCompanyIprTrademarkAppStructMapping.instance.dataCompanyIprTrademarkDOToDataCompanyIprTrademarkVO(byId);
		return SingleResponse.of(dataCompanyIprTrademarkVO);
	}


	@Autowired
	public void setIDataCompanyIprTrademarkService(IDataCompanyIprTrademarkService iDataCompanyIprTrademarkService) {
		this.iDataCompanyIprTrademarkService = iDataCompanyIprTrademarkService;
	}
}
