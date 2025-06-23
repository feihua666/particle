package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprGeograAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprGeograService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograPageQueryCommand;
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
 * 企业知识产权地理标识 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-16 11:16:33
 */
@Component
@Validated
public class DataCompanyIprGeograQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprGeograService iDataCompanyIprGeograService;

	/**
	 * 执行 企业知识产权地理标识 列表查询指令
	 * @param dataCompanyIprGeograQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprGeograVO> execute(@Valid DataCompanyIprGeograQueryListCommand dataCompanyIprGeograQueryListCommand) {
		List<DataCompanyIprGeograDO> dataCompanyIprGeograDO = iDataCompanyIprGeograService.list(dataCompanyIprGeograQueryListCommand);
		List<DataCompanyIprGeograVO> dataCompanyIprGeograVOs = DataCompanyIprGeograAppStructMapping.instance.dataCompanyIprGeograDOsToDataCompanyIprGeograVOs(dataCompanyIprGeograDO);
		return MultiResponse.of(dataCompanyIprGeograVOs);
	}
	/**
	 * 执行 企业知识产权地理标识 分页查询指令
	 * @param dataCompanyIprGeograPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprGeograVO> execute(@Valid DataCompanyIprGeograPageQueryCommand dataCompanyIprGeograPageQueryCommand) {
		Page<DataCompanyIprGeograDO> page = iDataCompanyIprGeograService.listPage(dataCompanyIprGeograPageQueryCommand);
		return DataCompanyIprGeograAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权地理标识 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprGeograDO byId = iDataCompanyIprGeograService.getById(detailCommand.getId());
		DataCompanyIprGeograVO dataCompanyIprGeograVO = DataCompanyIprGeograAppStructMapping.instance.dataCompanyIprGeograDOToDataCompanyIprGeograVO(byId);
		return SingleResponse.of(dataCompanyIprGeograVO);
	}
	/**
	 * 执行 企业知识产权地理标识 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprGeograDO byId = iDataCompanyIprGeograService.getById(detailForUpdateCommand.getId());
		DataCompanyIprGeograVO dataCompanyIprGeograVO = DataCompanyIprGeograAppStructMapping.instance.dataCompanyIprGeograDOToDataCompanyIprGeograVO(byId);
		return SingleResponse.of(dataCompanyIprGeograVO);
	}


	@Autowired
	public void setIDataCompanyIprGeograService(IDataCompanyIprGeograService iDataCompanyIprGeograService) {
		this.iDataCompanyIprGeograService = iDataCompanyIprGeograService;
	}
}
