package com.particle.func.client.dto.command.representation;

import com.particle.common.client.dto.command.BatchIdCommand;
import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 菜单功能 根据ids查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-12-29 14:21:41
 */
@Data
@Schema
public class FuncQueryListByIdsCommand extends BatchIdCommand {

    @Schema(description = "是否禁用")
    private Boolean isDisabled;
}
