package com.particle.agi.infrastructure.agent.mapper;

import com.particle.agi.infrastructure.agent.dos.AgiAgentDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 智能体 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Mapper
public interface AgiAgentMapper extends IBaseMapper<AgiAgentDO> {

}
