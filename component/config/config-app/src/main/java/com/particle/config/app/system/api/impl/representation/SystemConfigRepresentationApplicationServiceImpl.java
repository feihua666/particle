package com.particle.config.app.system.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.config.app.system.executor.representation.SystemConfigQueryCommandExecutor;
import com.particle.config.client.system.api.representation.ISystemConfigRepresentationApplicationService;
import com.particle.config.client.system.dto.command.representation.SystemConfigPageQueryCommand;
import com.particle.config.client.system.dto.command.representation.SystemConfigQueryListCommand;
import com.particle.config.client.system.dto.data.SystemConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 系统参数配置 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Service
@CatchAndLog
public class SystemConfigRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISystemConfigRepresentationApplicationService {

    private SystemConfigQueryCommandExecutor systemConfigQueryCommandExecutor;

    @Override
    public SingleResponse<SystemConfigVO> queryDetail(IdCommand detailCommand) {
        return systemConfigQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<SystemConfigVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return systemConfigQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<SystemConfigVO> pageQuery(SystemConfigPageQueryCommand systemConfigPageQueryCommand) {
        return systemConfigQueryCommandExecutor.execute(systemConfigPageQueryCommand);
    }

    @Override
    public MultiResponse<SystemConfigVO> queryList(SystemConfigQueryListCommand systemConfigQueryListCommand) {
        return systemConfigQueryCommandExecutor.execute(systemConfigQueryListCommand);
    }

    @Autowired
    public void setSystemConfigQueryCommandExecutor(SystemConfigQueryCommandExecutor systemConfigQueryCommandExecutor) {
        this.systemConfigQueryCommandExecutor = systemConfigQueryCommandExecutor;
    }
}
