package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPageQueryCommand;
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
 * 企业知识产权专利 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:39:48
 */
@Component
@Validated
public class DataCompanyIprPatentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentService iDataCompanyIprPatentService;

	/**
	 * 执行 企业知识产权专利 列表查询指令
	 * @param dataCompanyIprPatentQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentVO> execute(@Valid DataCompanyIprPatentQueryListCommand dataCompanyIprPatentQueryListCommand) {
		List<DataCompanyIprPatentDO> dataCompanyIprPatentDO = iDataCompanyIprPatentService.list(dataCompanyIprPatentQueryListCommand);
		List<DataCompanyIprPatentVO> dataCompanyIprPatentVOs = DataCompanyIprPatentAppStructMapping.instance.dataCompanyIprPatentDOsToDataCompanyIprPatentVOs(dataCompanyIprPatentDO);
		return MultiResponse.of(dataCompanyIprPatentVOs);
	}
	/**
	 * 执行 企业知识产权专利 分页查询指令
	 * @param dataCompanyIprPatentPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentVO> execute(@Valid DataCompanyIprPatentPageQueryCommand dataCompanyIprPatentPageQueryCommand) {
		Page<DataCompanyIprPatentDO> page = iDataCompanyIprPatentService.listPage(dataCompanyIprPatentPageQueryCommand);
		return DataCompanyIprPatentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权专利 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPatentDO byId = iDataCompanyIprPatentService.getById(detailCommand.getId());
		DataCompanyIprPatentVO dataCompanyIprPatentVO = DataCompanyIprPatentAppStructMapping.instance.dataCompanyIprPatentDOToDataCompanyIprPatentVO(byId);
		return SingleResponse.of(dataCompanyIprPatentVO);
	}
	/**
	 * 执行 企业知识产权专利 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPatentDO byId = iDataCompanyIprPatentService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPatentVO dataCompanyIprPatentVO = DataCompanyIprPatentAppStructMapping.instance.dataCompanyIprPatentDOToDataCompanyIprPatentVO(byId);
		return SingleResponse.of(dataCompanyIprPatentVO);
	}


	@Autowired
	public void setIDataCompanyIprPatentService(IDataCompanyIprPatentService iDataCompanyIprPatentService) {
		this.iDataCompanyIprPatentService = iDataCompanyIprPatentService;
	}
}
