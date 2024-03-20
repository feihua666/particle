package com.particle.openplatform.app.doc.executor.representation;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocDirAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirVO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocDirDO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocDirNameDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocDirNameService;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocDirService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirPageQueryCommand;
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
 * 开放接口目录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@Component
@Validated
public class OpenplatformDocDirQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformDocDirService iOpenplatformDocDirService;
	private IOpenplatformDocDirNameService iOpenplatformDocDirNameService;

	/**
	 * 执行 开放接口目录 列表查询指令
	 * @param openplatformDocDirQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformDocDirVO> execute(@Valid OpenplatformDocDirQueryListCommand openplatformDocDirQueryListCommand) {

		if (openplatformDocDirQueryListCommand.getOpenplatformDocDirNameCode() != null) {
			OpenplatformDocDirNameDO byCode = iOpenplatformDocDirNameService.getByCode(openplatformDocDirQueryListCommand.getOpenplatformDocDirNameCode());
			if (byCode == null) {
				return MultiResponse.buildSuccess();
			}else {
				openplatformDocDirQueryListCommand.setOpenplatformDocDirNameId(byCode.getId());
			}

		}

		List<OpenplatformDocDirDO> openplatformDocDirDO = iOpenplatformDocDirService.list(openplatformDocDirQueryListCommand);
		List<OpenplatformDocDirVO> openplatformDocDirVOs = OpenplatformDocDirAppStructMapping.instance.openplatformDocDirDOsToOpenplatformDocDirVOs(openplatformDocDirDO);
		return MultiResponse.of(openplatformDocDirVOs);
	}
	/**
	 * 执行 开放接口目录 分页查询指令
	 * @param openplatformDocDirPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformDocDirVO> execute(@Valid OpenplatformDocDirPageQueryCommand openplatformDocDirPageQueryCommand) {
		if (openplatformDocDirPageQueryCommand.getOpenplatformDocDirNameCode() != null) {
			OpenplatformDocDirNameDO byCode = iOpenplatformDocDirNameService.getByCode(openplatformDocDirPageQueryCommand.getOpenplatformDocDirNameCode());
			if (byCode == null) {
				return PageResponse.buildSuccess();
			}else {
				openplatformDocDirPageQueryCommand.setOpenplatformDocDirNameId(byCode.getId());
			}

		}
		Page<OpenplatformDocDirDO> page = iOpenplatformDocDirService.listPage(openplatformDocDirPageQueryCommand);
		return OpenplatformDocDirAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放接口目录 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocDirVO> executeDetail(IdCommand detailCommand) {
		OpenplatformDocDirDO byId = iOpenplatformDocDirService.getById(detailCommand.getId());
		OpenplatformDocDirVO openplatformDocDirVO = OpenplatformDocDirAppStructMapping.instance.openplatformDocDirDOToOpenplatformDocDirVO(byId);
		return SingleResponse.of(openplatformDocDirVO);
	}
	/**
	 * 执行 开放接口目录 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocDirVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformDocDirDO byId = iOpenplatformDocDirService.getById(detailForUpdateCommand.getId());
		OpenplatformDocDirVO openplatformDocDirVO = OpenplatformDocDirAppStructMapping.instance.openplatformDocDirDOToOpenplatformDocDirVO(byId);
		return SingleResponse.of(openplatformDocDirVO);
	}

	@Autowired
	public void setIOpenplatformDocDirService(IOpenplatformDocDirService iOpenplatformDocDirService) {
		this.iOpenplatformDocDirService = iOpenplatformDocDirService;
	}
	@Autowired
	public void setIOpenplatformDocDirNameService(IOpenplatformDocDirNameService iOpenplatformDocDirNameService) {
		this.iOpenplatformDocDirNameService = iOpenplatformDocDirNameService;
	}
}
