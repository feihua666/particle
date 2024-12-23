package com.particle.openplatform.app.doc.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocExampleCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocExampleCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocExampleCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocExampleCodeVO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocExampleCodeDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocExampleCodeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放接口文档示例代码 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@Component
@Validated
public class OpenplatformDocApiDocExampleCodeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformDocApiDocExampleCodeService iOpenplatformDocApiDocExampleCodeService;

	/**
	 * 执行 开放接口文档示例代码 列表查询指令
	 * @param openplatformDocApiDocExampleCodeQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformDocApiDocExampleCodeVO> execute(@Valid OpenplatformDocApiDocExampleCodeQueryListCommand openplatformDocApiDocExampleCodeQueryListCommand) {
		List<OpenplatformDocApiDocExampleCodeDO> openplatformDocApiDocExampleCodeDO = iOpenplatformDocApiDocExampleCodeService.list(openplatformDocApiDocExampleCodeQueryListCommand);
		List<OpenplatformDocApiDocExampleCodeVO> openplatformDocApiDocExampleCodeVOs = OpenplatformDocApiDocExampleCodeAppStructMapping.instance.openplatformDocApiDocExampleCodeDOsToOpenplatformDocApiDocExampleCodeVOs(openplatformDocApiDocExampleCodeDO);
		return MultiResponse.of(openplatformDocApiDocExampleCodeVOs);
	}
	/**
	 * 执行 开放接口文档示例代码 分页查询指令
	 * @param openplatformDocApiDocExampleCodePageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocExampleCodeVO> execute(@Valid OpenplatformDocApiDocExampleCodePageQueryCommand openplatformDocApiDocExampleCodePageQueryCommand) {
		Page<OpenplatformDocApiDocExampleCodeDO> page = iOpenplatformDocApiDocExampleCodeService.listPage(openplatformDocApiDocExampleCodePageQueryCommand);
		return OpenplatformDocApiDocExampleCodeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放接口文档示例代码 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocExampleCodeVO> executeDetail(IdCommand detailCommand) {
		OpenplatformDocApiDocExampleCodeDO byId = iOpenplatformDocApiDocExampleCodeService.getById(detailCommand.getId());
		OpenplatformDocApiDocExampleCodeVO openplatformDocApiDocExampleCodeVO = OpenplatformDocApiDocExampleCodeAppStructMapping.instance.openplatformDocApiDocExampleCodeDOToOpenplatformDocApiDocExampleCodeVO(byId);
		return SingleResponse.of(openplatformDocApiDocExampleCodeVO);
	}
	/**
	 * 执行 开放接口文档示例代码 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocExampleCodeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformDocApiDocExampleCodeDO byId = iOpenplatformDocApiDocExampleCodeService.getById(detailForUpdateCommand.getId());
		OpenplatformDocApiDocExampleCodeVO openplatformDocApiDocExampleCodeVO = OpenplatformDocApiDocExampleCodeAppStructMapping.instance.openplatformDocApiDocExampleCodeDOToOpenplatformDocApiDocExampleCodeVO(byId);
		return SingleResponse.of(openplatformDocApiDocExampleCodeVO);
	}

	@Autowired
	public void setIOpenplatformDocApiDocExampleCodeService(IOpenplatformDocApiDocExampleCodeService iOpenplatformDocApiDocExampleCodeService) {
		this.iOpenplatformDocApiDocExampleCodeService = iOpenplatformDocApiDocExampleCodeService;
	}
}
