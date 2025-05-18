package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyJudgmentDocumentContentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDocumentContentVO;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentContentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentContentService;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDocumentContentPageQueryCommand;
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
 * 企业裁判文书内容 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentContentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyJudgmentDocumentContentService iDataCompanyJudgmentDocumentContentService;

	/**
	 * 执行 企业裁判文书内容 列表查询指令
	 * @param dataCompanyJudgmentDocumentContentQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyJudgmentDocumentContentVO> execute(@Valid DataCompanyJudgmentDocumentContentQueryListCommand dataCompanyJudgmentDocumentContentQueryListCommand) {
		List<DataCompanyJudgmentDocumentContentDO> dataCompanyJudgmentDocumentContentDO = iDataCompanyJudgmentDocumentContentService.list(dataCompanyJudgmentDocumentContentQueryListCommand);
		List<DataCompanyJudgmentDocumentContentVO> dataCompanyJudgmentDocumentContentVOs = DataCompanyJudgmentDocumentContentAppStructMapping.instance.dataCompanyJudgmentDocumentContentDOsToDataCompanyJudgmentDocumentContentVOs(dataCompanyJudgmentDocumentContentDO);
		return MultiResponse.of(dataCompanyJudgmentDocumentContentVOs);
	}
	/**
	 * 执行 企业裁判文书内容 分页查询指令
	 * @param dataCompanyJudgmentDocumentContentPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDocumentContentVO> execute(@Valid DataCompanyJudgmentDocumentContentPageQueryCommand dataCompanyJudgmentDocumentContentPageQueryCommand) {
		Page<DataCompanyJudgmentDocumentContentDO> page = iDataCompanyJudgmentDocumentContentService.listPage(dataCompanyJudgmentDocumentContentPageQueryCommand);
		return DataCompanyJudgmentDocumentContentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业裁判文书内容 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentContentVO> executeDetail(IdCommand detailCommand) {
		DataCompanyJudgmentDocumentContentDO byId = iDataCompanyJudgmentDocumentContentService.getById(detailCommand.getId());
		DataCompanyJudgmentDocumentContentVO dataCompanyJudgmentDocumentContentVO = DataCompanyJudgmentDocumentContentAppStructMapping.instance.dataCompanyJudgmentDocumentContentDOToDataCompanyJudgmentDocumentContentVO(byId);
		return SingleResponse.of(dataCompanyJudgmentDocumentContentVO);
	}
	/**
	 * 执行 企业裁判文书内容 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentContentVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyJudgmentDocumentContentDO byId = iDataCompanyJudgmentDocumentContentService.getById(detailForUpdateCommand.getId());
		DataCompanyJudgmentDocumentContentVO dataCompanyJudgmentDocumentContentVO = DataCompanyJudgmentDocumentContentAppStructMapping.instance.dataCompanyJudgmentDocumentContentDOToDataCompanyJudgmentDocumentContentVO(byId);
		return SingleResponse.of(dataCompanyJudgmentDocumentContentVO);
	}


	@Autowired
	public void setIDataCompanyJudgmentDocumentContentService(IDataCompanyJudgmentDocumentContentService iDataCompanyJudgmentDocumentContentService) {
		this.iDataCompanyJudgmentDocumentContentService = iDataCompanyJudgmentDocumentContentService;
	}
}
