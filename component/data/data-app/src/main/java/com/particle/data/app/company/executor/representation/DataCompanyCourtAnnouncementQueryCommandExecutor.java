package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementVO;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementDO;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementService;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementPageQueryCommand;
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
 * 企业法院公告 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyCourtAnnouncementService iDataCompanyCourtAnnouncementService;

	/**
	 * 执行 企业法院公告 列表查询指令
	 * @param dataCompanyCourtAnnouncementQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyCourtAnnouncementVO> execute(@Valid DataCompanyCourtAnnouncementQueryListCommand dataCompanyCourtAnnouncementQueryListCommand) {
		List<DataCompanyCourtAnnouncementDO> dataCompanyCourtAnnouncementDO = iDataCompanyCourtAnnouncementService.list(dataCompanyCourtAnnouncementQueryListCommand);
		List<DataCompanyCourtAnnouncementVO> dataCompanyCourtAnnouncementVOs = DataCompanyCourtAnnouncementAppStructMapping.instance.dataCompanyCourtAnnouncementDOsToDataCompanyCourtAnnouncementVOs(dataCompanyCourtAnnouncementDO);
		return MultiResponse.of(dataCompanyCourtAnnouncementVOs);
	}
	/**
	 * 执行 企业法院公告 分页查询指令
	 * @param dataCompanyCourtAnnouncementPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyCourtAnnouncementVO> execute(@Valid DataCompanyCourtAnnouncementPageQueryCommand dataCompanyCourtAnnouncementPageQueryCommand) {
		Page<DataCompanyCourtAnnouncementDO> page = iDataCompanyCourtAnnouncementService.listPage(dataCompanyCourtAnnouncementPageQueryCommand);
		return DataCompanyCourtAnnouncementAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业法院公告 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementVO> executeDetail(IdCommand detailCommand) {
		DataCompanyCourtAnnouncementDO byId = iDataCompanyCourtAnnouncementService.getById(detailCommand.getId());
		DataCompanyCourtAnnouncementVO dataCompanyCourtAnnouncementVO = DataCompanyCourtAnnouncementAppStructMapping.instance.dataCompanyCourtAnnouncementDOToDataCompanyCourtAnnouncementVO(byId);
		return SingleResponse.of(dataCompanyCourtAnnouncementVO);
	}
	/**
	 * 执行 企业法院公告 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyCourtAnnouncementDO byId = iDataCompanyCourtAnnouncementService.getById(detailForUpdateCommand.getId());
		DataCompanyCourtAnnouncementVO dataCompanyCourtAnnouncementVO = DataCompanyCourtAnnouncementAppStructMapping.instance.dataCompanyCourtAnnouncementDOToDataCompanyCourtAnnouncementVO(byId);
		return SingleResponse.of(dataCompanyCourtAnnouncementVO);
	}


	@Autowired
	public void setIDataCompanyCourtAnnouncementService(IDataCompanyCourtAnnouncementService iDataCompanyCourtAnnouncementService) {
		this.iDataCompanyCourtAnnouncementService = iDataCompanyCourtAnnouncementService;
	}
}
