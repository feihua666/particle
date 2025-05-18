package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyRestrictHighConsumeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumeVO;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyRestrictHighConsumeService;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumePageQueryCommand;
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
 * 企业限制高消费 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyRestrictHighConsumeService iDataCompanyRestrictHighConsumeService;

	/**
	 * 执行 企业限制高消费 列表查询指令
	 * @param dataCompanyRestrictHighConsumeQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyRestrictHighConsumeVO> execute(@Valid DataCompanyRestrictHighConsumeQueryListCommand dataCompanyRestrictHighConsumeQueryListCommand) {
		List<DataCompanyRestrictHighConsumeDO> dataCompanyRestrictHighConsumeDO = iDataCompanyRestrictHighConsumeService.list(dataCompanyRestrictHighConsumeQueryListCommand);
		List<DataCompanyRestrictHighConsumeVO> dataCompanyRestrictHighConsumeVOs = DataCompanyRestrictHighConsumeAppStructMapping.instance.dataCompanyRestrictHighConsumeDOsToDataCompanyRestrictHighConsumeVOs(dataCompanyRestrictHighConsumeDO);
		return MultiResponse.of(dataCompanyRestrictHighConsumeVOs);
	}
	/**
	 * 执行 企业限制高消费 分页查询指令
	 * @param dataCompanyRestrictHighConsumePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyRestrictHighConsumeVO> execute(@Valid DataCompanyRestrictHighConsumePageQueryCommand dataCompanyRestrictHighConsumePageQueryCommand) {
		Page<DataCompanyRestrictHighConsumeDO> page = iDataCompanyRestrictHighConsumeService.listPage(dataCompanyRestrictHighConsumePageQueryCommand);
		return DataCompanyRestrictHighConsumeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业限制高消费 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumeVO> executeDetail(IdCommand detailCommand) {
		DataCompanyRestrictHighConsumeDO byId = iDataCompanyRestrictHighConsumeService.getById(detailCommand.getId());
		DataCompanyRestrictHighConsumeVO dataCompanyRestrictHighConsumeVO = DataCompanyRestrictHighConsumeAppStructMapping.instance.dataCompanyRestrictHighConsumeDOToDataCompanyRestrictHighConsumeVO(byId);
		return SingleResponse.of(dataCompanyRestrictHighConsumeVO);
	}
	/**
	 * 执行 企业限制高消费 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyRestrictHighConsumeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyRestrictHighConsumeDO byId = iDataCompanyRestrictHighConsumeService.getById(detailForUpdateCommand.getId());
		DataCompanyRestrictHighConsumeVO dataCompanyRestrictHighConsumeVO = DataCompanyRestrictHighConsumeAppStructMapping.instance.dataCompanyRestrictHighConsumeDOToDataCompanyRestrictHighConsumeVO(byId);
		return SingleResponse.of(dataCompanyRestrictHighConsumeVO);
	}


	@Autowired
	public void setIDataCompanyRestrictHighConsumeService(IDataCompanyRestrictHighConsumeService iDataCompanyRestrictHighConsumeService) {
		this.iDataCompanyRestrictHighConsumeService = iDataCompanyRestrictHighConsumeService;
	}
}
