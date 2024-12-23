package com.particle.navigation.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 导航分类 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Data
@Schema
public class NavigationCategoryVO extends AbstractBaseIdTreeVO {

    @Schema(description = "编码")
    private String code;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "排序")
    private Integer seq;



}
