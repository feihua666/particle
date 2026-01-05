package com.particle.componentadmin.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.componentadmin.app.executor.representation.AdminComponentDependencyQueryCommandExecutor;
import com.particle.componentadmin.client.api.representation.IAdminComponentDependencyRepresentationApplicationService;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentDependencyPageQueryCommand;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentDependencyQueryListCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentDependencyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 组件依赖关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Service
@CatchAndLog
public class AdminComponentDependencyRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAdminComponentDependencyRepresentationApplicationService {

    private AdminComponentDependencyQueryCommandExecutor adminComponentDependencyQueryCommandExecutor;

    @Override
    public SingleResponse<AdminComponentDependencyVO> queryDetail(IdCommand detailCommand) {
        return adminComponentDependencyQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<AdminComponentDependencyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return adminComponentDependencyQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<AdminComponentDependencyVO> pageQuery(AdminComponentDependencyPageQueryCommand adminComponentDependencyPageQueryCommand) {
        return adminComponentDependencyQueryCommandExecutor.execute(adminComponentDependencyPageQueryCommand);
    }

    @Override
    public MultiResponse<AdminComponentDependencyVO> queryList(AdminComponentDependencyQueryListCommand adminComponentDependencyQueryListCommand) {
        return adminComponentDependencyQueryCommandExecutor.execute(adminComponentDependencyQueryListCommand);
    }

	@Override
	public MultiResponse<Long> queryDependComponentIdsByComponentId(IdCommand componentIdCommand) {

		return adminComponentDependencyQueryCommandExecutor.queryDependComponentIdsByComponentId(componentIdCommand);
	}

	@Override
	public MultiResponse<Long> queryComponentIdsByDependComponentId(IdCommand dependComponentIdCommand) {
		return adminComponentDependencyQueryCommandExecutor.queryComponentIdsByDependComponentId(dependComponentIdCommand);
	}

    @Autowired
    public void setAdminComponentDependencyQueryCommandExecutor(AdminComponentDependencyQueryCommandExecutor adminComponentDependencyQueryCommandExecutor) {
        this.adminComponentDependencyQueryCommandExecutor = adminComponentDependencyQueryCommandExecutor;
    }
}
