package com.particle.openplatform.client.doc.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口目录名称 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Data
@Schema
public class OpenplatformDocDirNameVO extends AbstractBaseIdVO {

	@Schema(description = "编码，唯一")
	private String code;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "描述")
    private String remark;



}
