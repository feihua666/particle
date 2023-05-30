package com.particle.tenant.client.dto.command.representation;


import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 租户用户 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Data
@ApiModel
public class TenantUserQueryListCommand extends AbstractBaseQueryCommand {

	@ApiModelProperty("是否正式，1=正式，0=试用")
	private Boolean isFormal;



    @ApiModelProperty(value = "用户id")
    private Long userId;


    @Like
        @ApiModelProperty(value = "真实姓名,左前缀匹配")
    private String name;


    @ApiModelProperty(value = "是否过期")
    private Boolean isExpired;











}