package com.particle.message.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.message.app.executor.representation.MessageTemplateQueryCommandExecutor;
import com.particle.message.client.api.representation.IMessageTemplateRepresentationApplicationService;
import com.particle.message.client.dto.command.representation.MessageTemplatePageQueryCommand;
import com.particle.message.client.dto.command.representation.MessageTemplateQueryListCommand;
import com.particle.message.client.dto.data.MessageTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 消息模板 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Service
@CatchAndLog
public class MessageTemplateRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IMessageTemplateRepresentationApplicationService {

    private MessageTemplateQueryCommandExecutor messageTemplateQueryCommandExecutor;

    @Override
    public SingleResponse<MessageTemplateVO> queryDetail(IdCommand detailCommand) {
        return messageTemplateQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<MessageTemplateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return messageTemplateQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<MessageTemplateVO> pageQuery(MessageTemplatePageQueryCommand messageTemplatePageQueryCommand) {
        return messageTemplateQueryCommandExecutor.execute(messageTemplatePageQueryCommand);
    }

    @Override
    public MultiResponse<MessageTemplateVO> queryList(MessageTemplateQueryListCommand messageTemplateQueryListCommand) {
        return messageTemplateQueryCommandExecutor.execute(messageTemplateQueryListCommand);
    }

    @Autowired
    public void setMessageTemplateQueryCommandExecutor(MessageTemplateQueryCommandExecutor messageTemplateQueryCommandExecutor) {
        this.messageTemplateQueryCommandExecutor = messageTemplateQueryCommandExecutor;
    }
}
