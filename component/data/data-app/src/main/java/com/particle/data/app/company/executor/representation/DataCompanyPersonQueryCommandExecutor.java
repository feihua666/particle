package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyPersonAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyPersonQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPersonVO;
import com.particle.data.infrastructure.company.dos.DataCompanyPersonDO;
import com.particle.data.infrastructure.company.service.IDataCompanyPersonService;
import com.particle.data.client.company.dto.command.representation.DataCompanyPersonPageQueryCommand;
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
 * 企业个人 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Component
@Validated
public class DataCompanyPersonQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyPersonService iDataCompanyPersonService;

	/**
	 * 执行 企业个人 列表查询指令
	 * @param dataCompanyPersonQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyPersonVO> execute(@Valid DataCompanyPersonQueryListCommand dataCompanyPersonQueryListCommand) {
		List<DataCompanyPersonDO> dataCompanyPersonDO = iDataCompanyPersonService.list(dataCompanyPersonQueryListCommand);
		List<DataCompanyPersonVO> dataCompanyPersonVOs = DataCompanyPersonAppStructMapping.instance.dataCompanyPersonDOsToDataCompanyPersonVOs(dataCompanyPersonDO);
		return MultiResponse.of(dataCompanyPersonVOs);
	}
	/**
	 * 执行 企业个人 分页查询指令
	 * @param dataCompanyPersonPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyPersonVO> execute(@Valid DataCompanyPersonPageQueryCommand dataCompanyPersonPageQueryCommand) {
		Page<DataCompanyPersonDO> page = iDataCompanyPersonService.listPage(dataCompanyPersonPageQueryCommand);
		return DataCompanyPersonAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业个人 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPersonVO> executeDetail(IdCommand detailCommand) {
		DataCompanyPersonDO byId = iDataCompanyPersonService.getById(detailCommand.getId());
		DataCompanyPersonVO dataCompanyPersonVO = DataCompanyPersonAppStructMapping.instance.dataCompanyPersonDOToDataCompanyPersonVO(byId);
		return SingleResponse.of(dataCompanyPersonVO);
	}
	/**
	 * 执行 企业个人 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPersonVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyPersonDO byId = iDataCompanyPersonService.getById(detailForUpdateCommand.getId());
		DataCompanyPersonVO dataCompanyPersonVO = DataCompanyPersonAppStructMapping.instance.dataCompanyPersonDOToDataCompanyPersonVO(byId);
		return SingleResponse.of(dataCompanyPersonVO);
	}


	@Autowired
	public void setIDataCompanyPersonService(IDataCompanyPersonService iDataCompanyPersonService) {
		this.iDataCompanyPersonService = iDataCompanyPersonService;
	}
}
