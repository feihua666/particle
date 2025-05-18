package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementPartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementPartyVO;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementPartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementPartyService;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementPartyPageQueryCommand;
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
 * 企业开庭公告当事人 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:44:03
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementPartyQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyOpenCourtAnnouncementPartyService iDataCompanyOpenCourtAnnouncementPartyService;

	/**
	 * 执行 企业开庭公告当事人 列表查询指令
	 * @param dataCompanyOpenCourtAnnouncementPartyQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyOpenCourtAnnouncementPartyVO> execute(@Valid DataCompanyOpenCourtAnnouncementPartyQueryListCommand dataCompanyOpenCourtAnnouncementPartyQueryListCommand) {
		List<DataCompanyOpenCourtAnnouncementPartyDO> dataCompanyOpenCourtAnnouncementPartyDO = iDataCompanyOpenCourtAnnouncementPartyService.list(dataCompanyOpenCourtAnnouncementPartyQueryListCommand);
		List<DataCompanyOpenCourtAnnouncementPartyVO> dataCompanyOpenCourtAnnouncementPartyVOs = DataCompanyOpenCourtAnnouncementPartyAppStructMapping.instance.dataCompanyOpenCourtAnnouncementPartyDOsToDataCompanyOpenCourtAnnouncementPartyVOs(dataCompanyOpenCourtAnnouncementPartyDO);
		return MultiResponse.of(dataCompanyOpenCourtAnnouncementPartyVOs);
	}
	/**
	 * 执行 企业开庭公告当事人 分页查询指令
	 * @param dataCompanyOpenCourtAnnouncementPartyPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyOpenCourtAnnouncementPartyVO> execute(@Valid DataCompanyOpenCourtAnnouncementPartyPageQueryCommand dataCompanyOpenCourtAnnouncementPartyPageQueryCommand) {
		Page<DataCompanyOpenCourtAnnouncementPartyDO> page = iDataCompanyOpenCourtAnnouncementPartyService.listPage(dataCompanyOpenCourtAnnouncementPartyPageQueryCommand);
		return DataCompanyOpenCourtAnnouncementPartyAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业开庭公告当事人 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> executeDetail(IdCommand detailCommand) {
		DataCompanyOpenCourtAnnouncementPartyDO byId = iDataCompanyOpenCourtAnnouncementPartyService.getById(detailCommand.getId());
		DataCompanyOpenCourtAnnouncementPartyVO dataCompanyOpenCourtAnnouncementPartyVO = DataCompanyOpenCourtAnnouncementPartyAppStructMapping.instance.dataCompanyOpenCourtAnnouncementPartyDOToDataCompanyOpenCourtAnnouncementPartyVO(byId);
		return SingleResponse.of(dataCompanyOpenCourtAnnouncementPartyVO);
	}
	/**
	 * 执行 企业开庭公告当事人 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyOpenCourtAnnouncementPartyDO byId = iDataCompanyOpenCourtAnnouncementPartyService.getById(detailForUpdateCommand.getId());
		DataCompanyOpenCourtAnnouncementPartyVO dataCompanyOpenCourtAnnouncementPartyVO = DataCompanyOpenCourtAnnouncementPartyAppStructMapping.instance.dataCompanyOpenCourtAnnouncementPartyDOToDataCompanyOpenCourtAnnouncementPartyVO(byId);
		return SingleResponse.of(dataCompanyOpenCourtAnnouncementPartyVO);
	}


	@Autowired
	public void setIDataCompanyOpenCourtAnnouncementPartyService(IDataCompanyOpenCourtAnnouncementPartyService iDataCompanyOpenCourtAnnouncementPartyService) {
		this.iDataCompanyOpenCourtAnnouncementPartyService = iDataCompanyOpenCourtAnnouncementPartyService;
	}
}
