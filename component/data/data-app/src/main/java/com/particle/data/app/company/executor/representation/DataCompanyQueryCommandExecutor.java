package com.particle.data.app.company.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.structmapping.DataCompanyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVO;
import com.particle.data.infrastructure.company.dos.DataCompanyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Component
@Validated
public class DataCompanyQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyService iDataCompanyService;

	/**
	 * 执行 企业 列表查询指令
	 * @param dataCompanyQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyVO> execute(@Valid DataCompanyQueryListCommand dataCompanyQueryListCommand) {
		List<DataCompanyDO> dataCompanyDO = iDataCompanyService.list(dataCompanyQueryListCommand);
		List<DataCompanyVO> dataCompanyVOs = DataCompanyAppStructMapping.instance.dataCompanyDOsToDataCompanyVOs(dataCompanyDO);
		return MultiResponse.of(dataCompanyVOs);
	}
	/**
	 * 执行 企业 分页查询指令
	 * @param dataCompanyPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyVO> execute(@Valid DataCompanyPageQueryCommand dataCompanyPageQueryCommand) {
		Page<DataCompanyDO> page = iDataCompanyService.listPage(dataCompanyPageQueryCommand);
		return DataCompanyAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVO> executeDetail(IdCommand detailCommand) {
		DataCompanyDO byId = iDataCompanyService.getById(detailCommand.getId());
		DataCompanyVO dataCompanyVO = DataCompanyAppStructMapping.instance.dataCompanyDOToDataCompanyVO(byId);
		return SingleResponse.of(dataCompanyVO);
	}
	/**
	 * 执行 企业 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyDO byId = iDataCompanyService.getById(detailForUpdateCommand.getId());
		DataCompanyVO dataCompanyVO = DataCompanyAppStructMapping.instance.dataCompanyDOToDataCompanyVO(byId);
		return SingleResponse.of(dataCompanyVO);
	}

	@Autowired
	public void setIDataCompanyService(IDataCompanyService iDataCompanyService) {
		this.iDataCompanyService = iDataCompanyService;
	}
}
