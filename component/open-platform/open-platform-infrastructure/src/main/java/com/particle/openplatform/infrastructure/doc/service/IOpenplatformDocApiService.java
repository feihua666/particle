package com.particle.openplatform.infrastructure.doc.service;

import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 开放接口文档接口 服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
public interface IOpenplatformDocApiService extends IBaseService<OpenplatformDocApiDO> {

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    default OpenplatformDocApiDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<OpenplatformDocApiDO>lambdaQuery().eq(OpenplatformDocApiDO::getCode, code));
    }


    /**
     * 根据编码查询多个
     * @param codes
     * @return
     */
    default List<OpenplatformDocApiDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<OpenplatformDocApiDO>lambdaQuery().in(OpenplatformDocApiDO::getCode, codes));
    }

    /**
     * 根据开放接口id查询
     * @param openplatformOpenapiId
     * @return
     */
    default OpenplatformDocApiDO getByOpenplatformOpenapiId(Long openplatformOpenapiId) {
        Assert.notNull(openplatformOpenapiId,"openplatformOpenapiId 不能为空");
        return getOne(Wrappers.<OpenplatformDocApiDO>lambdaQuery().eq(OpenplatformDocApiDO::getOpenplatformOpenapiId, openplatformOpenapiId));
    }


    /**
     * 根据开放接口id查询多个
     * @param openplatformOpenapiIds
     * @return
     */
    default List<OpenplatformDocApiDO> getByOpenplatformOpenapiIds(List<String> openplatformOpenapiIds) {
        Assert.notEmpty(openplatformOpenapiIds,"openplatformOpenapiIds 不能为空");
        return list(Wrappers.<OpenplatformDocApiDO>lambdaQuery().in(OpenplatformDocApiDO::getOpenplatformOpenapiId, openplatformOpenapiIds));
    }













}
