package com.particle.openplatform.client.openapi.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Data
@Schema
public class OpenplatformOpenapiPageQueryCommand extends AbstractBaseTreePageQueryCommand {

    @Like
    @Schema(description = "编码,左前缀匹配")
    private String code;

    @Like
    @Schema(description = "名称,左前缀匹配")
    private String name;

    @Schema(description = "是否为组")
    private Boolean isGroup;

    @Like
    @Schema(description = "接口地址,左前缀匹配")
    private String url;

    @Schema(description = "是否禁用")
    private Boolean isDisabled;

    @Schema(description = "默认计费id")
    private Long defaultOpenplatformOpenapiFeeId;

	@Schema(description = "可用供应商")
	private String openplatformProviderIds;

}
