package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
/**
 * <p>
 * 企业知识产权商标许可信息 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyIprTrademarkLicenseExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业知识产权商标表id")
    private Long companyIprTrademarkId;

    @Schema(description = "许可期号")
    private String licenseIssueNo;

    @Schema(description = "许可页码")
    private String licensePageNo;

    @Schema(description = "许可备案号")
    private String licenseFilingNo;

    @Schema(description = "许可备案公告日期")
    private LocalDate licenseFilingPublicDate;

    @Schema(description = "许可期限")
    private String licenseTerm;

    @Schema(description = "许可使用的商品服务")
    private String licenseGoodService;

    @Schema(description = "数据md5")
    private String dataMd5;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;

    @Schema(description = "企业知识产权商标许可人信息")
    private List<DataCompanyIprTrademarkLicensePersonExWarehouseVO> licensePersons;

}
