package com.particle.user.domain.identifier;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import com.particle.global.exception.Assert;
import com.particle.user.domain.enums.UserAccountType;
import com.particle.user.domain.gateway.UserDictGateway;
import lombok.Data;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录标识 领域模型
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Entity
public class UserIdentifier extends AggreateRoot {

	private UserDictGateway userDictGateway;


	private UserIdentifierId id;
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


	public void changeIdentityTypeDictIdByValueIfNeccesary(String identityTypeDictValue) {
		if (identityTypeDictId != null) {
			return;
		}
		Assert.notEmpty(identityTypeDictValue,"identityTypeDictValue 不能为空");

		Long dictIdByGroupCodeAndItemValue = userDictGateway.getDictIdByGroupCodeAndItemValue(UserAccountType.Group.user_account_type.groupCode(), identityTypeDictValue);
		identityTypeDictId = dictIdByGroupCodeAndItemValue;
	}

	/**
	 * 创建用户登录标识领域模型对象
	 * @return 用户登录标识领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static UserIdentifier create(){
		return DomainFactory.create(UserIdentifier.class);
	}

	public static UserIdentifier create(Long userId,String identifier,Long identityTypeDictId,String groupFlag){
		UserIdentifier userIdentifier = UserIdentifier.create();
		userIdentifier.setUserId(userId);
		userIdentifier.setIdentifier(identifier);
		userIdentifier.setIdentityTypeDictId(identityTypeDictId);
		userIdentifier.setGroupFlag(groupFlag);

		userIdentifier.setIsLock(false);
		userIdentifier.setIsExpired(false);
		userIdentifier.setIsExpired(false);


		return userIdentifier;
	}


	@Autowired
	public void setUserDictGateway(UserDictGateway userDictGateway) {
		this.userDictGateway = userDictGateway;
	}
}
