package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyShareholderAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyShareholderQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyShareholderVO;
import com.particle.data.infrastructure.company.dos.DataCompanyShareholderDO;
import com.particle.data.infrastructure.company.service.IDataCompanyShareholderService;
import com.particle.data.client.company.dto.command.representation.DataCompanyShareholderPageQueryCommand;
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
 * 企业股东 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:46:01
 */
@Component
@Validated
public class DataCompanyShareholderQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyShareholderService iDataCompanyShareholderService;

	/**
	 * 执行 企业股东 列表查询指令
	 * @param dataCompanyShareholderQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyShareholderVO> execute(@Valid DataCompanyShareholderQueryListCommand dataCompanyShareholderQueryListCommand) {
		List<DataCompanyShareholderDO> dataCompanyShareholderDO = iDataCompanyShareholderService.list(dataCompanyShareholderQueryListCommand);
		List<DataCompanyShareholderVO> dataCompanyShareholderVOs = DataCompanyShareholderAppStructMapping.instance.dataCompanyShareholderDOsToDataCompanyShareholderVOs(dataCompanyShareholderDO);
		return MultiResponse.of(dataCompanyShareholderVOs);
	}
	/**
	 * 执行 企业股东 分页查询指令
	 * @param dataCompanyShareholderPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyShareholderVO> execute(@Valid DataCompanyShareholderPageQueryCommand dataCompanyShareholderPageQueryCommand) {
		Page<DataCompanyShareholderDO> page = iDataCompanyShareholderService.listPage(dataCompanyShareholderPageQueryCommand);
		return DataCompanyShareholderAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业股东 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyShareholderVO> executeDetail(IdCommand detailCommand) {
		DataCompanyShareholderDO byId = iDataCompanyShareholderService.getById(detailCommand.getId());
		DataCompanyShareholderVO dataCompanyShareholderVO = DataCompanyShareholderAppStructMapping.instance.dataCompanyShareholderDOToDataCompanyShareholderVO(byId);
		return SingleResponse.of(dataCompanyShareholderVO);
	}
	/**
	 * 执行 企业股东 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyShareholderVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyShareholderDO byId = iDataCompanyShareholderService.getById(detailForUpdateCommand.getId());
		DataCompanyShareholderVO dataCompanyShareholderVO = DataCompanyShareholderAppStructMapping.instance.dataCompanyShareholderDOToDataCompanyShareholderVO(byId);
		return SingleResponse.of(dataCompanyShareholderVO);
	}


	@Autowired
	public void setIDataCompanyShareholderService(IDataCompanyShareholderService iDataCompanyShareholderService) {
		this.iDataCompanyShareholderService = iDataCompanyShareholderService;
	}
}
