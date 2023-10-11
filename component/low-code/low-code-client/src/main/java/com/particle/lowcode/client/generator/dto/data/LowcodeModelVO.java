package com.particle.lowcode.client.generator.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class LowcodeModelVO extends AbstractBaseIdVO {


    @Schema(description = "名称")
    private String name;

    @Schema(description = "英文名称")
    private String nameEn;

    @Schema(title = "实体名称",description = "首字母大写，符合java类名规范")
    private String nameEnEntity;

    @Schema(title = "实体变量名称",description = "nameEnEntity 的首字母小写")
    private String nameEnEntityVar;

    @Schema(description = "表名称")
    private String tableName;

    @Schema(description = "模型表类型字典id，rel,tree,normal")
    private Long tableTypeDictId;

    @TransBy(tableName = TransConstants.TRANS_DICT_BY_ID,byFieldName = "tableTypeDictId",mapValueField = "value")
    @Schema(description = "模型表类型字典值")
    private String tableTypeDictValue;

    @TransBy(tableName = TransConstants.TRANS_DICT_BY_ID,byFieldName = "tableTypeDictId",mapValueField = "name")
    @Schema(description = "模型表类型字典名称")
    private String tableTypeDictName;

    @Schema(description = "请求路径")
    private String requestPath;

    @Schema(description = "建表语句，加载模型项后有值")
    private String tableCreateSql;

    @Schema(description = "描述,注意事项等")
    private String remark;


}
