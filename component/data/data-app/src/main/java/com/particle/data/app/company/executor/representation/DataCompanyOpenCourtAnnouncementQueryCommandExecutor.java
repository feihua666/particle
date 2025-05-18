package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementVO;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementDO;
import com.particle.data.infrastructure.company.service.IDataCompanyOpenCourtAnnouncementService;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementPageQueryCommand;
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
 * 企业开庭公告 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyOpenCourtAnnouncementService iDataCompanyOpenCourtAnnouncementService;

	/**
	 * 执行 企业开庭公告 列表查询指令
	 * @param dataCompanyOpenCourtAnnouncementQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyOpenCourtAnnouncementVO> execute(@Valid DataCompanyOpenCourtAnnouncementQueryListCommand dataCompanyOpenCourtAnnouncementQueryListCommand) {
		List<DataCompanyOpenCourtAnnouncementDO> dataCompanyOpenCourtAnnouncementDO = iDataCompanyOpenCourtAnnouncementService.list(dataCompanyOpenCourtAnnouncementQueryListCommand);
		List<DataCompanyOpenCourtAnnouncementVO> dataCompanyOpenCourtAnnouncementVOs = DataCompanyOpenCourtAnnouncementAppStructMapping.instance.dataCompanyOpenCourtAnnouncementDOsToDataCompanyOpenCourtAnnouncementVOs(dataCompanyOpenCourtAnnouncementDO);
		return MultiResponse.of(dataCompanyOpenCourtAnnouncementVOs);
	}
	/**
	 * 执行 企业开庭公告 分页查询指令
	 * @param dataCompanyOpenCourtAnnouncementPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyOpenCourtAnnouncementVO> execute(@Valid DataCompanyOpenCourtAnnouncementPageQueryCommand dataCompanyOpenCourtAnnouncementPageQueryCommand) {
		Page<DataCompanyOpenCourtAnnouncementDO> page = iDataCompanyOpenCourtAnnouncementService.listPage(dataCompanyOpenCourtAnnouncementPageQueryCommand);
		return DataCompanyOpenCourtAnnouncementAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业开庭公告 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementVO> executeDetail(IdCommand detailCommand) {
		DataCompanyOpenCourtAnnouncementDO byId = iDataCompanyOpenCourtAnnouncementService.getById(detailCommand.getId());
		DataCompanyOpenCourtAnnouncementVO dataCompanyOpenCourtAnnouncementVO = DataCompanyOpenCourtAnnouncementAppStructMapping.instance.dataCompanyOpenCourtAnnouncementDOToDataCompanyOpenCourtAnnouncementVO(byId);
		return SingleResponse.of(dataCompanyOpenCourtAnnouncementVO);
	}
	/**
	 * 执行 企业开庭公告 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyOpenCourtAnnouncementDO byId = iDataCompanyOpenCourtAnnouncementService.getById(detailForUpdateCommand.getId());
		DataCompanyOpenCourtAnnouncementVO dataCompanyOpenCourtAnnouncementVO = DataCompanyOpenCourtAnnouncementAppStructMapping.instance.dataCompanyOpenCourtAnnouncementDOToDataCompanyOpenCourtAnnouncementVO(byId);
		return SingleResponse.of(dataCompanyOpenCourtAnnouncementVO);
	}


	@Autowired
	public void setIDataCompanyOpenCourtAnnouncementService(IDataCompanyOpenCourtAnnouncementService iDataCompanyOpenCourtAnnouncementService) {
		this.iDataCompanyOpenCourtAnnouncementService = iDataCompanyOpenCourtAnnouncementService;
	}
}
