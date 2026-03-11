package com.particle.dataconstraint.adapter.feign.client.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.dataconstraint.client.dto.data.DataObjectTransVO;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 数据对象翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-02 11:58:56
 */
@FeignClient(name = "${particle.feign-client.dataconstraint.name:dataconstraint-start}", contextId = "dataObjectTransRpcFeignClient", url = "${particle.feign-client.dataconstraint.url:}", path = "/rpc/data_object")
public interface DataObjectTransRpcFeignClient extends ITransService<DataObjectTransVO,Long> {
    /**
     * 通用支持
     * @param type
     * @return
     */
    public static boolean supportCommon(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_DATA_OBJECT_BY_ID);
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
    default public TransResult<DataObjectTransVO, Long> trans(@RequestParam String type, @RequestParam Long key) {
        return null;
    }
}
