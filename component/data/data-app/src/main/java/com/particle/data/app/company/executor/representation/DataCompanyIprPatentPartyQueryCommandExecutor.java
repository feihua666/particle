package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentPartyAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPartyVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPartyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPartyService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPartyPageQueryCommand;
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
 * 企业知识产权专利当事人 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Component
@Validated
public class DataCompanyIprPatentPartyQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentPartyService iDataCompanyIprPatentPartyService;

	/**
	 * 执行 企业知识产权专利当事人 列表查询指令
	 * @param dataCompanyIprPatentPartyQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentPartyVO> execute(@Valid DataCompanyIprPatentPartyQueryListCommand dataCompanyIprPatentPartyQueryListCommand) {
		List<DataCompanyIprPatentPartyDO> dataCompanyIprPatentPartyDO = iDataCompanyIprPatentPartyService.list(dataCompanyIprPatentPartyQueryListCommand);
		List<DataCompanyIprPatentPartyVO> dataCompanyIprPatentPartyVOs = DataCompanyIprPatentPartyAppStructMapping.instance.dataCompanyIprPatentPartyDOsToDataCompanyIprPatentPartyVOs(dataCompanyIprPatentPartyDO);
		return MultiResponse.of(dataCompanyIprPatentPartyVOs);
	}
	/**
	 * 执行 企业知识产权专利当事人 分页查询指令
	 * @param dataCompanyIprPatentPartyPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentPartyVO> execute(@Valid DataCompanyIprPatentPartyPageQueryCommand dataCompanyIprPatentPartyPageQueryCommand) {
		Page<DataCompanyIprPatentPartyDO> page = iDataCompanyIprPatentPartyService.listPage(dataCompanyIprPatentPartyPageQueryCommand);
		return DataCompanyIprPatentPartyAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权专利当事人 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPartyVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPatentPartyDO byId = iDataCompanyIprPatentPartyService.getById(detailCommand.getId());
		DataCompanyIprPatentPartyVO dataCompanyIprPatentPartyVO = DataCompanyIprPatentPartyAppStructMapping.instance.dataCompanyIprPatentPartyDOToDataCompanyIprPatentPartyVO(byId);
		return SingleResponse.of(dataCompanyIprPatentPartyVO);
	}
	/**
	 * 执行 企业知识产权专利当事人 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPartyVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPatentPartyDO byId = iDataCompanyIprPatentPartyService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPatentPartyVO dataCompanyIprPatentPartyVO = DataCompanyIprPatentPartyAppStructMapping.instance.dataCompanyIprPatentPartyDOToDataCompanyIprPatentPartyVO(byId);
		return SingleResponse.of(dataCompanyIprPatentPartyVO);
	}


	@Autowired
	public void setIDataCompanyIprPatentPartyService(IDataCompanyIprPatentPartyService iDataCompanyIprPatentPartyService) {
		this.iDataCompanyIprPatentPartyService = iDataCompanyIprPatentPartyService;
	}
}
