package com.particle.agi.infrastructure.chat.mapper;

import com.particle.agi.infrastructure.chat.dos.AgiAgentChatDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 智能体对话 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Mapper
public interface AgiAgentChatMapper extends IBaseMapper<AgiAgentChatDO> {

}
