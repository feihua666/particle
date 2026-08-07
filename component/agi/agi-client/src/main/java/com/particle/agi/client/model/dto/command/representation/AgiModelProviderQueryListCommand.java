package com.particle.agi.client.model.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * AI模型提供商 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@OrderBy("seq")
@Data
@Schema
public class AgiModelProviderQueryListCommand extends AbstractBaseQueryCommand {

    @Like
    @Schema(description = "提供商编码,左前缀匹配")
    private String code;

    @Like
    @Schema(description = "提供商显示名称,左前缀匹配")
    private String name;

    @Schema(description = "提供商类型")
    private Long typeDictId;

    @Schema(description = "是否禁用")
    private Boolean isDisabled;


}
