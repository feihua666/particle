package com.particle.usagecount.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 使用次数配置 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Data
@Schema
public class UsageCountConfigQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "名称")
    private String name;


    @Schema(description = "使用次数定义id")
    private Long usageCountDefineId;



    @Schema(description = "限制规则类型字典id")
    private Long limitRuleTypeDictId;


    @Schema(description = "限制周期字典id")
    private Long limitPeriodDictId;

	@Schema(description = "限制租户id，如果为空代表是全局的设置")
	private Long limitTenantId;










}