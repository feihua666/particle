package com.particle.feedback.infrastructure.feedback.mapper;

import com.particle.feedback.infrastructure.feedback.dos.FeedbackDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 意见反馈 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Mapper
public interface FeedbackMapper extends IBaseMapper<FeedbackDO> {

}
