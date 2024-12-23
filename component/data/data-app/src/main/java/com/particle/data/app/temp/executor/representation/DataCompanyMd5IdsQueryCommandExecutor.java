package com.particle.data.app.temp.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.temp.structmapping.DataCompanyMd5IdsAppStructMapping;
import com.particle.data.client.temp.dto.command.representation.DataCompanyMd5IdsPageQueryCommand;
import com.particle.data.client.temp.dto.command.representation.DataCompanyMd5IdsQueryListCommand;
import com.particle.data.client.temp.dto.data.DataCompanyMd5IdsVO;
import com.particle.data.infrastructure.temp.dos.DataCompanyMd5IdsDO;
import com.particle.data.infrastructure.temp.service.IDataCompanyMd5IdsService;
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
 * 企业md5ids 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Component
@Validated
public class DataCompanyMd5IdsQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyMd5IdsService iDataCompanyMd5IdsService;

	/**
	 * 执行 企业md5ids 列表查询指令
	 * @param dataCompanyMd5IdsQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyMd5IdsVO> execute(@Valid DataCompanyMd5IdsQueryListCommand dataCompanyMd5IdsQueryListCommand) {
		List<DataCompanyMd5IdsDO> dataCompanyMd5IdsDO = iDataCompanyMd5IdsService.list(dataCompanyMd5IdsQueryListCommand);
		List<DataCompanyMd5IdsVO> dataCompanyMd5IdsVOs = DataCompanyMd5IdsAppStructMapping.instance.dataCompanyMd5IdsDOsToDataCompanyMd5IdsVOs(dataCompanyMd5IdsDO);
		return MultiResponse.of(dataCompanyMd5IdsVOs);
	}
	/**
	 * 执行 企业md5ids 分页查询指令
	 * @param dataCompanyMd5IdsPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyMd5IdsVO> execute(@Valid DataCompanyMd5IdsPageQueryCommand dataCompanyMd5IdsPageQueryCommand) {
		Page<DataCompanyMd5IdsDO> page = iDataCompanyMd5IdsService.listPage(dataCompanyMd5IdsPageQueryCommand);
		return DataCompanyMd5IdsAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业md5ids 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyMd5IdsVO> executeDetail(IdCommand detailCommand) {
		DataCompanyMd5IdsDO byId = iDataCompanyMd5IdsService.getById(detailCommand.getId());
		DataCompanyMd5IdsVO dataCompanyMd5IdsVO = DataCompanyMd5IdsAppStructMapping.instance.dataCompanyMd5IdsDOToDataCompanyMd5IdsVO(byId);
		return SingleResponse.of(dataCompanyMd5IdsVO);
	}
	/**
	 * 执行 企业md5ids 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyMd5IdsVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyMd5IdsDO byId = iDataCompanyMd5IdsService.getById(detailForUpdateCommand.getId());
		DataCompanyMd5IdsVO dataCompanyMd5IdsVO = DataCompanyMd5IdsAppStructMapping.instance.dataCompanyMd5IdsDOToDataCompanyMd5IdsVO(byId);
		return SingleResponse.of(dataCompanyMd5IdsVO);
	}


	@Autowired
	public void setIDataCompanyMd5IdsService(IDataCompanyMd5IdsService iDataCompanyMd5IdsService) {
		this.iDataCompanyMd5IdsService = iDataCompanyMd5IdsService;
	}
}
