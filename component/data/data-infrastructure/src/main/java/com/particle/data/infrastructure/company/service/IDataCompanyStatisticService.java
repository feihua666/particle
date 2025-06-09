package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.data.infrastructure.company.dos.DataCompanyStatisticDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业统计 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
public interface IDataCompanyStatisticService extends IBaseService<DataCompanyStatisticDO> {

    /**
     * 根据企业表ID查询
     * @param companyId
     * @return
     */
    default DataCompanyStatisticDO getByCompanyId(Long companyId) {
        Assert.notNull(companyId,"companyId 不能为空");
        return getOne(Wrappers.<DataCompanyStatisticDO>lambdaQuery().eq(DataCompanyStatisticDO::getCompanyId, companyId));
    }



    /**
     * 根据企业表ID查询多个
     * @param companyIds
     * @return
     */
    default List<DataCompanyStatisticDO> getByCompanyIds(List<Long> companyIds) {
        Assert.notEmpty(companyIds,"companyIds 不能为空");
        return list(Wrappers.<DataCompanyStatisticDO>lambdaQuery().in(DataCompanyStatisticDO::getCompanyId, companyIds));
    }


    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyStatisticDO>lambdaUpdate().eq(DataCompanyStatisticDO::getId, id)
                .set(DataCompanyStatisticDO::getLatestHandleAt, LocalDateTime.now()));
    }

}
