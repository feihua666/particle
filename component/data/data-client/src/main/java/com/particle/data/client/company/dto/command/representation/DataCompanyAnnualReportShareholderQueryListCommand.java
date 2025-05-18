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
 * 企业年报股东 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Data
@Schema
public class DataCompanyAnnualReportShareholderQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;


    @Schema(description = "年报年度")
    private Integer year;


    @Schema(description = "序号")
    private Integer serialNumber;


    @Schema(description = "股东名称")
    private String shareholderName;


    @Schema(description = "是否股东名称为自然人")
    private Boolean isShareholderNaturalPerson;


    @Schema(description = "股东名称公司id")
    private Long shareholderCompanyId;


    @Schema(description = "股东名称个人id")
    private Long shareholderCompanyPersonId;


    @Schema(description = "持股比例")
    private BigDecimal shareholdingPercent;
    

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

	@Schema(description = "数据md5,shareholder_name")
	private String dataMd5;
    

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
