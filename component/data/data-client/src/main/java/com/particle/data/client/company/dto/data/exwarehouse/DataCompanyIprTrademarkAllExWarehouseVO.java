package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 企业知识产权商标 全部 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 16:13:25
 */
@Data
@Schema
public class DataCompanyIprTrademarkAllExWarehouseVO extends VO {

    @Schema(description = "企业知识产权商标基本信息")
    private DataCompanyIprTrademarkExWarehouseVO basic;

    @Schema(description = "企业知识产权商标当事人信息")
    private List<DataCompanyIprTrademarkPartyExWarehouseVO> parties;

    @Schema(description = "企业知识产权商标许可信息")
    private List<DataCompanyIprTrademarkLicenseExWarehouseVO> licenses;

    @Schema(description = "企业知识产权商标质押信息")
    private List<DataCompanyIprTrademarkPledgeExWarehouseVO> pledges;

    @Schema(description = "企业知识产权商标转让信息")
    private List<DataCompanyIprTrademarkTransferExWarehouseVO> transfers;

}
