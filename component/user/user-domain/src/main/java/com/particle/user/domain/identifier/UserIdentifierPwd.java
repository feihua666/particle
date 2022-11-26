package com.particle.user.domain.identifier;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户密码 领域模型
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Entity
public class UserIdentifierPwd extends AggreateRoot {

	private UserIdentifierPwdId id;
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


	/**
	 * 创建用户密码领域模型对象
	 * @return 用户密码领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static UserIdentifierPwd create(){
		return DomainFactory.create(UserIdentifierPwd.class);
	}
}
