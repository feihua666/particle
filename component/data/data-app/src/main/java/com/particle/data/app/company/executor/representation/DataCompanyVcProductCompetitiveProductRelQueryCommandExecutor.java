package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyVcProductCompetitiveProductRelAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductCompetitiveProductRelQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductCompetitiveProductRelVO;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductCompetitiveProductRelDO;
import com.particle.data.infrastructure.company.service.IDataCompanyVcProductCompetitiveProductRelService;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductCompetitiveProductRelPageQueryCommand;
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
 * 企业融资产品竞品关系 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Component
@Validated
public class DataCompanyVcProductCompetitiveProductRelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyVcProductCompetitiveProductRelService iDataCompanyVcProductCompetitiveProductRelService;

	/**
	 * 执行 企业融资产品竞品关系 列表查询指令
	 * @param dataCompanyVcProductCompetitiveProductRelQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyVcProductCompetitiveProductRelVO> execute(@Valid DataCompanyVcProductCompetitiveProductRelQueryListCommand dataCompanyVcProductCompetitiveProductRelQueryListCommand) {
		List<DataCompanyVcProductCompetitiveProductRelDO> dataCompanyVcProductCompetitiveProductRelDO = iDataCompanyVcProductCompetitiveProductRelService.list(dataCompanyVcProductCompetitiveProductRelQueryListCommand);
		List<DataCompanyVcProductCompetitiveProductRelVO> dataCompanyVcProductCompetitiveProductRelVOs = DataCompanyVcProductCompetitiveProductRelAppStructMapping.instance.dataCompanyVcProductCompetitiveProductRelDOsToDataCompanyVcProductCompetitiveProductRelVOs(dataCompanyVcProductCompetitiveProductRelDO);
		return MultiResponse.of(dataCompanyVcProductCompetitiveProductRelVOs);
	}
	/**
	 * 执行 企业融资产品竞品关系 分页查询指令
	 * @param dataCompanyVcProductCompetitiveProductRelPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyVcProductCompetitiveProductRelVO> execute(@Valid DataCompanyVcProductCompetitiveProductRelPageQueryCommand dataCompanyVcProductCompetitiveProductRelPageQueryCommand) {
		Page<DataCompanyVcProductCompetitiveProductRelDO> page = iDataCompanyVcProductCompetitiveProductRelService.listPage(dataCompanyVcProductCompetitiveProductRelPageQueryCommand);
		return DataCompanyVcProductCompetitiveProductRelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业融资产品竞品关系 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> executeDetail(IdCommand detailCommand) {
		DataCompanyVcProductCompetitiveProductRelDO byId = iDataCompanyVcProductCompetitiveProductRelService.getById(detailCommand.getId());
		DataCompanyVcProductCompetitiveProductRelVO dataCompanyVcProductCompetitiveProductRelVO = DataCompanyVcProductCompetitiveProductRelAppStructMapping.instance.dataCompanyVcProductCompetitiveProductRelDOToDataCompanyVcProductCompetitiveProductRelVO(byId);
		return SingleResponse.of(dataCompanyVcProductCompetitiveProductRelVO);
	}
	/**
	 * 执行 企业融资产品竞品关系 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyVcProductCompetitiveProductRelDO byId = iDataCompanyVcProductCompetitiveProductRelService.getById(detailForUpdateCommand.getId());
		DataCompanyVcProductCompetitiveProductRelVO dataCompanyVcProductCompetitiveProductRelVO = DataCompanyVcProductCompetitiveProductRelAppStructMapping.instance.dataCompanyVcProductCompetitiveProductRelDOToDataCompanyVcProductCompetitiveProductRelVO(byId);
		return SingleResponse.of(dataCompanyVcProductCompetitiveProductRelVO);
	}


	/**
	 * 查询企业融资产品表ID已分配的企业竞品ids
	 * @param companyVcProductIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryCompanyVcCompetitiveProductIdsByCompanyVcProductId(@Valid IdCommand companyVcProductIdCommand) {

		DataCompanyVcProductCompetitiveProductRelQueryListCommand dataCompanyVcProductCompetitiveProductRelQueryListCommand = new DataCompanyVcProductCompetitiveProductRelQueryListCommand();
		dataCompanyVcProductCompetitiveProductRelQueryListCommand.setCompanyVcProductId(companyVcProductIdCommand.getId());
		MultiResponse<DataCompanyVcProductCompetitiveProductRelVO> dataCompanyVcProductCompetitiveProductRelVOMultiResponse = execute(dataCompanyVcProductCompetitiveProductRelQueryListCommand);
		if(dataCompanyVcProductCompetitiveProductRelVOMultiResponse.isNotEmpty()){
			List<Long> collect = dataCompanyVcProductCompetitiveProductRelVOMultiResponse.getData().stream().map(DataCompanyVcProductCompetitiveProductRelVO::getCompanyVcCompetitiveProductId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}
	/**
	 * 查询企业竞品已分配的企业融资产品表IDids
	 * @param companyVcCompetitiveProductIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryCompanyVcProductIdsByCompanyVcCompetitiveProductId(@Valid IdCommand companyVcCompetitiveProductIdCommand) {

		DataCompanyVcProductCompetitiveProductRelQueryListCommand dataCompanyVcProductCompetitiveProductRelQueryListCommand = new DataCompanyVcProductCompetitiveProductRelQueryListCommand();
		dataCompanyVcProductCompetitiveProductRelQueryListCommand.setCompanyVcCompetitiveProductId(companyVcCompetitiveProductIdCommand.getId());
		MultiResponse<DataCompanyVcProductCompetitiveProductRelVO> dataCompanyVcProductCompetitiveProductRelVOMultiResponse = execute(dataCompanyVcProductCompetitiveProductRelQueryListCommand);
		if(dataCompanyVcProductCompetitiveProductRelVOMultiResponse.isNotEmpty()){
			List<Long> collect = dataCompanyVcProductCompetitiveProductRelVOMultiResponse.getData().stream().map(DataCompanyVcProductCompetitiveProductRelVO::getCompanyVcProductId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}

	@Autowired
	public void setIDataCompanyVcProductCompetitiveProductRelService(IDataCompanyVcProductCompetitiveProductRelService iDataCompanyVcProductCompetitiveProductRelService) {
		this.iDataCompanyVcProductCompetitiveProductRelService = iDataCompanyVcProductCompetitiveProductRelService;
	}
}
