package com.particle.tenant.client.dto.command.representation;


import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
/**
 * <p>
 * 租户 全部查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-12-29 16:49:47
 */
@Data
@Schema
public class TenantQueryAllCommand extends AbstractBaseQueryCommand {
    /**
     * 过滤租户ids，即，只返回这些租户
     */
    @Schema(description = "过滤租户ids")
    private List<Long> filterTenantIds;


    public static TenantQueryAllCommand createEmpty() {
        return new TenantQueryAllCommand();
    }
}
