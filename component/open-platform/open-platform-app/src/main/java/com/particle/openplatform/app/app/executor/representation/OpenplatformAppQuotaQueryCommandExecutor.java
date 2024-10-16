package com.particle.openplatform.app.app.executor.representation;

import com.particle.openplatform.app.app.structmapping.OpenplatformAppQuotaAppStructMapping;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppQuotaQueryListCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppQuotaVO;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppQuotaDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppQuotaService;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppQuotaPageQueryCommand;
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
 * 开放平台应用额度 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Component
@Validated
public class OpenplatformAppQuotaQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformAppQuotaService iOpenplatformAppQuotaService;

	/**
	 * 执行 开放平台应用额度 列表查询指令
	 * @param openplatformAppQuotaQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformAppQuotaVO> execute(@Valid OpenplatformAppQuotaQueryListCommand openplatformAppQuotaQueryListCommand) {
		List<OpenplatformAppQuotaDO> openplatformAppQuotaDO = iOpenplatformAppQuotaService.list(openplatformAppQuotaQueryListCommand);
		List<OpenplatformAppQuotaVO> openplatformAppQuotaVOs = OpenplatformAppQuotaAppStructMapping.instance.openplatformAppQuotaDOsToOpenplatformAppQuotaVOs(openplatformAppQuotaDO);
		return MultiResponse.of(openplatformAppQuotaVOs);
	}
	/**
	 * 执行 开放平台应用额度 分页查询指令
	 * @param openplatformAppQuotaPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformAppQuotaVO> execute(@Valid OpenplatformAppQuotaPageQueryCommand openplatformAppQuotaPageQueryCommand) {
		Page<OpenplatformAppQuotaDO> page = iOpenplatformAppQuotaService.listPage(openplatformAppQuotaPageQueryCommand);
		return OpenplatformAppQuotaAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台应用额度 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformAppQuotaVO> executeDetail(IdCommand detailCommand) {
		OpenplatformAppQuotaDO byId = iOpenplatformAppQuotaService.getById(detailCommand.getId());
		OpenplatformAppQuotaVO openplatformAppQuotaVO = OpenplatformAppQuotaAppStructMapping.instance.openplatformAppQuotaDOToOpenplatformAppQuotaVO(byId);
		return SingleResponse.of(openplatformAppQuotaVO);
	}
	/**
	 * 执行 开放平台应用额度 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformAppQuotaVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformAppQuotaDO byId = iOpenplatformAppQuotaService.getById(detailForUpdateCommand.getId());
		OpenplatformAppQuotaVO openplatformAppQuotaVO = OpenplatformAppQuotaAppStructMapping.instance.openplatformAppQuotaDOToOpenplatformAppQuotaVO(byId);
		return SingleResponse.of(openplatformAppQuotaVO);
	}


	@Autowired
	public void setIOpenplatformAppQuotaService(IOpenplatformAppQuotaService iOpenplatformAppQuotaService) {
		this.iOpenplatformAppQuotaService = iOpenplatformAppQuotaService;
	}
}
