package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
/**
 * <p>
 * 企业年报 全部 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-25 15:21:27
 */
@Data
@Schema
public class DataCompanyAnnualReportAllExWarehouseVO extends VO {

    @Schema(description = "企业年报基本信息")
    private DataCompanyAnnualReportExWarehouseVO basic;

    @Schema(description = "企业年报社保")
    private DataCompanyAnnualReportSocialSecurityExWarehouseVO socialSecurity;

    @Schema(description = "企业资产状况信息")
    private DataCompanyAnnualReportAssetsExWarehouseVO assets;

    @Schema(description = "企业年报行政许可")
    private List<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> administrativeLicenses;

    @Schema(description = "企业年报变更")
    private List<DataCompanyAnnualReportChangeExWarehouseVO> changes;

    @Schema(description = "企业年报股权变更")
    private List<DataCompanyAnnualReportEquityChangeExWarehouseVO> equityChanges;

    @Schema(description = "企业年报对外担保")
    private List<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> foreignGuarantees;

    @Schema(description = "企业年报对外投资")
    private List<DataCompanyAnnualReportForeignInvestExWarehouseVO> foreignInvests;

    @Schema(description = "企业年报股东")
    private List<DataCompanyAnnualReportShareholderExWarehouseVO> shareholders;

    @Schema(description = "企业年报网站网店")
    private List<DataCompanyAnnualReportWebsiteExWarehouseVO> websites;

}
