package com.particle.lowcode.client.generator.dto.data;

import java.time.LocalDateTime;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class LowcodeSegmentTemplateVO extends AbstractBaseIdTreeVO {


    @ApiModelProperty("编码，唯一")
    private String code;

    @ApiModelProperty("模板名称，仅做展示")
    private String name;

    @ApiModelProperty("名称模板")
    private String nameTemplate;

    @ApiModelProperty("内容模板")
    private String contentTemplate;

    @ApiModelProperty("引用模板id")
    private Long referenceSegmentTemplateId;

    @TransBy(tableName = TransTableNameConstants.component_lowcode_segment_template, byFieldName = "referenceSegmentTemplateId", mapValueField = "name")
    @ApiModelProperty("引用模板名称")
    private String referenceSegmentTemplateName;

    @ApiModelProperty("输出类型，file=文件，dir=目录，segment=片段")
    private String outputType;

    @ApiModelProperty("输出变量名")
    private String outputVariable;

    @ApiModelProperty("共享变量名，多个以逗号分隔，变量类型为List<String>")
    private String shareVariables;

    @ApiModelProperty("描述")
    private String remark;

    @ApiModelProperty("父级名称")
    @TransBy(tableName = TransTableNameConstants.component_lowcode_segment_template, byFieldName = "parentId", mapValueField = "name")
    private String parentName;

}
