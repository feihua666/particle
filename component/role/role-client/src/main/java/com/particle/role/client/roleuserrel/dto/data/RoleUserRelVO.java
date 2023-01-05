package com.particle.role.client.roleuserrel.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 角色用户关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@ApiModel
public class RoleUserRelVO extends AbstractBaseIdVO {


    @ApiModelProperty("用户id")
    private Long userId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "userId",mapValueField = "nickname")
    @ApiModelProperty("用户昵称")
    private String userNickname;

    @ApiModelProperty("角色id")
    private Long roleId;

    @TransBy(tableName = TransTableNameConstants.component_role, byFieldName = "roleId", mapValueField = "name")
    @ApiModelProperty("角色名称")
    private String roleName;

}
