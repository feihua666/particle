package com.particle.openplatform.app.doc.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateResponseCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateResponseCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateResponseCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateResponseCodeVO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateResponseCodeDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocTemplateResponseCodeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放接口文档模板响应码 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-03-18 16:49:10
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateResponseCodeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformDocApiDocTemplateResponseCodeService iOpenplatformDocApiDocTemplateResponseCodeService;

	/**
	 * 执行 开放接口文档模板响应码 列表查询指令
	 * @param openplatformDocApiDocTemplateResponseCodeQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformDocApiDocTemplateResponseCodeVO> execute(@Valid OpenplatformDocApiDocTemplateResponseCodeQueryListCommand openplatformDocApiDocTemplateResponseCodeQueryListCommand) {
		List<OpenplatformDocApiDocTemplateResponseCodeDO> openplatformDocApiDocTemplateResponseCodeDO = iOpenplatformDocApiDocTemplateResponseCodeService.list(openplatformDocApiDocTemplateResponseCodeQueryListCommand);
		List<OpenplatformDocApiDocTemplateResponseCodeVO> openplatformDocApiDocTemplateResponseCodeVOs = OpenplatformDocApiDocTemplateResponseCodeAppStructMapping.instance.openplatformDocApiDocTemplateResponseCodeDOsToOpenplatformDocApiDocTemplateResponseCodeVOs(openplatformDocApiDocTemplateResponseCodeDO);
		return MultiResponse.of(openplatformDocApiDocTemplateResponseCodeVOs);
	}
	/**
	 * 执行 开放接口文档模板响应码 分页查询指令
	 * @param openplatformDocApiDocTemplateResponseCodePageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocTemplateResponseCodeVO> execute(@Valid OpenplatformDocApiDocTemplateResponseCodePageQueryCommand openplatformDocApiDocTemplateResponseCodePageQueryCommand) {
		Page<OpenplatformDocApiDocTemplateResponseCodeDO> page = iOpenplatformDocApiDocTemplateResponseCodeService.listPage(openplatformDocApiDocTemplateResponseCodePageQueryCommand);
		return OpenplatformDocApiDocTemplateResponseCodeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放接口文档模板响应码 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateResponseCodeVO> executeDetail(IdCommand detailCommand) {
		OpenplatformDocApiDocTemplateResponseCodeDO byId = iOpenplatformDocApiDocTemplateResponseCodeService.getById(detailCommand.getId());
		OpenplatformDocApiDocTemplateResponseCodeVO openplatformDocApiDocTemplateResponseCodeVO = OpenplatformDocApiDocTemplateResponseCodeAppStructMapping.instance.openplatformDocApiDocTemplateResponseCodeDOToOpenplatformDocApiDocTemplateResponseCodeVO(byId);
		return SingleResponse.of(openplatformDocApiDocTemplateResponseCodeVO);
	}
	/**
	 * 执行 开放接口文档模板响应码 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateResponseCodeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformDocApiDocTemplateResponseCodeDO byId = iOpenplatformDocApiDocTemplateResponseCodeService.getById(detailForUpdateCommand.getId());
		OpenplatformDocApiDocTemplateResponseCodeVO openplatformDocApiDocTemplateResponseCodeVO = OpenplatformDocApiDocTemplateResponseCodeAppStructMapping.instance.openplatformDocApiDocTemplateResponseCodeDOToOpenplatformDocApiDocTemplateResponseCodeVO(byId);
		return SingleResponse.of(openplatformDocApiDocTemplateResponseCodeVO);
	}

	@Autowired
	public void setIOpenplatformDocApiDocTemplateResponseCodeService(IOpenplatformDocApiDocTemplateResponseCodeService iOpenplatformDocApiDocTemplateResponseCodeService) {
		this.iOpenplatformDocApiDocTemplateResponseCodeService = iOpenplatformDocApiDocTemplateResponseCodeService;
	}
}
