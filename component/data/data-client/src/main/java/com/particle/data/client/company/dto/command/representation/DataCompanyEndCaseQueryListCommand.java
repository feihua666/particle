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
 * 企业终本案件 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
@Data
@Schema
public class DataCompanyEndCaseQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "案号")
    private String caseNo;


    @Schema(description = "被执行人名称")
    private String executedPersonName;


    @Schema(description = "是否被执行人为自然人")
    private Boolean isExecutedPersonNaturalPerson;


    @Schema(description = "被执行人公司id")
    private Long executedPersonCompanyId;


    @Schema(description = "被执行人个人id")
    private Long executedPersonCompanyPersonId;


    @Schema(description = "被执行人证照/证件号码")
    private String executedPersonLicenseNo;


    @Schema(description = "法院名称")
    private String courtName;


    @Schema(description = "法院名称公司id")
    private Long courtCompanyId;


    @Schema(description = "立案日期")
    private LocalDate fileCaseDate;


    @Schema(description = "结束日期")
    private LocalDate finishedCaseDate;


    @Schema(description = "执行标的金额（万元）")
    private BigDecimal executeAmount;


    @Schema(description = "执行标的金额币种")
    private Long executeAmountCurrencyDictId;


    @Schema(description = "未履行金额（万元）")
    private BigDecimal unPerformAmount;


    @Schema(description = "未履行金额币种")
    private Long unPerformAmountCurrencyDictId;


    @Schema(description = "数据md5")
    private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;









}
