package com.particle.common.client.dto.command;

import com.particle.global.dto.basic.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 基础指令
 * 该指令应该是应用层门面的入参使用，这里又接了一层，方便以后扩展
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 09:24
 */
@Data
public abstract class AbstractBaseCommand extends Command {
	private static final long serialVersionUID = 1L;


	/**
	 * 根据场景值，可以做一些额外处理
	 * 比如：添加时写入一些特定值，可以根据该值判断一些逻辑
	 */
	@Schema(description = "使用场景",hidden = true)
	private String scene;
}
