package com.particle.openplatform.client.doc.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口文档参数字段 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@OrderBy("seq")
@Data
@Schema
public class OpenplatformDocApiDocParamFieldQueryListCommand extends AbstractBaseTreeQueryCommand {



    @Like
    @Schema(description = "字段名称")
    private String name;


    @Schema(description = "字段类型")
    private Long typeDictId;

	@Schema(description = "嵌套字段类型，字典id，一般用于字段类型为array时里面的类型，如：string、object")
	private Long nestTypeDictId;


    @Schema(description = "是否一定有值")
    private Boolean isRequired;

    @Like
    @Schema(description = "字段说明")
    private String explanation;

	@Schema(description = "开放接口文档接口id")
	private Long openplatformDocApiId;


    @Schema(description = "开放接口文档id")
    private Long openplatformDocApiDocId;


    @Schema(description = "分类")
    private Long categoryDictId;

	@Schema(description = "默认值")
	private String defaultValue;

	@Schema(description = "最大长度")
	private String exampleValue;

	@Schema(description = "最大长度")
	private Integer maxLength;

	@Schema(description = "字典组字典，字典组id，字典组下面的字典项为字段枚举")
	private Long dictGroupDictId;

	@Schema(description = "字典项标签，如果某一个字典组下的字典项过多可以根据标签过滤")
	private String dictItemTags;


    @Schema(description = "排序")
    private Integer seq;


}