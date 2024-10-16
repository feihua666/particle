package com.particle.global.openapi.api.limitrule.ratelimit;

import com.particle.global.openapi.data.OpenapiLimitRuleInfo;
import com.particle.global.openapi.enums.LimitRuleTarget;
import com.particle.global.ratelimit.local.DefaultThreadLocalRateLimitInterceptServiceImpl;
import com.particle.global.tool.thread.ThreadContextTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * qps限制服务
 * 结合全局模块 global-ratelimit-boot-starter 实现，参考{@link DefaultThreadLocalRateLimitInterceptServiceImpl}
 * </p>
 *
 * @author yangwei
 * @since 2024/10/14 15:20
 */
@Slf4j
public class GlobalOpenapiRateLimitService {

    private static final String theadLocalRateLimitConfigKey = DefaultThreadLocalRateLimitInterceptServiceImpl.theadLocalRateLimitConfigKey;

    /**
     * 用来存储 key 和 rate 的映射关系，主要用来判断是否有变动
     */
    private Map<String, Integer> rateLimitKeyAndRateMapping = new ConcurrentHashMap<>();

    public void threadLocalRateLimit(OpenapiLimitRuleInfo limitRuleInfo,String clientId,String apiCode,String apiUrl) {
        LimitRuleTarget limitRuleTarget = limitRuleInfo.getLimitRuleTarget();
        Integer limitRate = limitRuleInfo.getLimitRate();
        if (limitRate <= 0) {
            return;
        }
        String key = "globalOpenapiRateLimit_";

        if (limitRuleTarget == LimitRuleTarget.client_id) {
            key += clientId;
        } else if (limitRuleTarget == LimitRuleTarget.client_id_and_openapi) {
            key += clientId + apiCode + apiUrl;
        }
        String name = limitRuleTarget.name() + "_" + clientId;
        String code = name;

        Integer oldLimitRate = rateLimitKeyAndRateMapping.computeIfAbsent(key, (k) -> limitRate);

        Boolean isReset = false;
        if (oldLimitRate != limitRate) {
            isReset = true;
            rateLimitKeyAndRateMapping.remove(key);
        }

        DefaultThreadLocalRateLimitInterceptServiceImpl.Config config = DefaultThreadLocalRateLimitInterceptServiceImpl.Config.create(
                key,
                name,
                code,
                limitRate,
                isReset
        );
        Object o = ThreadContextTool.get(theadLocalRateLimitConfigKey);
        if (o == null) {
            List<DefaultThreadLocalRateLimitInterceptServiceImpl.Config> configList = new ArrayList<>();
            configList.add(config);
            ThreadContextTool.put(theadLocalRateLimitConfigKey, configList);
        }else {
            ((List<DefaultThreadLocalRateLimitInterceptServiceImpl.Config>)o).add(config);
        }

    }

    public void threadLocalRateLimitRemove() {
        ThreadContextTool.remove(theadLocalRateLimitConfigKey);
    }
}
