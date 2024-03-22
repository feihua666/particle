package com.particle.openplatform.client.doc.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 开放接口文档参数字段 数据通用响应对象
 * 为了在 {@link OpenplatformDocApiDetailVO} 中使用提取一个基础VO，因为在模板合并到文档时，两个数据本身不是一个表，在翻译parentName时肯定是有问题的，干脆直接去掉
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Data
@Schema
public class OpenplatformDocApiDocParamFieldBasicVO extends AbstractBaseIdTreeVO {

    @Schema(description = "字段名称")
    private String name;
    
    @Schema(description = "字段类型")
    private Long typeDictId;


    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "字段类型对应字典名称")
    private String typeDictName;

    @Schema(description = "嵌套字段类型，字典id，一般用于字段类型为array时里面的类型，如：string、object")
    private Long nestTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "nestTypeDictId",mapValueField = "name")
    @Schema(description = "字段类型对应字典名称")
    private String nestTypeDictName;

    @Schema(description = "是否一定有值")
    private Boolean isRequired;
    
    @Schema(description = "字段说明")
    private String explanation;

	@Schema(description = "开放接口文档接口id")
	private Long openplatformDocApiId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api, byFieldName = "openplatformDocApiId", mapValueField = "code")
    @Schema(description = "开放接口文档接口编码")
    private String openplatformDocApiCode;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api, byFieldName = "openplatformDocApiId", mapValueField = "name")
    @Schema(description = "开放接口文档接口名称")
    private String openplatformDocApiName;
    
    @Schema(description = "开放接口文档id")
    private Long openplatformDocApiDocId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api_doc, byFieldName = "openplatformDocApiDocId", mapValueField = "requestUrl")
    @Schema(description = "开放接口文档请求地址")
    private String openplatformDocApiDocRequestUrl;

    @Schema(description = "分类")
    private Long categoryDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "categoryDictId",mapValueField = "name")
    @Schema(description = "分类对应字典名称")
    private String categoryDictName;

    @Schema(description = "默认值")
    private String defaultValue;

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
     * 由{@link OpenplatformDocApiDocParamFieldBasicVO#dictGroupDictId} 和 {@link OpenplatformDocApiDocParamFieldBasicVO#dictItemTags} 翻译而来
     */
    @Schema(description = "字典组对应的字典项")
    private List<OpenplatformDocParamFieldDictItemVO> dictItemVOs;

    @Schema(description = "排序")
    private Integer seq;


    /**
     * 根据模板创建，体现关键字段
     * @param docApiDocTemplateParamFieldVO
     * @return
     */
    public static OpenplatformDocApiDocParamFieldBasicVO createByOpenplatformDocApiDocTemplateParamFieldVO(OpenplatformDocApiDocTemplateParamFieldVO docApiDocTemplateParamFieldVO) {
        if (docApiDocTemplateParamFieldVO == null) {
            return null;
        }
        OpenplatformDocApiDocParamFieldBasicVO openplatformDocApiDocParamFieldBasicVO = new OpenplatformDocApiDocParamFieldBasicVO();
        openplatformDocApiDocParamFieldBasicVO.name = docApiDocTemplateParamFieldVO.getName();
        openplatformDocApiDocParamFieldBasicVO.typeDictId = docApiDocTemplateParamFieldVO.getTypeDictId();
        openplatformDocApiDocParamFieldBasicVO.nestTypeDictId = docApiDocTemplateParamFieldVO.getNestTypeDictId();
        openplatformDocApiDocParamFieldBasicVO.isRequired = docApiDocTemplateParamFieldVO.getIsRequired();
        openplatformDocApiDocParamFieldBasicVO.explanation = docApiDocTemplateParamFieldVO.getExplanation();
        openplatformDocApiDocParamFieldBasicVO.categoryDictId = docApiDocTemplateParamFieldVO.getCategoryDictId();
        openplatformDocApiDocParamFieldBasicVO.defaultValue = docApiDocTemplateParamFieldVO.getDefaultValue();
        openplatformDocApiDocParamFieldBasicVO.maxLength = docApiDocTemplateParamFieldVO.getMaxLength();
        openplatformDocApiDocParamFieldBasicVO.dictGroupDictId = docApiDocTemplateParamFieldVO.getDictGroupDictId();
        openplatformDocApiDocParamFieldBasicVO.dictItemTags = docApiDocTemplateParamFieldVO.getDictItemTags();
        openplatformDocApiDocParamFieldBasicVO.seq = docApiDocTemplateParamFieldVO.getSeq();
        return openplatformDocApiDocParamFieldBasicVO;

    }

}