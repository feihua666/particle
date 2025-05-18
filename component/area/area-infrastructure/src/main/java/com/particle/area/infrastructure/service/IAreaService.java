package com.particle.area.infrastructure.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.area.infrastructure.dos.AreaDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * 区域 服务类
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
public interface IAreaService extends IBaseService<AreaDO> {
    /**
     * 根据编码查询
     * @param code
     * @return
     */
    default AreaDO getByCode(String code) {
        Assert.hasText(code,"code 不能为空");
        return getOne(Wrappers.<AreaDO>lambdaQuery().eq(AreaDO::getCode, code));
    }

    /**
     * 根据名称和类型查询，一般用来查询省，否则可能会有多条情况
     * @param name
     * @param typeDictId
     * @return
     */
    default AreaDO getByNameAndTypeDictId(String name,Long typeDictId) {
        Assert.hasText(name,"name 不能为空");
        Assert.notNull(typeDictId,"typeDictId 不能为空");
        return getOne(Wrappers.<AreaDO>lambdaQuery().eq(AreaDO::getName, name).eq(AreaDO::getTypeDictId, typeDictId));
    }

    /**
     * 根据名称和类型查询某一级的子级，一般只有一条，也有个别的有多条
     * @param name
     * @param typeDictId
     * @param parentId
     * @return
     */
    default List<AreaDO> getByNameAndTypeDictIdAndParentId(String name, Long typeDictId, Long parentId) {
        Assert.hasText(name,"name 不能为空");
        Assert.notNull(typeDictId,"typeDictId 不能为空");
        Assert.notNull(parentId,"parentId 不能为空");
        return list(Wrappers.<AreaDO>lambdaQuery()
                .eq(AreaDO::getName, name)
                .eq(AreaDO::getTypeDictId, typeDictId)
                .eq(AreaDO::getParentId, parentId));
    }
}
