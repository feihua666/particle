package com.particle.data.client.company.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业融资 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Data
@Schema
public class DataCompanyVcFinancingVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;

    @Schema(description = "公司产品id")
    private Long companyVcProductId;

	@Schema(description = "产品名称，冗余产品名称")
	private String productName;

    @Schema(description = "融资轮次")
    private Long roundDictId;

	@Schema(description = "融资轮次名称")
	private String roundName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "roundDictId",mapValueField = "name")
    @Schema(description = "融资轮次对应字典名称")
    private String roundDictName;

    @Schema(description = "融资金额（万元）")
    private BigDecimal amount;

    @Schema(description = "融资金额币种")
    private Long amountCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "amountCurrencyDictId",mapValueField = "name")
    @Schema(description = "融资金额币种对应字典名称")
    private String amountCurrencyDictName;

    @Schema(description = "估值")
    private String valuation;

    @Schema(description = "融资日期")
    private LocalDate financingDate;

    @Schema(description = "报道时间")
    private LocalDateTime publishAt;

    @Schema(description = "报道标题")
    private String publishTitle;

    @Schema(description = "报道链接地址")
    private String publishUrl;

    @Schema(description = "报道快照链接地址")
    private String publishSnapshotUrl;

	@Schema(description = "数据md5,product_name + round_name + amount + valuation + financing_date + publish_title")
	private String dataMd5;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;



}