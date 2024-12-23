package com.particle.dataquery.client.datasource.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 数据查询数据源 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Data
@Schema
public class DataQueryDatasourcePageQueryCommand extends AbstractBasePageQueryCommand {

    @Like(left = true,right = true)
    @Schema(description = "编码,模糊匹配")
    private String code;

    @Like(left = true,right = true)
    @Schema(description = "名称,模糊匹配")
    private String name;

    @Schema(description = "类型")
    private Long typeDictId;

    @Like(left = true,right = true)
    @Schema(description = "json配置,模糊匹配")
    private String configJson;

    @Like(left = true,right = true)
    @Schema(description = "用户名,模糊匹配")
    private String username;

    @Schema(description = "数据查询供应商")
    private Long dataQueryProviderId;

}
