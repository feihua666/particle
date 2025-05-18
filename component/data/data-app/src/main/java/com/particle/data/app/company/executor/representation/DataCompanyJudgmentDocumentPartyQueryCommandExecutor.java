package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyJudgmentDocumentPartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentPartyVO;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentPartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentPartyService;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentPartyPageQueryCommand;
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
 * 企业裁判文书当事人 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:45:05
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentPartyQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyJudgmentDocumentPartyService iDataCompanyJudgmentDocumentPartyService;

	/**
	 * 执行 企业裁判文书当事人 列表查询指令
	 * @param dataCompanyJudgmentDocumentPartyQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyJudgmentDocumentPartyVO> execute(@Valid DataCompanyJudgmentDocumentPartyQueryListCommand dataCompanyJudgmentDocumentPartyQueryListCommand) {
		List<DataCompanyJudgmentDocumentPartyDO> dataCompanyJudgmentDocumentPartyDO = iDataCompanyJudgmentDocumentPartyService.list(dataCompanyJudgmentDocumentPartyQueryListCommand);
		List<DataCompanyJudgmentDocumentPartyVO> dataCompanyJudgmentDocumentPartyVOs = DataCompanyJudgmentDocumentPartyAppStructMapping.instance.dataCompanyJudgmentDocumentPartyDOsToDataCompanyJudgmentDocumentPartyVOs(dataCompanyJudgmentDocumentPartyDO);
		return MultiResponse.of(dataCompanyJudgmentDocumentPartyVOs);
	}
	/**
	 * 执行 企业裁判文书当事人 分页查询指令
	 * @param dataCompanyJudgmentDocumentPartyPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDocumentPartyVO> execute(@Valid DataCompanyJudgmentDocumentPartyPageQueryCommand dataCompanyJudgmentDocumentPartyPageQueryCommand) {
		Page<DataCompanyJudgmentDocumentPartyDO> page = iDataCompanyJudgmentDocumentPartyService.listPage(dataCompanyJudgmentDocumentPartyPageQueryCommand);
		return DataCompanyJudgmentDocumentPartyAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业裁判文书当事人 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentPartyVO> executeDetail(IdCommand detailCommand) {
		DataCompanyJudgmentDocumentPartyDO byId = iDataCompanyJudgmentDocumentPartyService.getById(detailCommand.getId());
		DataCompanyJudgmentDocumentPartyVO dataCompanyJudgmentDocumentPartyVO = DataCompanyJudgmentDocumentPartyAppStructMapping.instance.dataCompanyJudgmentDocumentPartyDOToDataCompanyJudgmentDocumentPartyVO(byId);
		return SingleResponse.of(dataCompanyJudgmentDocumentPartyVO);
	}
	/**
	 * 执行 企业裁判文书当事人 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentPartyVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyJudgmentDocumentPartyDO byId = iDataCompanyJudgmentDocumentPartyService.getById(detailForUpdateCommand.getId());
		DataCompanyJudgmentDocumentPartyVO dataCompanyJudgmentDocumentPartyVO = DataCompanyJudgmentDocumentPartyAppStructMapping.instance.dataCompanyJudgmentDocumentPartyDOToDataCompanyJudgmentDocumentPartyVO(byId);
		return SingleResponse.of(dataCompanyJudgmentDocumentPartyVO);
	}


	@Autowired
	public void setIDataCompanyJudgmentDocumentPartyService(IDataCompanyJudgmentDocumentPartyService iDataCompanyJudgmentDocumentPartyService) {
		this.iDataCompanyJudgmentDocumentPartyService = iDataCompanyJudgmentDocumentPartyService;
	}
}
