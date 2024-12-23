package com.particle.func.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 功能组 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Data
@Schema
public class FuncGroupPageQueryCommand extends AbstractBasePageQueryCommand {


    @Like
    @Schema(description = "编码，左前缀匹配")
    private String code;

    @Like
    @Schema(description = "名称，左前缀匹配")
    private String name;
}
