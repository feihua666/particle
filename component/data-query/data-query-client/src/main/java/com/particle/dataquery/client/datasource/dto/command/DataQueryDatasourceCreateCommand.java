package com.particle.dataquery.client.datasource.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @Schema(description = "名称",required = true)
    private String name;


    @NotNull(message = "类型 不能为空")
    @Schema(description = "类型",required = true)
    private Long typeDictId;


    @NotEmpty(message = "json配置 不能为空")
    @Schema(description = "json配置",required = true)
    private String configJson;


    @Schema(description = "用户名")
    private String username;


    @Schema(description = "密码")
    private String password;


    @NotNull(message = "数据查询供应商id 不能为空")
    @Schema(description = "数据查询供应商id",required = true)
    private Long dataQueryProviderId;


    @Schema(description = "描述")
    private String remark;

}
