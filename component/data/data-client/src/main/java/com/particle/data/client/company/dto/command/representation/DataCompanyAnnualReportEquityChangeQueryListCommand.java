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
 * 企业年报股权变更 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:57
 */
@Data
@Schema
public class DataCompanyAnnualReportEquityChangeQueryListCommand extends AbstractBaseQueryCommand {



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


    @Schema(description = "是否股东为自然人")
    private Boolean isShareholderNaturalPerson;


    @Schema(description = "股东公司id")
    private Long shareholderCompanyId;


    @Schema(description = "股东个人id")
    private Long shareholderCompanyPersonId;


    @Schema(description = "变更前比例")
    private BigDecimal percentBefore;
    

    @Schema(description = "变更后比例")
    private BigDecimal percentAfter;
    

    @Schema(description = "变更日期")
    private LocalDate changeDate;

	@Schema(description = "数据md5,shareholder_name + percent_before + percent_after + change_date")
	private String dataMd5;
    

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
