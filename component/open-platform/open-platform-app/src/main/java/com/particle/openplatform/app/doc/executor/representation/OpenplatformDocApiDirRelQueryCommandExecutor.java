package com.particle.openplatform.app.doc.executor.representation;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDirRelAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDirRelQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDirRelVO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDirRelDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDirRelService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDirRelPageQueryCommand;
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
 * 开放接口文档接口与目录关系 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Component
@Validated
public class OpenplatformDocApiDirRelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformDocApiDirRelService iOpenplatformDocApiDirRelService;

	/**
	 * 执行 开放接口文档接口与目录关系 列表查询指令
	 * @param openplatformDocApiDirRelQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformDocApiDirRelVO> execute(@Valid OpenplatformDocApiDirRelQueryListCommand openplatformDocApiDirRelQueryListCommand) {
		List<OpenplatformDocApiDirRelDO> openplatformDocApiDirRelDO = iOpenplatformDocApiDirRelService.list(openplatformDocApiDirRelQueryListCommand);
		List<OpenplatformDocApiDirRelVO> openplatformDocApiDirRelVOs = OpenplatformDocApiDirRelAppStructMapping.instance.openplatformDocApiDirRelDOsToOpenplatformDocApiDirRelVOs(openplatformDocApiDirRelDO);
		return MultiResponse.of(openplatformDocApiDirRelVOs);
	}
	/**
	 * 执行 开放接口文档接口与目录关系 分页查询指令
	 * @param openplatformDocApiDirRelPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDirRelVO> execute(@Valid OpenplatformDocApiDirRelPageQueryCommand openplatformDocApiDirRelPageQueryCommand) {
		Page<OpenplatformDocApiDirRelDO> page = iOpenplatformDocApiDirRelService.listPage(openplatformDocApiDirRelPageQueryCommand);
		return OpenplatformDocApiDirRelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放接口文档接口与目录关系 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDirRelVO> executeDetail(IdCommand detailCommand) {
		OpenplatformDocApiDirRelDO byId = iOpenplatformDocApiDirRelService.getById(detailCommand.getId());
		OpenplatformDocApiDirRelVO openplatformDocApiDirRelVO = OpenplatformDocApiDirRelAppStructMapping.instance.openplatformDocApiDirRelDOToOpenplatformDocApiDirRelVO(byId);
		return SingleResponse.of(openplatformDocApiDirRelVO);
	}
	/**
	 * 执行 开放接口文档接口与目录关系 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDirRelVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformDocApiDirRelDO byId = iOpenplatformDocApiDirRelService.getById(detailForUpdateCommand.getId());
		OpenplatformDocApiDirRelVO openplatformDocApiDirRelVO = OpenplatformDocApiDirRelAppStructMapping.instance.openplatformDocApiDirRelDOToOpenplatformDocApiDirRelVO(byId);
		return SingleResponse.of(openplatformDocApiDirRelVO);
	}

	@Autowired
	public void setIOpenplatformDocApiDirRelService(IOpenplatformDocApiDirRelService iOpenplatformDocApiDirRelService) {
		this.iOpenplatformDocApiDirRelService = iOpenplatformDocApiDirRelService;
	}
}
