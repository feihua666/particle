package com.particle.data.app.dynamictable.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.dynamictable.structmapping.DynamicTableFieldAppStructMapping;
import com.particle.data.client.dynamictable.dto.data.DynamicTableFieldVO;
import com.particle.data.domain.dynamictable.DynamicTableField;
import com.particle.data.domain.dynamictable.DynamicTableFieldId;
import com.particle.data.domain.dynamictable.gateway.DynamicTableFieldGateway;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableFieldService;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 动态数据表格字段 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Component
@Validated
public class DynamicTableFieldDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DynamicTableFieldGateway dynamicTableFieldGateway;
	private IDynamicTableFieldService iDynamicTableFieldService;
    private IDynamicTableService iDynamicTableService;
	/**
	 * 执行 动态数据表格字段 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DynamicTableFieldVO> execute(@Valid IdCommand deleteCommand) {
		DynamicTableFieldId dynamicTableFieldId = DynamicTableFieldId.of(deleteCommand.getId());
		DynamicTableField byId = dynamicTableFieldGateway.getById(dynamicTableFieldId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dynamicTableFieldGateway.delete(dynamicTableFieldId,deleteCommand);
		if (delete) {
            // 删除成功后，更新字段数量
            iDynamicTableService.updateDynamicTableFieldNum(byId.getDynamicTableId());
			return SingleResponse.of(DynamicTableFieldAppStructMapping.instance.toDynamicTableFieldVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dynamicTableFieldGateway
	 */
	@Autowired
	public void setDynamicTableFieldGateway(DynamicTableFieldGateway dynamicTableFieldGateway) {
		this.dynamicTableFieldGateway = dynamicTableFieldGateway;
	}
	@Autowired
	public void setIDynamicTableFieldService(IDynamicTableFieldService iDynamicTableFieldService) {
		this.iDynamicTableFieldService = iDynamicTableFieldService;
	}
    @Autowired
    public void setIDynamicTableService(IDynamicTableService iDynamicTableService) {
        this.iDynamicTableService = iDynamicTableService;
    }
}
