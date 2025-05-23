package com.particle.dataconstraint.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 数据范围自定义数据关系 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Data
@Schema
public class DataScopeCustomDataRelPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "数据范围id")
    private Long dataScopeId;


    @Schema(description = "自定义数据id")
    private Long dataId;









}
