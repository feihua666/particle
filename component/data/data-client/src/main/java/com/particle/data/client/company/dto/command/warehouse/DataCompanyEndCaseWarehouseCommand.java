package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEndCaseExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Objects;
import cn.hutool.core.util.NumberUtil;

/**
 * <p>
 * 企业终本案件入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyEndCaseWarehouseCommand extends AbstractBaseCommand {



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


    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyEndCaseDataMd5(caseNo, executedPersonName, fileCaseDate);
        }
        return dataMd5;
    }
    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(caseNo)
                && StrUtil.isEmpty(executedPersonName)
                && Objects.isNull(isExecutedPersonNaturalPerson)
                && Objects.isNull(executedPersonCompanyId)
                && Objects.isNull(executedPersonCompanyPersonId)
                && StrUtil.isEmpty(executedPersonLicenseNo)
                && StrUtil.isEmpty(courtName)
                && Objects.isNull(courtCompanyId)
                && Objects.isNull(fileCaseDate)
                && Objects.isNull(finishedCaseDate)
                && Objects.isNull(executeAmount)
                && Objects.isNull(executeAmountCurrencyDictId)
                && Objects.isNull(unPerformAmount)
                && Objects.isNull(unPerformAmountCurrencyDictId)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyEndCaseExWarehouseVO exWarehouseVO) {
                if (Objects.equals(caseNo, exWarehouseVO.getCaseNo())) {
            this.caseNo = null;
        }
        if (Objects.equals(executedPersonName, exWarehouseVO.getExecutedPersonName())) {
            this.executedPersonName = null;
        }
        if (Objects.equals(isExecutedPersonNaturalPerson, exWarehouseVO.getIsExecutedPersonNaturalPerson())) {
            this.isExecutedPersonNaturalPerson = null;
        }
        if (Objects.equals(executedPersonCompanyId, exWarehouseVO.getExecutedPersonCompanyId())) {
            this.executedPersonCompanyId = null;
        }
        if (Objects.equals(executedPersonCompanyPersonId, exWarehouseVO.getExecutedPersonCompanyPersonId())) {
            this.executedPersonCompanyPersonId = null;
        }
        if (Objects.equals(executedPersonLicenseNo, exWarehouseVO.getExecutedPersonLicenseNo())) {
            this.executedPersonLicenseNo = null;
        }
        if (Objects.equals(courtName, exWarehouseVO.getCourtName())) {
            this.courtName = null;
        }
        if (Objects.equals(courtCompanyId, exWarehouseVO.getCourtCompanyId())) {
            this.courtCompanyId = null;
        }
        if (Objects.equals(fileCaseDate, exWarehouseVO.getFileCaseDate())) {
            this.fileCaseDate = null;
        }
        if (Objects.equals(finishedCaseDate, exWarehouseVO.getFinishedCaseDate())) {
            this.finishedCaseDate = null;
        }
        if (NumberUtil.equals(executeAmount, exWarehouseVO.getExecuteAmount())) {
            this.executeAmount = null;
        }
        if (Objects.equals(executeAmountCurrencyDictId, exWarehouseVO.getExecuteAmountCurrencyDictId())) {
            this.executeAmountCurrencyDictId = null;
        }
        if (NumberUtil.equals(unPerformAmount, exWarehouseVO.getUnPerformAmount())) {
            this.unPerformAmount = null;
        }
        if (Objects.equals(unPerformAmountCurrencyDictId, exWarehouseVO.getUnPerformAmountCurrencyDictId())) {
            this.unPerformAmountCurrencyDictId = null;
        }


    }

    public static DataCompanyEndCaseWarehouseCommand createByDataCompanyEndCaseExWarehouseVO(DataCompanyEndCaseExWarehouseVO dataCompanyBasicWarehouseCommand){
        DataCompanyEndCaseWarehouseCommand command = new DataCompanyEndCaseWarehouseCommand();
        command.caseNo = dataCompanyBasicWarehouseCommand.getCaseNo();
        command.executedPersonName = dataCompanyBasicWarehouseCommand.getExecutedPersonName();
        command.isExecutedPersonNaturalPerson = dataCompanyBasicWarehouseCommand.getIsExecutedPersonNaturalPerson();
        command.executedPersonCompanyId = dataCompanyBasicWarehouseCommand.getExecutedPersonCompanyId();
        command.executedPersonCompanyPersonId = dataCompanyBasicWarehouseCommand.getExecutedPersonCompanyPersonId();
        command.executedPersonLicenseNo = dataCompanyBasicWarehouseCommand.getExecutedPersonLicenseNo();
        command.courtName = dataCompanyBasicWarehouseCommand.getCourtName();
        command.courtCompanyId = dataCompanyBasicWarehouseCommand.getCourtCompanyId();
        command.fileCaseDate = dataCompanyBasicWarehouseCommand.getFileCaseDate();
        command.finishedCaseDate = dataCompanyBasicWarehouseCommand.getFinishedCaseDate();
        command.executeAmount = dataCompanyBasicWarehouseCommand.getExecuteAmount();
        command.executeAmountCurrencyDictId = dataCompanyBasicWarehouseCommand.getExecuteAmountCurrencyDictId();
        command.unPerformAmount = dataCompanyBasicWarehouseCommand.getUnPerformAmount();
        command.unPerformAmountCurrencyDictId = dataCompanyBasicWarehouseCommand.getUnPerformAmountCurrencyDictId();


        return command;
    }
}
