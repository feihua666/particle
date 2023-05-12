package com.particle.tenant.client.tenantfunc.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 租户功能菜单 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Data
@ApiModel
public class TenantFuncPageQueryCommand extends AbstractBasePageQueryCommand {



    @ApiModelProperty(value = "功能id")
    private Long funcId;


    @Like
    @ApiModelProperty(value = "名称,左前缀匹配")
    private String name;

    @ApiModelProperty(value = "功能应用id")
    private Long funcApplicationId;







}
