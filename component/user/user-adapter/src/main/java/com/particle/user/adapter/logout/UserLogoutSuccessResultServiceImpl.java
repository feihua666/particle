package com.particle.user.adapter.logout;

import cn.hutool.crypto.digest.DigestUtil;
import com.particle.global.mybatis.plus.config.GlobalMybatisExecutorsConfig;
import com.particle.global.security.security.logout.ILogoutSuccessResultService;
import com.particle.user.infrastructure.login.dos.UserLoginRecordDO;
import com.particle.user.infrastructure.login.service.IUserLoginRecordService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;

/**
 * <p>
 * 用户退出登录后，记录登录记录的登出时间
 * </p>
 *
 * @author yangwei
 * @since 2022-11-26 20:46
 */
@Component
public class UserLogoutSuccessResultServiceImpl implements ILogoutSuccessResultService {
	@Autowired
	private IUserLoginRecordService iUserLoginRecordService;
	@Qualifier(GlobalMybatisExecutorsConfig.commonDbTaskExecutor)
	@Autowired
	private ExecutorService commonDbTaskExecutor;
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		LocalDateTime now = LocalDateTime.now();
		String sessionId = session.getId();
		commonDbTaskExecutor.execute(()-> {
			String sessionMd5 = DigestUtil.md5Hex(sessionId);
			iUserLoginRecordService.updateColumnByColumn(sessionMd5, UserLoginRecordDO::getSessionMd5, UserLoginRecordDO::getLogoutAt, now);
		});
	}
}
