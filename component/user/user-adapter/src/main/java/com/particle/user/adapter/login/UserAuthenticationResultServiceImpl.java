package com.particle.user.adapter.login;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.particle.common.constant.CommonConstants;
import com.particle.component.light.share.dict.UserEffectiveAtTrigger;
import com.particle.global.dto.response.Response;
import com.particle.global.mybatis.plus.config.GlobalMybatisExecutorsConfig;
import com.particle.global.security.security.login.IAuthenticationResultService;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.tenant.TenantTool;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.log.TraceTool;
import com.particle.global.tool.logical.TimeLogicTool;
import com.particle.global.tool.servlet.RequestTool;
import com.particle.global.tool.thread.ThreadContextTool;
import com.particle.user.domain.gateway.UserDictGateway;
import com.particle.user.infrastructure.dos.UserDO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import com.particle.user.infrastructure.login.dos.UserLoginDeviceDO;
import com.particle.user.infrastructure.login.dos.UserLoginRecordDO;
import com.particle.user.infrastructure.login.service.IUserLoginDeviceService;
import com.particle.user.infrastructure.login.service.IUserLoginRecordService;
import com.particle.user.infrastructure.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * <p>
 * identifier 登录事件
 * </p>
 *
 * @author yangwei
 * @since 2021-09-28 10:26
 */
@Slf4j
@Component
public class UserAuthenticationResultServiceImpl implements IAuthenticationResultService {

	public static final String login_header_device_id = CommonConstants.request_header_device_id;

	@Autowired
	private IUserIdentifierService iIdentifierService;
	@Autowired
	private IUserService iUserService;

