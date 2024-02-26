package com.particle.feedback.infrastructure.reply.mapper;

import com.particle.feedback.infrastructure.reply.dos.FeedbackReplyDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 意见反馈回复 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Mapper
public interface FeedbackReplyMapper extends IBaseMapper<FeedbackReplyDO> {

}
