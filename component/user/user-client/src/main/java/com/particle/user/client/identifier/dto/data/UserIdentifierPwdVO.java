package com.particle.user.client.identifier.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户密码 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Schema
public class UserIdentifierPwdVO extends AbstractBaseIdVO {


    @Schema(description = "用户id")
    private Long userId;

    @TransBy(tableName = TransTableNameConstants.component_user, byFieldName = "userId", mapValueField = "nickname")
    @Schema(description = "用户昵称")
    private String userNickname;

    @Schema(description = "用户标识id")
    private Long identifierId;

    @TransBy(tableName = TransTableNameConstants.component_user_identifier, byFieldName = "identifierId", mapValueField = "identifier")
    @Schema(description = "登录标识")
    private String userIdentifier;

    @Schema(description = "密码加密方式标识")
    private String pwdEncryptFlag;

    @Schema(description = "是否过期，过期后该密码不能登录")
    private Boolean isExpired;

    @Schema(description = "过期原因")
    private String expiredReason;

    @Schema(description = "到期时间，为空永不到期")
    private LocalDateTime expireAt;

    @Schema(description = "是否需要提示修改密码")
    private Boolean isNeedUpdate;

    @Schema(description = "提示修改密码消息内容")
    private String needUpdateMessage;

    @Schema(description = "密码的修改时间")
    private LocalDateTime pwdModifiedAt;

    @Schema(description = "复杂度，数字越高越复杂，取值 1-100")
    private Integer complexity;

    @Schema(description = "分组标识")
    private String groupFlag;


}
