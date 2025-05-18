package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyVcInvestInstitutionDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;

/**
 * <p>
 * 企业投资机构 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:13
 */
public interface IDataCompanyVcInvestInstitutionService extends IBaseService<DataCompanyVcInvestInstitutionDO> {

    /**
     * 根据 dataMd5 查询
     * @param dataMd5
     * @return
     */
    default public DataCompanyVcInvestInstitutionDO getByDataMd5(String dataMd5) {
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyVcInvestInstitutionDO>lambdaQuery().eq(DataCompanyVcInvestInstitutionDO::getDataMd5, dataMd5));
    }
    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyVcInvestInstitutionDO> listPageByCompanyId(Long companyId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyVcInvestInstitutionDO>lambdaQuery().eq(DataCompanyVcInvestInstitutionDO::getCompanyId, companyId));
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyVcInvestInstitutionDO>lambdaUpdate().eq(DataCompanyVcInvestInstitutionDO::getId, id)
                .set(DataCompanyVcInvestInstitutionDO::getLatestHandleAt, LocalDateTime.now()));
    }



}
