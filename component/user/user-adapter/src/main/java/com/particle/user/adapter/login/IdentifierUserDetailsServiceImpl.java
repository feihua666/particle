package com.particle.user.adapter.login;

import cn.hutool.core.util.StrUtil;
import com.particle.global.security.security.PasswordEncryptEnum;
import com.particle.global.security.security.login.AbstractUserDetailsService;
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
public class IdentifierUserDetailsServiceImpl extends AbstractUserDetailsService {

    public static String user_ext_identifier_key ="userIdentifier";

    @Autowired
    private IUserIdentifierService iIdentifierService;

    @Autowired
    private IUserIdentifierPwdService iIdentifierPwdService;

    @Override
    public LoginUser doLoadUserByUsername(String username) throws UsernameNotFoundException {

        UserIdentifierDO byIdentifier = iIdentifierService.getByIdentifier(username);
        if (byIdentifier == null) {
           return null;
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
        return loginUser;
    }
}
