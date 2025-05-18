package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
/**
 * <p>
 * 企业知识产权专利 全部 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-27 17:01:08
 */
@Data
@Schema
public class DataCompanyIprPatentAllExWarehouseVO extends VO {

    @Schema(description = "企业知识产权专利基本信息")
    private DataCompanyIprPatentExWarehouseVO basic;

    @Schema(description = "企业知识产权专利当事人信息")
    private List<DataCompanyIprPatentPartyExWarehouseVO> parties;

    @Schema(description = "企业知识产权专利证书信息")
    private List<DataCompanyIprPatentCertificateExWarehouseVO> certificates;

    @Schema(description = "企业知识产权专利被引证信息")
    private List<DataCompanyIprPatentCitedExWarehouseVO> cites;

    @Schema(description = "企业知识产权专利内容")
    private DataCompanyIprPatentContentExWarehouseVO content;

    @Schema(description = "企业知识产权专利同族信息")
    private List<DataCompanyIprPatentFamilyExWarehouseVO> families;

    @Schema(description = "企业知识产权专利法律状态信息")
    private List<DataCompanyIprPatentLegalStatusExWarehouseVO> legalStatuses;

    @Schema(description = "企业知识产权专利许可信息")
    private List<DataCompanyIprPatentLicenseExWarehouseVO> licenses;

    @Schema(description = "企业知识产权专利通知书信息")
    private List<DataCompanyIprPatentNoticeExWarehouseVO> notices;

    @Schema(description = "企业知识产权专利缴费信息")
    private List<DataCompanyIprPatentPaymentExWarehouseVO> payments;

    @Schema(description = "企业知识产权专利质押信息")
    private List<DataCompanyIprPatentPledgeExWarehouseVO> pledges;

    @Schema(description = "企业知识产权专利引证信息")
    private List<DataCompanyIprPatentQuoteExWarehouseVO> quotes;

    @Schema(description = "企业知识产权专利统计")
    private DataCompanyIprPatentStatisticExWarehouseVO statistic;

    @Schema(description = "企业知识产权专利转让信息")
    private List<DataCompanyIprPatentTransferExWarehouseVO> transfers;
}
