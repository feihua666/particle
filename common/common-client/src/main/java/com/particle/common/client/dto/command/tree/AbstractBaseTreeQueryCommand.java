package com.particle.common.client.dto.command.tree;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 支持对树的通用查询
 * 参见：{@link com.particle.global.mybatis.plus.crud.IBaseServiceImpl#getQueryWrapper(com.particle.global.dto.basic.QueryCommand)} 反射处理（直接引用不方便）
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 09:29
 */
@Data
public abstract class AbstractBaseTreeQueryCommand extends AbstractBaseQueryCommand {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("父级id")
	private Long parentId;

	@ApiModelProperty("是否包含父级本身，父级id不为空时有效")
	private Boolean isIncludeParent;

	@ApiModelProperty("是否包含所有子孙，父级id不为空时有效")
	private Boolean isIncludeAllChildren;
}
