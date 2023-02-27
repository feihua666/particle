package com.particle.lowcode.client.generator.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 低代码模型 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Data
@ApiModel
public class LowcodeModelVO extends AbstractBaseIdVO {


    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("英文名称")
    private String nameEn;

    @ApiModelProperty(value = "实体名称",example = "首字母大写，符合java类名规范")
    private String nameEnEntity;

    @ApiModelProperty(value = "实体变量名称",example = "nameEnEntity 的首字母小写")
    private String nameEnEntityVar;

    @ApiModelProperty("表名称")
    private String tableName;

    @ApiModelProperty("模型表类型字典id，rel,tree,normal")
    private Long tableTypeDictId;

    @TransBy(tableName = TransConstants.TRANS_DICT_BY_ID,byFieldName = "tableTypeDictId",mapValueField = "value")
    @ApiModelProperty("模型表类型字典值")
    private String tableTypeDictValue;

    @TransBy(tableName = TransConstants.TRANS_DICT_BY_ID,byFieldName = "tableTypeDictId",mapValueField = "name")
    @ApiModelProperty("模型表类型字典名称")
    private String tableTypeDictName;

    @ApiModelProperty("请求路径")
    private String requestPath;

    @ApiModelProperty("描述,注意事项等")
    private String remark;


}
