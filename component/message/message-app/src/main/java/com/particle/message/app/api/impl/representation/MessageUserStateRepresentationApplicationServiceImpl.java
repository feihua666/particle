package com.particle.message.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.message.app.executor.representation.MessageUserStateQueryCommandExecutor;
import com.particle.message.client.api.representation.IMessageUserStateRepresentationApplicationService;
import com.particle.message.client.dto.command.representation.MessageUserStatePageQueryCommand;
import com.particle.message.client.dto.command.representation.MessageUserStateQueryListCommand;
import com.particle.message.client.dto.data.MessageUserStateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 用户消息读取状态 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Service
@CatchAndLog
public class MessageUserStateRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IMessageUserStateRepresentationApplicationService {

    private MessageUserStateQueryCommandExecutor messageUserStateQueryCommandExecutor;

    @Override
    public SingleResponse<MessageUserStateVO> queryDetail(IdCommand detailCommand) {
        return messageUserStateQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<MessageUserStateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return messageUserStateQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<MessageUserStateVO> pageQuery(MessageUserStatePageQueryCommand messageUserStatePageQueryCommand) {
        return messageUserStateQueryCommandExecutor.execute(messageUserStatePageQueryCommand);
    }

    @Override
    public MultiResponse<MessageUserStateVO> queryList(MessageUserStateQueryListCommand messageUserStateQueryListCommand) {
        return messageUserStateQueryCommandExecutor.execute(messageUserStateQueryListCommand);
    }

    @Autowired
    public void setMessageUserStateQueryCommandExecutor(MessageUserStateQueryCommandExecutor messageUserStateQueryCommandExecutor) {
        this.messageUserStateQueryCommandExecutor = messageUserStateQueryCommandExecutor;
    }
}
