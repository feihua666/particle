package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyMd5AppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyMd5QueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyMd5VO;
import com.particle.data.infrastructure.company.dos.DataCompanyMd5DO;
import com.particle.data.infrastructure.company.service.IDataCompanyMd5Service;
import com.particle.data.client.company.dto.command.representation.DataCompanyMd5PageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业md5 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Component
@Validated
public class DataCompanyMd5QueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyMd5Service iDataCompanyMd5Service;

	/**
	 * 执行 企业md5 列表查询指令
	 * @param dataCompanyMd5QueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyMd5VO> execute(@Valid DataCompanyMd5QueryListCommand dataCompanyMd5QueryListCommand) {
		List<DataCompanyMd5DO> dataCompanyMd5DO = iDataCompanyMd5Service.list(dataCompanyMd5QueryListCommand);
		List<DataCompanyMd5VO> dataCompanyMd5VOs = DataCompanyMd5AppStructMapping.instance.dataCompanyMd5DOsToDataCompanyMd5VOs(dataCompanyMd5DO);
		return MultiResponse.of(dataCompanyMd5VOs);
	}
	/**
	 * 执行 企业md5 分页查询指令
	 * @param dataCompanyMd5PageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyMd5VO> execute(@Valid DataCompanyMd5PageQueryCommand dataCompanyMd5PageQueryCommand) {
		Page<DataCompanyMd5DO> page = iDataCompanyMd5Service.listPage(dataCompanyMd5PageQueryCommand);
		return DataCompanyMd5AppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业md5 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyMd5VO> executeDetail(IdCommand detailCommand) {
		DataCompanyMd5DO byId = iDataCompanyMd5Service.getById(detailCommand.getId());
		DataCompanyMd5VO dataCompanyMd5VO = DataCompanyMd5AppStructMapping.instance.dataCompanyMd5DOToDataCompanyMd5VO(byId);
		return SingleResponse.of(dataCompanyMd5VO);
	}
	/**
	 * 执行 企业md5 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyMd5VO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyMd5DO byId = iDataCompanyMd5Service.getById(detailForUpdateCommand.getId());
		DataCompanyMd5VO dataCompanyMd5VO = DataCompanyMd5AppStructMapping.instance.dataCompanyMd5DOToDataCompanyMd5VO(byId);
		return SingleResponse.of(dataCompanyMd5VO);
	}


	@Autowired
	public void setIDataCompanyMd5Service(IDataCompanyMd5Service iDataCompanyMd5Service) {
		this.iDataCompanyMd5Service = iDataCompanyMd5Service;
	}
}
