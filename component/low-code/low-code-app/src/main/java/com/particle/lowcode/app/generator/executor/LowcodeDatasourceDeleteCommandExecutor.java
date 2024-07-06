package com.particle.lowcode.app.generator.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.lowcode.app.generator.structmapping.LowcodeDatasourceAppStructMapping;
import com.particle.lowcode.client.generator.dto.data.LowcodeDatasourceVO;
import com.particle.lowcode.domain.generator.LowcodeDatasource;
import com.particle.lowcode.domain.generator.LowcodeDatasourceId;
import com.particle.lowcode.domain.generator.gateway.LowcodeDatasourceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 低代码数据源 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class LowcodeDatasourceDeleteCommandExecutor  extends AbstractBaseExecutor {

	private LowcodeDatasourceGateway lowcodeDatasourceGateway;

	/**
	 * 执行 低代码数据源 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<LowcodeDatasourceVO> execute(@Valid IdCommand deleteCommand) {
		LowcodeDatasourceId lowcodeDatasourceId = LowcodeDatasourceId.of(deleteCommand.getId());
		LowcodeDatasource byId = lowcodeDatasourceGateway.getById(lowcodeDatasourceId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = lowcodeDatasourceGateway.delete(lowcodeDatasourceId,deleteCommand);
		if (delete) {
			return SingleResponse.of(LowcodeDatasourceAppStructMapping.instance.toLowcodeDatasourceVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param lowcodeDatasourceGateway
	 */
	@Autowired
	public void setLowcodeDatasourceGateway(LowcodeDatasourceGateway lowcodeDatasourceGateway) {
		this.lowcodeDatasourceGateway = lowcodeDatasourceGateway;
	}
}
