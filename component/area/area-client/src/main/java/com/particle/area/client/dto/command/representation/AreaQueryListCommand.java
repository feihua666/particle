package com.particle.area.client.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 区域 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@OrderBy("seq")
@Data
@Schema
public class AreaQueryListCommand extends AbstractBaseTreeQueryCommand {


    @Like
    @Schema(description = "编码，左前缀匹配")
    private String code;

    @Like
    @Schema(description = "区域名称,左前缀匹配")
    private String name;

    @Like
    @Schema(description = "区域名称,左前缀匹配")
    private String nameSimple;

    @Schema(description = "第一个字的首字母")
    private String spellFirst;

    @Like
    @Schema(description = "每个字的首字母,左前缀匹配")
    private String spellSimple;

    @Like
    @Schema(description = "全拼,左前缀匹配")
    private String spell;

    @Schema(description = "类型，字典id")
    private Long typeDictId;

}
