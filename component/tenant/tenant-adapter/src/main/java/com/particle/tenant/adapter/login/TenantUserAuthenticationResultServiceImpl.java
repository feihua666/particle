package com.particle.tenant.adapter.login;

import com.particle.component.light.share.dict.UserEffectiveAtTrigger;
import com.particle.global.dto.response.Response;
import com.particle.global.mybatis.plus.config.GlobalMybatisExecutorsConfig;
import com.particle.global.security.security.login.IAuthenticationResultService;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.tool.logical.TimeLogicTool;
import com.particle.tenant.domain.gateway.TenantDictGateway;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * <p>
 * identifier 登录事件
 * </p>
 *
 * @author yangwei
 * @since 2023-07-21 15:53:18
 */
@Slf4j
@Component
public class TenantUserAuthenticationResultServiceImpl implements IAuthenticationResultService {

	@Qualifier(GlobalMybatisExecutorsConfig.commonDbTaskExecutor)
	@Autowired
	private ExecutorService commonDbTaskExecutor;
	@Autowired
	private TenantDictGateway tenantDictGateway;
	@Autowired
	private ITenantUserService iTenantUserService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication, Response response) throws IOException {
		// 更新过期时间
		updateExpireAtIfNecessary();
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e, Response response) throws IOException {
	}

	/**
	 * 更新过期时间,注意用户也有相同的处理方式，即：{@link com.particle.user.adapter.login.UserAuthenticationResultServiceImpl#updateExpireAtIfNecessary()}
	 */
	private void updateExpireAtIfNecessary() {

		LoginUser loginUser = LoginUserTool.getLoginUser();
		commonDbTaskExecutor.execute(() -> {
			List<TenantUserDO> tenantUserDOS = iTenantUserService.getByUserIdIgnoreTenantLimit(loginUser.getId());
			for (TenantUserDO tenantUserDO : tenantUserDOS) {
				if (tenantUserDO.getIsLeave() !=null && !tenantUserDO.getIsLeave()
						&& tenantUserDO.getEffectiveAtTriggerDictId() != null
						&& tenantUserDO.getExpireAt() == null
						&& tenantUserDO.getEffectiveDays() != null && tenantUserDO.getEffectiveDays() != 0) {
					String dictValueById = tenantDictGateway.getDictValueById(tenantUserDO.getEffectiveAtTriggerDictId());
					if (UserEffectiveAtTrigger.login_success.name().equals(dictValueById)) {
						LocalDateTime now = LocalDateTime.now();
						LocalDateTime expireAt = TimeLogicTool.calculateExpireAt(now, tenantUserDO.getEffectiveDays());
						tenantUserDO.setEffectiveAt(now);
						tenantUserDO.setExpireAt(expireAt);
						iTenantUserService.updateById(tenantUserDO);
					}
				}
			}

		});
		
	}

}
