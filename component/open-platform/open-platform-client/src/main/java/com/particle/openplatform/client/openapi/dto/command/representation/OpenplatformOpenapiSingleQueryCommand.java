package com.particle.openplatform.client.openapi.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * <p>
 * 开放接口单次查询 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-09-14 15:50:51
 */
@Data
@Schema
public class OpenplatformOpenapiSingleQueryCommand extends AbstractBaseQueryCommand {


	@NotNull(message = "开放平台应用id 不能为空")
	@Schema(description = "开放平台应用id")
	private Long openplatformAppId;

	@NotNull(message = "开放接口id 不能为空")
	@Schema(description = "开放接口id")
	private Long openplatformOpenapiId;

	/**
	 * 一般用于在http请求时，请求体参数
	 */
	@Schema(description = "开放接口请求体参数")
	private Map<String,Object> bodyParam;

	/**
	 * 一般用于在http请求时，在问题后面拼接的参数
	 */
	@Schema(description = "开放接口请求查询字符串参数")
	private Map<String,String> queryStringParam;

}