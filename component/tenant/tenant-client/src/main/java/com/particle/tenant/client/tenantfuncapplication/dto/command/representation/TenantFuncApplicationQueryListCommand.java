package com.particle.tenant.client.tenantfuncapplication.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 租户功能应用 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Data
@ApiModel
public class TenantFuncApplicationQueryListCommand extends AbstractBaseQueryCommand {



    @ApiModelProperty(value = "功能应用id")
    private Long funcApplicationId;


    @Like
        @ApiModelProperty(value = "名称,左前缀匹配")
    private String name;


    @ApiModelProperty(value = "应用主题")
    private String applicationTheme;











}
