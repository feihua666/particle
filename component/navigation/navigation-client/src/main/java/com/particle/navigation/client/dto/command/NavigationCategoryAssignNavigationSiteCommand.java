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
 * 导航分类分配导航网站 指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Schema(description = "导航分类分配导航网站表单对象")
public class NavigationCategoryAssignNavigationSiteCommand extends AbstractBaseCommand {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "导航分类id不能为空")
    @Schema(description = "导航分类id")
    private Long navigationCategoryId;

    @Schema(description = "选择的导航网站id")
    private List<Long> checkedNavigationSiteIds;

    @PropValid.DependCondition(message = "未选择的导航网站id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @Schema(title = "未选择的导航网站id",description = "如果为懒加载请传该值")
    private List<Long> uncheckedNavigationSiteIds;

    @Schema(description = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;


    @NotNull(message = "排序不能为空")
    @Schema(description = "排序,默认按该字段升序排序")
    private Integer seq = 1000;
}
