package com.particle.global.cache.endpoint;

import com.particle.global.dto.basic.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 缓存相关基础指令
 * </p>
 *
 * @author yangwei
 * @since 2023-10-09 13:28:24
 */
@Data
@Schema
public class CacheCommand extends Command {

	@NotEmpty(message = "缓存名称 不能为空")
	@Schema(description = "缓存名称")
	private String cacheName;

}
