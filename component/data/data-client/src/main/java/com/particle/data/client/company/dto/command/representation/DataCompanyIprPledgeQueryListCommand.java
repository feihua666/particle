package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权出质 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
@Data
@Schema
public class DataCompanyIprPledgeQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "知识产权登记证号")
    private String regNo;


    @Schema(description = "知识产权名称")
    private String name;


    @Schema(description = "知识产权种类")
    private Long typeName;


    @Schema(description = "出质人名称")
    private String pledgor;


    @Schema(description = "是否出质人为自然人")
    private Boolean isPledgorNaturalPerson;


    @Schema(description = "出质人公司id")
    private Long pledgorCompanyId;


    @Schema(description = "出质人个人id")
    private Long pledgorCompanyPersonId;


    @Schema(description = "质权人名称")
    private String pledgee;


    @Schema(description = "是否质权人为自然人")
    private Boolean isPledgeeNaturalPerson;


    @Schema(description = "质权人公司id")
    private Long pledgeeCompanyId;


    @Schema(description = "质权人个人id")
    private Long pledgeeCompanyPersonId;


    @Schema(description = "质权登记期限自")
    private LocalDate pledgeFromDate;
    

    @Schema(description = "质权登记期限至")
    private LocalDate pledgeToDate;
    

    @Schema(description = "状态")
    private String statusName;


    @Schema(description = "公示日期")
    private LocalDate publishDate;
    

    @Schema(description = "注销日期")
    private LocalDate cancelDate;
    

    @Schema(description = "注销原因")
    private String cancelReason;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
