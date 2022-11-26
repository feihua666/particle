package com.particle.role.app.rolefuncrel.api.impl.representation;

import com.particle.role.app.rolefuncrel.executor.representation.RoleFuncRelQueryCommandExecutor;
import com.particle.role.client.rolefuncrel.dto.command.RoleFuncRelDeleteCommand;
import com.particle.role.client.rolefuncrel.dto.command.RoleFuncRelUpdateCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryDetailCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryDetailForUpdateCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelPageQueryCommand;
import com.particle.role.client.rolefuncrel.api.representation.IRoleFuncRelRepresentationApplicationService;
import com.particle.role.client.rolefuncrel.dto.command.RoleFuncRelCreateCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 角色菜单功能关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Service
@CatchAndLog
public class RoleFuncRelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IRoleFuncRelRepresentationApplicationService {

	private RoleFuncRelQueryCommandExecutor roleFuncRelQueryCommandExecutor;

	@Override
	public SingleResponse<RoleFuncRelVO> queryDetail(RoleFuncRelQueryDetailCommand roleFuncRelQueryDetailCommand) {
		return roleFuncRelQueryCommandExecutor.execute(roleFuncRelQueryDetailCommand);
	}

	@Override
	public SingleResponse<RoleFuncRelVO> queryDetailForUpdate(RoleFuncRelQueryDetailForUpdateCommand roleFuncRelQueryDetailForUpdateCommand) {
		return roleFuncRelQueryCommandExecutor.execute(roleFuncRelQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<RoleFuncRelVO> pageQuery(RoleFuncRelPageQueryCommand roleFuncRelPageQueryCommand) {
		return roleFuncRelQueryCommandExecutor.execute(roleFuncRelPageQueryCommand);
	}

	@Override
	public MultiResponse<RoleFuncRelVO> queryList(RoleFuncRelQueryListCommand roleFuncRelQueryListCommand) {
		return roleFuncRelQueryCommandExecutor.execute(roleFuncRelQueryListCommand);
	}

	@Autowired
	public void setRoleFuncRelQueryCommandExecutor(RoleFuncRelQueryCommandExecutor roleFuncRelQueryCommandExecutor) {
		this.roleFuncRelQueryCommandExecutor = roleFuncRelQueryCommandExecutor;
	}
}
