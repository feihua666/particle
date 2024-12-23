package com.particle.dict.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dict.app.structmapping.DictAppStructMapping;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.dict.domain.Dict;
import com.particle.dict.domain.DictId;
import com.particle.dict.domain.gateway.DictGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 字典 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Component
@Validated
public class DictDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DictGateway dictGateway;

	/**
	 * 执行 字典 删除指令
	 * @param dictDeleteCommand
	 * @return
	 */
	public SingleResponse<DictVO> execute(@Valid IdCommand dictDeleteCommand) {
		DictId dictId = DictId.of(dictDeleteCommand.getId());
		Dict byId = dictGateway.getById(dictId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dictGateway.delete(dictId,dictDeleteCommand);
		if (delete) {
			return SingleResponse.of(DictAppStructMapping.instance.toDictVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param dictGateway
	 */
	@Autowired
	public void setDictGateway(DictGateway dictGateway) {
		this.dictGateway = dictGateway;
	}
}
