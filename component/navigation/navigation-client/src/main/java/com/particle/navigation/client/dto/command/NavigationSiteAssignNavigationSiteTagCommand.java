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
 * 网站分配网站标签 指令对象
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Schema(description = "网站分配网站标签表单对象")
public class NavigationSiteAssignNavigationSiteTagCommand extends AbstractBaseCommand {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "网站id不能为空")
    @Schema(description = "网站id")
    private Long navigationSiteId;

    @Schema(description = "选择的网站标签id")
    private List<Long> checkedNavigationSiteTagIds;

    @PropValid.DependCondition(message = "未选择的网站标签id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @Schema(title = "未选择的网站标签id",description = "如果为懒加载请传该值")
    private List<Long> uncheckedNavigationSiteTagIds;

    @Schema(description = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;

}
