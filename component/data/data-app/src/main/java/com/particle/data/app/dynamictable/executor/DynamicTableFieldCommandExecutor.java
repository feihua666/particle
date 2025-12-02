package com.particle.data.app.dynamictable.executor;

import com.particle.data.domain.dynamictable.gateway.DynamicTableFieldGateway;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableFieldService;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 动态数据表格字段 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Component
@Validated
public class DynamicTableFieldCommandExecutor  extends AbstractBaseExecutor {

	private DynamicTableFieldGateway dynamicTableFieldGateway;
	private IDynamicTableFieldService iDynamicTableFieldService;
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
}
