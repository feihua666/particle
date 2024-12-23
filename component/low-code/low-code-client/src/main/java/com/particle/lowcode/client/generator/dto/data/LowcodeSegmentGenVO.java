package com.particle.lowcode.client.generator.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 低代码生成 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-02-08
 */
@Data
@Schema
public class LowcodeSegmentGenVO extends AbstractBaseIdVO {


    @Schema(description = "生成名称，一般用于显示标识")
    private String name;

    @Schema(description = "低代码片段模板id")
    private Long lowcodeSegmentTemplateId;

    @TransBy(tableName = TransTableNameConstants.component_lowcode_segment_template, byFieldName = "lowcodeSegmentTemplateId", mapValueField = "name")
    @Schema(description = "低代码片段模板名称")
    private String lowcodeSegmentTemplateName;

    @Schema(description = "低代码模型id")
    private Long lowcodeModelId;

    @TransBy(tableName = TransTableNameConstants.component_lowcode_model, byFieldName = "lowcodeModelId", mapValueField = "name")
    @Schema(description = "模型名称")
    private String lowcodeModelName;

    @Schema(description = "低代码模型数据包括模型项数据")
    private String lowcodeModelJson;

    @Schema(description = "全局可用变量json数据")
    private String globalJson;

    @Schema(description = "扩展可用变量json数据")
    private String extJson;

    @Schema(description = "输出文件的父目录绝对路径")
    private String outputFileParentAbsoluteDir;

    @Schema(description = "java包名的key，根据key可以自动将对应的值转为后缀javaPath路径")
    private String javaPackageKeys;

    @Schema(description = "是否已生成")
    private Boolean isGenerated;

    @Schema(description = "生成类型字典id")
    private Long generateTypeDictId;


    @TransBy(tableName = TransConstants.TRANS_DICT_BY_ID,byFieldName = "generateTypeDictId",mapValueField = "value")
    @Schema(description = "生成类型，字典值")
    private String generateTypeDictValue;

    @TransBy(tableName = TransConstants.TRANS_DICT_BY_ID,byFieldName = "generateTypeDictId",mapValueField = "name")
    @Schema(description = "生成类型，字典名称")
    private String generateTypeDictName;

    @Schema(description = "引用生成id，主要用来获取引用的相关变量")
    private Long refrenceSegmentGenId;

    @TransBy(tableName = TransTableNameConstants.component_lowcode_segment_gen, byFieldName = "refrenceSegmentGenId", mapValueField = "name")
    @Schema(description = "引用生成名称")
    private String refrenceSegmentGenName;

    @Schema(description = "描述,注意事项等")
    private String remark;


}
