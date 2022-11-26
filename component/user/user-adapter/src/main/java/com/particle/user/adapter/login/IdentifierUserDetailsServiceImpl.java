package com.particle.user.adapter.login;

import cn.hutool.core.util.StrUtil;
import com.particle.global.security.security.PasswordEncryptEnum;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserExtPutService;
import com.particle.global.tool.thread.ThreadContextTool;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierPwdDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierPwdService;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 这个bean如果放到配置文件中通过bean注解方式会导致其依赖的注入为空
 * Created by yangwei
 * Created at 2020/12/10 20:59
 */
@Service
public class IdentifierUserDetailsServiceImpl implements UserDetailsService {

    public static String user_ext_identifier_key ="userIdentifier";
    public static String login_loaded_user_in_threadcontext_key ="loginLoadedUser";


    @Autowired
    private IUserIdentifierService iIdentifierService;

    @Autowired
    private IUserIdentifierPwdService iIdentifierPwdService;

    @Autowired(required = false)
    private UserAuthorityService userAuthorityService;

    @Autowired(required = false)
    private List<LoginUserExtPutService> loginUserExtPutServices;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserIdentifierDO byIdentifier = iIdentifierService.getByIdentifier(username);
        if (byIdentifier == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserIdentifierPwdDO byIdentifierId = iIdentifierPwdService.getByIdentifierId(byIdentifier.getId());
        LoginUser loginUser = new LoginUser();

        // 帐号信息
        loginUser.setId(byIdentifier.getUserId());
        loginUser.setUsername(username);
        loginUser.setIsEnabled(!byIdentifier.getIsDisabled());
        loginUser.setIsLocked(byIdentifier.getIsLock());
        loginUser.setIsExpired(byIdentifier.getIsExpired());

        // 密码信息
        loginUser.setPassword(PasswordEncryptEnum.prefixEncodePassword(byIdentifierId.getPwdEncryptFlag(),byIdentifierId.getPwd()));
        loginUser.setIsCredentialsExpired(byIdentifierId.getIsExpired());

        loginUser.addExt(user_ext_identifier_key, byIdentifier);

        // 默认添加用户权限
        loginUser.addAuthority("user");
        if (userAuthorityService != null) {
            List<String> list = userAuthorityService.retrieveUserAuthoritiesByUserId(loginUser.getId());
            loginUser.addAuthority(list);
            if (list != null && list.stream().filter(item -> StrUtil.equals(LoginUser.super_admin_role, item)).count() > 0) {
                loginUser.setIsSuperAdmin(true);
            }
        }

        if (loginUserExtPutServices != null) {
            for (LoginUserExtPutService loginUserExtPutService : loginUserExtPutServices) {
                loginUserExtPutService.addExt(loginUser);
            }
        }
        // 在线程中放置一个用户，不管用户密码对不对，都可以在后期获取到，主要用来记录用户登录日志
        ThreadContextTool.put(login_loaded_user_in_threadcontext_key,loginUser);
        return loginUser;
    }
}
