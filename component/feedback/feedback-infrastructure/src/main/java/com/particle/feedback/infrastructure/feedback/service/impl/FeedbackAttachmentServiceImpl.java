package com.particle.feedback.infrastructure.feedback.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.feedback.infrastructure.feedback.dos.FeedbackAttachmentDO;
import com.particle.feedback.infrastructure.feedback.mapper.FeedbackAttachmentMapper;
import com.particle.feedback.infrastructure.feedback.service.IFeedbackAttachmentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 意见反馈附件 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Component
public class FeedbackAttachmentServiceImpl extends IBaseServiceImpl<FeedbackAttachmentMapper, FeedbackAttachmentDO> implements IFeedbackAttachmentService {
	private IBaseQueryCommandMapStruct<FeedbackAttachmentDO> queryCommandMapStruct;

	@Override
	protected FeedbackAttachmentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<FeedbackAttachmentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(FeedbackAttachmentDO po) {
	}

	@Override
	protected void preUpdate(FeedbackAttachmentDO po) {
    
	}
}
