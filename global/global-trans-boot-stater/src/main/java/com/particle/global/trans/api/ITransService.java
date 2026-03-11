package com.particle.global.trans.api;


import com.particle.global.trans.result.TransResult;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * 提供翻译服务
 * 目前主要用于controller提供字典、机构等根据id翻译成名称
 * 该接口放在这意在也可以在纯service层提供翻译，但得加aop来处理相关逻辑
 * R 返回值尽量用String 以保证feign client的可用性，如果单机模式下则不限制，因为feign client远程调用时会有httpMessageConverter的转换，默认情况下有一些返回类型没有配置转换器
 * @author yangwei
 * Created at 2019/10/9 9:24
 */
public interface ITransService<R, K> {


    public static final ITransService emptyTransService = new ITransService(){
        @Override
        public boolean support(String type) {
            return false;
        }

        @Override
        public boolean supportBatch(String type) {
            return false;
        }

        @Override
        public List<TransResult> transBatch(String type, Set keys) {
            return null;
        }

        @Override
        public TransResult trans(String type, Object key) {
            return null;
        }
    };

    /**
     * 是否支持
     * @param type 一个翻译的标识
     * @return
     */
    @Operation(summary = "判断是否支持单个翻译")
    @GetMapping("/trans/support")
    boolean support(@RequestParam String type);

    /**
     * 是否支持批量翻译
     * @param type
     * @return
     */

    @Operation(summary = "判断是否支持批量翻译")
    @GetMapping("/trans/supportBatch")
    boolean supportBatch(@RequestParam String type);

    /**
     * 根据key批量翻译辅助，加速翻译减少数据库io
     * @param type
     * @param keys
     * @return
     */
    @Operation(summary = "批量翻译")
    @PostMapping("/trans/transBatch")
    List<TransResult<R,K>> transBatch(@RequestParam String type,@RequestBody Set<K> keys);
    /**
     * 根据key翻译
     * @param type 支持的类型
     * @param key
     * @return
     */
    @Operation(summary = "单个翻译")
    @GetMapping("/trans/trans")
    TransResult<R,K> trans(@RequestParam String type,@RequestParam  K key);
}
