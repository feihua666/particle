package com.particle.lowcode.client.generator.dto.command;

import java.time.LocalDateTime;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 低代码模型 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Data
@ApiModel
public class LowcodeModelCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "名称",required = true)
    private String name;

    @NotEmpty(message = "英文名称不能为空")
    @ApiModelProperty(value = "英文名称",required = true)
    private String nameEn;

    @ApiModelProperty("表名称")
    private String tableName;

    @NotEmpty(message = "模型类型不能为空")
    @ApiModelProperty(value = "模型类型，rel,tree,normal",required = true)
    private String tableType;

    @ApiModelProperty("描述,注意事项等")
    private String remark;
}
