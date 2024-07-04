package com.particle.role.app.roledatascoperel.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.app.roledatascoperel.executor.representation.RoleDataScopeRelQueryCommandExecutor;
import com.particle.role.client.roledatascoperel.api.representation.IRoleDataScopeRelRepresentationApplicationService;
import com.particle.role.client.roledatascoperel.dto.command.representation.RoleDataScopeRelPageQueryCommand;
import com.particle.role.client.roledatascoperel.dto.command.representation.RoleDataScopeRelQueryListCommand;
import com.particle.role.client.roledatascoperel.dto.data.RoleDataScopeRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 角色数据范围关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Service
@CatchAndLog
public class RoleDataScopeRelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IRoleDataScopeRelRepresentationApplicationService {

    private RoleDataScopeRelQueryCommandExecutor roleDataScopeRelQueryCommandExecutor;

    @Override
    public SingleResponse<RoleDataScopeRelVO> queryDetail(IdCommand detailCommand) {
        return roleDataScopeRelQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<RoleDataScopeRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return roleDataScopeRelQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<RoleDataScopeRelVO> pageQuery(RoleDataScopeRelPageQueryCommand roleDataScopeRelPageQueryCommand) {
        return roleDataScopeRelQueryCommandExecutor.execute(roleDataScopeRelPageQueryCommand);
    }

    @Override
    public MultiResponse<RoleDataScopeRelVO> queryList(RoleDataScopeRelQueryListCommand roleDataScopeRelQueryListCommand) {
        return roleDataScopeRelQueryCommandExecutor.execute(roleDataScopeRelQueryListCommand);
    }

	@Override
	public MultiResponse<Long> queryDataScopeIdsByRoleId(IdCommand roleIdCommand) {

		return roleDataScopeRelQueryCommandExecutor.queryDataScopeIdsByRoleId(roleIdCommand);
	}

	@Override
	public MultiResponse<Long> queryRoleIdsByDataScopeId(IdCommand dataScopeIdCommand) {
		return roleDataScopeRelQueryCommandExecutor.queryRoleIdsByDataScopeId(dataScopeIdCommand);
	}

    @Autowired
    public void setRoleDataScopeRelQueryCommandExecutor(RoleDataScopeRelQueryCommandExecutor roleDataScopeRelQueryCommandExecutor) {
        this.roleDataScopeRelQueryCommandExecutor = roleDataScopeRelQueryCommandExecutor;
    }
}
