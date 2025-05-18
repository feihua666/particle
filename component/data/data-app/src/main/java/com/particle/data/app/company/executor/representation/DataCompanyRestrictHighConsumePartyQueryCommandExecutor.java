package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyRestrictHighConsumePartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumePartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumePartyVO;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumePartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyRestrictHighConsumePartyService;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumePartyPageQueryCommand;
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
 * 企业限制高消费当事人 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumePartyQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyRestrictHighConsumePartyService iDataCompanyRestrictHighConsumePartyService;

	/**
	 * 执行 企业限制高消费当事人 列表查询指令
	 * @param dataCompanyRestrictHighConsumePartyQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyRestrictHighConsumePartyVO> execute(@Valid DataCompanyRestrictHighConsumePartyQueryListCommand dataCompanyRestrictHighConsumePartyQueryListCommand) {
		List<DataCompanyRestrictHighConsumePartyDO> dataCompanyRestrictHighConsumePartyDO = iDataCompanyRestrictHighConsumePartyService.list(dataCompanyRestrictHighConsumePartyQueryListCommand);
		List<DataCompanyRestrictHighConsumePartyVO> dataCompanyRestrictHighConsumePartyVOs = DataCompanyRestrictHighConsumePartyAppStructMapping.instance.dataCompanyRestrictHighConsumePartyDOsToDataCompanyRestrictHighConsumePartyVOs(dataCompanyRestrictHighConsumePartyDO);
		return MultiResponse.of(dataCompanyRestrictHighConsumePartyVOs);
	}
	/**
	 * 执行 企业限制高消费当事人 分页查询指令
	 * @param dataCompanyRestrictHighConsumePartyPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyRestrictHighConsumePartyVO> execute(@Valid DataCompanyRestrictHighConsumePartyPageQueryCommand dataCompanyRestrictHighConsumePartyPageQueryCommand) {
		Page<DataCompanyRestrictHighConsumePartyDO> page = iDataCompanyRestrictHighConsumePartyService.listPage(dataCompanyRestrictHighConsumePartyPageQueryCommand);
		return DataCompanyRestrictHighConsumePartyAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业限制高消费当事人 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumePartyVO> executeDetail(IdCommand detailCommand) {
		DataCompanyRestrictHighConsumePartyDO byId = iDataCompanyRestrictHighConsumePartyService.getById(detailCommand.getId());
		DataCompanyRestrictHighConsumePartyVO dataCompanyRestrictHighConsumePartyVO = DataCompanyRestrictHighConsumePartyAppStructMapping.instance.dataCompanyRestrictHighConsumePartyDOToDataCompanyRestrictHighConsumePartyVO(byId);
		return SingleResponse.of(dataCompanyRestrictHighConsumePartyVO);
	}
	/**
	 * 执行 企业限制高消费当事人 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumePartyVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyRestrictHighConsumePartyDO byId = iDataCompanyRestrictHighConsumePartyService.getById(detailForUpdateCommand.getId());
		DataCompanyRestrictHighConsumePartyVO dataCompanyRestrictHighConsumePartyVO = DataCompanyRestrictHighConsumePartyAppStructMapping.instance.dataCompanyRestrictHighConsumePartyDOToDataCompanyRestrictHighConsumePartyVO(byId);
		return SingleResponse.of(dataCompanyRestrictHighConsumePartyVO);
	}


	@Autowired
	public void setIDataCompanyRestrictHighConsumePartyService(IDataCompanyRestrictHighConsumePartyService iDataCompanyRestrictHighConsumePartyService) {
		this.iDataCompanyRestrictHighConsumePartyService = iDataCompanyRestrictHighConsumePartyService;
	}
}
