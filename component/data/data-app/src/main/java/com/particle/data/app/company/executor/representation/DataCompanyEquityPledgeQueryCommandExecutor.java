package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyEquityPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyEquityPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyEquityPledgeVO;
import com.particle.data.infrastructure.company.dos.DataCompanyEquityPledgeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyEquityPledgeService;
import com.particle.data.client.company.dto.command.representation.DataCompanyEquityPledgePageQueryCommand;
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
 * 企业股权出质 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-20 16:19:10
 */
@Component
@Validated
public class DataCompanyEquityPledgeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyEquityPledgeService iDataCompanyEquityPledgeService;

	/**
	 * 执行 企业股权出质 列表查询指令
	 * @param dataCompanyEquityPledgeQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyEquityPledgeVO> execute(@Valid DataCompanyEquityPledgeQueryListCommand dataCompanyEquityPledgeQueryListCommand) {
		List<DataCompanyEquityPledgeDO> dataCompanyEquityPledgeDO = iDataCompanyEquityPledgeService.list(dataCompanyEquityPledgeQueryListCommand);
		List<DataCompanyEquityPledgeVO> dataCompanyEquityPledgeVOs = DataCompanyEquityPledgeAppStructMapping.instance.dataCompanyEquityPledgeDOsToDataCompanyEquityPledgeVOs(dataCompanyEquityPledgeDO);
		return MultiResponse.of(dataCompanyEquityPledgeVOs);
	}
	/**
	 * 执行 企业股权出质 分页查询指令
	 * @param dataCompanyEquityPledgePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyEquityPledgeVO> execute(@Valid DataCompanyEquityPledgePageQueryCommand dataCompanyEquityPledgePageQueryCommand) {
		Page<DataCompanyEquityPledgeDO> page = iDataCompanyEquityPledgeService.listPage(dataCompanyEquityPledgePageQueryCommand);
		return DataCompanyEquityPledgeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业股权出质 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyEquityPledgeVO> executeDetail(IdCommand detailCommand) {
		DataCompanyEquityPledgeDO byId = iDataCompanyEquityPledgeService.getById(detailCommand.getId());
		DataCompanyEquityPledgeVO dataCompanyEquityPledgeVO = DataCompanyEquityPledgeAppStructMapping.instance.dataCompanyEquityPledgeDOToDataCompanyEquityPledgeVO(byId);
		return SingleResponse.of(dataCompanyEquityPledgeVO);
	}
	/**
	 * 执行 企业股权出质 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyEquityPledgeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyEquityPledgeDO byId = iDataCompanyEquityPledgeService.getById(detailForUpdateCommand.getId());
		DataCompanyEquityPledgeVO dataCompanyEquityPledgeVO = DataCompanyEquityPledgeAppStructMapping.instance.dataCompanyEquityPledgeDOToDataCompanyEquityPledgeVO(byId);
		return SingleResponse.of(dataCompanyEquityPledgeVO);
	}


	@Autowired
	public void setIDataCompanyEquityPledgeService(IDataCompanyEquityPledgeService iDataCompanyEquityPledgeService) {
		this.iDataCompanyEquityPledgeService = iDataCompanyEquityPledgeService;
	}
}
