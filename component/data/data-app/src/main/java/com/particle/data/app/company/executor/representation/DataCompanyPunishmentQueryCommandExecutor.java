package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyPunishmentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyPunishmentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPunishmentVO;
import com.particle.data.infrastructure.company.dos.DataCompanyPunishmentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyPunishmentService;
import com.particle.data.client.company.dto.command.representation.DataCompanyPunishmentPageQueryCommand;
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
 * 企业行政处罚 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:43:37
 */
@Component
@Validated
public class DataCompanyPunishmentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyPunishmentService iDataCompanyPunishmentService;

	/**
	 * 执行 企业行政处罚 列表查询指令
	 * @param dataCompanyPunishmentQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyPunishmentVO> execute(@Valid DataCompanyPunishmentQueryListCommand dataCompanyPunishmentQueryListCommand) {
		List<DataCompanyPunishmentDO> dataCompanyPunishmentDO = iDataCompanyPunishmentService.list(dataCompanyPunishmentQueryListCommand);
		List<DataCompanyPunishmentVO> dataCompanyPunishmentVOs = DataCompanyPunishmentAppStructMapping.instance.dataCompanyPunishmentDOsToDataCompanyPunishmentVOs(dataCompanyPunishmentDO);
		return MultiResponse.of(dataCompanyPunishmentVOs);
	}
	/**
	 * 执行 企业行政处罚 分页查询指令
	 * @param dataCompanyPunishmentPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyPunishmentVO> execute(@Valid DataCompanyPunishmentPageQueryCommand dataCompanyPunishmentPageQueryCommand) {
		Page<DataCompanyPunishmentDO> page = iDataCompanyPunishmentService.listPage(dataCompanyPunishmentPageQueryCommand);
		return DataCompanyPunishmentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业行政处罚 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPunishmentVO> executeDetail(IdCommand detailCommand) {
		DataCompanyPunishmentDO byId = iDataCompanyPunishmentService.getById(detailCommand.getId());
		DataCompanyPunishmentVO dataCompanyPunishmentVO = DataCompanyPunishmentAppStructMapping.instance.dataCompanyPunishmentDOToDataCompanyPunishmentVO(byId);
		return SingleResponse.of(dataCompanyPunishmentVO);
	}
	/**
	 * 执行 企业行政处罚 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPunishmentVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyPunishmentDO byId = iDataCompanyPunishmentService.getById(detailForUpdateCommand.getId());
		DataCompanyPunishmentVO dataCompanyPunishmentVO = DataCompanyPunishmentAppStructMapping.instance.dataCompanyPunishmentDOToDataCompanyPunishmentVO(byId);
		return SingleResponse.of(dataCompanyPunishmentVO);
	}


	@Autowired
	public void setIDataCompanyPunishmentService(IDataCompanyPunishmentService iDataCompanyPunishmentService) {
		this.iDataCompanyPunishmentService = iDataCompanyPunishmentService;
	}
}
