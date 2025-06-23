package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPledgeVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPledgeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPledgeService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPledgePageQueryCommand;
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
 * 企业知识产权出质 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-20 16:19:21
 */
@Component
@Validated
public class DataCompanyIprPledgeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPledgeService iDataCompanyIprPledgeService;

	/**
	 * 执行 企业知识产权出质 列表查询指令
	 * @param dataCompanyIprPledgeQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPledgeVO> execute(@Valid DataCompanyIprPledgeQueryListCommand dataCompanyIprPledgeQueryListCommand) {
		List<DataCompanyIprPledgeDO> dataCompanyIprPledgeDO = iDataCompanyIprPledgeService.list(dataCompanyIprPledgeQueryListCommand);
		List<DataCompanyIprPledgeVO> dataCompanyIprPledgeVOs = DataCompanyIprPledgeAppStructMapping.instance.dataCompanyIprPledgeDOsToDataCompanyIprPledgeVOs(dataCompanyIprPledgeDO);
		return MultiResponse.of(dataCompanyIprPledgeVOs);
	}
	/**
	 * 执行 企业知识产权出质 分页查询指令
	 * @param dataCompanyIprPledgePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPledgeVO> execute(@Valid DataCompanyIprPledgePageQueryCommand dataCompanyIprPledgePageQueryCommand) {
		Page<DataCompanyIprPledgeDO> page = iDataCompanyIprPledgeService.listPage(dataCompanyIprPledgePageQueryCommand);
		return DataCompanyIprPledgeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权出质 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPledgeVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPledgeDO byId = iDataCompanyIprPledgeService.getById(detailCommand.getId());
		DataCompanyIprPledgeVO dataCompanyIprPledgeVO = DataCompanyIprPledgeAppStructMapping.instance.dataCompanyIprPledgeDOToDataCompanyIprPledgeVO(byId);
		return SingleResponse.of(dataCompanyIprPledgeVO);
	}
	/**
	 * 执行 企业知识产权出质 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPledgeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPledgeDO byId = iDataCompanyIprPledgeService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPledgeVO dataCompanyIprPledgeVO = DataCompanyIprPledgeAppStructMapping.instance.dataCompanyIprPledgeDOToDataCompanyIprPledgeVO(byId);
		return SingleResponse.of(dataCompanyIprPledgeVO);
	}


	@Autowired
	public void setIDataCompanyIprPledgeService(IDataCompanyIprPledgeService iDataCompanyIprPledgeService) {
		this.iDataCompanyIprPledgeService = iDataCompanyIprPledgeService;
	}
}
