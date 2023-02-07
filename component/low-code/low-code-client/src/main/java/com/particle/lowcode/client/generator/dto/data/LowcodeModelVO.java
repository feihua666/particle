package com.particle.lowcode.client.generator.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
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

    @ApiModelProperty("表名称")
    private String tableName;

    @ApiModelProperty("模型类型，rel,tree,normal")
    private String tableType;

    @ApiModelProperty("描述,注意事项等")
    private String remark;


}
