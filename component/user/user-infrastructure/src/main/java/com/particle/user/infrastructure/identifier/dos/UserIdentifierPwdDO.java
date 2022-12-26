package com.particle.user.infrastructure.identifier.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 用户密码表
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@TableName("component_user_identifier_pwd")
public class UserIdentifierPwdDO extends BaseDO {
    /**
    * 用户id
    */
    private Long userId;
    /**
    * 用户标识id
    */
    private Long identifierId;
    /**
    * 密码
    */
    private String pwd;
    /**
    * 密码加密方式标识
    */
    private String pwdEncryptFlag;
    /**
    * 是否过期，过期后该密码不能登录
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
    * 是否需要提示修改密码
    */
    private Boolean isNeedUpdate;
    /**
     * 提示修改密码消息内容
     */
    private String needUpdateMessage;
    /**
    * 密码的修改时间
    */
    private LocalDateTime pwdModifiedAt;
    /**
    * 复杂度，数字越高越复杂，取值 1-100
    */
    private Integer complexity;
    /**
    * 分组标识
    */
    private String groupFlag;

}
