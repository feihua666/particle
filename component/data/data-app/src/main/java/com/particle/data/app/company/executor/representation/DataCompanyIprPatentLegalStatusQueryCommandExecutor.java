package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentLegalStatusAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLegalStatusQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLegalStatusVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLegalStatusDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentLegalStatusService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentLegalStatusPageQueryCommand;
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
 * 企业知识产权专利法律状态 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Component
@Validated
public class DataCompanyIprPatentLegalStatusQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentLegalStatusService iDataCompanyIprPatentLegalStatusService;

	/**
	 * 执行 企业知识产权专利法律状态 列表查询指令
	 * @param dataCompanyIprPatentLegalStatusQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentLegalStatusVO> execute(@Valid DataCompanyIprPatentLegalStatusQueryListCommand dataCompanyIprPatentLegalStatusQueryListCommand) {
		List<DataCompanyIprPatentLegalStatusDO> dataCompanyIprPatentLegalStatusDO = iDataCompanyIprPatentLegalStatusService.list(dataCompanyIprPatentLegalStatusQueryListCommand);
		List<DataCompanyIprPatentLegalStatusVO> dataCompanyIprPatentLegalStatusVOs = DataCompanyIprPatentLegalStatusAppStructMapping.instance.dataCompanyIprPatentLegalStatusDOsToDataCompanyIprPatentLegalStatusVOs(dataCompanyIprPatentLegalStatusDO);
		return MultiResponse.of(dataCompanyIprPatentLegalStatusVOs);
	}
	/**
	 * 执行 企业知识产权专利法律状态 分页查询指令
	 * @param dataCompanyIprPatentLegalStatusPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentLegalStatusVO> execute(@Valid DataCompanyIprPatentLegalStatusPageQueryCommand dataCompanyIprPatentLegalStatusPageQueryCommand) {
		Page<DataCompanyIprPatentLegalStatusDO> page = iDataCompanyIprPatentLegalStatusService.listPage(dataCompanyIprPatentLegalStatusPageQueryCommand);
		return DataCompanyIprPatentLegalStatusAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权专利法律状态 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLegalStatusVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPatentLegalStatusDO byId = iDataCompanyIprPatentLegalStatusService.getById(detailCommand.getId());
		DataCompanyIprPatentLegalStatusVO dataCompanyIprPatentLegalStatusVO = DataCompanyIprPatentLegalStatusAppStructMapping.instance.dataCompanyIprPatentLegalStatusDOToDataCompanyIprPatentLegalStatusVO(byId);
		return SingleResponse.of(dataCompanyIprPatentLegalStatusVO);
	}
	/**
	 * 执行 企业知识产权专利法律状态 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLegalStatusVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPatentLegalStatusDO byId = iDataCompanyIprPatentLegalStatusService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPatentLegalStatusVO dataCompanyIprPatentLegalStatusVO = DataCompanyIprPatentLegalStatusAppStructMapping.instance.dataCompanyIprPatentLegalStatusDOToDataCompanyIprPatentLegalStatusVO(byId);
		return SingleResponse.of(dataCompanyIprPatentLegalStatusVO);
	}


	@Autowired
	public void setIDataCompanyIprPatentLegalStatusService(IDataCompanyIprPatentLegalStatusService iDataCompanyIprPatentLegalStatusService) {
		this.iDataCompanyIprPatentLegalStatusService = iDataCompanyIprPatentLegalStatusService;
	}
}
