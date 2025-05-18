package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentPaymentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPaymentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPaymentVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPaymentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPaymentService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPaymentPageQueryCommand;
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
 * 企业知识产权专利缴费信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Component
@Validated
public class DataCompanyIprPatentPaymentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentPaymentService iDataCompanyIprPatentPaymentService;

	/**
	 * 执行 企业知识产权专利缴费信息 列表查询指令
	 * @param dataCompanyIprPatentPaymentQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentPaymentVO> execute(@Valid DataCompanyIprPatentPaymentQueryListCommand dataCompanyIprPatentPaymentQueryListCommand) {
		List<DataCompanyIprPatentPaymentDO> dataCompanyIprPatentPaymentDO = iDataCompanyIprPatentPaymentService.list(dataCompanyIprPatentPaymentQueryListCommand);
		List<DataCompanyIprPatentPaymentVO> dataCompanyIprPatentPaymentVOs = DataCompanyIprPatentPaymentAppStructMapping.instance.dataCompanyIprPatentPaymentDOsToDataCompanyIprPatentPaymentVOs(dataCompanyIprPatentPaymentDO);
		return MultiResponse.of(dataCompanyIprPatentPaymentVOs);
	}
	/**
	 * 执行 企业知识产权专利缴费信息 分页查询指令
	 * @param dataCompanyIprPatentPaymentPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentPaymentVO> execute(@Valid DataCompanyIprPatentPaymentPageQueryCommand dataCompanyIprPatentPaymentPageQueryCommand) {
		Page<DataCompanyIprPatentPaymentDO> page = iDataCompanyIprPatentPaymentService.listPage(dataCompanyIprPatentPaymentPageQueryCommand);
		return DataCompanyIprPatentPaymentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权专利缴费信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPaymentVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPatentPaymentDO byId = iDataCompanyIprPatentPaymentService.getById(detailCommand.getId());
		DataCompanyIprPatentPaymentVO dataCompanyIprPatentPaymentVO = DataCompanyIprPatentPaymentAppStructMapping.instance.dataCompanyIprPatentPaymentDOToDataCompanyIprPatentPaymentVO(byId);
		return SingleResponse.of(dataCompanyIprPatentPaymentVO);
	}
	/**
	 * 执行 企业知识产权专利缴费信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPaymentVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPatentPaymentDO byId = iDataCompanyIprPatentPaymentService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPatentPaymentVO dataCompanyIprPatentPaymentVO = DataCompanyIprPatentPaymentAppStructMapping.instance.dataCompanyIprPatentPaymentDOToDataCompanyIprPatentPaymentVO(byId);
		return SingleResponse.of(dataCompanyIprPatentPaymentVO);
	}


	@Autowired
	public void setIDataCompanyIprPatentPaymentService(IDataCompanyIprPatentPaymentService iDataCompanyIprPatentPaymentService) {
		this.iDataCompanyIprPatentPaymentService = iDataCompanyIprPatentPaymentService;
	}
}
