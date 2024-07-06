package com.particle.dataconstraint.infrastructure.service;

import com.particle.dataconstraint.infrastructure.dos.DataObjectDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 数据对象 服务类
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
public interface IDataObjectService extends IBaseService<DataObjectDO> {

    /**
     * 根据数据对象编码查询
     * @param code
     * @return
     */
    default DataObjectDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<DataObjectDO>lambdaQuery().eq(DataObjectDO::getCode, code));
    }
    /**
     * 根据数据对象编码查询多个
     * @param codes
     * @return
     */
    default List<DataObjectDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<DataObjectDO>lambdaQuery().in(DataObjectDO::getCode, codes));
    }

}
