package com.particle.global.crawler.runtime.session.auth;

import com.particle.global.crawler.common.constants.ReservedKeys;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 认证配置
 * </p>
 * 该配置应该从配置中获取在 pipeline 开始执行时，从变量中以 {@link com.particle.global.crawler.common.constants.ReservedKeys#AUTH_CONFIG} 获取并解析
 * @author yangwei
 * @since 2026/5/14 09:34
 */
@Data
public class AuthConfig {

    /**
     * 认证域名，如：www.baidu.com
     */
    private String domain;
    /**
     * 认证用户名
     */
    private String username;

    public static AuthConfig create(String domain, String username){
        AuthConfig authConfig = new AuthConfig();
        authConfig.setDomain(domain);
        authConfig.setUsername(username);
        return authConfig;
    }

    /**
     * 从变量中解析
     * @param variables
     * @return
     */
    public static List<AuthConfig> parse(Map<String, Object> variables){
        if (variables == null || variables.isEmpty()) {
            return null;
        }
        Object o = variables.get(ReservedKeys.AUTH_CONFIG);
        if (o == null) {
            return null;
        }
        if (o instanceof List) {
            List<?> configList = (List<?>) o;
            List<AuthConfig> resultList = new ArrayList<>(configList.size());
            for (Object config : configList) {
                String configStr = config.toString();
                String[] split = configStr.split("@");
                resultList.add(AuthConfig.create(split[0], split[1]));
            }
            return resultList;
        }
        return null;
    }
}
