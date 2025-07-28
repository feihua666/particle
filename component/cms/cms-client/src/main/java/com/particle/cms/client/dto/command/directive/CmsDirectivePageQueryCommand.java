package com.particle.cms.client.dto.command.directive;

import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * cms指令分页查询命令
 * </p>
 *
 * @author yangwei
 * @since 2025/7/8 10:39
 */
@Data
@Schema
public class CmsDirectivePageQueryCommand  extends AbstractBaseTreePageQueryCommand {

    @Schema(description = "是否开启分页")
    private Boolean isPage = false;

    @Schema(description = "是否开启排序")
    private Boolean isOrderBy = false;

}
