package com.particle.user.adapter.login;

import com.particle.global.security.security.PasswordEncryptEnum;
import com.particle.global.security.security.login.AbstractUserDetailsService;
import com.particle.global.security.security.login.LoginUser;
import com.particle.user.infrastructure.dos.UserDO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierPwdDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierPwdService;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import com.particle.user.infrastructure.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 这个bean如果放到配置文件中通过bean注解方式会导致其依赖的注入为空
 * Created by yangwei
 * Created at 2020/12/10 20:59
 */
@Slf4j
@Service
public class IdentifierUserDetailsServiceImpl extends AbstractUserDetailsService {

    public static String user_ext_identifier_key ="userIdentifier";

    @Autowired
    private IUserIdentifierService iIdentifierService;

    @Autowired
    private IUserIdentifierPwdService iIdentifierPwdService;

    @Autowired
    private IUserService iUserService;

    @Override
    public LoginUser doLoadUserByUsername(String username) throws UsernameNotFoundException {

        UserIdentifierDO userIdentifierDO = iIdentifierService.getByIdentifier(username);
        if (userIdentifierDO == null) {
           return null;
        }
        UserDO userDO = iUserService.getById(userIdentifierDO.getUserId());
        UserIdentifierPwdDO userIdentifierPwdDO = iIdentifierPwdService.getByIdentifierId(userIdentifierDO.getId());
        LoginUser loginUser = new LoginUser();

        // 帐号信息
        loginUser.setNickname(userDO.getNickname());
        loginUser.setAvatar(userDO.getAvatar());
        loginUser.setGender(Optional.ofNullable(userDO.getGenderDictId()).map(String::valueOf).orElse(null));


        loginUser.setId(userIdentifierDO.getUserId());
        loginUser.setName(userDO.getName());
        loginUser.setUsername(username);
        // 账号是否可用
        loginUser.setIsEnabled(true);
        loginUser.setIsLocked(userDO.getIsLock() || userIdentifierDO.getIsLock());
        // 账号是否过期
        loginUser.setIsExpired(
                userDO.getIsExpired() ||
                checkHasExpired(userDO.getExpireAt()) ||
                userIdentifierDO.getIsExpired() ||
                checkHasExpired(userIdentifierDO.getExpireAt())
                );


        // 密码信息
        loginUser.setPassword(PasswordEncryptEnum.prefixEncodePassword(userIdentifierPwdDO.getPwdEncryptFlag(),userIdentifierPwdDO.getPwd()));
        loginUser.setIsCredentialsExpired(userIdentifierPwdDO.getIsExpired() || checkHasExpired(userIdentifierPwdDO.getExpireAt()));

        loginUser.addExt(user_ext_identifier_key, userIdentifierDO);
        return loginUser;
    }

    /**
     * 是否过期
     * @param expiredAt
     * @return
     */
    private boolean checkHasExpired(LocalDateTime expiredAt) {
        if (expiredAt == null) {
            return false;
        }
        return expiredAt.isBefore(LocalDateTime.now());
    }
}
