package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyJudgmentDocumentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentVO;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentService;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentPageQueryCommand;
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
 * 企业裁判文书 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:43:21
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyJudgmentDocumentService iDataCompanyJudgmentDocumentService;

	/**
	 * 执行 企业裁判文书 列表查询指令
	 * @param dataCompanyJudgmentDocumentQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyJudgmentDocumentVO> execute(@Valid DataCompanyJudgmentDocumentQueryListCommand dataCompanyJudgmentDocumentQueryListCommand) {
		List<DataCompanyJudgmentDocumentDO> dataCompanyJudgmentDocumentDO = iDataCompanyJudgmentDocumentService.list(dataCompanyJudgmentDocumentQueryListCommand);
		List<DataCompanyJudgmentDocumentVO> dataCompanyJudgmentDocumentVOs = DataCompanyJudgmentDocumentAppStructMapping.instance.dataCompanyJudgmentDocumentDOsToDataCompanyJudgmentDocumentVOs(dataCompanyJudgmentDocumentDO);
		return MultiResponse.of(dataCompanyJudgmentDocumentVOs);
	}
	/**
	 * 执行 企业裁判文书 分页查询指令
	 * @param dataCompanyJudgmentDocumentPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDocumentVO> execute(@Valid DataCompanyJudgmentDocumentPageQueryCommand dataCompanyJudgmentDocumentPageQueryCommand) {
		Page<DataCompanyJudgmentDocumentDO> page = iDataCompanyJudgmentDocumentService.listPage(dataCompanyJudgmentDocumentPageQueryCommand);
		return DataCompanyJudgmentDocumentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业裁判文书 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentVO> executeDetail(IdCommand detailCommand) {
		DataCompanyJudgmentDocumentDO byId = iDataCompanyJudgmentDocumentService.getById(detailCommand.getId());
		DataCompanyJudgmentDocumentVO dataCompanyJudgmentDocumentVO = DataCompanyJudgmentDocumentAppStructMapping.instance.dataCompanyJudgmentDocumentDOToDataCompanyJudgmentDocumentVO(byId);
		return SingleResponse.of(dataCompanyJudgmentDocumentVO);
	}
	/**
	 * 执行 企业裁判文书 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyJudgmentDocumentDO byId = iDataCompanyJudgmentDocumentService.getById(detailForUpdateCommand.getId());
		DataCompanyJudgmentDocumentVO dataCompanyJudgmentDocumentVO = DataCompanyJudgmentDocumentAppStructMapping.instance.dataCompanyJudgmentDocumentDOToDataCompanyJudgmentDocumentVO(byId);
		return SingleResponse.of(dataCompanyJudgmentDocumentVO);
	}


	@Autowired
	public void setIDataCompanyJudgmentDocumentService(IDataCompanyJudgmentDocumentService iDataCompanyJudgmentDocumentService) {
		this.iDataCompanyJudgmentDocumentService = iDataCompanyJudgmentDocumentService;
	}
}
