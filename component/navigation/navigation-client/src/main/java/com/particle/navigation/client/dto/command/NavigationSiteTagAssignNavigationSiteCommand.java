package com.particle.navigation.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 网站标签分配网站 指令对象
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Schema(description = "网站标签分配网站表单对象")
public class NavigationSiteTagAssignNavigationSiteCommand extends AbstractBaseCommand {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "网站标签id不能为空")
    @Schema(description = "网站标签id")
    private Long navigationSiteTagId;

    @Schema(description = "选择的网站id")
    private List<Long> checkedNavigationSiteIds;

    @PropValid.DependCondition(message = "未选择的网站id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @Schema(title = "未选择的网站id",description = "如果为懒加载请传该值")
    private List<Long> uncheckedNavigationSiteIds;

    @Schema(description = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;

}
