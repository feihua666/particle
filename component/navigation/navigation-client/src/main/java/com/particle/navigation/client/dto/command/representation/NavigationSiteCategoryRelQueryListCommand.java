package com.particle.navigation.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 导航网站分类关系 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@OrderBy("seq")
@Data
@Schema
public class NavigationSiteCategoryRelQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "导航网站id")
    private Long navigationSiteId;


    @Schema(description = "导航分类id")
    private Long navigationCategoryId;


    @Schema(description = "排序")
    private Integer seq;









}
