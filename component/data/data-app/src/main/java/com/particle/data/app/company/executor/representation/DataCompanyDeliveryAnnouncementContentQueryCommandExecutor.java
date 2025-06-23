package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementContentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementContentVO;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementContentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementContentService;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementContentPageQueryCommand;
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
 * 企业送达公告内容 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementContentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyDeliveryAnnouncementContentService iDataCompanyDeliveryAnnouncementContentService;

	/**
	 * 执行 企业送达公告内容 列表查询指令
	 * @param dataCompanyDeliveryAnnouncementContentQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyDeliveryAnnouncementContentVO> execute(@Valid DataCompanyDeliveryAnnouncementContentQueryListCommand dataCompanyDeliveryAnnouncementContentQueryListCommand) {
		List<DataCompanyDeliveryAnnouncementContentDO> dataCompanyDeliveryAnnouncementContentDO = iDataCompanyDeliveryAnnouncementContentService.list(dataCompanyDeliveryAnnouncementContentQueryListCommand);
		List<DataCompanyDeliveryAnnouncementContentVO> dataCompanyDeliveryAnnouncementContentVOs = DataCompanyDeliveryAnnouncementContentAppStructMapping.instance.dataCompanyDeliveryAnnouncementContentDOsToDataCompanyDeliveryAnnouncementContentVOs(dataCompanyDeliveryAnnouncementContentDO);
		return MultiResponse.of(dataCompanyDeliveryAnnouncementContentVOs);
	}
	/**
	 * 执行 企业送达公告内容 分页查询指令
	 * @param dataCompanyDeliveryAnnouncementContentPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyDeliveryAnnouncementContentVO> execute(@Valid DataCompanyDeliveryAnnouncementContentPageQueryCommand dataCompanyDeliveryAnnouncementContentPageQueryCommand) {
		Page<DataCompanyDeliveryAnnouncementContentDO> page = iDataCompanyDeliveryAnnouncementContentService.listPage(dataCompanyDeliveryAnnouncementContentPageQueryCommand);
		return DataCompanyDeliveryAnnouncementContentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业送达公告内容 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementContentVO> executeDetail(IdCommand detailCommand) {
		DataCompanyDeliveryAnnouncementContentDO byId = iDataCompanyDeliveryAnnouncementContentService.getById(detailCommand.getId());
		DataCompanyDeliveryAnnouncementContentVO dataCompanyDeliveryAnnouncementContentVO = DataCompanyDeliveryAnnouncementContentAppStructMapping.instance.dataCompanyDeliveryAnnouncementContentDOToDataCompanyDeliveryAnnouncementContentVO(byId);
		return SingleResponse.of(dataCompanyDeliveryAnnouncementContentVO);
	}
	/**
	 * 执行 企业送达公告内容 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementContentVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyDeliveryAnnouncementContentDO byId = iDataCompanyDeliveryAnnouncementContentService.getById(detailForUpdateCommand.getId());
		DataCompanyDeliveryAnnouncementContentVO dataCompanyDeliveryAnnouncementContentVO = DataCompanyDeliveryAnnouncementContentAppStructMapping.instance.dataCompanyDeliveryAnnouncementContentDOToDataCompanyDeliveryAnnouncementContentVO(byId);
		return SingleResponse.of(dataCompanyDeliveryAnnouncementContentVO);
	}


	@Autowired
	public void setIDataCompanyDeliveryAnnouncementContentService(IDataCompanyDeliveryAnnouncementContentService iDataCompanyDeliveryAnnouncementContentService) {
		this.iDataCompanyDeliveryAnnouncementContentService = iDataCompanyDeliveryAnnouncementContentService;
	}
}
