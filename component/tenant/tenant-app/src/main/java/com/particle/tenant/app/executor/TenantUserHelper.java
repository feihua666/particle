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
					   LocalDateTime tenantExpireAt,
					   Boolean isSendEmailNotice,
					   Boolean isSendMobileNotice
					   ) {

		boolean sendEmailNotice = isSendEmailNotice != null && isSendEmailNotice;
		boolean sendMobileNotice = isSendMobileNotice != null && isSendMobileNotice;
		String email = userEmail;
		if (sendEmailNotice) {
			if (StrUtil.isEmpty(email)) {
				// 邮箱为空获取用户已绑定的邮箱
				Long dictIdByGroupCodeAndItemValue = tenantDictGateway.getDictIdByGroupCodeAndItemValue(UserAccountType.Group.user_account_type.groupCode(), UserAccountType.front_email.itemValue());
				email = tenantUserUserGateway.getIdentifierByUserIdAndType(userId, dictIdByGroupCodeAndItemValue);
			}
		}else {
			log.info("尝试根据邮箱发送模板消息，但参数 sendEmailNotice={}，不要求发送，消息未发送 userId={}，email={}",sendEmailNotice,userId,email);
		}
		String mobile = userMobile;
		if (sendMobileNotice) {
			if (StrUtil.isEmpty(mobile)) {
				Long dictIdByGroupCodeAndItemValue = tenantDictGateway.getDictIdByGroupCodeAndItemValue(UserAccountType.Group.user_account_type.groupCode(), UserAccountType.front_mobile.itemValue());
				mobile = tenantUserUserGateway.getIdentifierByUserIdAndType(userId, dictIdByGroupCodeAndItemValue);
			}
		}else {
			log.info("尝试根据手机号发送模板消息，但参数 sendMobileNotice={}，不要求发送，消息未发送 userId={}，mobile={}",sendMobileNotice,userId,mobile);
		}

		TenantCreateApplyAuditPassDomainEvent.DataContent dataContent = TenantCreateApplyAuditPassDomainEvent.DataContent.create(
				// url为空请在模板中指定
				"",
				null,
				sendEmailNotice ? email : null,
				password,
				sendMobileNotice ? mobile: null,
				tenantIsFormal,
				tenantExpireAt
		);

		TenantCreateApplyAuditPassDomainEvent event = new TenantCreateApplyAuditPassDomainEvent(dataContent);
		TemplatingDomainMessageEvent templatingDomainMessageEvent = event.toTemplatingDomainMessageEvent(userId,sendUserId);
		tenantCreateApplyGateway.sendDomainEvent(templatingDomainMessageEvent);
	}
	/**
	 * 判断用户登录标识是否为空，
	 * @param userEmail 邮箱
	 * @param userMobile 手机
	 * @return null=为空
	 */
	public Long userIdentifierExist(String account, String userEmail,String userMobile) {
		Long applyUserId = null;
		if (StrUtil.isNotEmpty(account)) {
			applyUserId = tenantUserUserGateway.getByUserIdentifier(account);
		}
		if (applyUserId != null) {
			log.info("用户已存在，直接使用账号对应的id，accont={}，userId={}", account, applyUserId);
			return applyUserId;
		}

		// 根据邮箱获取
		if (StrUtil.isNotEmpty(userEmail)) {
			applyUserId = tenantUserUserGateway.getByUserIdentifier(userEmail);
		}
		if (applyUserId != null) {
			log.info("用户已存在，直接使用邮箱对应的id，email={}，userId={}", userEmail, applyUserId);
			return applyUserId;
		}
		// 还为空根据手机号获取
		if (StrUtil.isNotEmpty(userMobile)) {
			applyUserId = tenantUserUserGateway.getByUserIdentifier(userMobile);
		}
		if (applyUserId != null) {
			log.info("用户已存在，直接使用手机号对应的id，mobile={}，userId={}", userMobile, applyUserId);
			return applyUserId;
		}
		return applyUserId;
	}
	/**
	 * 创建用户
	 * @param userAccount
	 * @param userEmail
	 * @param userMobile
	 * @param userName
	 * @param password
	 * @return
	 */
	public Long createUser(String userAccount,String userEmail,String userMobile, String userName,String password,String createUserScene) {
		log.info("开始创建用户，account={}，email={}，mobile={}",userAccount, userEmail, userMobile);
		List<TenantUserUserGateway.IdentifierParam> identifierParams = new ArrayList<>(3);

		// 如果账号不为空，直接以账号为账号
		if (StrUtil.isNotEmpty(userAccount)) {
			identifierParams.add(TenantUserUserGateway.IdentifierParam.create(userAccount,null, UserAccountType.front_account.itemValue()));
		}
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
