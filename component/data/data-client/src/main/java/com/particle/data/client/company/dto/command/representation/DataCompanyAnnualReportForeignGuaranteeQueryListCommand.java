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
 * 企业年报对外担保 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@Data
@Schema
public class DataCompanyAnnualReportForeignGuaranteeQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;


    @Schema(description = "年报年度")
    private Integer year;


    @Schema(description = "序号")
    private Integer serialNumber;


    @Schema(description = "债务人名称")
    private String debtorName;


    @Schema(description = "是否债务人为自然人")
    private Boolean isDebtorNaturalPerson;


    @Schema(description = "债务人公司id")
    private Long debtorCompanyId;


    @Schema(description = "债务人个人id")
    private Long debtorCompanyPersonId;


    @Schema(description = "债权人名称")
    private String creditorName;


    @Schema(description = "是否债权人为自然人")
    private Boolean isCreditorNaturalPerson;


    @Schema(description = "债权人公司id")
    private Long creditorCompanyId;


    @Schema(description = "债权人个人id")
    private Long creditorCompanyPersonId;


    @Schema(description = "主债权种类")
    private Long creditoTypeDictId;


    @Schema(description = "主债权金额(万元)")
    private BigDecimal creditoAmount;
    

    @Schema(description = "主债权金额币种")
    private Long creditoAmountCurrencyDictId;


    @Schema(description = "履行债务的期限自")
    private LocalDate debtFromDate;
    

    @Schema(description = "履行债务的期限至")
    private LocalDate debtToDate;
    

    @Schema(description = "保证担保的范围")
    private String guaranteeScope;


    @Schema(description = "保证的期间")
    private Long guaranteeTermDictId;


    @Schema(description = "保证的方式")
    private Long guaranteeTypeDictId;

	@Schema(description = "数据md5,debtor_name + creditor_name")
	private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}