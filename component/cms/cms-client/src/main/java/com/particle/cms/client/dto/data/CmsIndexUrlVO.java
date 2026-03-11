package com.particle.cms.client.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 通用首页地址 响应对象
 * </p>
 *
 * @author yw
 * @since 2026-01-20 22:23:51
 */
@Data
@Schema
public class CmsIndexUrlVO extends VO {

    @Schema(description = "动态首页地址")
    private String dynamicIndexUrl;

	@Schema(description = "动态预览首页地址")
	private String dynamicPreviewIndexUrl;
}
