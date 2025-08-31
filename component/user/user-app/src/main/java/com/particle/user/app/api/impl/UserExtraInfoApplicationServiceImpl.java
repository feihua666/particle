package com.particle.user.app.api.impl;

import com.particle.user.app.executor.UserExtraInfoCreateCommandExecutor;
import com.particle.user.app.executor.UserExtraInfoDeleteCommandExecutor;
import com.particle.user.app.executor.UserExtraInfoUpdateCommandExecutor;
import com.particle.user.app.executor.UserExtraInfoCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.user.client.dto.command.UserExtraInfoUpdateCommand;
import com.particle.user.client.api.IUserExtraInfoApplicationService;
import com.particle.user.client.dto.command.UserExtraInfoCreateCommand;
import com.particle.user.client.dto.data.UserExtraInfoVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 用户扩展信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Transactional
@Service
@CatchAndLog
public class UserExtraInfoApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IUserExtraInfoApplicationService {

    private UserExtraInfoCreateCommandExecutor userExtraInfoCreateCommandExecutor;

    private UserExtraInfoDeleteCommandExecutor userExtraInfoDeleteCommandExecutor;

    private UserExtraInfoUpdateCommandExecutor userExtraInfoUpdateCommandExecutor;

    private UserExtraInfoCommandExecutor userExtraInfoCommandExecutor;


    @Override
    public SingleResponse<UserExtraInfoVO> create(UserExtraInfoCreateCommand userExtraInfoCreateCommand) {
        return userExtraInfoCreateCommandExecutor.execute(userExtraInfoCreateCommand);
    }

    @Override
    public SingleResponse<UserExtraInfoVO> delete(IdCommand deleteCommand) {
        return userExtraInfoDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<UserExtraInfoVO> update(UserExtraInfoUpdateCommand userExtraInfoUpdateCommand) {
        return userExtraInfoUpdateCommandExecutor.execute(userExtraInfoUpdateCommand);
    }


    @Autowired
    public void setUserExtraInfoCreateCommandExecutor(UserExtraInfoCreateCommandExecutor userExtraInfoCreateCommandExecutor) {
        this.userExtraInfoCreateCommandExecutor = userExtraInfoCreateCommandExecutor;
    }

    @Autowired
    public void setUserExtraInfoDeleteCommandExecutor(UserExtraInfoDeleteCommandExecutor userExtraInfoDeleteCommandExecutor) {
        this.userExtraInfoDeleteCommandExecutor = userExtraInfoDeleteCommandExecutor;
    }
    @Autowired
    public void setUserExtraInfoUpdateCommandExecutor(UserExtraInfoUpdateCommandExecutor userExtraInfoUpdateCommandExecutor) {
        this.userExtraInfoUpdateCommandExecutor = userExtraInfoUpdateCommandExecutor;
    }
    @Autowired
    public void setUserExtraInfoCommandExecutor(UserExtraInfoCommandExecutor userExtraInfoCommandExecutor) {
        this.userExtraInfoCommandExecutor = userExtraInfoCommandExecutor;
    }
}
