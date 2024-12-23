package com.particle.report.infrastructure.reportapi.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.report.infrastructure.reportapi.dos.ReportReportApiDO;

import java.util.List;

/**
 * <p>
 * 报告接口 服务类
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
public interface IReportReportApiService extends IBaseService<ReportReportApiDO> {

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    default ReportReportApiDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<ReportReportApiDO>lambdaQuery().eq(ReportReportApiDO::getCode, code));
    }



    /**
     * 根据编码查询多个
     * @param codes
     * @return
     */
    default List<ReportReportApiDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<ReportReportApiDO>lambdaQuery().in(ReportReportApiDO::getCode, codes));
    }





    /**
     * 根据接口地址查询
     * @param url
     * @return
     */
    default ReportReportApiDO getByUrl(String url) {
        Assert.notNull(url,"url 不能为空");
        return getOne(Wrappers.<ReportReportApiDO>lambdaQuery().eq(ReportReportApiDO::getUrl, url));
    }



    /**
     * 根据接口地址查询多个
     * @param urls
     * @return
     */
    default List<ReportReportApiDO> getByUrls(List<String> urls) {
        Assert.notEmpty(urls,"urls 不能为空");
        return list(Wrappers.<ReportReportApiDO>lambdaQuery().in(ReportReportApiDO::getUrl, urls));
    }


    /**
     * 根据报告片段模板id查询
     * @param reportSegmentTemplateId
     * @return
     */
    default List<ReportReportApiDO> getByReportSegmentTemplateId(Long reportSegmentTemplateId) {
        Assert.notNull(reportSegmentTemplateId,"reportSegmentTemplateId 不能为空");
        return list(Wrappers.<ReportReportApiDO>lambdaQuery().eq(ReportReportApiDO::getReportSegmentTemplateId, reportSegmentTemplateId));
    }



    /**
     * 根据报告片段模板id查询多个
     * @param reportSegmentTemplateIds
     * @return
     */
    default List<ReportReportApiDO> getByReportSegmentTemplateIds(List<Long> reportSegmentTemplateIds) {
        Assert.notEmpty(reportSegmentTemplateIds,"reportSegmentTemplateIds 不能为空");
        return list(Wrappers.<ReportReportApiDO>lambdaQuery().in(ReportReportApiDO::getReportSegmentTemplateId, reportSegmentTemplateIds));
    }























}
