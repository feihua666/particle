package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementContentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementContentVO;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementContentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementContentService;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementContentPageQueryCommand;
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
 * 企业开庭公告内容 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementContentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyOpenCourtAnnouncementContentService iDataCompanyOpenCourtAnnouncementContentService;

	/**
	 * 执行 企业开庭公告内容 列表查询指令
	 * @param dataCompanyOpenCourtAnnouncementContentQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyOpenCourtAnnouncementContentVO> execute(@Valid DataCompanyOpenCourtAnnouncementContentQueryListCommand dataCompanyOpenCourtAnnouncementContentQueryListCommand) {
		List<DataCompanyOpenCourtAnnouncementContentDO> dataCompanyOpenCourtAnnouncementContentDO = iDataCompanyOpenCourtAnnouncementContentService.list(dataCompanyOpenCourtAnnouncementContentQueryListCommand);
		List<DataCompanyOpenCourtAnnouncementContentVO> dataCompanyOpenCourtAnnouncementContentVOs = DataCompanyOpenCourtAnnouncementContentAppStructMapping.instance.dataCompanyOpenCourtAnnouncementContentDOsToDataCompanyOpenCourtAnnouncementContentVOs(dataCompanyOpenCourtAnnouncementContentDO);
		return MultiResponse.of(dataCompanyOpenCourtAnnouncementContentVOs);
	}
	/**
	 * 执行 企业开庭公告内容 分页查询指令
	 * @param dataCompanyOpenCourtAnnouncementContentPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyOpenCourtAnnouncementContentVO> execute(@Valid DataCompanyOpenCourtAnnouncementContentPageQueryCommand dataCompanyOpenCourtAnnouncementContentPageQueryCommand) {
		Page<DataCompanyOpenCourtAnnouncementContentDO> page = iDataCompanyOpenCourtAnnouncementContentService.listPage(dataCompanyOpenCourtAnnouncementContentPageQueryCommand);
		return DataCompanyOpenCourtAnnouncementContentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业开庭公告内容 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> executeDetail(IdCommand detailCommand) {
		DataCompanyOpenCourtAnnouncementContentDO byId = iDataCompanyOpenCourtAnnouncementContentService.getById(detailCommand.getId());
		DataCompanyOpenCourtAnnouncementContentVO dataCompanyOpenCourtAnnouncementContentVO = DataCompanyOpenCourtAnnouncementContentAppStructMapping.instance.dataCompanyOpenCourtAnnouncementContentDOToDataCompanyOpenCourtAnnouncementContentVO(byId);
		return SingleResponse.of(dataCompanyOpenCourtAnnouncementContentVO);
	}
	/**
	 * 执行 企业开庭公告内容 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyOpenCourtAnnouncementContentDO byId = iDataCompanyOpenCourtAnnouncementContentService.getById(detailForUpdateCommand.getId());
		DataCompanyOpenCourtAnnouncementContentVO dataCompanyOpenCourtAnnouncementContentVO = DataCompanyOpenCourtAnnouncementContentAppStructMapping.instance.dataCompanyOpenCourtAnnouncementContentDOToDataCompanyOpenCourtAnnouncementContentVO(byId);
		return SingleResponse.of(dataCompanyOpenCourtAnnouncementContentVO);
	}


	@Autowired
	public void setIDataCompanyOpenCourtAnnouncementContentService(IDataCompanyOpenCourtAnnouncementContentService iDataCompanyOpenCourtAnnouncementContentService) {
		this.iDataCompanyOpenCourtAnnouncementContentService = iDataCompanyOpenCourtAnnouncementContentService;
	}
}
