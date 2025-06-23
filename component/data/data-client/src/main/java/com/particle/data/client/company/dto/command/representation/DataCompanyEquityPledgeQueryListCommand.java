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
 * 企业股权出质 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
@Data
@Schema
public class DataCompanyEquityPledgeQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "登记编号")
    private String regNo;


    @Schema(description = "出质人")
    private String pledgor;


    @Schema(description = "是否出质人为自然人")
    private Boolean isPledgorNaturalPerson;


    @Schema(description = "出质人公司id")
    private Long pledgorCompanyId;


    @Schema(description = "出质人个人id")
    private Long pledgorCompanyPersonId;


    @Schema(description = "出质人证照/证件号码")
    private String pledgorLicenseNo;


    @Schema(description = "出质股权数额（万股）")
    private BigDecimal equityAmount;
    

    @Schema(description = "质权人")
    private String pledgee;


    @Schema(description = "是否质权人为自然人")
    private Boolean isPledgeeNaturalPerson;


    @Schema(description = "质权人公司id")
    private Long pledgeeCompanyId;


    @Schema(description = "质权人个人id")
    private Long pledgeeCompanyPersonId;


    @Schema(description = "质权人证照/证件号码")
    private String pledgeeLicenseNo;


    @Schema(description = "股权出质设立登记日期")
    private LocalDate regDate;
    

    @Schema(description = "状态")
    private String statusName;


    @Schema(description = "公示日期")
    private LocalDate publishDate;
    

    @Schema(description = "变化情况")
    private String changeSituation;


    @Schema(description = "注销日期")
    private LocalDate cancelDate;
    

    @Schema(description = "注销原因")
    private String cancelReason;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
