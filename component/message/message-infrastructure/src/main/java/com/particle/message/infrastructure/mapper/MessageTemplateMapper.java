package com.particle.message.infrastructure.mapper;

import com.particle.message.infrastructure.dos.MessageTemplateDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 消息模板 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Mapper
public interface MessageTemplateMapper extends IBaseMapper<MessageTemplateDO> {

}
