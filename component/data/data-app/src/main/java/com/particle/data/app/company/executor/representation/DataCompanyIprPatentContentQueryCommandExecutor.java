package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentContentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentContentVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentContentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentContentService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentContentPageQueryCommand;
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
 * 企业知识产权专利内容 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Component
@Validated
public class DataCompanyIprPatentContentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentContentService iDataCompanyIprPatentContentService;

	/**
	 * 执行 企业知识产权专利内容 列表查询指令
	 * @param dataCompanyIprPatentContentQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentContentVO> execute(@Valid DataCompanyIprPatentContentQueryListCommand dataCompanyIprPatentContentQueryListCommand) {
		List<DataCompanyIprPatentContentDO> dataCompanyIprPatentContentDO = iDataCompanyIprPatentContentService.list(dataCompanyIprPatentContentQueryListCommand);
		List<DataCompanyIprPatentContentVO> dataCompanyIprPatentContentVOs = DataCompanyIprPatentContentAppStructMapping.instance.dataCompanyIprPatentContentDOsToDataCompanyIprPatentContentVOs(dataCompanyIprPatentContentDO);
		return MultiResponse.of(dataCompanyIprPatentContentVOs);
	}
	/**
	 * 执行 企业知识产权专利内容 分页查询指令
	 * @param dataCompanyIprPatentContentPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentContentVO> execute(@Valid DataCompanyIprPatentContentPageQueryCommand dataCompanyIprPatentContentPageQueryCommand) {
		Page<DataCompanyIprPatentContentDO> page = iDataCompanyIprPatentContentService.listPage(dataCompanyIprPatentContentPageQueryCommand);
		return DataCompanyIprPatentContentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权专利内容 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentContentVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPatentContentDO byId = iDataCompanyIprPatentContentService.getById(detailCommand.getId());
		DataCompanyIprPatentContentVO dataCompanyIprPatentContentVO = DataCompanyIprPatentContentAppStructMapping.instance.dataCompanyIprPatentContentDOToDataCompanyIprPatentContentVO(byId);
		return SingleResponse.of(dataCompanyIprPatentContentVO);
	}
	/**
	 * 执行 企业知识产权专利内容 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentContentVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPatentContentDO byId = iDataCompanyIprPatentContentService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPatentContentVO dataCompanyIprPatentContentVO = DataCompanyIprPatentContentAppStructMapping.instance.dataCompanyIprPatentContentDOToDataCompanyIprPatentContentVO(byId);
		return SingleResponse.of(dataCompanyIprPatentContentVO);
	}


	@Autowired
	public void setIDataCompanyIprPatentContentService(IDataCompanyIprPatentContentService iDataCompanyIprPatentContentService) {
		this.iDataCompanyIprPatentContentService = iDataCompanyIprPatentContentService;
	}
}
