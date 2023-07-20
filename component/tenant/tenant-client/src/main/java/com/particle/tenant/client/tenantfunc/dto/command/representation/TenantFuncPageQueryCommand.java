package com.particle.tenant.client.tenantfunc.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 租户功能菜单 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Data
@Schema
public class TenantFuncPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "功能id")
    private Long funcId;


    @Like
    @Schema(description = "名称,左前缀匹配")
    private String name;

    @Schema(description = "功能应用id")
    private Long funcApplicationId;

    @Schema(description = "租户id")
    private Long tenantId;


}
