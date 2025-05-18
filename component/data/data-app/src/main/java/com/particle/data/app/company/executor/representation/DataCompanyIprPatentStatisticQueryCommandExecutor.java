package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentStatisticAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentStatisticQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentStatisticVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentStatisticDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentStatisticService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentStatisticPageQueryCommand;
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
 * 企业知识产权专利统计 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Component
@Validated
public class DataCompanyIprPatentStatisticQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentStatisticService iDataCompanyIprPatentStatisticService;

	/**
	 * 执行 企业知识产权专利统计 列表查询指令
	 * @param dataCompanyIprPatentStatisticQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentStatisticVO> execute(@Valid DataCompanyIprPatentStatisticQueryListCommand dataCompanyIprPatentStatisticQueryListCommand) {
		List<DataCompanyIprPatentStatisticDO> dataCompanyIprPatentStatisticDO = iDataCompanyIprPatentStatisticService.list(dataCompanyIprPatentStatisticQueryListCommand);
		List<DataCompanyIprPatentStatisticVO> dataCompanyIprPatentStatisticVOs = DataCompanyIprPatentStatisticAppStructMapping.instance.dataCompanyIprPatentStatisticDOsToDataCompanyIprPatentStatisticVOs(dataCompanyIprPatentStatisticDO);
		return MultiResponse.of(dataCompanyIprPatentStatisticVOs);
	}
	/**
	 * 执行 企业知识产权专利统计 分页查询指令
	 * @param dataCompanyIprPatentStatisticPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentStatisticVO> execute(@Valid DataCompanyIprPatentStatisticPageQueryCommand dataCompanyIprPatentStatisticPageQueryCommand) {
		Page<DataCompanyIprPatentStatisticDO> page = iDataCompanyIprPatentStatisticService.listPage(dataCompanyIprPatentStatisticPageQueryCommand);
		return DataCompanyIprPatentStatisticAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权专利统计 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentStatisticVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPatentStatisticDO byId = iDataCompanyIprPatentStatisticService.getById(detailCommand.getId());
		DataCompanyIprPatentStatisticVO dataCompanyIprPatentStatisticVO = DataCompanyIprPatentStatisticAppStructMapping.instance.dataCompanyIprPatentStatisticDOToDataCompanyIprPatentStatisticVO(byId);
		return SingleResponse.of(dataCompanyIprPatentStatisticVO);
	}
	/**
	 * 执行 企业知识产权专利统计 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentStatisticVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPatentStatisticDO byId = iDataCompanyIprPatentStatisticService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPatentStatisticVO dataCompanyIprPatentStatisticVO = DataCompanyIprPatentStatisticAppStructMapping.instance.dataCompanyIprPatentStatisticDOToDataCompanyIprPatentStatisticVO(byId);
		return SingleResponse.of(dataCompanyIprPatentStatisticVO);
	}


	@Autowired
	public void setIDataCompanyIprPatentStatisticService(IDataCompanyIprPatentStatisticService iDataCompanyIprPatentStatisticService) {
		this.iDataCompanyIprPatentStatisticService = iDataCompanyIprPatentStatisticService;
	}
}
