package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanySpotCheckAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanySpotCheckQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanySpotCheckVO;
import com.particle.data.infrastructure.company.dos.DataCompanySpotCheckDO;
import com.particle.data.infrastructure.company.service.IDataCompanySpotCheckService;
import com.particle.data.client.company.dto.command.representation.DataCompanySpotCheckPageQueryCommand;
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
 * 企业抽查检查 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@Component
@Validated
public class DataCompanySpotCheckQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanySpotCheckService iDataCompanySpotCheckService;

	/**
	 * 执行 企业抽查检查 列表查询指令
	 * @param dataCompanySpotCheckQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanySpotCheckVO> execute(@Valid DataCompanySpotCheckQueryListCommand dataCompanySpotCheckQueryListCommand) {
		List<DataCompanySpotCheckDO> dataCompanySpotCheckDO = iDataCompanySpotCheckService.list(dataCompanySpotCheckQueryListCommand);
		List<DataCompanySpotCheckVO> dataCompanySpotCheckVOs = DataCompanySpotCheckAppStructMapping.instance.dataCompanySpotCheckDOsToDataCompanySpotCheckVOs(dataCompanySpotCheckDO);
		return MultiResponse.of(dataCompanySpotCheckVOs);
	}
	/**
	 * 执行 企业抽查检查 分页查询指令
	 * @param dataCompanySpotCheckPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanySpotCheckVO> execute(@Valid DataCompanySpotCheckPageQueryCommand dataCompanySpotCheckPageQueryCommand) {
		Page<DataCompanySpotCheckDO> page = iDataCompanySpotCheckService.listPage(dataCompanySpotCheckPageQueryCommand);
		return DataCompanySpotCheckAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业抽查检查 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanySpotCheckVO> executeDetail(IdCommand detailCommand) {
		DataCompanySpotCheckDO byId = iDataCompanySpotCheckService.getById(detailCommand.getId());
		DataCompanySpotCheckVO dataCompanySpotCheckVO = DataCompanySpotCheckAppStructMapping.instance.dataCompanySpotCheckDOToDataCompanySpotCheckVO(byId);
		return SingleResponse.of(dataCompanySpotCheckVO);
	}
	/**
	 * 执行 企业抽查检查 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanySpotCheckVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanySpotCheckDO byId = iDataCompanySpotCheckService.getById(detailForUpdateCommand.getId());
		DataCompanySpotCheckVO dataCompanySpotCheckVO = DataCompanySpotCheckAppStructMapping.instance.dataCompanySpotCheckDOToDataCompanySpotCheckVO(byId);
		return SingleResponse.of(dataCompanySpotCheckVO);
	}


	@Autowired
	public void setIDataCompanySpotCheckService(IDataCompanySpotCheckService iDataCompanySpotCheckService) {
		this.iDataCompanySpotCheckService = iDataCompanySpotCheckService;
	}
}
