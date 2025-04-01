package com.particle.dict.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 字典 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@PropValid
@Data
@Schema
public class DictCreateCommand extends AbstractBaseCommand {


    /**
     * 添加字典组，编码必填
     */
    @PropValid.DependCondition(message = "字典编码不能为空",dependProp = "isGroup",ifEqual = "true")
    @Schema(description = "字典编码,模糊查询，字典组时必填",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

    @Schema(description = "字典名称,模糊查询")
    private String name;

    /**
     * 添加字典项，字典值必填
     */
    @PropValid.DependCondition(message = "字典值不能为空",dependProp = "isGroup",ifEqual = "false")
    @Schema(description = "字典值,模糊查询")
    private String value;

    @Schema(description = "字典值,单位")
    private String valueUnit;

    @NotNull(message = "是否为系统字典不能为空")
    @Schema(description = "是否为系统字典，一般系统字典代码中会做判断，不能修改或删除",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isSystem;

    @NotNull(message = "是否为公共字典不能为空")
    @Schema(description = "是否为公共字典，如果为公共字典不限制使用，否则按相应数据权限查询",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isPublic;

    @NotNull(message = "是否为字典组不能为空")
    @Schema(description = "是否为字典组",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isGroup;

    @NotNull(message = "是否为字典项不能为空")
    @Schema(description = "是否为字典项",requiredMode = Schema.RequiredMode.REQUIRED)
	private Boolean isItem;

    @NotNull(message = "是否禁用不能为空")
    @Schema(description = "是否禁用",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isDisabled;

    /**
     * 禁用时，禁用原因必填
     */
    @PropValid.DependCondition(message = "禁用原因不能为空",dependProp = "isDisabled",ifEqual = "true")
    @Schema(description = "禁用原因")
    private String disabledReason;

    @Schema(description = "私有标识,模糊查询")
    private String privateFlag;

    @PropValid.Depend(message = "私有标识说明不能为空",dependProp = "privateFlag")
    @Schema(description = "私有标识说明")
    private String privateFlagMemo;

    @Schema(description = "分组标识")
    private String groupFlag;

    @PropValid.Depend(message = "分组标识说明不能为空",dependProp = "groupFlag")
    @Schema(description = "分组标识说明")
    private String groupFlagMemo;

    @Schema(description = "标签，多个以逗号分隔，用来区分字典项")
    private String tags;

	@Schema(description = "关联字典组编码，用于在字典项下还有字典项的扩展场景")
	private String relatedGroupCode;

    @Schema(description = "描述")
    private String remark;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序,默认按该字段升序排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;

    /**
     * 如果添加字典项，必须指定父级
     */
    @PropValid.DependCondition(message = "父级id不能为空",dependProp = "isGroup",ifEqual = "false")
    @Schema(description = "父级id")
    private Long parentId;
}
