package com.particle.openplatform.client.openapi.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 开放接口下载批量查询模板 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-09-18 14:30:24
 */
@Data
@Schema
public class OpenplatformOpenapiDownloadBatchQueryTemplateCommand extends AbstractBaseQueryCommand {


	@NotNull(message = "开放平台应用id 不能为空")
	@Schema(description = "开放平台应用id")
	private Long openplatformAppId;

	@NotNull(message = "开放接口id 不能为空")
	@Schema(description = "开放接口id")
	private Long openplatformOpenapiId;

}