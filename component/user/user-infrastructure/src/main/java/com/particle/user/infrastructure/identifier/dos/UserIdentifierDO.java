package com.particle.user.infrastructure.identifier.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录标识表
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@TableName("component_user_identifier")
public class UserIdentifierDO extends BaseDO {
    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 登录标识
    */
    private String identifier;
    /**
    * 授权类型,字典id
    */
    private Long identityTypeDictId;
    /**
    * 锁定状态，0=未锁定；1=锁定
    */
    private Boolean isLock;
    /**
    * 锁定原因
    */
    private String lockReason;
    /**
    * unionId，支持第三方登录unionId
    */
    private String unionId;
    /**
    * 是否过期
    */
    private Boolean isExpired;
    /**
     * 过期原因
     */
    private String expiredReason;
    /**
     * 到期时间，为空永不到期
     */
    private LocalDateTime expireAt;

    /**
    * 分组标识
    */
    private String groupFlag;
    /**
    * 最后一次登录时间
    */
    private LocalDateTime lastLoginAt;
    /**
    * 最后一次登录ip
    */
    private String lastLoginIp;

}
