package com.particle.func.adapter.feign.client.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.func.client.dto.data.FuncTransVO;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 功能菜单翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@FeignClient(name = "${particle.feign-client.func.name:func-start}", contextId = "funcTransRpcFeignClient", url = "${particle.feign-client.func.url:}", path = "/rpc/func")
public interface FuncTransRpcFeignClient extends ITransService<FuncTransVO,Long> {
    /**
     * 通用支持
     * @param type
     * @return
     */
    public static boolean supportCommon(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_FUNC_BY_ID);
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
    default public TransResult<FuncTransVO, Long> trans(@RequestParam String type, @RequestParam Long key) {
        return null;
    }
}
