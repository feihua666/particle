package com.particle.tenant.adapter.feign.client.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import com.particle.tenant.client.dto.data.TenantTransVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 租户翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2023-10-20 17:03:41
 */
@FeignClient(name = "${particle.feign-client.tenant.name:tenant-start}", contextId = "tenantTransRpcFeignClient", url = "${particle.feign-client.tenant.url:}", path = "/rpc/tenant")
public interface TenantTransRpcFeignClient extends ITransService<TenantTransVO,Long> {
    /**
     * 通用支持
     * @param type
     * @return
     */
    public static boolean supportCommon(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_TENANT_BY_ID);
    }


    @Override
    default public boolean support(String type) {
        return false;
    }


    @Override
    default public boolean supportBatch(String type) {
        return supportCommon(type);
    }

    @GetMapping("/trans/trans")
    @Override
    default public TransResult<TenantTransVO, Long> trans(@RequestParam String type, @RequestParam Long key) {
        return null;
    }
}
