package com.particle.tenant.client.tenantfunc.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 租户功能菜单 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Data
@ApiModel
public class TenantFuncQueryListCommand extends AbstractBaseQueryCommand {



    @ApiModelProperty(value = "功能id")
    private Long funcId;


    @Like
        @ApiModelProperty(value = "名称,左前缀匹配")
    private String name;









}