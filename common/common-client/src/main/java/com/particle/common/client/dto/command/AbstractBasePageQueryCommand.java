package com.particle.common.client.dto.command;

import com.particle.global.dto.basic.PageQueryCommand;

/**
 * <p>
 * 基础分页查询指令
 * 该指令应该是应用层门面的入参使用，这里又接了一层，方便以后扩展
 * </p>
 *
 * @author yangwei
 * @since 2022-04-27 19:58:33
 */
public abstract class AbstractBasePageQueryCommand extends PageQueryCommand {
	private static final long serialVersionUID = 1L;
}
