package com.particle.user.client.identifier.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class UserIdentifierVO extends AbstractBaseIdVO {


    @Schema(description = "用户ID")
    private Long userId;

    @TransBy(tableName = TransTableNameConstants.component_user, byFieldName = "userId", mapValueField = "nickname")
    @Schema(description = "用户昵称")
    private String userNickname;

    @Schema(description = "登录标识")
    private String identifier;

    @Schema(description = "授权类型,字典id")
    private Long identityTypeDictId;

    @Schema(description = "授权类型,字典名称")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "identityTypeDictId",mapValueField = "name")
    private String identityTypeDictName;

    @Schema(description = "授权类型,字典值")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "identityTypeDictId",mapValueField = "value")
    private String identityTypeDictValue;

    @Schema(description = "锁定状态，0=未锁定；1=锁定")
    private Boolean isLock;

    @Schema(description = "锁定原因")
    private String lockReason;

    @Schema(description = "unionId，支持第三方登录unionId")
    private String unionId;

    @Schema(description = "是否过期")
    private Boolean isExpired;

    @Schema(description = "过期原因")
    private String expiredReason;

    @Schema(description = "到期时间，为空永不到期")
    private LocalDateTime expireAt;

    @Schema(description = "分组标识")
    private String groupFlag;

    @Schema(description = "最后一次登录时间")
    private LocalDateTime lastLoginAt;

    @Schema(description = "最后一次登录ip")
    private String lastLoginIp;


}
