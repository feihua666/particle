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
 * 企业股东 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
@Data
@Schema
public class DataCompanyShareholderQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "股东名称")
    private String shareholderName;


    @Schema(description = "是否法人为自然人")
    private Boolean isShareholderNaturalPerson;


    @Schema(description = "股东公司id")
    private Long shareholderCompanyId;


    @Schema(description = "股东个人id")
    private Long shareholderCompanyPersonId;


    @Schema(description = "持股比例")
    private BigDecimal shareholdingPercent;
    

    @Schema(description = "持股数量")
    private Integer shareholdingNum;


    @Schema(description = "持股金额（万元）")
    private BigDecimal shareholdingAmount;
    

    @Schema(description = "持股金额币种")
    private Long shareholdingAmountCurrencyDictId;


    @Schema(description = "认缴出资金额（万元）")
    private BigDecimal subCapital;
    

    @Schema(description = "认缴出资金额币种")
    private Long subCapitalCurrencyDictId;


    @Schema(description = "认缴出资方式")
    private Long subCapitalTypeDictId;


    @Schema(description = "认缴出资日期")
    private LocalDate subCapitalDate;
    

    @Schema(description = "实缴出资金额（万元）")
    private BigDecimal actualCapital;
    

    @Schema(description = "实缴出资金额币种")
    private Long actualCapitalCurrencyDictId;


    @Schema(description = "实缴出资日期")
    private LocalDate actualCapitalDate;
    

    @Schema(description = "是否工商登记股东")
    private Boolean isRegPublic;


    @Schema(description = "是否上市最新公示股东")
    private Boolean isListedLatestPublic;

	@Schema(description = "上市最新公示股东日期")
	private LocalDate listedLatestPublicDate;


    @Schema(description = "是否最新年报股东")
    private Boolean isYearReportLatestPublic;


    @Schema(description = "最新年报股东年份")
    private Integer yearReportLatestPublicYear;

	@Schema(description = "数据md5,shareholder_name")
	private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
