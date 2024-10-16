package com.particle.openplatform.client.openapi.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口限制规则 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Data
@Schema
public class OpenplatformOpenapiLimitRuleQueryListCommand extends AbstractBaseQueryCommand {



    @Like
    @Schema(description = "限制名称，左前缀匹配")
    private String name;


    @Schema(description = "限制方式")
    private Long limitTypeDictId;


    @Schema(description = "限制条数")
    private Integer limitCount;


    @Schema(description = "限制金额费用")
    private Integer limitFee;


    @Schema(description = "限制周期")
    private Long limitPeriodDictId;


    @Schema(description = "限制速率")
    private Integer limitRate;










}
