package com.particle.componentadmin.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.componentadmin.app.executor.representation.AdminComponentQueryCommandExecutor;
import com.particle.componentadmin.client.api.representation.IAdminComponentRepresentationApplicationService;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentPageQueryCommand;
import com.particle.componentadmin.client.dto.command.representation.AdminComponentQueryListCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 组件 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Service
@CatchAndLog
public class AdminComponentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAdminComponentRepresentationApplicationService {

    private AdminComponentQueryCommandExecutor adminComponentQueryCommandExecutor;

    @Override
    public SingleResponse<AdminComponentVO> queryDetail(CommonIdCommand detailCommand) {
        return adminComponentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<AdminComponentVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return adminComponentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<AdminComponentVO> pageQuery(AdminComponentPageQueryCommand adminComponentPageQueryCommand) {
        return adminComponentQueryCommandExecutor.execute(adminComponentPageQueryCommand);
    }

    @Override
    public MultiResponse<AdminComponentVO> queryList(AdminComponentQueryListCommand adminComponentQueryListCommand) {
        return adminComponentQueryCommandExecutor.execute(adminComponentQueryListCommand);
    }


    @Autowired
    public void setAdminComponentQueryCommandExecutor(AdminComponentQueryCommandExecutor adminComponentQueryCommandExecutor) {
        this.adminComponentQueryCommandExecutor = adminComponentQueryCommandExecutor;
    }
}
