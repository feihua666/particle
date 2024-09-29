package com.particle.openplatform.app.openapi.executor.representation;

import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiBatchQueryRecordDetailAppStructMapping;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordDetailQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordDetailVO;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiBatchQueryRecordDetailDO;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiBatchQueryRecordDetailService;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordDetailPageQueryCommand;
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
 * 开放接口批量查询记录明细 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Component
@Validated
public class OpenplatformOpenapiBatchQueryRecordDetailQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformOpenapiBatchQueryRecordDetailService iOpenplatformOpenapiBatchQueryRecordDetailService;

	/**
	 * 执行 开放接口批量查询记录明细 列表查询指令
	 * @param openplatformOpenapiBatchQueryRecordDetailQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> execute(@Valid OpenplatformOpenapiBatchQueryRecordDetailQueryListCommand openplatformOpenapiBatchQueryRecordDetailQueryListCommand) {
		List<OpenplatformOpenapiBatchQueryRecordDetailDO> openplatformOpenapiBatchQueryRecordDetailDO = iOpenplatformOpenapiBatchQueryRecordDetailService.list(openplatformOpenapiBatchQueryRecordDetailQueryListCommand);
		List<OpenplatformOpenapiBatchQueryRecordDetailVO> openplatformOpenapiBatchQueryRecordDetailVOs = OpenplatformOpenapiBatchQueryRecordDetailAppStructMapping.instance.openplatformOpenapiBatchQueryRecordDetailDOsToOpenplatformOpenapiBatchQueryRecordDetailVOs(openplatformOpenapiBatchQueryRecordDetailDO);
		return MultiResponse.of(openplatformOpenapiBatchQueryRecordDetailVOs);
	}
	/**
	 * 执行 开放接口批量查询记录明细 分页查询指令
	 * @param openplatformOpenapiBatchQueryRecordDetailPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> execute(@Valid OpenplatformOpenapiBatchQueryRecordDetailPageQueryCommand openplatformOpenapiBatchQueryRecordDetailPageQueryCommand) {
		Page<OpenplatformOpenapiBatchQueryRecordDetailDO> page = iOpenplatformOpenapiBatchQueryRecordDetailService.listPage(openplatformOpenapiBatchQueryRecordDetailPageQueryCommand);
		return OpenplatformOpenapiBatchQueryRecordDetailAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放接口批量查询记录明细 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> executeDetail(IdCommand detailCommand) {
		OpenplatformOpenapiBatchQueryRecordDetailDO byId = iOpenplatformOpenapiBatchQueryRecordDetailService.getById(detailCommand.getId());
		OpenplatformOpenapiBatchQueryRecordDetailVO openplatformOpenapiBatchQueryRecordDetailVO = OpenplatformOpenapiBatchQueryRecordDetailAppStructMapping.instance.openplatformOpenapiBatchQueryRecordDetailDOToOpenplatformOpenapiBatchQueryRecordDetailVO(byId);
		return SingleResponse.of(openplatformOpenapiBatchQueryRecordDetailVO);
	}
	/**
	 * 执行 开放接口批量查询记录明细 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformOpenapiBatchQueryRecordDetailDO byId = iOpenplatformOpenapiBatchQueryRecordDetailService.getById(detailForUpdateCommand.getId());
		OpenplatformOpenapiBatchQueryRecordDetailVO openplatformOpenapiBatchQueryRecordDetailVO = OpenplatformOpenapiBatchQueryRecordDetailAppStructMapping.instance.openplatformOpenapiBatchQueryRecordDetailDOToOpenplatformOpenapiBatchQueryRecordDetailVO(byId);
		return SingleResponse.of(openplatformOpenapiBatchQueryRecordDetailVO);
	}


	@Autowired
	public void setIOpenplatformOpenapiBatchQueryRecordDetailService(IOpenplatformOpenapiBatchQueryRecordDetailService iOpenplatformOpenapiBatchQueryRecordDetailService) {
		this.iOpenplatformOpenapiBatchQueryRecordDetailService = iOpenplatformOpenapiBatchQueryRecordDetailService;
	}
}
