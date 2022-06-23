package com.particle.global.security.security.voter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;

/**
 * 授权字符串配置投票器
 * 用于哪些在没有用户登录的情况下可以访问
 * 该投票器也可以说spring security的投票器如果禁用匿名会被提前拦截不会走到这里，参数源码{@link org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor}
 * @Author yw
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "particle.security.authority-str")
public class NoAuthenticationAuthorityStrConfigVoter extends RoleVoter {

    private List<String> grantedAuthority;

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        int result = ACCESS_ABSTAIN;
        // 未配置保持中立
        if (grantedAuthority == null) {
            return result;
        }
        for (ConfigAttribute attribute : attributes) {
            for (String authority : grantedAuthority) {
                if (authority.equals(attribute.getAttribute()) || attribute.toString().contains(authority)) {
                    return ACCESS_GRANTED;
                }
            }
        }
        return result;
    }
}
