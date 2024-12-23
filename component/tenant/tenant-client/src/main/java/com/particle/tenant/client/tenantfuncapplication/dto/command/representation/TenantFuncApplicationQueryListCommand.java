package com.particle.tenant.client.tenantfuncapplication.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 租户功能应用 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Data
@Schema
public class TenantFuncApplicationQueryListCommand extends AbstractBaseQueryCommand {

    @Schema(description = "功能应用id")
    private Long funcApplicationId;

    @Like
        @Schema(description = "名称,左前缀匹配")
    private String name;

    @Schema(description = "应用主题")
    private String applicationTheme;

    @Schema(description = "租户id")
    private Long tenantId;
}
