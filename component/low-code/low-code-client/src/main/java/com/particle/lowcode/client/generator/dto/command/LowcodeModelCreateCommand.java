package com.particle.lowcode.client.generator.dto.command;

import java.time.LocalDateTime;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @NotEmpty(message = "实体名称")
    @ApiModelProperty(value = "实体名称",example = "首字母大写，符合java类名规范",required = true)
    private String nameEnEntity;

    @NotEmpty(message = "实体变量名称")
    @ApiModelProperty(value = "实体变量名称",example = "nameEnEntity 的首字母小写",required = true)
    private String nameEnEntityVar;

    @ApiModelProperty("表名称")
    private String tableName;

    @NotNull(message = "模型表类型字典id 不能为空")
    @ApiModelProperty(value = "模型表类型字典id，rel,tree,normal",required = true)
    private Long tableTypeDictId;

    @NotEmpty(message = "请求路径不能为空")
    @ApiModelProperty("请求路径")
    private String requestPath;

    @ApiModelProperty("描述,注意事项等")
    private String remark;
}
