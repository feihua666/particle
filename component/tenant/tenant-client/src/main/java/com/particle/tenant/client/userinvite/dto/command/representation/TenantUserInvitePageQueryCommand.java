package com.particle.tenant.client.userinvite.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * <p>
 * 租户用户邀请 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Data
@ApiModel
public class TenantUserInvitePageQueryCommand extends AbstractBasePageQueryCommand {



    @ApiModelProperty(value = "邀请码")
    private String inviteCode;




    @ApiModelProperty(value = "是否过期")
    private Boolean isExpired;




    @ApiModelProperty(value = "邀请人用户id")
    private Long inviterUserId;









}
