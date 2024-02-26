package com.particle.feedback.infrastructure.reply.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.feedback.infrastructure.reply.dos.FeedbackReplyDO;
import com.particle.feedback.infrastructure.reply.mapper.FeedbackReplyMapper;
import com.particle.feedback.infrastructure.reply.service.IFeedbackReplyService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 意见反馈回复 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Component
public class FeedbackReplyServiceImpl extends IBaseServiceImpl<FeedbackReplyMapper, FeedbackReplyDO> implements IFeedbackReplyService {
	private IBaseQueryCommandMapStruct<FeedbackReplyDO> queryCommandMapStruct;

	@Override
	protected FeedbackReplyDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<FeedbackReplyDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(FeedbackReplyDO po) {
	}

	@Override
	protected void preUpdate(FeedbackReplyDO po) {
    
	}
}
