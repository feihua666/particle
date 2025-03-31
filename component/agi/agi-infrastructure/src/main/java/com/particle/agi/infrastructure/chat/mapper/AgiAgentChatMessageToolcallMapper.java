package com.particle.agi.infrastructure.chat.mapper;

import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolcallDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 智能体对话消息工具调用 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Mapper
public interface AgiAgentChatMessageToolcallMapper extends IBaseMapper<AgiAgentChatMessageToolcallDO> {

}
