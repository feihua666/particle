package com.particle.global.cache.endpoint;

import com.particle.global.cache.CacheHelper;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 全局服务缓存相关接口
 * </p>
 *
 * @author yangwei
 * @since 2023/10/9 11:47
 */
@Tag(name = "全局服务缓存相关接口")
@RestController
@RequestMapping("/cache")
public class GlobalCacheController {

    @Autowired
    private CacheHelper cacheHelper;

    /**
     * 根据字符串key删除缓存
     * @param removeCacheByStringKeyCommand
     * @return
     */
    @PreAuthorize("hasAnyRole('"+ LoginUser.super_admin_role +"','cacheDelete')")
    @Operation(summary = "根据字符串key删除缓存,只能超级管理员才能删除")
    @PostMapping("/removeCacheByStringKey")
    public SingleResponse<Boolean> removeCacheByStringKey(@Valid @RequestBody RemoveCacheByStringKeyCommand removeCacheByStringKeyCommand){
        Boolean b = cacheHelper.removeCacheByKey(removeCacheByStringKeyCommand.getCacheName(), removeCacheByStringKeyCommand.getCacheKey());
        return SingleResponse.of(b);
    }

    /**
     * 清空缓存
     * @param cacheCommand
     * @return
     */
    @PreAuthorize("hasAnyRole('"+ LoginUser.super_admin_role +"','cacheDelete')")
    @Operation(summary = "清空缓存,只能超级管理员才能删除")
    @PostMapping("/clearCache")
    public SingleResponse<Boolean> clearCache(@Valid @RequestBody CacheCommand cacheCommand){
        Boolean b = cacheHelper.clearCache(cacheCommand.getCacheName());
        return SingleResponse.of(b);
    }
}
