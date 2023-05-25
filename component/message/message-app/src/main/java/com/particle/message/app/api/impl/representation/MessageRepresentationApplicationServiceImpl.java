package com.particle.message.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.message.app.executor.representation.MessageQueryCommandExecutor;
import com.particle.message.client.api.representation.IMessageRepresentationApplicationService;
import com.particle.message.client.dto.command.representation.MessagePageQueryCommand;
import com.particle.message.client.dto.command.representation.MessageQueryListCommand;
import com.particle.message.client.dto.data.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 消息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Service
@CatchAndLog
public class MessageRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IMessageRepresentationApplicationService {

    private MessageQueryCommandExecutor messageQueryCommandExecutor;

    @Override
    public SingleResponse<MessageVO> queryDetail(IdCommand detailCommand) {
        return messageQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<MessageVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return messageQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<MessageVO> pageQuery(MessagePageQueryCommand messagePageQueryCommand) {
        return messageQueryCommandExecutor.execute(messagePageQueryCommand);
    }

    @Override
    public MultiResponse<MessageVO> queryList(MessageQueryListCommand messageQueryListCommand) {
        return messageQueryCommandExecutor.execute(messageQueryListCommand);
    }

    @Autowired
    public void setMessageQueryCommandExecutor(MessageQueryCommandExecutor messageQueryCommandExecutor) {
        this.messageQueryCommandExecutor = messageQueryCommandExecutor;
    }
}
