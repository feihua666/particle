package com.particle.area.app.executor;

import com.particle.area.app.structmapping.AreaAppStructMapping;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.area.domain.Area;
import com.particle.area.domain.AreaId;
import com.particle.area.domain.gateway.AreaGateway;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 区域 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Component
@Validated
public class AreaDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AreaGateway areaGateway;

	/**
	 * 执行 区域 删除指令
	 * @param areaDeleteCommand
	 * @return
	 */
	public SingleResponse<AreaVO> execute(@Valid IdCommand areaDeleteCommand) {
		AreaId areaId = AreaId.of(areaDeleteCommand.getId());
		Area byId = areaGateway.getById(areaId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = areaGateway.delete(areaId,areaDeleteCommand);
		if (delete) {
			return SingleResponse.of(AreaAppStructMapping.instance.toAreaVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param areaGateway
	 */
	@Autowired
	public void setAreaGateway(AreaGateway areaGateway) {
		this.areaGateway = areaGateway;
	}
}
