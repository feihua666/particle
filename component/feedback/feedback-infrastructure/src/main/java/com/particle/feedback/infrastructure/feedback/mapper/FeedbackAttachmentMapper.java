package com.particle.feedback.infrastructure.feedback.mapper;

import com.particle.feedback.infrastructure.feedback.dos.FeedbackAttachmentDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 意见反馈附件 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:09
 */
@Mapper
public interface FeedbackAttachmentMapper extends IBaseMapper<FeedbackAttachmentDO> {

}
