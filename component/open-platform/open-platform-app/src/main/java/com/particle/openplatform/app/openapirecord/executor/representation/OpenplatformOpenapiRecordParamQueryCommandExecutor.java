package com.particle.openplatform.app.openapirecord.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapirecord.structmapping.OpenplatformOpenapiRecordParamAppStructMapping;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordParamPageQueryCommand;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordParamQueryListCommand;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordParamVO;
import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordParamDO;
import com.particle.openplatform.infrastructure.openapirecord.service.IOpenplatformOpenapiRecordParamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放平台开放接口调用记录参数 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-08-16 16:15:03
 */
@Component
@Validated
public class OpenplatformOpenapiRecordParamQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformOpenapiRecordParamService iOpenplatformOpenapiRecordParamService;

	/**
	 * 执行 开放平台开放接口调用记录参数 列表查询指令
	 * @param openplatformOpenapiRecordParamQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformOpenapiRecordParamVO> execute(@Valid OpenplatformOpenapiRecordParamQueryListCommand openplatformOpenapiRecordParamQueryListCommand) {
		List<OpenplatformOpenapiRecordParamDO> openplatformOpenapiRecordParamDO = iOpenplatformOpenapiRecordParamService.list(openplatformOpenapiRecordParamQueryListCommand);
		List<OpenplatformOpenapiRecordParamVO> openplatformOpenapiRecordParamVOs = OpenplatformOpenapiRecordParamAppStructMapping.instance.openplatformOpenapiRecordParamDOsToOpenplatformOpenapiRecordParamVOs(openplatformOpenapiRecordParamDO);
		return MultiResponse.of(openplatformOpenapiRecordParamVOs);
	}
	/**
	 * 执行 开放平台开放接口调用记录参数 分页查询指令
	 * @param openplatformOpenapiRecordParamPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiRecordParamVO> execute(@Valid OpenplatformOpenapiRecordParamPageQueryCommand openplatformOpenapiRecordParamPageQueryCommand) {
		Page<OpenplatformOpenapiRecordParamDO> page = iOpenplatformOpenapiRecordParamService.listPage(openplatformOpenapiRecordParamPageQueryCommand);
		return OpenplatformOpenapiRecordParamAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台开放接口调用记录参数 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordParamVO> executeDetail(IdCommand detailCommand) {
		OpenplatformOpenapiRecordParamDO byId = iOpenplatformOpenapiRecordParamService.getById(detailCommand.getId());
		OpenplatformOpenapiRecordParamVO openplatformOpenapiRecordParamVO = OpenplatformOpenapiRecordParamAppStructMapping.instance.openplatformOpenapiRecordParamDOToOpenplatformOpenapiRecordParamVO(byId);
		return SingleResponse.of(openplatformOpenapiRecordParamVO);
	}
	/**
	 * 执行 开放平台开放接口调用记录id 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordParamVO> detailByOpenplatformOpenapiRecordId(IdCommand detailCommand) {
		OpenplatformOpenapiRecordParamDO byId = iOpenplatformOpenapiRecordParamService.getByOpenplatformOpenapiRecordId(detailCommand.getId());
		OpenplatformOpenapiRecordParamVO openplatformOpenapiRecordParamVO = OpenplatformOpenapiRecordParamAppStructMapping.instance.openplatformOpenapiRecordParamDOToOpenplatformOpenapiRecordParamVO(byId);
		return SingleResponse.of(openplatformOpenapiRecordParamVO);
	}

	@Autowired
	public void setIOpenplatformOpenapiRecordParamService(IOpenplatformOpenapiRecordParamService iOpenplatformOpenapiRecordParamService) {
		this.iOpenplatformOpenapiRecordParamService = iOpenplatformOpenapiRecordParamService;
	}
}
