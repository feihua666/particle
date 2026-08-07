package com.particle.agi.client.model.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * AI模型 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@OrderBy("seq")
@Data
@Schema
public class AgiAiModelPageQueryCommand extends AbstractBasePageQueryCommand {



    @Like
        @Schema(description = "模型编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "模型显示名称,左前缀匹配")
    private String name;


    @Schema(description = "模型类型")
    private Long typeDictId;





    @Schema(description = "是否禁用")
    private Boolean isDisabled;


    @Schema(description = "是否为默认模型")
    private Boolean isDefault;



    @Schema(description = "所属提供商ID")
    private Long agiModelProviderId;











}
