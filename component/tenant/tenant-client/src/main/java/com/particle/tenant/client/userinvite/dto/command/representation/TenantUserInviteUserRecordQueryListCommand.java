package com.particle.tenant.client.userinvite.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * <p>
 * 租户用户邀请记录 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Data
@ApiModel
public class TenantUserInviteUserRecordQueryListCommand extends AbstractBaseQueryCommand {



    @ApiModelProperty(value = "租户用户邀请id")
    private Integer tenantUserInviteId;


    @ApiModelProperty(value = "被邀请人用户id")
    private Long invitedUserId;











}
