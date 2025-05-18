package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateAppStructMapping;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplate;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateGateway;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateExampleCodeDO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateParamFieldDO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateResponseCodeDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocTemplateExampleCodeService;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocTemplateParamFieldService;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocTemplateResponseCodeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放接口文档模板 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocTemplateGateway openplatformDocApiDocTemplateGateway;
	private IOpenplatformDocApiDocTemplateParamFieldService iOpenplatformDocApiDocTemplateParamFieldService;
	private IOpenplatformDocApiDocTemplateExampleCodeService iOpenplatformDocApiDocTemplateExampleCodeService;
	private IOpenplatformDocApiDocTemplateResponseCodeService iOpenplatformDocApiDocTemplateResponseCodeService;

	/**
	 * 执行 开放接口文档模板 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformDocApiDocTemplateId openplatformDocApiDocTemplateId = OpenplatformDocApiDocTemplateId.of(deleteCommand.getId());
		OpenplatformDocApiDocTemplate byId = openplatformDocApiDocTemplateGateway.getById(openplatformDocApiDocTemplateId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformDocApiDocTemplateGateway.delete(openplatformDocApiDocTemplateId,deleteCommand);
		if (delete) {
			// 将参数字段删除
			iOpenplatformDocApiDocTemplateParamFieldService.deleteByColumn(openplatformDocApiDocTemplateId.getId(), OpenplatformDocApiDocTemplateParamFieldDO::getOpenplatformDocApiDocTemplateId);
			// 将示例代码删除
			iOpenplatformDocApiDocTemplateExampleCodeService.deleteByColumn(openplatformDocApiDocTemplateId.getId(), OpenplatformDocApiDocTemplateExampleCodeDO::getOpenplatformDocApiDocTemplateId);
			// 将响应代码删除
			iOpenplatformDocApiDocTemplateResponseCodeService.deleteByColumn(openplatformDocApiDocTemplateId.getId(), OpenplatformDocApiDocTemplateResponseCodeDO::getOpenplatformDocApiDocTemplateId);
			return SingleResponse.of(OpenplatformDocApiDocTemplateAppStructMapping.instance.toOpenplatformDocApiDocTemplateVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocTemplateGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocTemplateGateway(OpenplatformDocApiDocTemplateGateway openplatformDocApiDocTemplateGateway) {
		this.openplatformDocApiDocTemplateGateway = openplatformDocApiDocTemplateGateway;
	}
	@Autowired
	public void setiOpenplatformDocApiDocTemplateParamFieldService(IOpenplatformDocApiDocTemplateParamFieldService iOpenplatformDocApiDocTemplateParamFieldService) {
		this.iOpenplatformDocApiDocTemplateParamFieldService = iOpenplatformDocApiDocTemplateParamFieldService;
	}
	@Autowired
	public void setiOpenplatformDocApiDocTemplateExampleCodeService(IOpenplatformDocApiDocTemplateExampleCodeService iOpenplatformDocApiDocTemplateExampleCodeService) {
		this.iOpenplatformDocApiDocTemplateExampleCodeService = iOpenplatformDocApiDocTemplateExampleCodeService;
	}
	@Autowired
	public void setiOpenplatformDocApiDocTemplateResponseCodeService(IOpenplatformDocApiDocTemplateResponseCodeService iOpenplatformDocApiDocTemplateResponseCodeService) {
		this.iOpenplatformDocApiDocTemplateResponseCodeService = iOpenplatformDocApiDocTemplateResponseCodeService;
	}
}
