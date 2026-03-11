package com.particle.dict.adapter.feign.client.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.dict.client.dto.data.DictTransVO;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 字典翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@FeignClient(name = "${particle.feign-client.dict.name:dict-start}",contextId = "dictTransRpcFeignClient", url = "${particle.feign-client.dict.url:}",path = "/rpc/dict")
public interface DictTransRpcFeignClient extends ITransService<DictTransVO,Long> {
    /**
     * 通用支持
     * @param type
     * @return
     */
    public static boolean supportCommon(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_DICT_BY_ID);
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
    default public TransResult<DictTransVO, Long> trans(@RequestParam String type,@RequestParam Long key) {
        return null;
    }
}
