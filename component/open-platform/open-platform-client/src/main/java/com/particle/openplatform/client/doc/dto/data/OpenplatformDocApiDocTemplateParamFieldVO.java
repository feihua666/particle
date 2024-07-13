package com.particle.openplatform.client.doc.dto.data;

import java.util.List;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
/**
 * <p>
 * 开放接口文档模板参数字段 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
@Data
@Schema
public class OpenplatformDocApiDocTemplateParamFieldVO extends AbstractBaseIdTreeVO {

    @Schema(description = "字段名称")
    private String name;
    
    @Schema(description = "字段类型")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "字段类型对应字典名称")
    private String typeDictName;
        
    @Schema(description = "嵌套字段类型")
    private Long nestTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "nestTypeDictId",mapValueField = "name")
    @Schema(description = "嵌套字段类型对应字典名称")
    private String nestTypeDictName;
        
    @Schema(description = "是否一定有值")
    private Boolean isRequired;
    
    @Schema(description = "字段说明")
    private String explanation;
    
    @Schema(description = "开放接口文档模板id")
    private Long openplatformDocApiDocTemplateId;


    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api_doc_template, byFieldName = "openplatformDocApiDocTemplateId", mapValueField = "name")
    @Schema(description = "开放接口文档模板名称")
    private String openplatformDocApiDocTemplateName;
    
    @Schema(description = "分类")
    private Long categoryDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "categoryDictId",mapValueField = "name")
    @Schema(description = "分类对应字典名称")
    private String categoryDictName;

	@Schema(description = "默认值")
	private String defaultValue;

	@Schema(description = "示例值")
	private String exampleValue;

	@Schema(description = "最大长度")
	private Integer maxLength;

	@Schema(description = "字典组字典，字典组id，字典组下面的字典项为字段枚举")
	private Long dictGroupDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "dictGroupDictId",mapValueField = "code")
    @Schema(description = "字典组字典编码")
    private String dictGroupDictCode;


    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "dictGroupDictId",mapValueField = "name")
    @Schema(description = "字典组字典名称")
    private String dictGroupDictName;

	@Schema(description = "字典项标签，如果某一个字典组下的字典项过多可以根据标签过滤")
	private String dictItemTags;

    /**
     * 由{@link OpenplatformDocApiDocTemplateParamFieldVO#dictGroupDictId} 和 {@link OpenplatformDocApiDocTemplateParamFieldVO#dictItemTags} 翻译而来
     */
    @Schema(description = "字典组对应的字典项")
    private List<OpenplatformDocParamFieldDictItemVO> dictItemVOs;
        
    @Schema(description = "排序")
    private Integer seq;


    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api_doc_template_param_field, byFieldName = "parentId", mapValueField = "name")
    private String parentName;

}