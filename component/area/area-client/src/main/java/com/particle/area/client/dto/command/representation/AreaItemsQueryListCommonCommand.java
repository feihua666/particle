package com.particle.area.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 区域 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-02-27 11:22:22
 */
@Data
@Schema
public class AreaItemsQueryListCommonCommand extends AbstractBaseQueryCommand {


    @Schema(description = "父级id,字典相关字典不传该字段应该必传")
    private Long parentId;

    @Schema(description = "类型，字典id，字典值二选一")
    private Long typeDictId;

    @Schema(description = "类型，字典值，字典id二选一")
    private String typeDictValue;
}
