package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyDeliveryAnnouncementPartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementPartyVO;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementPartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyDeliveryAnnouncementPartyService;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementPartyPageQueryCommand;
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
 * 企业送达公告当事人 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-20 16:18:33
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementPartyQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyDeliveryAnnouncementPartyService iDataCompanyDeliveryAnnouncementPartyService;

	/**
	 * 执行 企业送达公告当事人 列表查询指令
	 * @param dataCompanyDeliveryAnnouncementPartyQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyDeliveryAnnouncementPartyVO> execute(@Valid DataCompanyDeliveryAnnouncementPartyQueryListCommand dataCompanyDeliveryAnnouncementPartyQueryListCommand) {
		List<DataCompanyDeliveryAnnouncementPartyDO> dataCompanyDeliveryAnnouncementPartyDO = iDataCompanyDeliveryAnnouncementPartyService.list(dataCompanyDeliveryAnnouncementPartyQueryListCommand);
		List<DataCompanyDeliveryAnnouncementPartyVO> dataCompanyDeliveryAnnouncementPartyVOs = DataCompanyDeliveryAnnouncementPartyAppStructMapping.instance.dataCompanyDeliveryAnnouncementPartyDOsToDataCompanyDeliveryAnnouncementPartyVOs(dataCompanyDeliveryAnnouncementPartyDO);
		return MultiResponse.of(dataCompanyDeliveryAnnouncementPartyVOs);
	}
	/**
	 * 执行 企业送达公告当事人 分页查询指令
	 * @param dataCompanyDeliveryAnnouncementPartyPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyDeliveryAnnouncementPartyVO> execute(@Valid DataCompanyDeliveryAnnouncementPartyPageQueryCommand dataCompanyDeliveryAnnouncementPartyPageQueryCommand) {
		Page<DataCompanyDeliveryAnnouncementPartyDO> page = iDataCompanyDeliveryAnnouncementPartyService.listPage(dataCompanyDeliveryAnnouncementPartyPageQueryCommand);
		return DataCompanyDeliveryAnnouncementPartyAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业送达公告当事人 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> executeDetail(IdCommand detailCommand) {
		DataCompanyDeliveryAnnouncementPartyDO byId = iDataCompanyDeliveryAnnouncementPartyService.getById(detailCommand.getId());
		DataCompanyDeliveryAnnouncementPartyVO dataCompanyDeliveryAnnouncementPartyVO = DataCompanyDeliveryAnnouncementPartyAppStructMapping.instance.dataCompanyDeliveryAnnouncementPartyDOToDataCompanyDeliveryAnnouncementPartyVO(byId);
		return SingleResponse.of(dataCompanyDeliveryAnnouncementPartyVO);
	}
	/**
	 * 执行 企业送达公告当事人 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyDeliveryAnnouncementPartyDO byId = iDataCompanyDeliveryAnnouncementPartyService.getById(detailForUpdateCommand.getId());
		DataCompanyDeliveryAnnouncementPartyVO dataCompanyDeliveryAnnouncementPartyVO = DataCompanyDeliveryAnnouncementPartyAppStructMapping.instance.dataCompanyDeliveryAnnouncementPartyDOToDataCompanyDeliveryAnnouncementPartyVO(byId);
		return SingleResponse.of(dataCompanyDeliveryAnnouncementPartyVO);
	}


	@Autowired
	public void setIDataCompanyDeliveryAnnouncementPartyService(IDataCompanyDeliveryAnnouncementPartyService iDataCompanyDeliveryAnnouncementPartyService) {
		this.iDataCompanyDeliveryAnnouncementPartyService = iDataCompanyDeliveryAnnouncementPartyService;
	}
}
