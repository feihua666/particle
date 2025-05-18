package com.particle.dataquery.client.datasource.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 数据查询数据源 重新加载指令对象
 * </p>
 *
 * @author yw
 * @since 2025-05-13 12:21:45
 */
@Data
@Schema
public class DataQueryDatasourceReloadCommand extends AbstractBaseUpdateCommand {

    @Schema(description = "是否仅删除")
    private Boolean isRemoveOnly;

}
