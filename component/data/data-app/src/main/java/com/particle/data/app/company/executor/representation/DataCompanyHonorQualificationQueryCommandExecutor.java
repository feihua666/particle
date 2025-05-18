package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyHonorQualificationAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyHonorQualificationQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyHonorQualificationVO;
import com.particle.data.infrastructure.company.dos.DataCompanyHonorQualificationDO;
import com.particle.data.infrastructure.company.service.IDataCompanyHonorQualificationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyHonorQualificationPageQueryCommand;
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
 * 企业荣誉资质 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Component
@Validated
public class DataCompanyHonorQualificationQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyHonorQualificationService iDataCompanyHonorQualificationService;

	/**
	 * 执行 企业荣誉资质 列表查询指令
	 * @param dataCompanyHonorQualificationQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyHonorQualificationVO> execute(@Valid DataCompanyHonorQualificationQueryListCommand dataCompanyHonorQualificationQueryListCommand) {
		List<DataCompanyHonorQualificationDO> dataCompanyHonorQualificationDO = iDataCompanyHonorQualificationService.list(dataCompanyHonorQualificationQueryListCommand);
		List<DataCompanyHonorQualificationVO> dataCompanyHonorQualificationVOs = DataCompanyHonorQualificationAppStructMapping.instance.dataCompanyHonorQualificationDOsToDataCompanyHonorQualificationVOs(dataCompanyHonorQualificationDO);
		return MultiResponse.of(dataCompanyHonorQualificationVOs);
	}
	/**
	 * 执行 企业荣誉资质 分页查询指令
	 * @param dataCompanyHonorQualificationPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyHonorQualificationVO> execute(@Valid DataCompanyHonorQualificationPageQueryCommand dataCompanyHonorQualificationPageQueryCommand) {
		Page<DataCompanyHonorQualificationDO> page = iDataCompanyHonorQualificationService.listPage(dataCompanyHonorQualificationPageQueryCommand);
		return DataCompanyHonorQualificationAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业荣誉资质 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyHonorQualificationVO> executeDetail(IdCommand detailCommand) {
		DataCompanyHonorQualificationDO byId = iDataCompanyHonorQualificationService.getById(detailCommand.getId());
		DataCompanyHonorQualificationVO dataCompanyHonorQualificationVO = DataCompanyHonorQualificationAppStructMapping.instance.dataCompanyHonorQualificationDOToDataCompanyHonorQualificationVO(byId);
		return SingleResponse.of(dataCompanyHonorQualificationVO);
	}
	/**
	 * 执行 企业荣誉资质 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyHonorQualificationVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyHonorQualificationDO byId = iDataCompanyHonorQualificationService.getById(detailForUpdateCommand.getId());
		DataCompanyHonorQualificationVO dataCompanyHonorQualificationVO = DataCompanyHonorQualificationAppStructMapping.instance.dataCompanyHonorQualificationDOToDataCompanyHonorQualificationVO(byId);
		return SingleResponse.of(dataCompanyHonorQualificationVO);
	}


	@Autowired
	public void setIDataCompanyHonorQualificationService(IDataCompanyHonorQualificationService iDataCompanyHonorQualificationService) {
		this.iDataCompanyHonorQualificationService = iDataCompanyHonorQualificationService;
	}
}
