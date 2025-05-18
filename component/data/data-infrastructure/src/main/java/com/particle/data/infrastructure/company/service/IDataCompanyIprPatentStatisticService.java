package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentStatisticDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权专利统计 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
public interface IDataCompanyIprPatentStatisticService extends IBaseService<DataCompanyIprPatentStatisticDO> {

    /**
     * 根据企业知识产权专利表id查询
     * @param companyIprPatentId
     * @return
     */
    default DataCompanyIprPatentStatisticDO getByCompanyIprPatentId(Long companyIprPatentId) {
        Assert.notNull(companyIprPatentId,"companyIprPatentId 不能为空");
        return getOne(Wrappers.<DataCompanyIprPatentStatisticDO>lambdaQuery().eq(DataCompanyIprPatentStatisticDO::getCompanyIprPatentId, companyIprPatentId));
    }



    /**
     * 根据企业知识产权专利表id查询多个
     * @param companyIprPatentIds
     * @return
     */
    default List<DataCompanyIprPatentStatisticDO> listByCompanyIprPatentIds(List<Long> companyIprPatentIds) {
        Assert.notEmpty(companyIprPatentIds,"companyIprPatentIds 不能为空");
        return list(Wrappers.<DataCompanyIprPatentStatisticDO>lambdaQuery().in(DataCompanyIprPatentStatisticDO::getCompanyIprPatentId, companyIprPatentIds));
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprPatentStatisticDO>lambdaUpdate().eq(DataCompanyIprPatentStatisticDO::getId, id)
                .set(DataCompanyIprPatentStatisticDO::getLatestHandleAt, LocalDateTime.now()));
    }
}
