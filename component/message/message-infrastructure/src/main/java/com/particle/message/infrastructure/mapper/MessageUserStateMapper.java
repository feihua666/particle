package com.particle.message.infrastructure.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.message.infrastructure.dos.MessageUserStateDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 用户消息读取状态 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Mapper
public interface MessageUserStateMapper extends IBaseMapper<MessageUserStateDO> {

}
