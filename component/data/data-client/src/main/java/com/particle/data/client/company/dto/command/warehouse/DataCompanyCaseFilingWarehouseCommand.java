package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业立案信息入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyCaseFilingWarehouseCommand extends AbstractBaseCommand {


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

    public String obtainDataMd5() {
        return SomeMd5Tool.dataCompanyCaseFilingDataMd5(caseNo,caseReason,executeCourtName,caseTrialProcedure);
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(caseNo)
                && StrUtil.isEmpty(caseReason)
                && Objects.isNull(executeCourtCompanyId)
                && StrUtil.isEmpty(executeCourtName)
                && Objects.isNull(fileCaseDate)
                && Objects.isNull(openCourtDate)
                && Objects.isNull(finishedCourtDate)
                && Objects.isNull(isFinished)
                && StrUtil.isEmpty(undertakeDept)
                && StrUtil.isEmpty(undertakeJudger)
                && StrUtil.isEmpty(assistant)
                && StrUtil.isEmpty(caseTrialProcedure);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyCaseFilingExWarehouseVO exWarehouseVO) {
        if (Objects.equals(caseNo, exWarehouseVO.getCaseNo())) {
            this.caseNo = null;
        }
        if (Objects.equals(caseReason, exWarehouseVO.getCaseReason())) {
            this.caseReason = null;
        }
        if (Objects.equals(executeCourtCompanyId, exWarehouseVO.getExecuteCourtCompanyId())) {
            this.executeCourtCompanyId = null;
        }
        if (Objects.equals(executeCourtName, exWarehouseVO.getExecuteCourtName())) {
            this.executeCourtName = null;
        }
        if (Objects.equals(fileCaseDate, exWarehouseVO.getFileCaseDate())) {
            this.fileCaseDate = null;
        }
        if (Objects.equals(openCourtDate, exWarehouseVO.getOpenCourtDate())) {
            this.openCourtDate = null;
        }
        if (Objects.equals(finishedCourtDate, exWarehouseVO.getFinishedCourtDate())) {
            this.finishedCourtDate = null;
        }
        if (Objects.equals(isFinished, exWarehouseVO.getIsFinished())) {
            this.isFinished = null;
        }
        if (Objects.equals(undertakeDept, exWarehouseVO.getUndertakeDept())) {
            this.undertakeDept = null;
        }
        if (Objects.equals(undertakeJudger, exWarehouseVO.getUndertakeJudger())) {
            this.undertakeJudger = null;
        }
        if (Objects.equals(assistant, exWarehouseVO.getAssistant())) {
            this.assistant = null;
        }
        if (Objects.equals(caseTrialProcedure, exWarehouseVO.getCaseTrialProcedure())) {
            this.caseTrialProcedure = null;
        }

    }

    public static DataCompanyCaseFilingWarehouseCommand createByDataCompanyCaseFilingExWarehouseVO(DataCompanyCaseFilingExWarehouseVO exWarehouseVO) {
        DataCompanyCaseFilingWarehouseCommand command = new DataCompanyCaseFilingWarehouseCommand();
        command.caseNo = exWarehouseVO.getCaseNo();
        command.caseReason = exWarehouseVO.getCaseReason();
        command.executeCourtCompanyId = exWarehouseVO.getExecuteCourtCompanyId();
        command.executeCourtName = exWarehouseVO.getExecuteCourtName();
        command.fileCaseDate = exWarehouseVO.getFileCaseDate();
        command.openCourtDate = exWarehouseVO.getOpenCourtDate();
        command.finishedCourtDate = exWarehouseVO.getFinishedCourtDate();
        command.isFinished = exWarehouseVO.getIsFinished();
        command.undertakeDept = exWarehouseVO.getUndertakeDept();
        command.undertakeJudger = exWarehouseVO.getUndertakeJudger();
        command.assistant = exWarehouseVO.getAssistant();
        command.caseTrialProcedure = exWarehouseVO.getCaseTrialProcedure();


        return command;
    }
}
