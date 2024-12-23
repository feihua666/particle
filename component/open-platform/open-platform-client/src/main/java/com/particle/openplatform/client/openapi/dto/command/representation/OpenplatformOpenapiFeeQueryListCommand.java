package com.particle.openplatform.client.openapi.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口费用 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Data
@Schema
public class OpenplatformOpenapiFeeQueryListCommand extends AbstractBaseQueryCommand {

    @Like
	@Schema(description = "费用名称，可以理解为类似一个套餐")
	private String name;

    @Schema(description = "单价")
    private Integer price;

    @Schema(description = "计费方式")
    private Long feeTypeDictId;

    @Schema(description = "去重方式")
    private Long deduplicateTypeDictId;

    @Schema(description = "是否按请求参数去重")
    private Boolean isDeduplicateByParameter;

    @Schema(description = "是否检查是否返回值")
    private Boolean isCheckHasValue;

    @Schema(description = "是否检查处理时长")
    private Boolean isCheckHandleDuration;

    @Schema(description = "处理时长")
    private Integer handleDuration;
}
