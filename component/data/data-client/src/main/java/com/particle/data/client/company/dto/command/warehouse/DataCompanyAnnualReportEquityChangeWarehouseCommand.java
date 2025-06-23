package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportEquityChangeExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业年报股权变更入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyAnnualReportEquityChangeWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业id 不能为空")
    @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @NotNull(message = "企业年报id 不能为空")
    @Schema(description = "企业年报id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyAnnualReportId;


    @NotNull(message = "年报年度 不能为空")
    @Schema(description = "年报年度",requiredMode = Schema.RequiredMode.REQUIRED)
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

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyAnnualReportEquityDataMd5(shareholderName, percentBefore, percentAfter, changeDate);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && Objects.isNull(companyAnnualReportId)
                && Objects.isNull(year)
                && Objects.isNull(serialNumber)
                && StrUtil.isEmpty(shareholderName)
                && Objects.isNull(isShareholderNaturalPerson)
                && Objects.isNull(shareholderCompanyId)
                && Objects.isNull(shareholderCompanyPersonId)
                && Objects.isNull(percentBefore)
                && Objects.isNull(percentAfter)
                && Objects.isNull(changeDate);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyAnnualReportEquityChangeExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(companyAnnualReportId, exWarehouseVO.getCompanyAnnualReportId())) {
            this.companyAnnualReportId = null;
        }
        if (Objects.equals(year, exWarehouseVO.getYear())) {
            this.year = null;
        }
        if (Objects.equals(serialNumber, exWarehouseVO.getSerialNumber())) {
            this.serialNumber = null;
        }
        if (Objects.equals(shareholderName, exWarehouseVO.getShareholderName())) {
            this.shareholderName = null;
        }
        if (Objects.equals(isShareholderNaturalPerson, exWarehouseVO.getIsShareholderNaturalPerson())) {
            this.isShareholderNaturalPerson = null;
        }
        if (Objects.equals(shareholderCompanyId, exWarehouseVO.getShareholderCompanyId())) {
            this.shareholderCompanyId = null;
        }
        if (Objects.equals(shareholderCompanyPersonId, exWarehouseVO.getShareholderCompanyPersonId())) {
            this.shareholderCompanyPersonId = null;
        }
        if (NumberUtil.equals(percentBefore, exWarehouseVO.getPercentBefore())) {
            this.percentBefore = null;
        }
        if (NumberUtil.equals(percentAfter, exWarehouseVO.getPercentAfter())) {
            this.percentAfter = null;
        }
        if (Objects.equals(changeDate, exWarehouseVO.getChangeDate())) {
            this.changeDate = null;
        }
    }

    public static DataCompanyAnnualReportEquityChangeWarehouseCommand createByDataCompanyAnnualReportEquityChangeExWarehouseVO(DataCompanyAnnualReportEquityChangeExWarehouseVO exWarehouseVO) {
        DataCompanyAnnualReportEquityChangeWarehouseCommand command = new DataCompanyAnnualReportEquityChangeWarehouseCommand();
        command.companyId = exWarehouseVO.getCompanyId();
        command.companyAnnualReportId = exWarehouseVO.getCompanyAnnualReportId();
        command.year = exWarehouseVO.getYear();
        command.serialNumber = exWarehouseVO.getSerialNumber();
        command.shareholderName = exWarehouseVO.getShareholderName();
        command.isShareholderNaturalPerson = exWarehouseVO.getIsShareholderNaturalPerson();
        command.shareholderCompanyId = exWarehouseVO.getShareholderCompanyId();
        command.shareholderCompanyPersonId = exWarehouseVO.getShareholderCompanyPersonId();
        command.percentBefore = exWarehouseVO.getPercentBefore();
        command.percentAfter = exWarehouseVO.getPercentAfter();
        command.changeDate = exWarehouseVO.getChangeDate();

        return command;
    }
}
