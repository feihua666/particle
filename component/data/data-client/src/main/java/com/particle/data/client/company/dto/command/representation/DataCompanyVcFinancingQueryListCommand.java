package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业融资 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Data
@Schema
public class DataCompanyVcFinancingQueryListCommand extends AbstractBaseQueryCommand {



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


    @Schema(description = "融资金额（万元）")
    private BigDecimal amount;


    @Schema(description = "融资金额币种")
    private Long amountCurrencyDictId;


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