package com.particle.crm.adapter.feign.client.customer.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.crm.client.customer.dto.data.CrmCustomerTransVO;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 客户翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2024-05-06 16:29:57
 */
@FeignClient(name = "${particle.feign-client.crm.name:crm-start}", contextId = "crmCustomerTransRpcFeignClient", url = "${particle.feign-client.crm.url:}", path = "/rpc/crm_customer")
public interface CrmCustomerTransRpcFeignClient extends ITransService<CrmCustomerTransVO,Long> {
    /**
     * 通用支持
     * @param type
     * @return
     */
    public static boolean supportCommon(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_CRM_CUSTOMER_BY_ID);
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
    default public TransResult<CrmCustomerTransVO, Long> trans(@RequestParam String type, @RequestParam Long key) {
        return null;
    }
}
