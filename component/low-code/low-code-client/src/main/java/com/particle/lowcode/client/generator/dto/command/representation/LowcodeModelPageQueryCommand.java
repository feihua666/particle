package com.particle.lowcode.client.generator.dto.command.representation;

import java.time.LocalDateTime;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 低代码模型 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Data
@ApiModel
public class LowcodeModelPageQueryCommand extends AbstractBasePageQueryCommand {


    @Like
    @ApiModelProperty("名称")
    private String name;

    @Like
    @ApiModelProperty("表名称")
    private String tableName;

    @ApiModelProperty("模型类型，rel,tree,normal")
    private String tableType;

}
