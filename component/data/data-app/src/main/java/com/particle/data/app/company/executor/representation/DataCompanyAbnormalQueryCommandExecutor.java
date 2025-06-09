package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyAbnormalAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyAbnormalQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAbnormalVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAbnormalDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAbnormalService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAbnormalPageQueryCommand;
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
 * 企业经营异常 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Component
@Validated
public class DataCompanyAbnormalQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyAbnormalService iDataCompanyAbnormalService;

	/**
	 * 执行 企业经营异常 列表查询指令
	 * @param dataCompanyAbnormalQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyAbnormalVO> execute(@Valid DataCompanyAbnormalQueryListCommand dataCompanyAbnormalQueryListCommand) {
		List<DataCompanyAbnormalDO> dataCompanyAbnormalDO = iDataCompanyAbnormalService.list(dataCompanyAbnormalQueryListCommand);
		List<DataCompanyAbnormalVO> dataCompanyAbnormalVOs = DataCompanyAbnormalAppStructMapping.instance.dataCompanyAbnormalDOsToDataCompanyAbnormalVOs(dataCompanyAbnormalDO);
		return MultiResponse.of(dataCompanyAbnormalVOs);
	}
	/**
	 * 执行 企业经营异常 分页查询指令
	 * @param dataCompanyAbnormalPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAbnormalVO> execute(@Valid DataCompanyAbnormalPageQueryCommand dataCompanyAbnormalPageQueryCommand) {
		Page<DataCompanyAbnormalDO> page = iDataCompanyAbnormalService.listPage(dataCompanyAbnormalPageQueryCommand);
		return DataCompanyAbnormalAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业经营异常 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAbnormalVO> executeDetail(IdCommand detailCommand) {
		DataCompanyAbnormalDO byId = iDataCompanyAbnormalService.getById(detailCommand.getId());
		DataCompanyAbnormalVO dataCompanyAbnormalVO = DataCompanyAbnormalAppStructMapping.instance.dataCompanyAbnormalDOToDataCompanyAbnormalVO(byId);
		return SingleResponse.of(dataCompanyAbnormalVO);
	}
	/**
	 * 执行 企业经营异常 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAbnormalVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyAbnormalDO byId = iDataCompanyAbnormalService.getById(detailForUpdateCommand.getId());
		DataCompanyAbnormalVO dataCompanyAbnormalVO = DataCompanyAbnormalAppStructMapping.instance.dataCompanyAbnormalDOToDataCompanyAbnormalVO(byId);
		return SingleResponse.of(dataCompanyAbnormalVO);
	}


	@Autowired
	public void setIDataCompanyAbnormalService(IDataCompanyAbnormalService iDataCompanyAbnormalService) {
		this.iDataCompanyAbnormalService = iDataCompanyAbnormalService;
	}
}
