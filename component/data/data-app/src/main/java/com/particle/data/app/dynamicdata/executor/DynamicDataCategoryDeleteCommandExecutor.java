package com.particle.data.app.dynamicdata.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.dynamicdata.structmapping.DynamicDataCategoryAppStructMapping;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataCategoryVO;
import com.particle.data.domain.dynamicdata.DynamicDataCategory;
import com.particle.data.domain.dynamicdata.DynamicDataCategoryId;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataCategoryGateway;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataCategoryService;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataCategoryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 动态数据分类 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Component
@Validated
public class DynamicDataCategoryDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DynamicDataCategoryGateway dynamicDataCategoryGateway;
	private IDynamicDataCategoryService iDynamicDataCategoryService;

	/**
	 * 执行 动态数据分类 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DynamicDataCategoryVO> execute(@Valid IdCommand deleteCommand) {
		DynamicDataCategoryId dynamicDataCategoryId = DynamicDataCategoryId.of(deleteCommand.getId());
		DynamicDataCategory byId = dynamicDataCategoryGateway.getById(dynamicDataCategoryId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dynamicDataCategoryGateway.delete(dynamicDataCategoryId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DynamicDataCategoryAppStructMapping.instance.toDynamicDataCategoryVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dynamicDataCategoryGateway
	 */
	@Autowired
	public void setDynamicDataCategoryGateway(DynamicDataCategoryGateway dynamicDataCategoryGateway) {
		this.dynamicDataCategoryGateway = dynamicDataCategoryGateway;
	}
	@Autowired
	public void setIDynamicDataCategoryService(IDynamicDataCategoryService iDynamicDataCategoryService) {
		this.iDynamicDataCategoryService = iDynamicDataCategoryService;
	}
}
