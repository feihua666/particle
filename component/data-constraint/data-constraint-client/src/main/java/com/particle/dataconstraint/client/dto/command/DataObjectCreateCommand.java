package com.particle.dataconstraint.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 数据对象 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Data
@Schema
public class DataObjectCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "数据对象编码 不能为空")
        @Schema(description = "数据对象编码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;


    @NotEmpty(message = "数据对象名称 不能为空")
        @Schema(description = "数据对象名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "数据范围自定义时用来绑定自定义数据的url")
    private String customDataUrl;


    @NotNull(message = "自定义数据是否懒加载 不能为空")
        @Schema(description = "自定义数据是否懒加载",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isCustomDataLazy;


    @Schema(description = "自定义数据交互方式")
    private Long customDataTypeDictId;


    @Schema(description = "数据交互方式内容")
    private String customDataConfigJson;

    @NotNull(message = "是否禁用 不能为空")
    @Schema(description = "是否禁用",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isDisabled;

    /**
     * 禁用时，禁用原因必填
     */
    @PropValid.DependCondition(message = "禁用原因不能为空",dependProp = "isDisabled",ifEqual = "true")
    @Schema(description = "禁用原因")
    private String disabledReason;


    @Schema(description = "描述")
    private String remark;









}
