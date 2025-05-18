package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPledgeVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPledgeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPledgeService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPledgePageQueryCommand;
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
 * 企业知识产权专利质押信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:41:40
 */
@Component
@Validated
public class DataCompanyIprPatentPledgeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentPledgeService iDataCompanyIprPatentPledgeService;

	/**
	 * 执行 企业知识产权专利质押信息 列表查询指令
	 * @param dataCompanyIprPatentPledgeQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentPledgeVO> execute(@Valid DataCompanyIprPatentPledgeQueryListCommand dataCompanyIprPatentPledgeQueryListCommand) {
		List<DataCompanyIprPatentPledgeDO> dataCompanyIprPatentPledgeDO = iDataCompanyIprPatentPledgeService.list(dataCompanyIprPatentPledgeQueryListCommand);
		List<DataCompanyIprPatentPledgeVO> dataCompanyIprPatentPledgeVOs = DataCompanyIprPatentPledgeAppStructMapping.instance.dataCompanyIprPatentPledgeDOsToDataCompanyIprPatentPledgeVOs(dataCompanyIprPatentPledgeDO);
		return MultiResponse.of(dataCompanyIprPatentPledgeVOs);
	}
	/**
	 * 执行 企业知识产权专利质押信息 分页查询指令
	 * @param dataCompanyIprPatentPledgePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentPledgeVO> execute(@Valid DataCompanyIprPatentPledgePageQueryCommand dataCompanyIprPatentPledgePageQueryCommand) {
		Page<DataCompanyIprPatentPledgeDO> page = iDataCompanyIprPatentPledgeService.listPage(dataCompanyIprPatentPledgePageQueryCommand);
		return DataCompanyIprPatentPledgeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权专利质押信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPledgeVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPatentPledgeDO byId = iDataCompanyIprPatentPledgeService.getById(detailCommand.getId());
		DataCompanyIprPatentPledgeVO dataCompanyIprPatentPledgeVO = DataCompanyIprPatentPledgeAppStructMapping.instance.dataCompanyIprPatentPledgeDOToDataCompanyIprPatentPledgeVO(byId);
		return SingleResponse.of(dataCompanyIprPatentPledgeVO);
	}
	/**
	 * 执行 企业知识产权专利质押信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPledgeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPatentPledgeDO byId = iDataCompanyIprPatentPledgeService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPatentPledgeVO dataCompanyIprPatentPledgeVO = DataCompanyIprPatentPledgeAppStructMapping.instance.dataCompanyIprPatentPledgeDOToDataCompanyIprPatentPledgeVO(byId);
		return SingleResponse.of(dataCompanyIprPatentPledgeVO);
	}


	@Autowired
	public void setIDataCompanyIprPatentPledgeService(IDataCompanyIprPatentPledgeService iDataCompanyIprPatentPledgeService) {
		this.iDataCompanyIprPatentPledgeService = iDataCompanyIprPatentPledgeService;
	}
}
