package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAdministrativeLicenseDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业年报行政许可 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
public interface IDataCompanyAnnualReportAdministrativeLicenseService extends IBaseService<DataCompanyAnnualReportAdministrativeLicenseDO> {


    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param year
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyAnnualReportAdministrativeLicenseDO> listPageByCompanyId(Long companyId,Integer year, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyAnnualReportAdministrativeLicenseDO>lambdaQuery()
                .eq(DataCompanyAnnualReportAdministrativeLicenseDO::getCompanyId, companyId)
                .eq(year != null,DataCompanyAnnualReportAdministrativeLicenseDO::getYear, year)
        );
    }
    /**
     * 根据年报ID分页查询列表
     * @param companyAnnualReportId
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyAnnualReportAdministrativeLicenseDO> listPageByCompanyAnnualReportId(Long companyAnnualReportId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyAnnualReportId,"companyAnnualReportId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyAnnualReportAdministrativeLicenseDO>lambdaQuery()
                .eq(DataCompanyAnnualReportAdministrativeLicenseDO::getCompanyAnnualReportId, companyAnnualReportId));
    }
    /**
     * 根据 公司id 和 年报年度 查询
     * @param companyId
     * @param year
     * @param dataMd5
     * @return
     */
    default public DataCompanyAnnualReportAdministrativeLicenseDO getByCompanyIdAndYearAndDataMd5(Long companyId,Integer year,String dataMd5) {
        Assert.notNull(companyId,"companyId 不能为空");
        Assert.notNull(year,"year 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyAnnualReportAdministrativeLicenseDO>lambdaQuery()
                .eq(DataCompanyAnnualReportAdministrativeLicenseDO::getCompanyId, companyId)
                .eq(DataCompanyAnnualReportAdministrativeLicenseDO::getYear, year)
                .eq(DataCompanyAnnualReportAdministrativeLicenseDO::getDataMd5, dataMd5));
    }
    /**
     * 根据 年报id 查询
     * @param companyAnnualReportIds
     * @return
     */
    default public List<DataCompanyAnnualReportAdministrativeLicenseDO> listByCompanyAnnualReportIds(List<Long> companyAnnualReportIds) {
        Assert.notEmpty(companyAnnualReportIds,"companyAnnualReportIds 不能为空");
        return list(Wrappers.<DataCompanyAnnualReportAdministrativeLicenseDO>lambdaQuery()
                .in(DataCompanyAnnualReportAdministrativeLicenseDO::getCompanyAnnualReportId, companyAnnualReportIds));
    }
    /**
     * 根据 年报id 和 文件名 查询
     * @param companyAnnualReportId
     * @param dataMd5
     * @return
     */
    default public DataCompanyAnnualReportAdministrativeLicenseDO getByCompanyAnnualReportIdAndDataMd5(Long companyAnnualReportId,String dataMd5) {
        Assert.notNull(companyAnnualReportId,"companyAnnualReportId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyAnnualReportAdministrativeLicenseDO>lambdaQuery()
                .eq(DataCompanyAnnualReportAdministrativeLicenseDO::getCompanyAnnualReportId, companyAnnualReportId)
                .eq(DataCompanyAnnualReportAdministrativeLicenseDO::getDataMd5, dataMd5));
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyAnnualReportAdministrativeLicenseDO>lambdaUpdate().eq(DataCompanyAnnualReportAdministrativeLicenseDO::getId, id)
                .set(DataCompanyAnnualReportAdministrativeLicenseDO::getLatestHandleAt, LocalDateTime.now()));
    }



}
