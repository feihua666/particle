package com.particle.openplatform.client.app.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台应用 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Data
@Schema
public class OpenplatformAppQueryListCommand extends AbstractBaseQueryCommand {



    @Like
        @Schema(description = "名称,左前缀匹配")
    private String name;


    @Schema(description = "appId")
    private String appId;


    @Schema(description = "归属用户id")
    private Long ownerUserId;


    @Schema(description = "归属客户id")
    private Long ownerCustomerId;





    @Schema(description = "计费id")
    private Long openplatformOpenapiFeeId;

	@Schema(description = "限制规则id，不配置不限制，应用级限制")
	private Long openplatformOpenapiLimitRuleId;

	@Schema(description = "是否禁用")
	private Boolean isDisabled;

	@Schema(description = "禁用原因")
	private String disabledReason;

	@Schema(description = "是否检查签名,主要用于在oauth2 token时可以不检查")
	private Boolean isCheckSignature;









}
