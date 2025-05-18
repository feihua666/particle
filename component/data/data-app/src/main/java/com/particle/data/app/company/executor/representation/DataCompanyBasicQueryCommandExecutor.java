package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyBasicAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyBasicQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyBasicVO;
import com.particle.data.infrastructure.company.dos.DataCompanyBasicDO;
import com.particle.data.infrastructure.company.service.IDataCompanyBasicService;
import com.particle.data.client.company.dto.command.representation.DataCompanyBasicPageQueryCommand;
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
 * 企业基本信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Component
@Validated
public class DataCompanyBasicQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyBasicService iDataCompanyBasicService;

	/**
	 * 执行 企业基本信息 列表查询指令
	 * @param dataCompanyBasicQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyBasicVO> execute(@Valid DataCompanyBasicQueryListCommand dataCompanyBasicQueryListCommand) {
		List<DataCompanyBasicDO> dataCompanyBasicDO = iDataCompanyBasicService.list(dataCompanyBasicQueryListCommand);
		List<DataCompanyBasicVO> dataCompanyBasicVOs = DataCompanyBasicAppStructMapping.instance.dataCompanyBasicDOsToDataCompanyBasicVOs(dataCompanyBasicDO);
		return MultiResponse.of(dataCompanyBasicVOs);
	}
	/**
	 * 执行 企业基本信息 分页查询指令
	 * @param dataCompanyBasicPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyBasicVO> execute(@Valid DataCompanyBasicPageQueryCommand dataCompanyBasicPageQueryCommand) {
		Page<DataCompanyBasicDO> page = iDataCompanyBasicService.listPage(dataCompanyBasicPageQueryCommand);
		return DataCompanyBasicAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业基本信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyBasicVO> executeDetail(IdCommand detailCommand) {
		DataCompanyBasicDO byId = iDataCompanyBasicService.getById(detailCommand.getId());
		DataCompanyBasicVO dataCompanyBasicVO = DataCompanyBasicAppStructMapping.instance.dataCompanyBasicDOToDataCompanyBasicVO(byId);
		return SingleResponse.of(dataCompanyBasicVO);
	}
	/**
	 * 执行 企业基本信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyBasicVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyBasicDO byId = iDataCompanyBasicService.getById(detailForUpdateCommand.getId());
		DataCompanyBasicVO dataCompanyBasicVO = DataCompanyBasicAppStructMapping.instance.dataCompanyBasicDOToDataCompanyBasicVO(byId);
		return SingleResponse.of(dataCompanyBasicVO);
	}


	@Autowired
	public void setIDataCompanyBasicService(IDataCompanyBasicService iDataCompanyBasicService) {
		this.iDataCompanyBasicService = iDataCompanyBasicService;
	}
}
