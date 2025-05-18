package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingInvestInstitutionRelDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;
/**
 * <p>
 * 企业融资历史投资机构关系 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
public interface IDataCompanyVcFinancingInvestInstitutionRelService extends IBaseService<DataCompanyVcFinancingInvestInstitutionRelDO> {

    /**
     * 根据 融资id 查询
     * @param companyVcFinancingIds
     * @return
     */
    default public List<DataCompanyVcFinancingInvestInstitutionRelDO> listByCompanyVcFinancingIds(List<Long> companyVcFinancingIds) {
        Assert.notEmpty(companyVcFinancingIds,"companyVcFinancingIds 不能为空");
        return list(Wrappers.<DataCompanyVcFinancingInvestInstitutionRelDO>lambdaQuery().in(DataCompanyVcFinancingInvestInstitutionRelDO::getCompanyVcFinancingId, companyVcFinancingIds));
    }
    /**
     * 根据 融资id 查询
     * @param companyVcFinancingId
     * @return
     */
    default public List<DataCompanyVcFinancingInvestInstitutionRelDO> listByCompanyVcFinancingId(Long companyVcFinancingId) {
        Assert.notNull(companyVcFinancingId,"companyVcFinancingId 不能为空");
        return list(Wrappers.<DataCompanyVcFinancingInvestInstitutionRelDO>lambdaQuery().eq(DataCompanyVcFinancingInvestInstitutionRelDO::getCompanyVcFinancingId, companyVcFinancingId));
    }

    /**
     * 根据 投资机构id 查询
     * @param companyVcInvestInstitutionId
     * @return
     */
    default public List<DataCompanyVcFinancingInvestInstitutionRelDO> listByCompanyVcInvestInstitutionId(Long companyVcInvestInstitutionId) {
        Assert.notNull(companyVcInvestInstitutionId,"companyVcInvestInstitutionId 不能为空");
        return list(Wrappers.<DataCompanyVcFinancingInvestInstitutionRelDO>lambdaQuery().eq(DataCompanyVcFinancingInvestInstitutionRelDO::getCompanyVcInvestInstitutionId, companyVcInvestInstitutionId));
    }
    /**
     * 根据 融资id 和 投资机构id 查询
     * @param companyVcFinancingId
     * @param companyVcInvestInstitutionId
     * @return
     */
    default public DataCompanyVcFinancingInvestInstitutionRelDO getByCompanyVcFinancingIdAndCompanyVcInvestInstitutionId(Long companyVcFinancingId, Long companyVcInvestInstitutionId) {
        Assert.notNull(companyVcFinancingId,"companyVcFinancingId 不能为空");
        Assert.notNull(companyVcInvestInstitutionId,"companyVcInvestInstitutionId 不能为空");
        return getOne(Wrappers.<DataCompanyVcFinancingInvestInstitutionRelDO>lambdaQuery().eq(DataCompanyVcFinancingInvestInstitutionRelDO::getCompanyVcFinancingId, companyVcFinancingId).eq(DataCompanyVcFinancingInvestInstitutionRelDO::getCompanyVcInvestInstitutionId, companyVcInvestInstitutionId));
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyVcFinancingInvestInstitutionRelDO>lambdaUpdate().eq(DataCompanyVcFinancingInvestInstitutionRelDO::getId, id)
                .set(DataCompanyVcFinancingInvestInstitutionRelDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
