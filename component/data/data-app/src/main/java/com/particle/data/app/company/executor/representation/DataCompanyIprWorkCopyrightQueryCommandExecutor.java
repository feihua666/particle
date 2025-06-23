package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprWorkCopyrightAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprWorkCopyrightQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprWorkCopyrightVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprWorkCopyrightDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprWorkCopyrightService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprWorkCopyrightPageQueryCommand;
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
 * 企业知识产权作品著作 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-16 11:16:45
 */
@Component
@Validated
public class DataCompanyIprWorkCopyrightQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprWorkCopyrightService iDataCompanyIprWorkCopyrightService;

	/**
	 * 执行 企业知识产权作品著作 列表查询指令
	 * @param dataCompanyIprWorkCopyrightQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprWorkCopyrightVO> execute(@Valid DataCompanyIprWorkCopyrightQueryListCommand dataCompanyIprWorkCopyrightQueryListCommand) {
		List<DataCompanyIprWorkCopyrightDO> dataCompanyIprWorkCopyrightDO = iDataCompanyIprWorkCopyrightService.list(dataCompanyIprWorkCopyrightQueryListCommand);
		List<DataCompanyIprWorkCopyrightVO> dataCompanyIprWorkCopyrightVOs = DataCompanyIprWorkCopyrightAppStructMapping.instance.dataCompanyIprWorkCopyrightDOsToDataCompanyIprWorkCopyrightVOs(dataCompanyIprWorkCopyrightDO);
		return MultiResponse.of(dataCompanyIprWorkCopyrightVOs);
	}
	/**
	 * 执行 企业知识产权作品著作 分页查询指令
	 * @param dataCompanyIprWorkCopyrightPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprWorkCopyrightVO> execute(@Valid DataCompanyIprWorkCopyrightPageQueryCommand dataCompanyIprWorkCopyrightPageQueryCommand) {
		Page<DataCompanyIprWorkCopyrightDO> page = iDataCompanyIprWorkCopyrightService.listPage(dataCompanyIprWorkCopyrightPageQueryCommand);
		return DataCompanyIprWorkCopyrightAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权作品著作 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprWorkCopyrightVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprWorkCopyrightDO byId = iDataCompanyIprWorkCopyrightService.getById(detailCommand.getId());
		DataCompanyIprWorkCopyrightVO dataCompanyIprWorkCopyrightVO = DataCompanyIprWorkCopyrightAppStructMapping.instance.dataCompanyIprWorkCopyrightDOToDataCompanyIprWorkCopyrightVO(byId);
		return SingleResponse.of(dataCompanyIprWorkCopyrightVO);
	}
	/**
	 * 执行 企业知识产权作品著作 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprWorkCopyrightVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprWorkCopyrightDO byId = iDataCompanyIprWorkCopyrightService.getById(detailForUpdateCommand.getId());
		DataCompanyIprWorkCopyrightVO dataCompanyIprWorkCopyrightVO = DataCompanyIprWorkCopyrightAppStructMapping.instance.dataCompanyIprWorkCopyrightDOToDataCompanyIprWorkCopyrightVO(byId);
		return SingleResponse.of(dataCompanyIprWorkCopyrightVO);
	}


	@Autowired
	public void setIDataCompanyIprWorkCopyrightService(IDataCompanyIprWorkCopyrightService iDataCompanyIprWorkCopyrightService) {
		this.iDataCompanyIprWorkCopyrightService = iDataCompanyIprWorkCopyrightService;
	}
}
