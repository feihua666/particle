package com.particle.user.client.dto.data;

import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户 带角色翻译 响应对象
 * </p>
 *
 * @author yw
 * @since 2023-05-26 15:17:21
 */
@Data
@ApiModel
public class UserWithRoleVO extends UserVO {

    @TransBy(type = TransConstants.TRANS_ROLE_BY_USER_ID,byFieldName = "id",mapValueField = "name")
    @ApiModelProperty("角色名称")
    private String roleName;

}
