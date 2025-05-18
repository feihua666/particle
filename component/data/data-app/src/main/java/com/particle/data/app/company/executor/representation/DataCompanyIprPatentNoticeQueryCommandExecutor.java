package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentNoticeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentNoticeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentNoticeVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentNoticeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentNoticeService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentNoticePageQueryCommand;
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
 * 企业知识产权专利通知书信息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@Component
@Validated
public class DataCompanyIprPatentNoticeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentNoticeService iDataCompanyIprPatentNoticeService;

	/**
	 * 执行 企业知识产权专利通知书信息 列表查询指令
	 * @param dataCompanyIprPatentNoticeQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentNoticeVO> execute(@Valid DataCompanyIprPatentNoticeQueryListCommand dataCompanyIprPatentNoticeQueryListCommand) {
		List<DataCompanyIprPatentNoticeDO> dataCompanyIprPatentNoticeDO = iDataCompanyIprPatentNoticeService.list(dataCompanyIprPatentNoticeQueryListCommand);
		List<DataCompanyIprPatentNoticeVO> dataCompanyIprPatentNoticeVOs = DataCompanyIprPatentNoticeAppStructMapping.instance.dataCompanyIprPatentNoticeDOsToDataCompanyIprPatentNoticeVOs(dataCompanyIprPatentNoticeDO);
		return MultiResponse.of(dataCompanyIprPatentNoticeVOs);
	}
	/**
	 * 执行 企业知识产权专利通知书信息 分页查询指令
	 * @param dataCompanyIprPatentNoticePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentNoticeVO> execute(@Valid DataCompanyIprPatentNoticePageQueryCommand dataCompanyIprPatentNoticePageQueryCommand) {
		Page<DataCompanyIprPatentNoticeDO> page = iDataCompanyIprPatentNoticeService.listPage(dataCompanyIprPatentNoticePageQueryCommand);
		return DataCompanyIprPatentNoticeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业知识产权专利通知书信息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentNoticeVO> executeDetail(IdCommand detailCommand) {
		DataCompanyIprPatentNoticeDO byId = iDataCompanyIprPatentNoticeService.getById(detailCommand.getId());
		DataCompanyIprPatentNoticeVO dataCompanyIprPatentNoticeVO = DataCompanyIprPatentNoticeAppStructMapping.instance.dataCompanyIprPatentNoticeDOToDataCompanyIprPatentNoticeVO(byId);
		return SingleResponse.of(dataCompanyIprPatentNoticeVO);
	}
	/**
	 * 执行 企业知识产权专利通知书信息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentNoticeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyIprPatentNoticeDO byId = iDataCompanyIprPatentNoticeService.getById(detailForUpdateCommand.getId());
		DataCompanyIprPatentNoticeVO dataCompanyIprPatentNoticeVO = DataCompanyIprPatentNoticeAppStructMapping.instance.dataCompanyIprPatentNoticeDOToDataCompanyIprPatentNoticeVO(byId);
		return SingleResponse.of(dataCompanyIprPatentNoticeVO);
	}


	@Autowired
	public void setIDataCompanyIprPatentNoticeService(IDataCompanyIprPatentNoticeService iDataCompanyIprPatentNoticeService) {
		this.iDataCompanyIprPatentNoticeService = iDataCompanyIprPatentNoticeService;
	}
}
