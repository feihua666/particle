package com.particle.message.infrastructure.mapper;

import com.particle.message.infrastructure.dos.MessageDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 消息 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Mapper
public interface MessageMapper extends IBaseMapper<MessageDO> {

}
