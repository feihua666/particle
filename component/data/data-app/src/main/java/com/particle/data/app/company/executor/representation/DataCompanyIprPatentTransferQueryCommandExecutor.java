package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentTransferAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentTransferQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentTransferVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentTransferDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentTransferService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentTransferPageQueryCommand;
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
 * 企业知识产权专利转让信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Component
@Validated
public class DataCompanyIprPatentTransferQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentTransferService iDataCompanyIprPatentTransferService;

	/**
	 * 执行 企业知识产权专利转让信息 列表查询指令
	 * @param dataCompanyIprPatentTransferQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentTransferVO> execute(@Valid DataCompanyIprPatentTransferQueryListCommand dataCompanyIprPatentTransferQueryListCommand) {
		List<DataCompanyIprPatentTransferDO> dataCompanyIprPatentTransferDO = iDataCompanyIprPatentTransferService.list(dataCompanyIprPatentTransferQueryListCommand);
		List<DataCompanyIprPatentTransferVO> dataCompanyIprPatentTransferVOs = DataCompanyIprPatentTransferAppStructMapping.instance.dataCompanyIprPatentTransferDOsToDataCompanyIprPatentTransferVOs(dataCompanyIprPatentTransferDO);
		return MultiResponse.of(dataCompanyIprPatentTransferVOs);
	}
	/**
	 * 执行 企业知识产权专利转让信息 分页查询指令
	 * @param dataCompanyIprPatentTransferPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentTransferVO> execute(@Valid DataCompanyIprPatentTransferPageQueryCommand dataCompanyIprPatentTransferPageQueryCommand) {
		Page<DataCompanyIprPatentTransferDO> page = iDataCompanyIprPatentTransferService.listPage(dataCompanyIprPatentTransferPageQueryCommand);
		return DataCompanyIprPatentTransferAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权专利转让信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentTransferVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPatentTransferDO byId = iDataCompanyIprPatentTransferService.getById(detailCommand.getId());
		DataCompanyIprPatentTransferVO dataCompanyIprPatentTransferVO = DataCompanyIprPatentTransferAppStructMapping.instance.dataCompanyIprPatentTransferDOToDataCompanyIprPatentTransferVO(byId);
		return SingleResponse.of(dataCompanyIprPatentTransferVO);
	}
	/**
	 * 执行 企业知识产权专利转让信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentTransferVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPatentTransferDO byId = iDataCompanyIprPatentTransferService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPatentTransferVO dataCompanyIprPatentTransferVO = DataCompanyIprPatentTransferAppStructMapping.instance.dataCompanyIprPatentTransferDOToDataCompanyIprPatentTransferVO(byId);
		return SingleResponse.of(dataCompanyIprPatentTransferVO);
	}


	@Autowired
	public void setIDataCompanyIprPatentTransferService(IDataCompanyIprPatentTransferService iDataCompanyIprPatentTransferService) {
		this.iDataCompanyIprPatentTransferService = iDataCompanyIprPatentTransferService;
	}
}
