package com.particle.feedback.infrastructure.reply.service.impl;

import com.particle.feedback.infrastructure.reply.dos.FeedbackReplyAttachmentDO;
import com.particle.feedback.infrastructure.reply.mapper.FeedbackReplyAttachmentMapper;
import com.particle.feedback.infrastructure.reply.service.IFeedbackReplyAttachmentService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 意见反馈回复附件 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Component
public class FeedbackReplyAttachmentServiceImpl extends IBaseServiceImpl<FeedbackReplyAttachmentMapper, FeedbackReplyAttachmentDO> implements IFeedbackReplyAttachmentService {
	private IBaseQueryCommandMapStruct<FeedbackReplyAttachmentDO> queryCommandMapStruct;

	@Override
	protected FeedbackReplyAttachmentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<FeedbackReplyAttachmentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(FeedbackReplyAttachmentDO po) {
	}

	@Override
	protected void preUpdate(FeedbackReplyAttachmentDO po) {

	}
}
