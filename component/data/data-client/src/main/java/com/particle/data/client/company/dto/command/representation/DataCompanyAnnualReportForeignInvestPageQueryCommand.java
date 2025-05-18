package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业年报对外投资 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Data
@Schema
public class DataCompanyAnnualReportForeignInvestPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;


    @Schema(description = "年报年度")
    private Integer year;


    @Schema(description = "序号")
    private Integer serialNumber;


    @Schema(description = "对外投资企业")
    private Long investCompanyId;

	@Schema(description = "对外投资企业名称")
	private String investCompanyName;

	@Schema(description = "对外投资企业统一社会信用代码")
	private String investCompanyUscc;


    @Schema(description = "对外投资比例")
    private BigDecimal investPercent;
    

    @Schema(description = "对外投资金额（万元）")
    private BigDecimal investAmount;
    

    @Schema(description = "对外投资金额币种")
    private Long investAmountCurrencyDictId;

	@Schema(description = "数据md5,invest_company_name + invest_company_uscc")
	private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}