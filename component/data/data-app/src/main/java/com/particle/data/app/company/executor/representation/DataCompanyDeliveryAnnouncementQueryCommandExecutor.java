package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementVO;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementDO;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementService;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementPageQueryCommand;
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
 * 企业送达公告 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyDeliveryAnnouncementService iDataCompanyDeliveryAnnouncementService;

	/**
	 * 执行 企业送达公告 列表查询指令
	 * @param dataCompanyDeliveryAnnouncementQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyDeliveryAnnouncementVO> execute(@Valid DataCompanyDeliveryAnnouncementQueryListCommand dataCompanyDeliveryAnnouncementQueryListCommand) {
		List<DataCompanyDeliveryAnnouncementDO> dataCompanyDeliveryAnnouncementDO = iDataCompanyDeliveryAnnouncementService.list(dataCompanyDeliveryAnnouncementQueryListCommand);
		List<DataCompanyDeliveryAnnouncementVO> dataCompanyDeliveryAnnouncementVOs = DataCompanyDeliveryAnnouncementAppStructMapping.instance.dataCompanyDeliveryAnnouncementDOsToDataCompanyDeliveryAnnouncementVOs(dataCompanyDeliveryAnnouncementDO);
		return MultiResponse.of(dataCompanyDeliveryAnnouncementVOs);
	}
	/**
	 * 执行 企业送达公告 分页查询指令
	 * @param dataCompanyDeliveryAnnouncementPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyDeliveryAnnouncementVO> execute(@Valid DataCompanyDeliveryAnnouncementPageQueryCommand dataCompanyDeliveryAnnouncementPageQueryCommand) {
		Page<DataCompanyDeliveryAnnouncementDO> page = iDataCompanyDeliveryAnnouncementService.listPage(dataCompanyDeliveryAnnouncementPageQueryCommand);
		return DataCompanyDeliveryAnnouncementAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业送达公告 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementVO> executeDetail(IdCommand detailCommand) {
		DataCompanyDeliveryAnnouncementDO byId = iDataCompanyDeliveryAnnouncementService.getById(detailCommand.getId());
		DataCompanyDeliveryAnnouncementVO dataCompanyDeliveryAnnouncementVO = DataCompanyDeliveryAnnouncementAppStructMapping.instance.dataCompanyDeliveryAnnouncementDOToDataCompanyDeliveryAnnouncementVO(byId);
		return SingleResponse.of(dataCompanyDeliveryAnnouncementVO);
	}
	/**
	 * 执行 企业送达公告 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyDeliveryAnnouncementDO byId = iDataCompanyDeliveryAnnouncementService.getById(detailForUpdateCommand.getId());
		DataCompanyDeliveryAnnouncementVO dataCompanyDeliveryAnnouncementVO = DataCompanyDeliveryAnnouncementAppStructMapping.instance.dataCompanyDeliveryAnnouncementDOToDataCompanyDeliveryAnnouncementVO(byId);
		return SingleResponse.of(dataCompanyDeliveryAnnouncementVO);
	}


	@Autowired
	public void setIDataCompanyDeliveryAnnouncementService(IDataCompanyDeliveryAnnouncementService iDataCompanyDeliveryAnnouncementService) {
		this.iDataCompanyDeliveryAnnouncementService = iDataCompanyDeliveryAnnouncementService;
	}
}
