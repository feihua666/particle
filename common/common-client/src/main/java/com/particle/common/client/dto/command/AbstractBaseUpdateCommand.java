package com.particle.common.client.dto.command;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

public abstract class AbstractBaseUpdateCommand extends AbstractBaseCommand {
	private static final long serialVersionUID = 1L;

	@Min(value = 1,message = "数据版本不能小于1")
	@NotNull(message = "数据版本不能为空")
	@ApiModelProperty(value = "数据版本",notes = "数据版本用来充当数据乐观锁字段，验证数据是否已经修改")
	private Integer version;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
