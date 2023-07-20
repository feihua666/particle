package com.particle.lowcode.client.generator.dto.command.representation;


import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class LowcodeDatasourceQueryListCommand extends AbstractBaseQueryCommand {

    @Like
    @Schema(description = "编码")
    private String code;

    @Like
    @Schema(description = "名称")
    private String name;

    @Like
    @Schema(description = "用户名")
    private String username;

}
