package com.particle.message.infrastructure.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.message.infrastructure.dos.MessageTemplateDO;
import org.springframework.util.Assert;

/**
 * <p>
 * 消息模板 服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
public interface IMessageTemplateService extends IBaseService<MessageTemplateDO> {

	/**
	 * 根据模板编码查询
	 * @param code
	 * @return
	 */
	default MessageTemplateDO getByCode(String code) {
		Assert.hasText(code,"code不能为空");
		return getOne(Wrappers.<MessageTemplateDO>lambdaQuery().eq(MessageTemplateDO::getCode, code));
	}

}
