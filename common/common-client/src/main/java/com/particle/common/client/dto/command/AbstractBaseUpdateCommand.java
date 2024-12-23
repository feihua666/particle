package com.particle.common.client.dto.command;

import com.particle.global.dto.basic.UpdateCommand;
import lombok.Data;

/**
 * <p>
 * 基础更新指令
 * 主要涉及版本
 * 该指令应该是应用层门面的入参使用，这里又接了一层，方便以后扩展
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 09:24
 */
@Data
public abstract class AbstractBaseUpdateCommand extends UpdateCommand {

}
