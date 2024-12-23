package com.particle.role.client.roledatascoperel.dto.command;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色分配数据范围 指令对象
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@PropValid
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Schema(description = "角色分配数据范围表单对象")
public class RoleAssignDataScopeCommand extends AbstractBaseCommand {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "角色id不能为空")
    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "选择的数据范围id")
    private List<Long> checkedDataScopeIds;

    /**
     * key=数据范围id
     * value=数据范围对应的数据对象id
     */
    @Schema(description = "选择的数据范围对应的数据对象ids")
    private Map<Long,Long> checkedDataScopeIdAgainstDataObjectIds;

    @PropValid.DependCondition(message = "未选择的数据范围id不能为空",dependProp = "isLazyLoad",ifEqual = "true")
    @Schema(title = "未选择的数据范围id",description = "如果为懒加载请传该值")
    private List<Long> uncheckedDataScopeIds;

    @Schema(description = "页面可选择的数据是否为懒加载")
    private Boolean isLazyLoad = false;

    public Long fetchDataObjectIdByDataScopeId(Long dataScopeId) {
        if (CollectionUtil.isEmpty(checkedDataScopeIdAgainstDataObjectIds)) {
            return null;
        }
        return checkedDataScopeIdAgainstDataObjectIds.get(dataScopeId);
    }
}
