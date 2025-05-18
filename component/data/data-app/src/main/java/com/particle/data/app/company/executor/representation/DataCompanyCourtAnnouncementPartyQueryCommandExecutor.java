package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyCourtAnnouncementPartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementPartyVO;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementPartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyCourtAnnouncementPartyService;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementPartyPageQueryCommand;
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
 * 企业法院公告当事人 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:38:44
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementPartyQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyCourtAnnouncementPartyService iDataCompanyCourtAnnouncementPartyService;

	/**
	 * 执行 企业法院公告当事人 列表查询指令
	 * @param dataCompanyCourtAnnouncementPartyQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyCourtAnnouncementPartyVO> execute(@Valid DataCompanyCourtAnnouncementPartyQueryListCommand dataCompanyCourtAnnouncementPartyQueryListCommand) {
		List<DataCompanyCourtAnnouncementPartyDO> dataCompanyCourtAnnouncementPartyDO = iDataCompanyCourtAnnouncementPartyService.list(dataCompanyCourtAnnouncementPartyQueryListCommand);
		List<DataCompanyCourtAnnouncementPartyVO> dataCompanyCourtAnnouncementPartyVOs = DataCompanyCourtAnnouncementPartyAppStructMapping.instance.dataCompanyCourtAnnouncementPartyDOsToDataCompanyCourtAnnouncementPartyVOs(dataCompanyCourtAnnouncementPartyDO);
		return MultiResponse.of(dataCompanyCourtAnnouncementPartyVOs);
	}
	/**
	 * 执行 企业法院公告当事人 分页查询指令
	 * @param dataCompanyCourtAnnouncementPartyPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyCourtAnnouncementPartyVO> execute(@Valid DataCompanyCourtAnnouncementPartyPageQueryCommand dataCompanyCourtAnnouncementPartyPageQueryCommand) {
		Page<DataCompanyCourtAnnouncementPartyDO> page = iDataCompanyCourtAnnouncementPartyService.listPage(dataCompanyCourtAnnouncementPartyPageQueryCommand);
		return DataCompanyCourtAnnouncementPartyAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业法院公告当事人 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementPartyVO> executeDetail(IdCommand detailCommand) {
		DataCompanyCourtAnnouncementPartyDO byId = iDataCompanyCourtAnnouncementPartyService.getById(detailCommand.getId());
		DataCompanyCourtAnnouncementPartyVO dataCompanyCourtAnnouncementPartyVO = DataCompanyCourtAnnouncementPartyAppStructMapping.instance.dataCompanyCourtAnnouncementPartyDOToDataCompanyCourtAnnouncementPartyVO(byId);
		return SingleResponse.of(dataCompanyCourtAnnouncementPartyVO);
	}
	/**
	 * 执行 企业法院公告当事人 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyCourtAnnouncementPartyVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyCourtAnnouncementPartyDO byId = iDataCompanyCourtAnnouncementPartyService.getById(detailForUpdateCommand.getId());
		DataCompanyCourtAnnouncementPartyVO dataCompanyCourtAnnouncementPartyVO = DataCompanyCourtAnnouncementPartyAppStructMapping.instance.dataCompanyCourtAnnouncementPartyDOToDataCompanyCourtAnnouncementPartyVO(byId);
		return SingleResponse.of(dataCompanyCourtAnnouncementPartyVO);
	}


	@Autowired
	public void setIDataCompanyCourtAnnouncementPartyService(IDataCompanyCourtAnnouncementPartyService iDataCompanyCourtAnnouncementPartyService) {
		this.iDataCompanyCourtAnnouncementPartyService = iDataCompanyCourtAnnouncementPartyService;
	}
}
