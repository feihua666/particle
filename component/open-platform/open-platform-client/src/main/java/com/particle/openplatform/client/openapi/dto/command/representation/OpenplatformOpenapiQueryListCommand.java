package com.particle.openplatform.client.openapi.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 开放平台开放接口 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Data
@Schema
public class OpenplatformOpenapiQueryListCommand extends AbstractBaseTreeQueryCommand {


	@Like(left = true,right = true)
	@Schema(description = "编码,模糊匹配")
	private String code;

	@Like(left = true,right = true)
	@Schema(description = "名称,模糊匹配")
	private String name;

	@Schema(description = "是否为组")
	private Boolean isGroup;

	@Like(left = true,right = true)
	@Schema(description = "接口地址,模糊匹配")
	private String url;

	@Schema(description = "是否禁用")
	private Boolean isDisabled;

	@Schema(description = "默认计费id")
	private Long defaultOpenplatformOpenapiFeeId;

	@Schema(description = "供应商配置json")
	private String providerConfigJson;

	/**
	 * 可根据应用接口配置关系过滤配置中的开放平台接口，仅根据该字段查询
	 */
	@Schema(description = "开放平台应用id")
	private Long openplatformAppId;

	/**
	 * 可根据应用接口配置关系过滤配置中的开放平台接口
	 */
	@Schema(description = "开放平台应用id过滤，不过滤接口组")
	private Long filterOpenplatformAppId;


}
