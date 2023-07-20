package com.particle.lowcode.client.generator.dto.data;

import java.time.LocalDateTime;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 低代码片段模板 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Data
@Schema
public class LowcodeSegmentTemplateVO extends AbstractBaseIdTreeVO {


    @Schema(description = "编码，唯一")
    private String code;

    @Schema(description = "模板名称，仅做展示")
    private String name;

    @Schema(description = "计算模板")
    private String computeTemplate;

    @Schema(description = "名称模板")
    private String nameTemplate;

    @Schema(description = "名称输出变量名")
    private String nameOutputVariable;

    @Schema(description = "内容模板")
    private String contentTemplate;

    @Schema(description = "引用模板id")
    private Long referenceSegmentTemplateId;

    @TransBy(tableName = TransTableNameConstants.component_lowcode_segment_template, byFieldName = "referenceSegmentTemplateId", mapValueField = "name")
    @Schema(description = "引用模板名称")
    private String referenceSegmentTemplateName;

    @Schema(description = "输出类型字典id，file=文件，dir=目录，segment=片段")
    private Long outputTypeDictId;

    @TransBy(tableName = TransConstants.TRANS_DICT_BY_ID,byFieldName = "outputTypeDictId",mapValueField = "name")
    @Schema(description = "模型表类型字典名称")
    private String outputTypeDictName;

    @Schema(description = "内容输出变量名")
    private String outputVariable;

    @Schema(description = "共享变量名，多个以逗号分隔，变量类型为Set<String>")
    private String shareVariables;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_lowcode_segment_template, byFieldName = "parentId", mapValueField = "name")
    private String parentName;

}
