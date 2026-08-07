package com.particle.global.dto.login;

import com.particle.global.dto.basic.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 添加一个登录标识信息，用于全局可用，否则按原来的逻辑会导致部分服务在获取session时找不到类的情况
 * </p>
 *
 * @author yangwei
 * @since 2026/3/4 21:04
 */
@Data
@Schema(description = "登录标识信息")
public class UserIdentifierInfo extends DTO {

    @Schema(description = "登录标识 id")
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "登录标识")
    private String identifier;

    @Schema(description = "授权类型,字典id")
    private Long identityTypeDictId;

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

    public static UserIdentifierInfo create(Long id,
                                            Long userId,
                                            String identifier,
                                            Long identityTypeDictId,
                                            Boolean isLock,
                                            String lockReason,
                                            String unionId,
                                            Boolean isExpired,
                                            String expiredReason,
                                            LocalDateTime expireAt,
                                            String groupFlag,
                                            LocalDateTime lastLoginAt,
                                            String lastLoginIp){
        UserIdentifierInfo userIdentifierInfo = new UserIdentifierInfo();
        userIdentifierInfo.id = id;
        userIdentifierInfo.userId = userId;
        userIdentifierInfo.identifier = identifier;
        userIdentifierInfo.identityTypeDictId = identityTypeDictId;
        userIdentifierInfo.isLock = isLock;
        userIdentifierInfo.lockReason = lockReason;
        userIdentifierInfo.unionId = unionId;
        userIdentifierInfo.isExpired = isExpired;
        userIdentifierInfo.expiredReason = expiredReason;
        userIdentifierInfo.expireAt = expireAt;
        userIdentifierInfo.groupFlag = groupFlag;
        userIdentifierInfo.lastLoginAt = lastLoginAt;
        userIdentifierInfo.lastLoginIp = lastLoginIp;
        return userIdentifierInfo;
    }
}
