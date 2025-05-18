package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignGuaranteeDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业年报对外担保 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
public interface IDataCompanyAnnualReportForeignGuaranteeService extends IBaseService<DataCompanyAnnualReportForeignGuaranteeDO> {

    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param year
     * @param pageQueryForm
     * @return year有值时，只会有一条
     */
    default public Page<DataCompanyAnnualReportForeignGuaranteeDO> listPageByCompanyId(Long companyId, Integer year, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyAnnualReportForeignGuaranteeDO>lambdaQuery()
                .eq(DataCompanyAnnualReportForeignGuaranteeDO::getCompanyId, companyId)
                .eq(year != null,DataCompanyAnnualReportForeignGuaranteeDO::getYear, year)
        );
    }
    /**
     * 根据年报ID分页查询列表
     * @param companyAnnualReportId
     * @param pageQueryForm
     * @return 只会返回一条
     */
    default public Page<DataCompanyAnnualReportForeignGuaranteeDO> listPageByCompanyAnnualReportId(Long companyAnnualReportId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyAnnualReportId,"companyAnnualReportId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyAnnualReportForeignGuaranteeDO>lambdaQuery()
                .eq(DataCompanyAnnualReportForeignGuaranteeDO::getCompanyAnnualReportId, companyAnnualReportId));
    }
    /**
     * 根据 公司id 和 年报年度 和 dataMd5 查询
     * @param companyId
     * @param year
     * @param dataMd5
     * @return
     */
    default public DataCompanyAnnualReportForeignGuaranteeDO getByCompanyIdAndYearAndDataMd5(Long companyId,Integer year,String dataMd5) {
        Assert.notNull(companyId,"companyId 不能为空");
        Assert.notNull(year,"year 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyAnnualReportForeignGuaranteeDO>lambdaQuery()
                .eq(DataCompanyAnnualReportForeignGuaranteeDO::getCompanyId, companyId)
                .eq(DataCompanyAnnualReportForeignGuaranteeDO::getYear, year)
                .eq(DataCompanyAnnualReportForeignGuaranteeDO::getDataMd5, dataMd5)
        );
    }
    /**
     * 根据 年报id 和 dataMd5 查询
     * @param companyAnnualReportId
     * @param dataMd5
     * @return
     */
    default public DataCompanyAnnualReportForeignGuaranteeDO getByCompanyAnnualReportIdAndDataMd5(Long companyAnnualReportId,String dataMd5) {
        Assert.notNull(companyAnnualReportId,"companyAnnualReportId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyAnnualReportForeignGuaranteeDO>lambdaQuery()
                .eq(DataCompanyAnnualReportForeignGuaranteeDO::getCompanyAnnualReportId, companyAnnualReportId)
                .eq(DataCompanyAnnualReportForeignGuaranteeDO::getDataMd5, dataMd5)
        );
    }
    /**
     * 根据 年报id 查询
     * @param companyAnnualReportIds
     * @return
     */
    default public List<DataCompanyAnnualReportForeignGuaranteeDO> listByCompanyAnnualReportIds(List<Long> companyAnnualReportIds) {
        Assert.notEmpty(companyAnnualReportIds,"companyAnnualReportIds 不能为空");
        return list(Wrappers.<DataCompanyAnnualReportForeignGuaranteeDO>lambdaQuery()
                .in(DataCompanyAnnualReportForeignGuaranteeDO::getCompanyAnnualReportId, companyAnnualReportIds));
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyAnnualReportForeignGuaranteeDO>lambdaUpdate().eq(DataCompanyAnnualReportForeignGuaranteeDO::getId, id)
                .set(DataCompanyAnnualReportForeignGuaranteeDO::getLatestHandleAt, LocalDateTime.now()));
    }

}
