package com.particle.common.client.dto.command.tree;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 对树的通用分页查询
 * 参见：{@link com.particle.global.mybatis.plus.crud.IBaseServiceImpl#getQueryWrapper(com.particle.global.dto.basic.QueryCommand)} 反射处理（直接引用不方便）
 * </p>
 *
 * @author yangwei
 * @since 2022-12-22 10:12
 */
@Data
public class AbstractBaseTreePageQueryCommand extends AbstractBasePageQueryCommand {

	@Schema(description = "父级id")
	private Long parentId;

	@Schema(description = "是否包含父级本身，父级id不为空时有效")
	private Boolean isIncludeParent;

	@Schema(description = "是否包含所有子孙，父级id不为空时有效")
	private Boolean isIncludeAllChildren;
}
