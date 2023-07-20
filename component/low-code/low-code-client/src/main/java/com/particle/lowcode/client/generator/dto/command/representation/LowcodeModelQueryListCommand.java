package com.particle.lowcode.client.generator.dto.command.representation;


import java.time.LocalDateTime;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 低代码模型 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Data
@Schema
public class LowcodeModelQueryListCommand extends AbstractBaseQueryCommand {


    @Like
    @Schema(description = "名称")
    private String name;

    @Like
    @Schema(description = "英文名称")
    private String nameEn;

    @Like
    @Schema(description = "实体名称",example = "首字母大写，符合java类名规范")
    private String nameEnEntity;

    @Like
    @Schema(description = "实体变量名称",example = "nameEnEntity 的首字母小写")
    private String nameEnEntityVar;

    @Like
    @Schema(description = "表名称")
    private String tableName;

    @Schema(description = "模型表类型字典id，rel,tree,normal")
    private Long tableTypeDictId;

}
