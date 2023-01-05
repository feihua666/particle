package com.particle.user.client.identifier.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录标识 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@ApiModel
public class UserIdentifierVO extends AbstractBaseIdVO {


    @ApiModelProperty("用户ID")
    private Long userId;

    @TransBy(tableName = TransTableNameConstants.component_user, byFieldName = "userId", mapValueField = "nickname")
    @ApiModelProperty("用户昵称")
    private String userNickname;

    @ApiModelProperty("登录标识")
    private String identifier;

    @ApiModelProperty("授权类型,字典id")
    private Long identityTypeDictId;

    @ApiModelProperty("授权类型,字典名称")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "identityTypeDictId",mapValueField = "name")
    private String identityTypeDictName;

    @ApiModelProperty("锁定状态，0=未锁定；1=锁定")
    private Boolean isLock;

    @ApiModelProperty("锁定原因")
    private String lockReason;

    @ApiModelProperty("unionId，支持第三方登录unionId")
    private String unionId;

    @ApiModelProperty("是否过期")
    private Boolean isExpired;

    @ApiModelProperty("过期原因")
    private String expiredReason;

    @ApiModelProperty("到期时间，为空永不到期")
    private LocalDateTime expireAt;

    @ApiModelProperty("分组标识")
    private String groupFlag;

    @ApiModelProperty("最后一次登录时间")
    private LocalDateTime lastLoginAt;

    @ApiModelProperty("最后一次登录ip")
    private String lastLoginIp;


}
