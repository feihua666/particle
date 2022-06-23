package com.particle.common.client.dto.command;

import com.particle.global.dto.basic.Command;

/**
 * <p>
 * 基础指令
 * 该指令应该是应用层门面的入参使用，这里又接了一层，方便以后扩展
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 09:24
 */
public abstract class AbstractBaseCommand extends Command {
	private static final long serialVersionUID = 1L;
}
