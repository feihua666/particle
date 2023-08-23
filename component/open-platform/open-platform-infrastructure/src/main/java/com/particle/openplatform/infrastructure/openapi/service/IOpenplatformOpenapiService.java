package com.particle.openplatform.infrastructure.openapi.service;

import cn.hutool.cache.CacheUtil;
import cn.hutool.core.annotation.AnnotationUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppOpenapiDO;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiDO;

import java.util.List;

/**
 * <p>
 * 开放平台开放接口 服务类
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
public interface IOpenplatformOpenapiService extends IBaseService<OpenplatformOpenapiDO> {

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    default OpenplatformOpenapiDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<OpenplatformOpenapiDO>lambdaQuery().eq(OpenplatformOpenapiDO::getCode, code));
    }



    /**
     * 根据编码查询多个
     * @param codes
     * @return
     */
    default List<OpenplatformOpenapiDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<OpenplatformOpenapiDO>lambdaQuery().in(OpenplatformOpenapiDO::getCode, codes));
    }
            




    /**
     * 根据接口地址查询
     * @param url
     * @return
     */
    default OpenplatformOpenapiDO getByUrl(String url) {
        Assert.notNull(url,"url 不能为空");
        return getOne(Wrappers.<OpenplatformOpenapiDO>lambdaQuery().eq(OpenplatformOpenapiDO::getUrl, url));
    }



    /**
     * 根据接口地址查询多个
     * @param urls
     * @return
     */
    default List<OpenplatformOpenapiDO> getByUrls(List<String> urls) {
        Assert.notEmpty(urls,"urls 不能为空");
        return list(Wrappers.<OpenplatformOpenapiDO>lambdaQuery().in(OpenplatformOpenapiDO::getUrl, urls));
    }


    /**
     * 获取主键 openPlatformAppId 配置的接口权限
     * @param openPlatformAppId
     * @return
     */
    List<String> getPermissionsByAppId(Long openPlatformAppId);

}
