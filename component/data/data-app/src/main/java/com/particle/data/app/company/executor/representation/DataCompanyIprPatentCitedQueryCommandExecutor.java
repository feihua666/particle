package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentCitedAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCitedQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCitedVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCitedDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentCitedService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCitedPageQueryCommand;
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
 * 企业知识产权专利被引证信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:40:15
 */
@Component
@Validated
public class DataCompanyIprPatentCitedQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentCitedService iDataCompanyIprPatentCitedService;

	/**
	 * 执行 企业知识产权专利被引证信息 列表查询指令
	 * @param dataCompanyIprPatentCitedQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentCitedVO> execute(@Valid DataCompanyIprPatentCitedQueryListCommand dataCompanyIprPatentCitedQueryListCommand) {
		List<DataCompanyIprPatentCitedDO> dataCompanyIprPatentCitedDO = iDataCompanyIprPatentCitedService.list(dataCompanyIprPatentCitedQueryListCommand);
		List<DataCompanyIprPatentCitedVO> dataCompanyIprPatentCitedVOs = DataCompanyIprPatentCitedAppStructMapping.instance.dataCompanyIprPatentCitedDOsToDataCompanyIprPatentCitedVOs(dataCompanyIprPatentCitedDO);
		return MultiResponse.of(dataCompanyIprPatentCitedVOs);
	}
	/**
	 * 执行 企业知识产权专利被引证信息 分页查询指令
	 * @param dataCompanyIprPatentCitedPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentCitedVO> execute(@Valid DataCompanyIprPatentCitedPageQueryCommand dataCompanyIprPatentCitedPageQueryCommand) {
		Page<DataCompanyIprPatentCitedDO> page = iDataCompanyIprPatentCitedService.listPage(dataCompanyIprPatentCitedPageQueryCommand);
		return DataCompanyIprPatentCitedAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权专利被引证信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCitedVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPatentCitedDO byId = iDataCompanyIprPatentCitedService.getById(detailCommand.getId());
		DataCompanyIprPatentCitedVO dataCompanyIprPatentCitedVO = DataCompanyIprPatentCitedAppStructMapping.instance.dataCompanyIprPatentCitedDOToDataCompanyIprPatentCitedVO(byId);
		return SingleResponse.of(dataCompanyIprPatentCitedVO);
	}
	/**
	 * 执行 企业知识产权专利被引证信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCitedVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPatentCitedDO byId = iDataCompanyIprPatentCitedService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPatentCitedVO dataCompanyIprPatentCitedVO = DataCompanyIprPatentCitedAppStructMapping.instance.dataCompanyIprPatentCitedDOToDataCompanyIprPatentCitedVO(byId);
		return SingleResponse.of(dataCompanyIprPatentCitedVO);
	}


	@Autowired
	public void setIDataCompanyIprPatentCitedService(IDataCompanyIprPatentCitedService iDataCompanyIprPatentCitedService) {
		this.iDataCompanyIprPatentCitedService = iDataCompanyIprPatentCitedService;
	}
}
