package com.particle.user.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.app.executor.representation.UserExtraInfoQueryCommandExecutor;
import com.particle.user.client.api.representation.IUserExtraInfoRepresentationApplicationService;
import com.particle.user.client.dto.command.representation.UserExtraInfoPageQueryCommand;
import com.particle.user.client.dto.command.representation.UserExtraInfoQueryListCommand;
import com.particle.user.client.dto.data.UserExtraInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 用户扩展信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Service
@CatchAndLog
public class UserExtraInfoRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUserExtraInfoRepresentationApplicationService {

    private UserExtraInfoQueryCommandExecutor userExtraInfoQueryCommandExecutor;

    @Override
    public SingleResponse<UserExtraInfoVO> queryDetail(IdCommand detailCommand) {
        return userExtraInfoQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<UserExtraInfoVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return userExtraInfoQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<UserExtraInfoVO> pageQuery(UserExtraInfoPageQueryCommand userExtraInfoPageQueryCommand) {
        return userExtraInfoQueryCommandExecutor.execute(userExtraInfoPageQueryCommand);
    }

    @Override
    public MultiResponse<UserExtraInfoVO> queryList(UserExtraInfoQueryListCommand userExtraInfoQueryListCommand) {
        return userExtraInfoQueryCommandExecutor.execute(userExtraInfoQueryListCommand);
    }


    @Autowired
    public void setUserExtraInfoQueryCommandExecutor(UserExtraInfoQueryCommandExecutor userExtraInfoQueryCommandExecutor) {
        this.userExtraInfoQueryCommandExecutor = userExtraInfoQueryCommandExecutor;
    }
}
