package com.particle.agi.client.model.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * AI模型 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@OrderBy("seq")
@Data
@Schema
public class AgiAiModelQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "模型编码")
    private String code;


    @Schema(description = "模型显示名称")
    private String name;


    @Schema(description = "模型类型")
    private Long typeDictId;





    @Schema(description = "是否禁用")
    private Boolean isDisabled;


    @Schema(description = "是否为默认模型")
    private Boolean isDefault;



    @Schema(description = "所属提供商ID")
    private Long agiModelProviderId;


    @Schema(description = "排序")
    private Integer seq;










}
