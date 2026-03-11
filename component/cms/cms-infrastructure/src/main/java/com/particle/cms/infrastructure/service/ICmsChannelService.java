package com.particle.cms.infrastructure.service;

import com.particle.cms.infrastructure.dos.CmsChannelDO;
import com.particle.cms.infrastructure.dos.CmsChannelDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 栏目 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
public interface ICmsChannelService extends IBaseService<CmsChannelDO> {


    /**
     * 根据栏目编码查询
     * @param code
     * @return
     */
    default CmsChannelDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<CmsChannelDO>lambdaQuery().eq(CmsChannelDO::getCode, code));
    }



    /**
     * 根据栏目编码查询多个
     * @param codes
     * @return
     */
    default List<CmsChannelDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<CmsChannelDO>lambdaQuery().in(CmsChannelDO::getCode, codes));
    }


    /**
     * 根据栏目名称查询
     * @param name
     * @return
     */
    default CmsChannelDO getByName(String name) {
        Assert.notNull(name,"name 不能为空");
        return getOne(Wrappers.<CmsChannelDO>lambdaQuery().eq(CmsChannelDO::getName, name));
    }



    /**
     * 根据栏目名称查询多个
     * @param names
     * @return
     */
    default List<CmsChannelDO> getByNames(List<String> names) {
        Assert.notEmpty(names,"names 不能为空");
        return list(Wrappers.<CmsChannelDO>lambdaQuery().in(CmsChannelDO::getName, names));
    }


    /**
     * 根据栏目 id 和 isPublic 查询
     * @param id
     * @return
     */
    default CmsChannelDO getByIdAndIsPublic(Long id, Boolean isPublic) {
        Assert.notNull(id,"id 不能为空");
        return getOne(Wrappers.<CmsChannelDO>lambdaQuery().eq(CmsChannelDO::getId, id).eq(isPublic != null,CmsChannelDO::getIsPublic, isPublic));
    }

    /**
     * 根据栏目 ids 和 isPublic 查询
     * @param ids
     * @return
     */
    default List<CmsChannelDO> listByIdsAndIsPublic(List<Long> ids, Boolean isPublic) {
        Assert.notEmpty(ids,"ids 不能为空");
        return list(Wrappers.<CmsChannelDO>lambdaQuery().in(CmsChannelDO::getId, ids).eq(isPublic != null,CmsChannelDO::getIsPublic, isPublic));
    }



}
