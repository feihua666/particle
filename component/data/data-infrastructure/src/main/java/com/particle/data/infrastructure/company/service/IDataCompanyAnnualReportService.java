package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;

/**
 * <p>
 * 企业年报 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
public interface IDataCompanyAnnualReportService extends IBaseService<DataCompanyAnnualReportDO> {


    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param year 可选 年报年度
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyAnnualReportDO> listPageByCompanyId(Long companyId,Integer year, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyAnnualReportDO>lambdaQuery()
                .eq(DataCompanyAnnualReportDO::getCompanyId, companyId)
                .eq(year != null,DataCompanyAnnualReportDO::getYear, year))
                ;
    }
    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param year 可选 年报年度
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyAnnualReportDO> listPageByCompanyIdOrderByYearDesc(Long companyId,Integer year, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyAnnualReportDO>lambdaQuery()
                .eq(DataCompanyAnnualReportDO::getCompanyId, companyId)
                .eq(year != null,DataCompanyAnnualReportDO::getYear, year)
                .orderByDesc(DataCompanyAnnualReportDO::getYear)
        );
    }

    /**
     * 根据公司id和年度查询
     * @param companyId
     * @param year
     * @return
     */
    default public DataCompanyAnnualReportDO getByCompanyIdAndYear(Long companyId,Integer year) {
        Assert.notNull(companyId,"companyId 不能为空");
        Assert.notNull(year,"year 不能为空");
        return getOne(Wrappers.<DataCompanyAnnualReportDO>lambdaQuery()
                .eq(DataCompanyAnnualReportDO::getCompanyId, companyId)
                .eq(DataCompanyAnnualReportDO::getYear, year));
    }
    /**
     * 根据公司id和年度查询
     * @param companyId
     * @param year
     * @return
     */
    default public DataCompanyAnnualReportDO getByCompanyIdAndYearOrderByYearDesc(Long companyId,Integer year) {
        Assert.notNull(companyId,"companyId 不能为空");
        Assert.notNull(year,"year 不能为空");
        return getOne(Wrappers.<DataCompanyAnnualReportDO>lambdaQuery()
                .eq(DataCompanyAnnualReportDO::getCompanyId, companyId)
                .eq(DataCompanyAnnualReportDO::getYear, year)
                .orderByDesc(DataCompanyAnnualReportDO::getYear)
        );
    }


    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyAnnualReportDO>lambdaUpdate().eq(DataCompanyAnnualReportDO::getId, id)
                .set(DataCompanyAnnualReportDO::getLatestHandleAt, LocalDateTime.now()));
    }



}
