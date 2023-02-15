package com.particle.lowcode.client.generator.dto.command.representation;


import java.time.LocalDateTime;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 低代码模型 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Data
@ApiModel
public class LowcodeModelQueryListCommand extends AbstractBaseQueryCommand {


    @Like
    @ApiModelProperty("名称")
    private String name;

    @Like
    @ApiModelProperty("英文名称")
    private String nameEn;

    @Like
    @ApiModelProperty("表名称")
    private String tableName;

    @ApiModelProperty("模型表类型字典id，rel,tree,normal")
    private Long tableTypeDictId;

}
