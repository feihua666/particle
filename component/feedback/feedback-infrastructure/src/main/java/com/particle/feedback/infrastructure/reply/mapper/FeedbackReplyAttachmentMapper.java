package com.particle.feedback.infrastructure.reply.mapper;

import com.particle.feedback.infrastructure.reply.dos.FeedbackReplyAttachmentDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 意见反馈回复附件 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:49:16
 */
@Mapper
public interface FeedbackReplyAttachmentMapper extends IBaseMapper<FeedbackReplyAttachmentDO> {

}
