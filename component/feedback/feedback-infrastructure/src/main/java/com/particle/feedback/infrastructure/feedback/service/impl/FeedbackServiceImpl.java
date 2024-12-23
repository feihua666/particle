package com.particle.feedback.infrastructure.feedback.service.impl;

import com.particle.feedback.infrastructure.feedback.dos.FeedbackDO;
import com.particle.feedback.infrastructure.feedback.mapper.FeedbackMapper;
import com.particle.feedback.infrastructure.feedback.service.IFeedbackService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 意见反馈 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Component
public class FeedbackServiceImpl extends IBaseServiceImpl<FeedbackMapper, FeedbackDO> implements IFeedbackService {
	private IBaseQueryCommandMapStruct<FeedbackDO> queryCommandMapStruct;

	@Override
	protected FeedbackDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<FeedbackDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(FeedbackDO po) {
	}

	@Override
	protected void preUpdate(FeedbackDO po) {

	}
}
