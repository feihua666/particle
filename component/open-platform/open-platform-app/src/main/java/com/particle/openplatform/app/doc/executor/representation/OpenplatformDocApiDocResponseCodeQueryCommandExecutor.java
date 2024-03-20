package com.particle.openplatform.app.doc.executor.representation;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocResponseCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocResponseCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocResponseCodeVO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocResponseCodeDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocResponseCodeService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocResponseCodePageQueryCommand;
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
 * 开放接口文档响应码 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-03-18 14:44:43
 */
@Component
@Validated
public class OpenplatformDocApiDocResponseCodeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformDocApiDocResponseCodeService iOpenplatformDocApiDocResponseCodeService;

	/**
	 * 执行 开放接口文档响应码 列表查询指令
	 * @param openplatformDocApiDocResponseCodeQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformDocApiDocResponseCodeVO> execute(@Valid OpenplatformDocApiDocResponseCodeQueryListCommand openplatformDocApiDocResponseCodeQueryListCommand) {
		List<OpenplatformDocApiDocResponseCodeDO> openplatformDocApiDocResponseCodeDO = iOpenplatformDocApiDocResponseCodeService.list(openplatformDocApiDocResponseCodeQueryListCommand);
		List<OpenplatformDocApiDocResponseCodeVO> openplatformDocApiDocResponseCodeVOs = OpenplatformDocApiDocResponseCodeAppStructMapping.instance.openplatformDocApiDocResponseCodeDOsToOpenplatformDocApiDocResponseCodeVOs(openplatformDocApiDocResponseCodeDO);
		return MultiResponse.of(openplatformDocApiDocResponseCodeVOs);
	}
	/**
	 * 执行 开放接口文档响应码 分页查询指令
	 * @param openplatformDocApiDocResponseCodePageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocResponseCodeVO> execute(@Valid OpenplatformDocApiDocResponseCodePageQueryCommand openplatformDocApiDocResponseCodePageQueryCommand) {
		Page<OpenplatformDocApiDocResponseCodeDO> page = iOpenplatformDocApiDocResponseCodeService.listPage(openplatformDocApiDocResponseCodePageQueryCommand);
		return OpenplatformDocApiDocResponseCodeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放接口文档响应码 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocResponseCodeVO> executeDetail(IdCommand detailCommand) {
		OpenplatformDocApiDocResponseCodeDO byId = iOpenplatformDocApiDocResponseCodeService.getById(detailCommand.getId());
		OpenplatformDocApiDocResponseCodeVO openplatformDocApiDocResponseCodeVO = OpenplatformDocApiDocResponseCodeAppStructMapping.instance.openplatformDocApiDocResponseCodeDOToOpenplatformDocApiDocResponseCodeVO(byId);
		return SingleResponse.of(openplatformDocApiDocResponseCodeVO);
	}
	/**
	 * 执行 开放接口文档响应码 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocResponseCodeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformDocApiDocResponseCodeDO byId = iOpenplatformDocApiDocResponseCodeService.getById(detailForUpdateCommand.getId());
		OpenplatformDocApiDocResponseCodeVO openplatformDocApiDocResponseCodeVO = OpenplatformDocApiDocResponseCodeAppStructMapping.instance.openplatformDocApiDocResponseCodeDOToOpenplatformDocApiDocResponseCodeVO(byId);
		return SingleResponse.of(openplatformDocApiDocResponseCodeVO);
	}

	@Autowired
	public void setIOpenplatformDocApiDocResponseCodeService(IOpenplatformDocApiDocResponseCodeService iOpenplatformDocApiDocResponseCodeService) {
		this.iOpenplatformDocApiDocResponseCodeService = iOpenplatformDocApiDocResponseCodeService;
	}
}
