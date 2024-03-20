package com.particle.openplatform.app.doc.executor.representation;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocDirNameAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirNameQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirNameVO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocDirNameDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocDirNameService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirNamePageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 开放接口目录名称 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Component
@Validated
public class OpenplatformDocDirNameQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformDocDirNameService iOpenplatformDocDirNameService;

	/**
	 * 执行 开放接口目录名称 列表查询指令
	 * @param openplatformDocDirNameQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformDocDirNameVO> execute(@Valid OpenplatformDocDirNameQueryListCommand openplatformDocDirNameQueryListCommand) {
		List<OpenplatformDocDirNameDO> openplatformDocDirNameDO = iOpenplatformDocDirNameService.list(openplatformDocDirNameQueryListCommand);
		List<OpenplatformDocDirNameVO> openplatformDocDirNameVOs = OpenplatformDocDirNameAppStructMapping.instance.openplatformDocDirNameDOsToOpenplatformDocDirNameVOs(openplatformDocDirNameDO);
		return MultiResponse.of(openplatformDocDirNameVOs);
	}
	/**
	 * 执行 开放接口目录名称 分页查询指令
	 * @param openplatformDocDirNamePageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformDocDirNameVO> execute(@Valid OpenplatformDocDirNamePageQueryCommand openplatformDocDirNamePageQueryCommand) {
		Page<OpenplatformDocDirNameDO> page = iOpenplatformDocDirNameService.listPage(openplatformDocDirNamePageQueryCommand);
		return OpenplatformDocDirNameAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放接口目录名称 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocDirNameVO> executeDetail(IdCommand detailCommand) {
		OpenplatformDocDirNameDO byId = iOpenplatformDocDirNameService.getById(detailCommand.getId());
		OpenplatformDocDirNameVO openplatformDocDirNameVO = OpenplatformDocDirNameAppStructMapping.instance.openplatformDocDirNameDOToOpenplatformDocDirNameVO(byId);
		return SingleResponse.of(openplatformDocDirNameVO);
	}
	/**
	 * 执行 开放接口目录名称 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocDirNameVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformDocDirNameDO byId = iOpenplatformDocDirNameService.getById(detailForUpdateCommand.getId());
		OpenplatformDocDirNameVO openplatformDocDirNameVO = OpenplatformDocDirNameAppStructMapping.instance.openplatformDocDirNameDOToOpenplatformDocDirNameVO(byId);
		return SingleResponse.of(openplatformDocDirNameVO);
	}

	@Autowired
	public void setIOpenplatformDocDirNameService(IOpenplatformDocDirNameService iOpenplatformDocDirNameService) {
		this.iOpenplatformDocDirNameService = iOpenplatformDocDirNameService;
	}
}
