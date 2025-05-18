package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductCompetitiveProductRelDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业融资产品竞品关系 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
public interface IDataCompanyVcProductCompetitiveProductRelService extends IBaseService<DataCompanyVcProductCompetitiveProductRelDO> {

    /**
     * 根据 融资产品id 查询
     * @param companyVcProductIds
     * @return
     */
    default public List<DataCompanyVcProductCompetitiveProductRelDO> listByCompanyVcProductIds(List<Long> companyVcProductIds) {
        Assert.notEmpty(companyVcProductIds,"companyVcProductIds 不能为空");
        return list(Wrappers.<DataCompanyVcProductCompetitiveProductRelDO>lambdaQuery().in(DataCompanyVcProductCompetitiveProductRelDO::getCompanyVcProductId, companyVcProductIds));
    }
    /**
     * 根据 融资产品id 查询
     * @param companyVcProductId
     * @return
     */
    default public List<DataCompanyVcProductCompetitiveProductRelDO> listByCompanyVcProductId(Long companyVcProductId) {
        Assert.notNull(companyVcProductId,"companyVcProductId 不能为空");
        return list(Wrappers.<DataCompanyVcProductCompetitiveProductRelDO>lambdaQuery().eq(DataCompanyVcProductCompetitiveProductRelDO::getCompanyVcProductId, companyVcProductId));
    }

    /**
     * 根据 融资产品竞品id 查询
     * @param companyVcCompetitiveProductId
     * @return
     */
    default public List<DataCompanyVcProductCompetitiveProductRelDO> listByCompanyVcCompetitiveProductId(Long companyVcCompetitiveProductId) {
        Assert.notNull(companyVcCompetitiveProductId,"companyVcCompetitiveProductId 不能为空");
        return list(Wrappers.<DataCompanyVcProductCompetitiveProductRelDO>lambdaQuery().eq(DataCompanyVcProductCompetitiveProductRelDO::getCompanyVcCompetitiveProductId, companyVcCompetitiveProductId));
    }
    /**
     * 根据 融资产品id 和 融资产品竞品id 查询
     * @param companyVcProductId
     * @param companyVcCompetitiveProductId
     * @return
     */
    default public DataCompanyVcProductCompetitiveProductRelDO getByCompanyVcProductIdAndCompanyVcCompetitiveProductId(Long companyVcProductId, Long companyVcCompetitiveProductId) {
        Assert.notNull(companyVcProductId,"companyVcProductId 不能为空");
        Assert.notNull(companyVcCompetitiveProductId,"companyVcCompetitiveProductId 不能为空");
        return getOne(Wrappers.<DataCompanyVcProductCompetitiveProductRelDO>lambdaQuery().eq(DataCompanyVcProductCompetitiveProductRelDO::getCompanyVcProductId, companyVcProductId).eq(DataCompanyVcProductCompetitiveProductRelDO::getCompanyVcCompetitiveProductId, companyVcCompetitiveProductId));
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyVcProductCompetitiveProductRelDO>lambdaUpdate().eq(DataCompanyVcProductCompetitiveProductRelDO::getId, id)
                .set(DataCompanyVcProductCompetitiveProductRelDO::getLatestHandleAt, LocalDateTime.now()));
    }

}
