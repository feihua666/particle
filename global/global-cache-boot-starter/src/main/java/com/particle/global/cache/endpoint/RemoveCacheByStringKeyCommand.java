package com.particle.global.cache.endpoint;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 根据key删除缓存
 * </p>
 *
 * @author yangwei
 * @since 2023-10-09 13:28:24
 */
@Data
@Schema
public class RemoveCacheByStringKeyCommand extends CacheCommand {

	@NotEmpty(message = "缓存键值 不能为空")
	@Schema(description = "缓存键值")
	private String cacheKey;

}
