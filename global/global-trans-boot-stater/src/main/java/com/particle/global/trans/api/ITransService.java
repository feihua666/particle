package com.particle.global.trans.api;


import com.particle.global.trans.result.TransResult;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;

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


    public static final ITransService emptyTransService = new ITransService(){};

    /**
     * 是否支持
     * @param type 一个翻译的标识
     * @return
     */
    @Operation(summary = "判断是否支持单个翻译")
    @GetMapping("/trans/support")
    default boolean support(String type){return false;}

    /**
     * 是否支持批量翻译
     * @param type
     * @return
     */

    @Operation(summary = "判断是否支持批量翻译")
    @GetMapping("/trans/supportBatch")
    default boolean supportBatch(String type){
        return false;
    }

    /**
     * 根据key批量翻译辅助，加速翻译减少数据库io
     * @param type
     * @param keys
     * @return
     */
    @Operation(summary = "批量翻译")
    @GetMapping("/trans/transBatch")
    default List<TransResult<R,K>> transBatch(String type, Set<K> keys){
        return null;
    }
    /**
     * 根据key翻译
     * @param type 支持的类型
     * @param key
     * @return
     */
    @Operation(summary = "单个翻译")
    @GetMapping("/trans/trans")
    default TransResult<R,K> trans(String type, K key){
        return null;
    }
}
