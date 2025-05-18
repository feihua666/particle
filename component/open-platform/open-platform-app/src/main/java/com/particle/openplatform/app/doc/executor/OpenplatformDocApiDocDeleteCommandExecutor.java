package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocAppStructMapping;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDoc;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocGateway;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocExampleCodeDO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocParamFieldDO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocResponseCodeDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocExampleCodeService;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocParamFieldService;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocResponseCodeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放接口文档 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Component
@Validated
public class OpenplatformDocApiDocDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocGateway openplatformDocApiDocGateway;
	private IOpenplatformDocApiDocParamFieldService iOpenplatformDocApiDocParamFieldService;
	private IOpenplatformDocApiDocExampleCodeService iOpenplatformDocApiDocExampleCodeService;
	private IOpenplatformDocApiDocResponseCodeService iOpenplatformDocApiDocResponseCodeService;

	/**
	 * 执行 开放接口文档 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformDocApiDocId openplatformDocApiDocId = OpenplatformDocApiDocId.of(deleteCommand.getId());
		OpenplatformDocApiDoc byId = openplatformDocApiDocGateway.getById(openplatformDocApiDocId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformDocApiDocGateway.delete(openplatformDocApiDocId,deleteCommand);
		if (delete) {
			// 将参数字段也删除
			iOpenplatformDocApiDocParamFieldService.deleteByColumn(openplatformDocApiDocId.getId(), OpenplatformDocApiDocParamFieldDO::getOpenplatformDocApiId);
			// 将响应码也删除
			iOpenplatformDocApiDocResponseCodeService.deleteByColumn(openplatformDocApiDocId.getId(), OpenplatformDocApiDocResponseCodeDO::getOpenplatformDocApiId);
			// 将示例也删除
			iOpenplatformDocApiDocExampleCodeService.deleteByColumn(openplatformDocApiDocId.getId(), OpenplatformDocApiDocExampleCodeDO::getOpenplatformDocApiId);
			return SingleResponse.of(OpenplatformDocApiDocAppStructMapping.instance.toOpenplatformDocApiDocVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocGateway(OpenplatformDocApiDocGateway openplatformDocApiDocGateway) {
		this.openplatformDocApiDocGateway = openplatformDocApiDocGateway;
	}
	@Autowired
	public void setiOpenplatformDocApiDocParamFieldService(IOpenplatformDocApiDocParamFieldService iOpenplatformDocApiDocParamFieldService) {
		this.iOpenplatformDocApiDocParamFieldService = iOpenplatformDocApiDocParamFieldService;
	}
	@Autowired
	public void setiOpenplatformDocApiDocExampleCodeService(IOpenplatformDocApiDocExampleCodeService iOpenplatformDocApiDocExampleCodeService) {
		this.iOpenplatformDocApiDocExampleCodeService = iOpenplatformDocApiDocExampleCodeService;
	}
	@Autowired
	public void setiOpenplatformDocApiDocResponseCodeService(IOpenplatformDocApiDocResponseCodeService iOpenplatformDocApiDocResponseCodeService) {
		this.iOpenplatformDocApiDocResponseCodeService = iOpenplatformDocApiDocResponseCodeService;
	}
}
