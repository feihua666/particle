package com.particle.openplatform.client.app.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台应用与开放接口配置 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Data
@Schema
public class OpenplatformAppOpenapiQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "开放平台应用id")
    private Long openplatformAppId;


    @Schema(description = "开放接口id")
    private Long openplatformOpenapiId;


    @Schema(description = "计费id")
    private Long openplatformOpenapiFeeId;

	@Schema(description = "限制规则id，不配置不限制，应用和接口级限制")
	private Long openplatformOpenapiLimitRuleId;

	@Schema(description = "指定供应商，如果不指定将按默认编码调用")
	private Long openplatformProviderId;

	@Schema(description = "描述")
	private String remark;









}
