package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprSoftwareCopyrightAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprSoftwareCopyrightQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprSoftwareCopyrightVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprSoftwareCopyrightDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprSoftwareCopyrightService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprSoftwareCopyrightPageQueryCommand;
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
 * 企业知识产权软件著作 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-16 11:17:01
 */
@Component
@Validated
public class DataCompanyIprSoftwareCopyrightQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprSoftwareCopyrightService iDataCompanyIprSoftwareCopyrightService;

	/**
	 * 执行 企业知识产权软件著作 列表查询指令
	 * @param dataCompanyIprSoftwareCopyrightQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprSoftwareCopyrightVO> execute(@Valid DataCompanyIprSoftwareCopyrightQueryListCommand dataCompanyIprSoftwareCopyrightQueryListCommand) {
		List<DataCompanyIprSoftwareCopyrightDO> dataCompanyIprSoftwareCopyrightDO = iDataCompanyIprSoftwareCopyrightService.list(dataCompanyIprSoftwareCopyrightQueryListCommand);
		List<DataCompanyIprSoftwareCopyrightVO> dataCompanyIprSoftwareCopyrightVOs = DataCompanyIprSoftwareCopyrightAppStructMapping.instance.dataCompanyIprSoftwareCopyrightDOsToDataCompanyIprSoftwareCopyrightVOs(dataCompanyIprSoftwareCopyrightDO);
		return MultiResponse.of(dataCompanyIprSoftwareCopyrightVOs);
	}
	/**
	 * 执行 企业知识产权软件著作 分页查询指令
	 * @param dataCompanyIprSoftwareCopyrightPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprSoftwareCopyrightVO> execute(@Valid DataCompanyIprSoftwareCopyrightPageQueryCommand dataCompanyIprSoftwareCopyrightPageQueryCommand) {
		Page<DataCompanyIprSoftwareCopyrightDO> page = iDataCompanyIprSoftwareCopyrightService.listPage(dataCompanyIprSoftwareCopyrightPageQueryCommand);
		return DataCompanyIprSoftwareCopyrightAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权软件著作 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprSoftwareCopyrightVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprSoftwareCopyrightDO byId = iDataCompanyIprSoftwareCopyrightService.getById(detailCommand.getId());
		DataCompanyIprSoftwareCopyrightVO dataCompanyIprSoftwareCopyrightVO = DataCompanyIprSoftwareCopyrightAppStructMapping.instance.dataCompanyIprSoftwareCopyrightDOToDataCompanyIprSoftwareCopyrightVO(byId);
		return SingleResponse.of(dataCompanyIprSoftwareCopyrightVO);
	}
	/**
	 * 执行 企业知识产权软件著作 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprSoftwareCopyrightVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprSoftwareCopyrightDO byId = iDataCompanyIprSoftwareCopyrightService.getById(detailForUpdateCommand.getId());
		DataCompanyIprSoftwareCopyrightVO dataCompanyIprSoftwareCopyrightVO = DataCompanyIprSoftwareCopyrightAppStructMapping.instance.dataCompanyIprSoftwareCopyrightDOToDataCompanyIprSoftwareCopyrightVO(byId);
		return SingleResponse.of(dataCompanyIprSoftwareCopyrightVO);
	}


	@Autowired
	public void setIDataCompanyIprSoftwareCopyrightService(IDataCompanyIprSoftwareCopyrightService iDataCompanyIprSoftwareCopyrightService) {
		this.iDataCompanyIprSoftwareCopyrightService = iDataCompanyIprSoftwareCopyrightService;
	}
}
