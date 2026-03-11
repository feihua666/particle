package com.particle.dept.adapter.feign.client.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.dept.client.dto.data.DeptTransVO;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 部门翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@FeignClient(name = "${particle.feign-client.dept.name:dept-start}", contextId = "deptTransRpcFeignClient", url = "${particle.feign-client.dept.url:}", path = "/rpc/dept")
public interface DeptTransRpcFeignClient extends ITransService<DeptTransVO,Long> {

    /**
     * 通用支持
     * @param type
     * @return
     */
    public static boolean supportCommon(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_DEPT_BY_ID,TransConstants.TRANS_DEPT_BY_USER_ID);
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
    default public TransResult<DeptTransVO, Long> trans(@RequestParam String type, @RequestParam Long key) {
        return null;
    }
}
