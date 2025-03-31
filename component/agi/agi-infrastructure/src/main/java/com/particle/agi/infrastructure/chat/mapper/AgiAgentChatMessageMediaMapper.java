package com.particle.agi.infrastructure.chat.mapper;

import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageMediaDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 智能体对话消息媒体 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Mapper
public interface AgiAgentChatMessageMediaMapper extends IBaseMapper<AgiAgentChatMessageMediaDO> {

}
