package com.particle.openplatform.client.openapi.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * <p>
 * 开放接口批量查询 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-09-18 14:30:24
 */
@Data
@Schema
public class OpenplatformOpenapiBatchQueryCommand extends AbstractBaseQueryCommand {


	@NotNull(message = "开放平台应用id 不能为空")
	@Schema(description = "开放平台应用id")
	private Long openplatformAppId;

	@NotNull(message = "开放接口id 不能为空")
	@Schema(description = "开放接口id")
	private Long openplatformOpenapiId;


	@NotNull(message = "请选择要上传的文件")
	@Schema(description = "上传的文件",requiredMode = Schema.RequiredMode.REQUIRED,type = "org.springframework.web.multipart.MultipartFile")
	private MultipartFile file;

	@NotEmpty(message = "备注 不能为空")
	@Schema(description = "备注",requiredMode = Schema.RequiredMode.REQUIRED)
	private String remark;

	@NotNull(message = "用户id 不能为空")
	@Schema(description = "用户id",requiredMode = Schema.RequiredMode.REQUIRED,hidden = true)
	private Long userId;


}