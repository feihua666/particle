package com.particle.tenant.app.executor;

import cn.hutool.core.util.StrUtil;
import com.particle.common.domain.event.TemplatingDomainMessageEvent;
import com.particle.tenant.domain.createapply.TenantCreateApplyAuditPassDomainEvent;
import com.particle.tenant.domain.createapply.gateway.TenantCreateApplyGateway;
import com.particle.tenant.domain.gateway.TenantDictGateway;
import com.particle.tenant.domain.gateway.TenantUserUserGateway;
import com.particle.user.domain.enums.UserAccountType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 租户、用户相关帮助类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Slf4j
@Component
@Validated
public class TenantUserHelper{


	private TenantCreateApplyGateway tenantCreateApplyGateway;

	private TenantDictGateway tenantDictGateway;

	private TenantUserUserGateway tenantUserUserGateway;

	/**
	 * 通知
	 * @param userId
	 * @param userEmail 通知目标，以邮箱通知
	 * @param userMobile 手机号，仅用在消息内容中
	 * @param password 初始密码
	 * @param sendUserId 发送人
	 * @param tenantIsFormal 租户是否正式
	 * @param tenantExpireAt 租户过期时间
	 */
	public void notify(Long userId,
					   String userEmail,
					   String userMobile,
					   String password,
					   Long sendUserId,
					   Boolean tenantIsFormal,
					   LocalDateTime tenantExpireAt
					   ) {
		String email = userEmail;
		if (StrUtil.isEmpty(email)) {
			Long dictIdByGroupCodeAndItemValue = tenantDictGateway.getDictIdByGroupCodeAndItemValue(UserAccountType.Group.user_account_type.groupCode(), UserAccountType.front_email.itemValue());
			tenantUserUserGateway.getIdentifierByUserIdAndType(userId, dictIdByGroupCodeAndItemValue);
		}
		if (StrUtil.isNotEmpty(email)) {
			TenantCreateApplyAuditPassDomainEvent.DataContent dataContent = TenantCreateApplyAuditPassDomainEvent.DataContent.create(
					// url为空请在模板中指定
					"",
					userEmail,
					password,
					userMobile,
					tenantIsFormal,
					tenantExpireAt
			);

			TenantCreateApplyAuditPassDomainEvent event = new TenantCreateApplyAuditPassDomainEvent(dataContent);
			TemplatingDomainMessageEvent templatingDomainMessageEvent = event.toTemplatingDomainMessageEvent(userId,sendUserId);
			tenantCreateApplyGateway.sendDomainEvent(templatingDomainMessageEvent);
		}else {
			log.warn("尝试根据邮箱发送模板消息，但用户邮箱为空，消息未发送 userId={}",userId);
		}
	}
	/**
	 * 判断用户登录标识是否为空，
	 * @param userEmail 邮箱
	 * @param userMobile 手机
	 * @return null=为空
	 */
	public Long userIdentifierExist(String userEmail,String userMobile) {
		Long applyUserId = null;
		if (StrUtil.isNotEmpty(userEmail)) {
			applyUserId = tenantUserUserGateway.getByUserIdentifier(userEmail);
		}
		if (applyUserId != null) {
			log.info("用户已存在，直接使用邮箱对应的id，email={}，userId={}", userEmail, applyUserId);
		}
		// 还为空根据手机号获取
		if (applyUserId == null) {
			if (StrUtil.isNotEmpty(userMobile)) {
				applyUserId = tenantUserUserGateway.getByUserIdentifier(userMobile);
			}
		}
		if (applyUserId != null) {
			log.info("用户已存在，直接使用手机号对应的id，mobile={}，userId={}", userMobile, applyUserId);
		}
		return applyUserId;
	}
	/**
	 * 创建用户
	 * @param userEmail
	 * @param userMobile
	 * @param userName
	 * @param password
	 * @return
	 */
	public Long createUser(String userEmail,String userMobile, String userName,String password,String createUserScene) {
		log.info("开始创建用户，email={}，mobile={}", userEmail, userMobile);
		List<TenantUserUserGateway.IdentifierParam> identifierParams = new ArrayList<>(2);


		// 如果邮箱不为空，直接以邮箱为账号
		if (StrUtil.isNotEmpty(userEmail)) {
			identifierParams.add(TenantUserUserGateway.IdentifierParam.create(userEmail,null, UserAccountType.front_email.itemValue()));
		}
		// 如果手机号不为空，以手机号为账号
		if (StrUtil.isNotEmpty(userMobile)) {
			identifierParams.add(TenantUserUserGateway.IdentifierParam.create(userMobile,null, UserAccountType.front_mobile.itemValue()));

		}
		String nickname = Optional.ofNullable(userName).orElse("未填写");


		Long applyUserId = tenantUserUserGateway.createUser(nickname, nickname, identifierParams, password, createUserScene);
		log.info("用户创建完成，userId={},password={}", applyUserId, password);
		return applyUserId;
	}

	/**
	 * 注入使用set方法
	 *
	 * @param tenantCreateApplyGateway
	 */
	@Autowired
	public void setTenantCreateApplyGateway(TenantCreateApplyGateway tenantCreateApplyGateway) {
		this.tenantCreateApplyGateway = tenantCreateApplyGateway;
	}

	@Autowired
	public void setTenantDictGateway(TenantDictGateway tenantDictGateway) {
		this.tenantDictGateway = tenantDictGateway;
	}

	@Autowired
	public void setTenantUserUserGateway(TenantUserUserGateway tenantUserUserGateway) {
		this.tenantUserUserGateway = tenantUserUserGateway;
	}
}
