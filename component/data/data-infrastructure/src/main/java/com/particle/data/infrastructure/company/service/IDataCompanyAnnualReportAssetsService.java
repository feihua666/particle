package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAssetsDO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAssetsDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业资产状况信息 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:31
 */
public interface IDataCompanyAnnualReportAssetsService extends IBaseService<DataCompanyAnnualReportAssetsDO> {


    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param year
     * @param pageQueryForm
     * @return year有值时，只会有一条
     */
    default public Page<DataCompanyAnnualReportAssetsDO> listPageByCompanyId(Long companyId, Integer year, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyAnnualReportAssetsDO>lambdaQuery()
                .eq(DataCompanyAnnualReportAssetsDO::getCompanyId, companyId)
                .eq(year != null,DataCompanyAnnualReportAssetsDO::getYear, year)
        );
    }
    /**
     * 根据年报ID分页查询列表
     * @param companyAnnualReportId
     * @param pageQueryForm
     * @return 只会返回一条
     */
    default public Page<DataCompanyAnnualReportAssetsDO> listPageByCompanyAnnualReportId(Long companyAnnualReportId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyAnnualReportId,"companyAnnualReportId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyAnnualReportAssetsDO>lambdaQuery()
                .eq(DataCompanyAnnualReportAssetsDO::getCompanyAnnualReportId, companyAnnualReportId));
    }
    /**
     * 根据 公司id 和 年报年度 查询
     * @param companyId
     * @param year
     * @return
     */
    default public DataCompanyAnnualReportAssetsDO getByCompanyIdAndYear(Long companyId,Integer year) {
        Assert.notNull(companyId,"companyId 不能为空");
        Assert.notNull(year,"year 不能为空");
        return getOne(Wrappers.<DataCompanyAnnualReportAssetsDO>lambdaQuery()
                .eq(DataCompanyAnnualReportAssetsDO::getCompanyId, companyId)
                .eq(DataCompanyAnnualReportAssetsDO::getYear, year));
    }
    /**
     * 根据 年报id 查询
     * @param companyAnnualReportId
     * @return
     */
    default public DataCompanyAnnualReportAssetsDO getByCompanyAnnualReportId(Long companyAnnualReportId) {
        Assert.notNull(companyAnnualReportId,"companyAnnualReportId 不能为空");
        return getOne(Wrappers.<DataCompanyAnnualReportAssetsDO>lambdaQuery()
                .eq(DataCompanyAnnualReportAssetsDO::getCompanyAnnualReportId, companyAnnualReportId));
    }
    /**
     * 根据 年报id 查询
     * @param companyAnnualReportIds
     * @return
     */
    default public List<DataCompanyAnnualReportAssetsDO> listByCompanyAnnualReportIds(List<Long> companyAnnualReportIds) {
        Assert.notEmpty(companyAnnualReportIds,"companyAnnualReportIds 不能为空");
        return list(Wrappers.<DataCompanyAnnualReportAssetsDO>lambdaQuery()
                .in(DataCompanyAnnualReportAssetsDO::getCompanyAnnualReportId, companyAnnualReportIds));
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyAnnualReportAssetsDO>lambdaUpdate().eq(DataCompanyAnnualReportAssetsDO::getId, id)
                .set(DataCompanyAnnualReportAssetsDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
