package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPledgeVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPledgeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkPledgeService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPledgePageQueryCommand;
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
 * 企业知识产权商标质押信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Component
@Validated
public class DataCompanyIprTrademarkPledgeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprTrademarkPledgeService iDataCompanyIprTrademarkPledgeService;

	/**
	 * 执行 企业知识产权商标质押信息 列表查询指令
	 * @param dataCompanyIprTrademarkPledgeQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprTrademarkPledgeVO> execute(@Valid DataCompanyIprTrademarkPledgeQueryListCommand dataCompanyIprTrademarkPledgeQueryListCommand) {
		List<DataCompanyIprTrademarkPledgeDO> dataCompanyIprTrademarkPledgeDO = iDataCompanyIprTrademarkPledgeService.list(dataCompanyIprTrademarkPledgeQueryListCommand);
		List<DataCompanyIprTrademarkPledgeVO> dataCompanyIprTrademarkPledgeVOs = DataCompanyIprTrademarkPledgeAppStructMapping.instance.dataCompanyIprTrademarkPledgeDOsToDataCompanyIprTrademarkPledgeVOs(dataCompanyIprTrademarkPledgeDO);
		return MultiResponse.of(dataCompanyIprTrademarkPledgeVOs);
	}
	/**
	 * 执行 企业知识产权商标质押信息 分页查询指令
	 * @param dataCompanyIprTrademarkPledgePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkPledgeVO> execute(@Valid DataCompanyIprTrademarkPledgePageQueryCommand dataCompanyIprTrademarkPledgePageQueryCommand) {
		Page<DataCompanyIprTrademarkPledgeDO> page = iDataCompanyIprTrademarkPledgeService.listPage(dataCompanyIprTrademarkPledgePageQueryCommand);
		return DataCompanyIprTrademarkPledgeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权商标质押信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPledgeVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprTrademarkPledgeDO byId = iDataCompanyIprTrademarkPledgeService.getById(detailCommand.getId());
		DataCompanyIprTrademarkPledgeVO dataCompanyIprTrademarkPledgeVO = DataCompanyIprTrademarkPledgeAppStructMapping.instance.dataCompanyIprTrademarkPledgeDOToDataCompanyIprTrademarkPledgeVO(byId);
		return SingleResponse.of(dataCompanyIprTrademarkPledgeVO);
	}
	/**
	 * 执行 企业知识产权商标质押信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPledgeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprTrademarkPledgeDO byId = iDataCompanyIprTrademarkPledgeService.getById(detailForUpdateCommand.getId());
		DataCompanyIprTrademarkPledgeVO dataCompanyIprTrademarkPledgeVO = DataCompanyIprTrademarkPledgeAppStructMapping.instance.dataCompanyIprTrademarkPledgeDOToDataCompanyIprTrademarkPledgeVO(byId);
		return SingleResponse.of(dataCompanyIprTrademarkPledgeVO);
	}


	@Autowired
	public void setIDataCompanyIprTrademarkPledgeService(IDataCompanyIprTrademarkPledgeService iDataCompanyIprTrademarkPledgeService) {
		this.iDataCompanyIprTrademarkPledgeService = iDataCompanyIprTrademarkPledgeService;
	}
}
