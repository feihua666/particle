package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprGeograApproveAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograApproveAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograApproveAnnouncementVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograApproveAnnouncementDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprGeograApproveAnnouncementService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograApproveAnnouncementPageQueryCommand;
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
 * 企业知识产权地理标识核准公告 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-16 11:16:21
 */
@Component
@Validated
public class DataCompanyIprGeograApproveAnnouncementQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprGeograApproveAnnouncementService iDataCompanyIprGeograApproveAnnouncementService;

	/**
	 * 执行 企业知识产权地理标识核准公告 列表查询指令
	 * @param dataCompanyIprGeograApproveAnnouncementQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprGeograApproveAnnouncementVO> execute(@Valid DataCompanyIprGeograApproveAnnouncementQueryListCommand dataCompanyIprGeograApproveAnnouncementQueryListCommand) {
		List<DataCompanyIprGeograApproveAnnouncementDO> dataCompanyIprGeograApproveAnnouncementDO = iDataCompanyIprGeograApproveAnnouncementService.list(dataCompanyIprGeograApproveAnnouncementQueryListCommand);
		List<DataCompanyIprGeograApproveAnnouncementVO> dataCompanyIprGeograApproveAnnouncementVOs = DataCompanyIprGeograApproveAnnouncementAppStructMapping.instance.dataCompanyIprGeograApproveAnnouncementDOsToDataCompanyIprGeograApproveAnnouncementVOs(dataCompanyIprGeograApproveAnnouncementDO);
		return MultiResponse.of(dataCompanyIprGeograApproveAnnouncementVOs);
	}
	/**
	 * 执行 企业知识产权地理标识核准公告 分页查询指令
	 * @param dataCompanyIprGeograApproveAnnouncementPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprGeograApproveAnnouncementVO> execute(@Valid DataCompanyIprGeograApproveAnnouncementPageQueryCommand dataCompanyIprGeograApproveAnnouncementPageQueryCommand) {
		Page<DataCompanyIprGeograApproveAnnouncementDO> page = iDataCompanyIprGeograApproveAnnouncementService.listPage(dataCompanyIprGeograApproveAnnouncementPageQueryCommand);
		return DataCompanyIprGeograApproveAnnouncementAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权地理标识核准公告 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprGeograApproveAnnouncementDO byId = iDataCompanyIprGeograApproveAnnouncementService.getById(detailCommand.getId());
		DataCompanyIprGeograApproveAnnouncementVO dataCompanyIprGeograApproveAnnouncementVO = DataCompanyIprGeograApproveAnnouncementAppStructMapping.instance.dataCompanyIprGeograApproveAnnouncementDOToDataCompanyIprGeograApproveAnnouncementVO(byId);
		return SingleResponse.of(dataCompanyIprGeograApproveAnnouncementVO);
	}
	/**
	 * 执行 企业知识产权地理标识核准公告 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprGeograApproveAnnouncementDO byId = iDataCompanyIprGeograApproveAnnouncementService.getById(detailForUpdateCommand.getId());
		DataCompanyIprGeograApproveAnnouncementVO dataCompanyIprGeograApproveAnnouncementVO = DataCompanyIprGeograApproveAnnouncementAppStructMapping.instance.dataCompanyIprGeograApproveAnnouncementDOToDataCompanyIprGeograApproveAnnouncementVO(byId);
		return SingleResponse.of(dataCompanyIprGeograApproveAnnouncementVO);
	}


	@Autowired
	public void setIDataCompanyIprGeograApproveAnnouncementService(IDataCompanyIprGeograApproveAnnouncementService iDataCompanyIprGeograApproveAnnouncementService) {
		this.iDataCompanyIprGeograApproveAnnouncementService = iDataCompanyIprGeograApproveAnnouncementService;
	}
}
