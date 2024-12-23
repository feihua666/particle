package com.particle.openplatform.app.doc.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateExampleCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateExampleCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateExampleCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateExampleCodeVO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateExampleCodeDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocTemplateExampleCodeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放接口文档模板示例代码 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-03-18 17:04:26
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateExampleCodeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformDocApiDocTemplateExampleCodeService iOpenplatformDocApiDocTemplateExampleCodeService;

	/**
	 * 执行 开放接口文档模板示例代码 列表查询指令
	 * @param openplatformDocApiDocTemplateExampleCodeQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformDocApiDocTemplateExampleCodeVO> execute(@Valid OpenplatformDocApiDocTemplateExampleCodeQueryListCommand openplatformDocApiDocTemplateExampleCodeQueryListCommand) {
		List<OpenplatformDocApiDocTemplateExampleCodeDO> openplatformDocApiDocTemplateExampleCodeDO = iOpenplatformDocApiDocTemplateExampleCodeService.list(openplatformDocApiDocTemplateExampleCodeQueryListCommand);
		List<OpenplatformDocApiDocTemplateExampleCodeVO> openplatformDocApiDocTemplateExampleCodeVOs = OpenplatformDocApiDocTemplateExampleCodeAppStructMapping.instance.openplatformDocApiDocTemplateExampleCodeDOsToOpenplatformDocApiDocTemplateExampleCodeVOs(openplatformDocApiDocTemplateExampleCodeDO);
		return MultiResponse.of(openplatformDocApiDocTemplateExampleCodeVOs);
	}
	/**
	 * 执行 开放接口文档模板示例代码 分页查询指令
	 * @param openplatformDocApiDocTemplateExampleCodePageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocTemplateExampleCodeVO> execute(@Valid OpenplatformDocApiDocTemplateExampleCodePageQueryCommand openplatformDocApiDocTemplateExampleCodePageQueryCommand) {
		Page<OpenplatformDocApiDocTemplateExampleCodeDO> page = iOpenplatformDocApiDocTemplateExampleCodeService.listPage(openplatformDocApiDocTemplateExampleCodePageQueryCommand);
		return OpenplatformDocApiDocTemplateExampleCodeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放接口文档模板示例代码 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> executeDetail(IdCommand detailCommand) {
		OpenplatformDocApiDocTemplateExampleCodeDO byId = iOpenplatformDocApiDocTemplateExampleCodeService.getById(detailCommand.getId());
		OpenplatformDocApiDocTemplateExampleCodeVO openplatformDocApiDocTemplateExampleCodeVO = OpenplatformDocApiDocTemplateExampleCodeAppStructMapping.instance.openplatformDocApiDocTemplateExampleCodeDOToOpenplatformDocApiDocTemplateExampleCodeVO(byId);
		return SingleResponse.of(openplatformDocApiDocTemplateExampleCodeVO);
	}
	/**
	 * 执行 开放接口文档模板示例代码 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformDocApiDocTemplateExampleCodeDO byId = iOpenplatformDocApiDocTemplateExampleCodeService.getById(detailForUpdateCommand.getId());
		OpenplatformDocApiDocTemplateExampleCodeVO openplatformDocApiDocTemplateExampleCodeVO = OpenplatformDocApiDocTemplateExampleCodeAppStructMapping.instance.openplatformDocApiDocTemplateExampleCodeDOToOpenplatformDocApiDocTemplateExampleCodeVO(byId);
		return SingleResponse.of(openplatformDocApiDocTemplateExampleCodeVO);
	}

	@Autowired
	public void setIOpenplatformDocApiDocTemplateExampleCodeService(IOpenplatformDocApiDocTemplateExampleCodeService iOpenplatformDocApiDocTemplateExampleCodeService) {
		this.iOpenplatformDocApiDocTemplateExampleCodeService = iOpenplatformDocApiDocTemplateExampleCodeService;
	}
}
