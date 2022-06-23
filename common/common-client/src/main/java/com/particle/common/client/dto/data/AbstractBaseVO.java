package com.particle.common.client.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 基础VO，带数据版本
 * </p>
 *
 * @author yangwei
 * @since 2022-05-17 16:55
 */
@Data
public abstract class AbstractBaseVO extends VO {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据版本
	 */
	@ApiModelProperty(value = "数据版本",notes = "从 1 开始取值")
	private Integer version;
}
