package com.particle.data.infrastructure.company.service;

import com.particle.data.infrastructure.company.dos.DataCompanyBasicDO;
import com.particle.data.infrastructure.company.dos.DataCompanyDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;

import java.time.LocalDateTime;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 企业基本信息 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
public interface IDataCompanyBasicService extends IBaseService<DataCompanyBasicDO> {

    /**
     * 根据企业表ID查询
     * @param companyId
     * @return
     */
    default DataCompanyBasicDO getByCompanyId(Long companyId) {
        Assert.notNull(companyId,"companyId 不能为空");
        return getOne(Wrappers.<DataCompanyBasicDO>lambdaQuery().eq(DataCompanyBasicDO::getCompanyId, companyId));
    }



    /**
     * 根据企业表ID查询多个
     * @param companyIds
     * @return
     */
    default List<DataCompanyBasicDO> getByCompanyIds(List<Long> companyIds) {
        Assert.notEmpty(companyIds,"companyIds 不能为空");
        return list(Wrappers.<DataCompanyBasicDO>lambdaQuery().in(DataCompanyBasicDO::getCompanyId, companyIds));
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyBasicDO>lambdaUpdate().eq(DataCompanyBasicDO::getId, id)
                .set(DataCompanyBasicDO::getLatestHandleAt, LocalDateTime.now()));
    }

}
