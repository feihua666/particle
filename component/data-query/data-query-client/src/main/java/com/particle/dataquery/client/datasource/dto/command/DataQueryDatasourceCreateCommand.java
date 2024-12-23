package com.particle.dataquery.client.datasource.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 数据查询数据源 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Data
@Schema
public class DataQueryDatasourceCreateCommand extends AbstractBaseCommand {



    @Schema(description = "编码")
    private String code;


    @NotEmpty(message = "名称 不能为空")
    @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "类型 不能为空")
    @Schema(description = "类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long typeDictId;


    @NotEmpty(message = "json配置 不能为空")
    @Schema(description = "json配置",requiredMode = Schema.RequiredMode.REQUIRED)
    private String configJson;


    @Schema(description = "用户名")
    private String username;


    @Schema(description = "密码")
    private String password;


    @NotNull(message = "数据查询供应商id 不能为空")
    @Schema(description = "数据查询供应商id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataQueryProviderId;


    @Schema(description = "描述")
    private String remark;

}