	@Qualifier(GlobalMybatisExecutorsConfig.commonDbTaskExecutor)
	@Autowired
	private ExecutorService commonDbTaskExecutor;
	@Autowired
	private IUserLoginRecordService iUserLoginRecordService;
	@Autowired
	private IUserLoginDeviceService iUserLoginDeviceService;
	@Autowired
	private UserDictGateway userDictGateway;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication, Response response) throws IOException {
		LoginUser loginUser = LoginUserTool.getLoginUser();
		log.info("用户认证成功，更新用户 identifier 登录时间和ip，userId={}", loginUser.getId());
		UserIdentifierDO identifier = (UserIdentifierDO) loginUser.getExt().get(IdentifierUserDetailsServiceImpl.user_ext_identifier_key);
		// 更新上次登录时间和登录ip
		identifier.setLastLoginAt(LocalDateTimeUtil.now());
		identifier.setLastLoginIp(RequestTool.getClientIP(httpServletRequest));
		// 减少登录时间，采用异步方式
		commonDbTaskExecutor.execute(() -> {
			iIdentifierService.updateById(identifier);
		});

		// 初始化apicount
		UserSecurityFilterPersistentLoginUserReadyListener.initApiCountToOne(httpServletRequest);
		addLoginRecordAndDevice(httpServletRequest, null, response);
		// 更新过期时间
		updateExpireAtIfNecessary();
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e, Response response) throws IOException {
		addLoginRecordAndDevice(httpServletRequest, e, response);
	}

	/**
	 * 更新过期时间,注意租户用户也有相同的处理方式，即：{@link com.particle.tenant.adapter.login.TenantUserAuthenticationResultServiceImpl#updateExpireAtIfNecessary()}
	 */
	private void updateExpireAtIfNecessary() {

		LoginUser loginUser = LoginUserTool.getLoginUser();
		commonDbTaskExecutor.execute(() -> {
			UserDO user = iUserService.getById(loginUser.getId());
			if (user.getEffectiveAtTriggerDictId() != null && user.getExpireAt() == null && user.getEffectiveDays() != null && user.getEffectiveDays() != 0) {
				String dictValueById = userDictGateway.getDictValueById(user.getEffectiveAtTriggerDictId());
				if (UserEffectiveAtTrigger.login_success.name().equals(dictValueById)) {
					LocalDateTime now = LocalDateTime.now();
					LocalDateTime expireAt = TimeLogicTool.calculateExpireAt(now, user.getEffectiveDays());
					user.setEffectiveAt(now);
					user.setExpireAt(expireAt);
					iUserService.updateById(user);
				}
			}
		});

	}

	/**
	 * 添加登录记录
	 */
	private void addLoginRecordAndDevice(HttpServletRequest httpServletRequest, AuthenticationException e, Response response) {

		// 该值在 {@link com.particle.user.adapter.login.IdentifierUserDetailsServiceImpl.loadUserByUsername} 中设置的
		LoginUser loginUser = ((LoginUser) ThreadContextTool.get(IdentifierUserDetailsServiceImpl.login_loaded_user_in_threadcontext_key));
		// 为空说明账号输出错误，不处理
		if (loginUser == null) {
			return;
		}
		LocalDateTime now = LocalDateTime.now();
		UserIdentifierDO userIdentifier = ((UserIdentifierDO) loginUser.getExt().get(IdentifierUserDetailsServiceImpl.user_ext_identifier_key));

		String userAgentStr = JakartaServletUtil.getHeaderIgnoreCase(httpServletRequest, "User-Agent");
		UserAgent userAgent = UserAgentUtil.parse(userAgentStr);

		UserLoginRecordDO userLoginRecordDO = new UserLoginRecordDO();
		userLoginRecordDO.setUserId(loginUser.getId());
		userLoginRecordDO.setLoginAt(now);
		userLoginRecordDO.setLoginIp(RequestTool.getClientIP(httpServletRequest));

		String deviceId = JakartaServletUtil.getHeaderIgnoreCase(httpServletRequest, login_header_device_id);
		userLoginRecordDO.setDeviceId(Optional.ofNullable(StrUtil.emptyToNull(deviceId)).orElse("none"));
		userLoginRecordDO.setDeviceName(userAgent.getPlatform().getName());

		userLoginRecordDO.setUserIdentifierId(userIdentifier.getId());

		userLoginRecordDO.setOperatingSystem(userAgent.getOs().getName());

		HttpSession session = httpServletRequest.getSession(false);
		if (session != null) {
			userLoginRecordDO.setSession(session.getId());
			userLoginRecordDO.setSessionMd5(DigestUtil.md5Hex(session.getId()));
		} else {
			userLoginRecordDO.setSession("none");
			userLoginRecordDO.setSessionMd5("none");
		}

		userLoginRecordDO.setIsSuccess(e == null);
		if (e != null) {
			String reason = Optional.ofNullable(response).map(r -> r.getErrMessage()).orElse(Optional.ofNullable(e).map(e1 -> e1.getMessage()).orElse(null));
			userLoginRecordDO.setFailedReason(reason);
		}

		userLoginRecordDO.setTraceId(TraceTool.getTraceId());
		// 登录即算一次
		userLoginRecordDO.setApiCount(1L);
		userLoginRecordDO.setLastActiveAt(now);
		doAddLoginRecordAndDevice(userLoginRecordDO);
	}

	/**
	 * 执行异步添加
	 *
	 * @param userLoginRecordDO
	 */
	private void doAddLoginRecordAndDevice(UserLoginRecordDO userLoginRecordDO) {


		Long tenantId = TenantTool.getTenantId();
		// 减少登录时间，采用异步方式
		commonDbTaskExecutor.execute(() -> {
			try {
				TenantTool.setTenantId(tenantId);
				iUserLoginRecordService.add(userLoginRecordDO);

				//	设备检查
				UserLoginDeviceDO userLoginDeviceDO = iUserLoginDeviceService.getByUserIdAndDeviceId(userLoginRecordDO.getUserId(), userLoginRecordDO.getDeviceId());
				UserLoginDeviceDO userLoginDeviceDOTemp = new UserLoginDeviceDO();
				if (userLoginDeviceDO != null) {
					userLoginDeviceDOTemp.setId(userLoginDeviceDO.getId());
					userLoginDeviceDOTemp.setVersion(userLoginDeviceDO.getVersion());
					userLoginDeviceDOTemp.setLoginLastAt(userLoginRecordDO.getLoginAt());
					userLoginDeviceDOTemp.setOperatingSystem(userLoginRecordDO.getOperatingSystem());
					boolean b = iUserLoginDeviceService.updateById(userLoginDeviceDOTemp);
					if (!b) {
						// 没有成功，有可能是版本错误，并发问题，但数据影响不严重，这里只打印日志
						log.warn("user login device update failed!. data={}", JsonTool.toJsonStr(userLoginDeviceDOTemp));
					}
				} else {
					userLoginDeviceDOTemp.setUserId(userLoginRecordDO.getUserId());
					userLoginDeviceDOTemp.setLoginFirstAt(userLoginRecordDO.getLoginAt());
					userLoginDeviceDOTemp.setLoginLastAt(userLoginRecordDO.getLoginAt());
					userLoginDeviceDOTemp.setDeviceId(userLoginRecordDO.getDeviceId());
					userLoginDeviceDOTemp.setDeviceName(userLoginRecordDO.getDeviceName());
					// 新设备时，需要设备验证，目前尚未实现，这里默认直接验证通过
					userLoginDeviceDOTemp.setValidateAt(userLoginRecordDO.getLoginAt());
					userLoginDeviceDOTemp.setOperatingSystem(userLoginRecordDO.getOperatingSystem());
					iUserLoginDeviceService.add(userLoginDeviceDOTemp);
				}
			} finally {
				TenantTool.clear();
			}
		});
	}
}
