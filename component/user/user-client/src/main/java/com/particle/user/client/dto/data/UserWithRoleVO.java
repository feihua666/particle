package com.particle.user.client.dto.data;

import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class UserWithRoleVO extends UserVO {

    @TransBy(type = TransConstants.TRANS_ROLE_BY_USER_ID,byFieldName = "id",mapValueField = "name")
    @Schema(description = "角色名称")
    private String roleName;

}
