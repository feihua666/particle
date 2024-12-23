package com.particle.openplatform.app.providerrecord.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.providerrecord.structmapping.OpenplatformProviderRecordParamAppStructMapping;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordParamPageQueryCommand;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordParamQueryListCommand;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordParamVO;
import com.particle.openplatform.infrastructure.providerrecord.dos.OpenplatformProviderRecordParamDO;
import com.particle.openplatform.infrastructure.providerrecord.service.IOpenplatformProviderRecordParamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放平台开放接口供应商调用记录参数 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-08-16 16:18:54
 */
@Component
@Validated
public class OpenplatformProviderRecordParamQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformProviderRecordParamService iOpenplatformProviderRecordParamService;

	/**
	 * 执行 开放平台开放接口供应商调用记录参数 列表查询指令
	 * @param openplatformProviderRecordParamQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformProviderRecordParamVO> execute(@Valid OpenplatformProviderRecordParamQueryListCommand openplatformProviderRecordParamQueryListCommand) {
		List<OpenplatformProviderRecordParamDO> openplatformProviderRecordParamDO = iOpenplatformProviderRecordParamService.list(openplatformProviderRecordParamQueryListCommand);
		List<OpenplatformProviderRecordParamVO> openplatformProviderRecordParamVOs = OpenplatformProviderRecordParamAppStructMapping.instance.openplatformProviderRecordParamDOsToOpenplatformProviderRecordParamVOs(openplatformProviderRecordParamDO);
		return MultiResponse.of(openplatformProviderRecordParamVOs);
	}
	/**
	 * 执行 开放平台开放接口供应商调用记录参数 分页查询指令
	 * @param openplatformProviderRecordParamPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformProviderRecordParamVO> execute(@Valid OpenplatformProviderRecordParamPageQueryCommand openplatformProviderRecordParamPageQueryCommand) {
		Page<OpenplatformProviderRecordParamDO> page = iOpenplatformProviderRecordParamService.listPage(openplatformProviderRecordParamPageQueryCommand);
		return OpenplatformProviderRecordParamAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台开放接口供应商调用记录参数 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordParamVO> executeDetail(IdCommand detailCommand) {
		OpenplatformProviderRecordParamDO byId = iOpenplatformProviderRecordParamService.getById(detailCommand.getId());
		OpenplatformProviderRecordParamVO openplatformProviderRecordParamVO = OpenplatformProviderRecordParamAppStructMapping.instance.openplatformProviderRecordParamDOToOpenplatformProviderRecordParamVO(byId);
		return SingleResponse.of(openplatformProviderRecordParamVO);
	}
	/**
	 * 执行 开放平台开放接口供应商调用记录参数 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordParamVO> detailByOpenplatformProviderRecordId(IdCommand detailCommand) {
		OpenplatformProviderRecordParamDO byId = iOpenplatformProviderRecordParamService.getByOpenplatformProviderRecordId(detailCommand.getId());
		OpenplatformProviderRecordParamVO openplatformProviderRecordParamVO = OpenplatformProviderRecordParamAppStructMapping.instance.openplatformProviderRecordParamDOToOpenplatformProviderRecordParamVO(byId);
		return SingleResponse.of(openplatformProviderRecordParamVO);
	}
	/**
	 * 执行 开放平台开放接口供应商调用记录参数 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordParamVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformProviderRecordParamDO byId = iOpenplatformProviderRecordParamService.getById(detailForUpdateCommand.getId());
		OpenplatformProviderRecordParamVO openplatformProviderRecordParamVO = OpenplatformProviderRecordParamAppStructMapping.instance.openplatformProviderRecordParamDOToOpenplatformProviderRecordParamVO(byId);
		return SingleResponse.of(openplatformProviderRecordParamVO);
	}

	@Autowired
	public void setIOpenplatformProviderRecordParamService(IOpenplatformProviderRecordParamService iOpenplatformProviderRecordParamService) {
		this.iOpenplatformProviderRecordParamService = iOpenplatformProviderRecordParamService;
	}
}
