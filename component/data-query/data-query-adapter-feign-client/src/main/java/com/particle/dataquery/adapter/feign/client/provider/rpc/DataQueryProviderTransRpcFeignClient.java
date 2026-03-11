package com.particle.dataquery.adapter.feign.client.provider.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.dataquery.client.provider.dto.data.DataQueryProviderTransVO;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 数据查询供应商翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-17 10:10:43
 */
@FeignClient(name = "${particle.feign-client.dataquery.name:dataquery-start}", contextId = "dataQueryProviderTransRpcFeignClient", url = "${particle.feign-client.dataquery.url:}", path = "/rpc/data_query_provider")
public interface DataQueryProviderTransRpcFeignClient extends ITransService<DataQueryProviderTransVO,Long> {
    /**
     * 通用支持
     * @param type
     * @return
     */
    public static boolean supportCommon(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_DATAQUERY_PROVIDER_BY_USER_ID);
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
    default public TransResult<DataQueryProviderTransVO, Long> trans(@RequestParam String type, @RequestParam Long key) {
        return null;
    }
}
