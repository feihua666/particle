package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementContentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementContentVO;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementContentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementContentService;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementContentPageQueryCommand;
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
 * 企业法院公告内容 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementContentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyCourtAnnouncementContentService iDataCompanyCourtAnnouncementContentService;

	/**
	 * 执行 企业法院公告内容 列表查询指令
	 * @param dataCompanyCourtAnnouncementContentQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyCourtAnnouncementContentVO> execute(@Valid DataCompanyCourtAnnouncementContentQueryListCommand dataCompanyCourtAnnouncementContentQueryListCommand) {
		List<DataCompanyCourtAnnouncementContentDO> dataCompanyCourtAnnouncementContentDO = iDataCompanyCourtAnnouncementContentService.list(dataCompanyCourtAnnouncementContentQueryListCommand);
		List<DataCompanyCourtAnnouncementContentVO> dataCompanyCourtAnnouncementContentVOs = DataCompanyCourtAnnouncementContentAppStructMapping.instance.dataCompanyCourtAnnouncementContentDOsToDataCompanyCourtAnnouncementContentVOs(dataCompanyCourtAnnouncementContentDO);
		return MultiResponse.of(dataCompanyCourtAnnouncementContentVOs);
	}
	/**
	 * 执行 企业法院公告内容 分页查询指令
	 * @param dataCompanyCourtAnnouncementContentPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyCourtAnnouncementContentVO> execute(@Valid DataCompanyCourtAnnouncementContentPageQueryCommand dataCompanyCourtAnnouncementContentPageQueryCommand) {
		Page<DataCompanyCourtAnnouncementContentDO> page = iDataCompanyCourtAnnouncementContentService.listPage(dataCompanyCourtAnnouncementContentPageQueryCommand);
		return DataCompanyCourtAnnouncementContentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业法院公告内容 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementContentVO> executeDetail(IdCommand detailCommand) {
		DataCompanyCourtAnnouncementContentDO byId = iDataCompanyCourtAnnouncementContentService.getById(detailCommand.getId());
		DataCompanyCourtAnnouncementContentVO dataCompanyCourtAnnouncementContentVO = DataCompanyCourtAnnouncementContentAppStructMapping.instance.dataCompanyCourtAnnouncementContentDOToDataCompanyCourtAnnouncementContentVO(byId);
		return SingleResponse.of(dataCompanyCourtAnnouncementContentVO);
	}
	/**
	 * 执行 企业法院公告内容 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementContentVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyCourtAnnouncementContentDO byId = iDataCompanyCourtAnnouncementContentService.getById(detailForUpdateCommand.getId());
		DataCompanyCourtAnnouncementContentVO dataCompanyCourtAnnouncementContentVO = DataCompanyCourtAnnouncementContentAppStructMapping.instance.dataCompanyCourtAnnouncementContentDOToDataCompanyCourtAnnouncementContentVO(byId);
		return SingleResponse.of(dataCompanyCourtAnnouncementContentVO);
	}


	@Autowired
	public void setIDataCompanyCourtAnnouncementContentService(IDataCompanyCourtAnnouncementContentService iDataCompanyCourtAnnouncementContentService) {
		this.iDataCompanyCourtAnnouncementContentService = iDataCompanyCourtAnnouncementContentService;
	}
}
