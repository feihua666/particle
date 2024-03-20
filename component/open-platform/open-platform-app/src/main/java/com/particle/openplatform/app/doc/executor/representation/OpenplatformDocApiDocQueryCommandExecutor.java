package com.particle.openplatform.app.doc.executor.representation;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocVO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocPageQueryCommand;
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
 * 开放接口文档 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Component
@Validated
public class OpenplatformDocApiDocQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformDocApiDocService iOpenplatformDocApiDocService;

	/**
	 * 执行 开放接口文档 列表查询指令
	 * @param openplatformDocApiDocQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformDocApiDocVO> execute(@Valid OpenplatformDocApiDocQueryListCommand openplatformDocApiDocQueryListCommand) {
		List<OpenplatformDocApiDocDO> openplatformDocApiDocDO = iOpenplatformDocApiDocService.list(openplatformDocApiDocQueryListCommand);
		List<OpenplatformDocApiDocVO> openplatformDocApiDocVOs = OpenplatformDocApiDocAppStructMapping.instance.openplatformDocApiDocDOsToOpenplatformDocApiDocVOs(openplatformDocApiDocDO);
		return MultiResponse.of(openplatformDocApiDocVOs);
	}
	/**
	 * 执行 开放接口文档 分页查询指令
	 * @param openplatformDocApiDocPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocVO> execute(@Valid OpenplatformDocApiDocPageQueryCommand openplatformDocApiDocPageQueryCommand) {
		Page<OpenplatformDocApiDocDO> page = iOpenplatformDocApiDocService.listPage(openplatformDocApiDocPageQueryCommand);
		return OpenplatformDocApiDocAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放接口文档 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocVO> executeDetail(IdCommand detailCommand) {
		OpenplatformDocApiDocDO byId = iOpenplatformDocApiDocService.getById(detailCommand.getId());
		OpenplatformDocApiDocVO openplatformDocApiDocVO = OpenplatformDocApiDocAppStructMapping.instance.openplatformDocApiDocDOToOpenplatformDocApiDocVO(byId);
		return SingleResponse.of(openplatformDocApiDocVO);
	}
	/**
	 * 执行 开放接口文档 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformDocApiDocDO byId = iOpenplatformDocApiDocService.getById(detailForUpdateCommand.getId());
		OpenplatformDocApiDocVO openplatformDocApiDocVO = OpenplatformDocApiDocAppStructMapping.instance.openplatformDocApiDocDOToOpenplatformDocApiDocVO(byId);
		return SingleResponse.of(openplatformDocApiDocVO);
	}

	@Autowired
	public void setIOpenplatformDocApiDocService(IOpenplatformDocApiDocService iOpenplatformDocApiDocService) {
		this.iOpenplatformDocApiDocService = iOpenplatformDocApiDocService;
	}
}
