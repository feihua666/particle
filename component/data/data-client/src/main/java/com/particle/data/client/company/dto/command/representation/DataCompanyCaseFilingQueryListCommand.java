package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业立案信息 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Data
@Schema
public class DataCompanyCaseFilingQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "案号")
    private String caseNo;


    @Schema(description = "案由")
    private String caseReason;


    @Schema(description = "执行法院公司id")
    private Long executeCourtCompanyId;


    @Schema(description = "执行法院名称")
    private String executeCourtName;


    @Schema(description = "立案日期")
    private LocalDate fileCaseDate;
    

    @Schema(description = "开庭日期")
    private LocalDate openCourtDate;
    

    @Schema(description = "结束日期")
    private LocalDate finishedCourtDate;
    

    @Schema(description = "是否已结案")
    private Boolean isFinished;


    @Schema(description = "承办部门")
    private String undertakeDept;


    @Schema(description = "承办法官")
    private String undertakeJudger;


    @Schema(description = "法官助理")
    private String assistant;


    @Schema(description = "案件审理程序")
    private String caseTrialProcedure;

	@Schema(description = "数据md5,case_no + case_reason + execute_court_name + case_trial_procedure")
	private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}