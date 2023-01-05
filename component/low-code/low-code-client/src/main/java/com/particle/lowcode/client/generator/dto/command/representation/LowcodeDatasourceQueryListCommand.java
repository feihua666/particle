package com.particle.lowcode.client.generator.dto.command.representation;


import java.time.LocalDateTime;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 低代码数据源 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Data
@ApiModel
public class LowcodeDatasourceQueryListCommand extends AbstractBaseQueryCommand {

    @Like
    @ApiModelProperty("编码")
    private String code;

    @Like
    @ApiModelProperty("名称")
    private String name;

    @Like
    @ApiModelProperty("用户名")
    private String username;

}
