package com.particle.navigation.client.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 导航分类 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@OrderBy("seq")
@Data
@Schema
public class NavigationCategoryQueryListCommand extends AbstractBaseTreeQueryCommand {



    @Like
        @Schema(description = "编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "名称,左前缀匹配")
    private String name;


    @Schema(description = "图标")
    private String icon;



    @Schema(description = "排序")
    private Integer seq;





















}
