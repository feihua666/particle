package com.particle.openplatform.client.doc.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 开放接口文档参数字段 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Data
@Schema
public class OpenplatformDocApiDocParamFieldCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "字段名称 不能为空")
    @Schema(description = "字段名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "字段类型 不能为空")
    @Schema(description = "字段类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long typeDictId;

	@Schema(description = "嵌套字段类型，字典id，一般用于字段类型为array时里面的类型，如：string、object")
	private Long nestTypeDictId;


    @NotNull(message = "是否一定有值 不能为空")
    @Schema(description = "是否一定有值",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isRequired;


    @NotEmpty(message = "字段说明 不能为空")
    @Schema(description = "字段说明",requiredMode = Schema.RequiredMode.REQUIRED)
    private String explanation;

    /**
     * 已在添加时处理
     */
	@Schema(description = "开放接口文档接口id")
	private Long openplatformDocApiId;


    @NotNull(message = "开放接口文档id 不能为空")
    @Schema(description = "开放接口文档id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long openplatformDocApiDocId;


    @NotNull(message = "分类 不能为空")
    @Schema(description = "分类",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long categoryDictId;

	@Schema(description = "默认值")
	private String defaultValue;

	@Schema(description = "最大长度")
	private String exampleValue;

    @SetNullWhenNull
	@Schema(description = "最大长度")
	private Integer maxLength;

	@Schema(description = "字典组字典，字典组id，字典组下面的字典项为字段枚举")
	private Long dictGroupDictId;


    @Schema(description = "字典组字典，字典组编码，dictGroupDictId二选一")
    private String dictGroupDictCode;

	@Schema(description = "字典项标签，如果某一个字典组下的字典项过多可以根据标签过滤")
	private String dictItemTags;


    @NotNull(message = "排序 不能为空")
    @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;


    @Schema(description = "父级id")
    private Long parentId;


}
