package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportSocialSecurityDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业年报社保 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
public interface IDataCompanyAnnualReportSocialSecurityService extends IBaseService<DataCompanyAnnualReportSocialSecurityDO> {


    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param year
     * @param pageQueryForm
     * @return year有值时，只会有一条
     */
    default public Page<DataCompanyAnnualReportSocialSecurityDO> listPageByCompanyId(Long companyId, Integer year, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyAnnualReportSocialSecurityDO>lambdaQuery()
                .eq(DataCompanyAnnualReportSocialSecurityDO::getCompanyId, companyId)
                .eq(year != null,DataCompanyAnnualReportSocialSecurityDO::getYear, year)
        );
    }
    /**
     * 根据年报ID分页查询列表
     * @param companyAnnualReportId
     * @param pageQueryForm
     * @return 只会返回一条
     */
    default public Page<DataCompanyAnnualReportSocialSecurityDO> listPageByCompanyAnnualReportId(Long companyAnnualReportId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyAnnualReportId,"companyAnnualReportId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyAnnualReportSocialSecurityDO>lambdaQuery()
                .eq(DataCompanyAnnualReportSocialSecurityDO::getCompanyAnnualReportId, companyAnnualReportId));
    }
    /**
     * 根据 公司id 和 年报年度查询
     * @param companyId
     * @param year
     * @return
     */
    default public DataCompanyAnnualReportSocialSecurityDO getByCompanyIdAndYear(Long companyId, Integer year) {
        Assert.notNull(companyId,"companyId 不能为空");
        Assert.notNull(year,"year 不能为空");
        return getOne(Wrappers.<DataCompanyAnnualReportSocialSecurityDO>lambdaQuery()
                .eq(DataCompanyAnnualReportSocialSecurityDO::getCompanyId, companyId)
                .eq(DataCompanyAnnualReportSocialSecurityDO::getYear, year));
    }
    /**
     * 根据 年报id 查询
     * @param companyAnnualReportId
     * @return
     */
    default public DataCompanyAnnualReportSocialSecurityDO getByCompanyAnnualReportId(Long companyAnnualReportId) {
        Assert.notNull(companyAnnualReportId,"companyAnnualReportId 不能为空");
        return getOne(Wrappers.<DataCompanyAnnualReportSocialSecurityDO>lambdaQuery()
                .eq(DataCompanyAnnualReportSocialSecurityDO::getCompanyAnnualReportId, companyAnnualReportId));
    }
    /**
     * 根据 年报id 查询
     * @param companyAnnualReportIds
     * @return
     */
    default public List<DataCompanyAnnualReportSocialSecurityDO> listByCompanyAnnualReportIds(List<Long> companyAnnualReportIds) {
        Assert.notEmpty(companyAnnualReportIds,"companyAnnualReportIds 不能为空");
        return list(Wrappers.<DataCompanyAnnualReportSocialSecurityDO>lambdaQuery()
                .in(DataCompanyAnnualReportSocialSecurityDO::getCompanyAnnualReportId, companyAnnualReportIds));
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyAnnualReportSocialSecurityDO>lambdaUpdate().eq(DataCompanyAnnualReportSocialSecurityDO::getId, id)
                .set(DataCompanyAnnualReportSocialSecurityDO::getLatestHandleAt, LocalDateTime.now()));
    }
}
