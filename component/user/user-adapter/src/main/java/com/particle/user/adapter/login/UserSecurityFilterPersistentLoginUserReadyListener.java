package com.particle.user.adapter.login;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.global.mybatis.plus.config.GlobalMybatisExecutorsConfig;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.security.login.SecurityFilterPersistentLoginUserReadyListener;
import com.particle.user.infrastructure.login.dos.UserLoginRecordDO;
import com.particle.user.infrastructure.login.service.IUserLoginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;

/**
 * <p>
 * 统计用户 session api 计数
 * </p>
 *
 * @author yangwei
 * @since 2022-11-26 18:13
 */
@Component
public class UserSecurityFilterPersistentLoginUserReadyListener implements SecurityFilterPersistentLoginUserReadyListener {

	private static String api_count_key = "apiCount";

	// 每请求多少次更新一次登录情况
	private static Integer api_count_persistent_threshold = 7;
	@Autowired
	private IUserLoginRecordService iUserLoginRecordService;
	@Qualifier(GlobalMybatisExecutorsConfig.commonDbTaskExecutor)
	@Autowired
	private ExecutorService commonDbTaskExecutor;
	@Override
	public void onLoginUserReady(ServletRequest request) {
		LoginUser loginUser = LoginUserTool.getLoginUser();
		// 用户不存在不处理
		if (loginUser == null) {
			return;
		}
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		if (session == null) {
			return;
		}
		Integer apiCount = ((Integer) session.getAttribute(api_count_key));
		if (apiCount == null) {
			apiCount = 1;
		}else {
			apiCount ++;
		}
		if(apiCount > api_count_persistent_threshold){
			// 随机一下
			if (RandomUtil.randomBoolean()) {
				// 持久化
				Integer finalApiCount = apiCount;
				LocalDateTime now = LocalDateTime.now();
				// 重新计数
				session.setAttribute(api_count_key,0);
				String sessionId = session.getId();
				commonDbTaskExecutor.execute(()-> {
					String sessionMd5 = DigestUtil.md5Hex(sessionId);
					iUserLoginRecordService.plusForColumnByColumn(sessionMd5, UserLoginRecordDO::getSessionMd5, UserLoginRecordDO::getApiCount, finalApiCount);
					iUserLoginRecordService.updateColumnByColumn(sessionMd5, UserLoginRecordDO::getSessionMd5, UserLoginRecordDO::getLastActiveAt, now);
				});
			}else {
				session.setAttribute(api_count_key,apiCount);
			}
		}else {
			session.setAttribute(api_count_key,apiCount);
		}


	}
}
