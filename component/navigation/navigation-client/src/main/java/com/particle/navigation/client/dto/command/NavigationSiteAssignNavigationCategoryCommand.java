package com.particle.navigation.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 导航网站分配导航分类 指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Schema(description = "导航网站分配导航分类表单对象")
public class NavigationSiteAssignNavigationCategoryCommand extends AbstractBaseCommand {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "导航网站id不能为空")
    @Schema(description = "导航网站id")
    private Long navigationSiteId;

    @Schema(description = "选择的导航分类id")
    private List<Long> checkedNavigationCategoryIds;

    @PropValid.DependCondition(message = "未选择的导航分类id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @Schema(title = "未选择的导航分类id",description = "如果为懒加载请传该值")
    private List<Long> uncheckedNavigationCategoryIds;

    @Schema(description = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序,默认按该字段升序排序")
    private Integer seq = 1000;
}
