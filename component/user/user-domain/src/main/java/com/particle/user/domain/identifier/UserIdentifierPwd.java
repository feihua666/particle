package com.particle.user.domain.identifier;

import cn.hutool.core.util.StrUtil;
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

	/**
	 * 如果密码不为空表示要修改密码，将修改时间设置为当前时间
	 */
	public void changePwdModifiedAtForUpdate(){
		if (StrUtil.isNotEmpty(pwd)) {
			pwdModifiedAt = LocalDateTime.now();
		}
	}

	/**
	 * 创建用户密码领域模型对象
	 * @return 用户密码领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static UserIdentifierPwd create(){
		return DomainFactory.create(UserIdentifierPwd.class);
	}


	public static UserIdentifierPwd create(Long userId,
										   Long identifierId,
										   String pwd,
										   String pwdEncryptFlag,
										   Integer complexity,
										   Boolean isExpired,String expiredReason,LocalDateTime expireAt,
										   Boolean isNeedUpdate,String needUpdateMessage){
		UserIdentifierPwd userIdentifierPwd = create();
		userIdentifierPwd.setUserId(userId);
		userIdentifierPwd.setIdentifierId(identifierId);
		userIdentifierPwd.setPwd(pwd);

		userIdentifierPwd.setComplexity(complexity);
		userIdentifierPwd.setPwdEncryptFlag(pwdEncryptFlag);
		userIdentifierPwd.setIsNeedUpdate(isNeedUpdate);
		userIdentifierPwd.setNeedUpdateMessage(needUpdateMessage);
		userIdentifierPwd.setIsExpired(isExpired);
		userIdentifierPwd.setExpiredReason(expiredReason);
		userIdentifierPwd.setExpireAt(expireAt);


		userIdentifierPwd.setPwdModifiedAt(LocalDateTime.now());

		return userIdentifierPwd;
	}
}
