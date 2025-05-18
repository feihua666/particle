package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentFamilyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentFamilyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentFamilyVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentFamilyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentFamilyService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentFamilyPageQueryCommand;
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
 * 企业知识产权专利同族信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:40:43
 */
@Component
@Validated
public class DataCompanyIprPatentFamilyQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentFamilyService iDataCompanyIprPatentFamilyService;

	/**
	 * 执行 企业知识产权专利同族信息 列表查询指令
	 * @param dataCompanyIprPatentFamilyQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentFamilyVO> execute(@Valid DataCompanyIprPatentFamilyQueryListCommand dataCompanyIprPatentFamilyQueryListCommand) {
		List<DataCompanyIprPatentFamilyDO> dataCompanyIprPatentFamilyDO = iDataCompanyIprPatentFamilyService.list(dataCompanyIprPatentFamilyQueryListCommand);
		List<DataCompanyIprPatentFamilyVO> dataCompanyIprPatentFamilyVOs = DataCompanyIprPatentFamilyAppStructMapping.instance.dataCompanyIprPatentFamilyDOsToDataCompanyIprPatentFamilyVOs(dataCompanyIprPatentFamilyDO);
		return MultiResponse.of(dataCompanyIprPatentFamilyVOs);
	}
	/**
	 * 执行 企业知识产权专利同族信息 分页查询指令
	 * @param dataCompanyIprPatentFamilyPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentFamilyVO> execute(@Valid DataCompanyIprPatentFamilyPageQueryCommand dataCompanyIprPatentFamilyPageQueryCommand) {
		Page<DataCompanyIprPatentFamilyDO> page = iDataCompanyIprPatentFamilyService.listPage(dataCompanyIprPatentFamilyPageQueryCommand);
		return DataCompanyIprPatentFamilyAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权专利同族信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentFamilyVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPatentFamilyDO byId = iDataCompanyIprPatentFamilyService.getById(detailCommand.getId());
		DataCompanyIprPatentFamilyVO dataCompanyIprPatentFamilyVO = DataCompanyIprPatentFamilyAppStructMapping.instance.dataCompanyIprPatentFamilyDOToDataCompanyIprPatentFamilyVO(byId);
		return SingleResponse.of(dataCompanyIprPatentFamilyVO);
	}
	/**
	 * 执行 企业知识产权专利同族信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentFamilyVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPatentFamilyDO byId = iDataCompanyIprPatentFamilyService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPatentFamilyVO dataCompanyIprPatentFamilyVO = DataCompanyIprPatentFamilyAppStructMapping.instance.dataCompanyIprPatentFamilyDOToDataCompanyIprPatentFamilyVO(byId);
		return SingleResponse.of(dataCompanyIprPatentFamilyVO);
	}


	@Autowired
	public void setIDataCompanyIprPatentFamilyService(IDataCompanyIprPatentFamilyService iDataCompanyIprPatentFamilyService) {
		this.iDataCompanyIprPatentFamilyService = iDataCompanyIprPatentFamilyService;
	}
}
