package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignInvestExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 * 企业年报对外投资入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyAnnualReportForeignInvestWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @NotNull(message = "企业年报表ID 不能为空")
    @Schema(description = "企业年报表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyAnnualReportId;


    @NotNull(message = "年报年度 不能为空")
    @Schema(description = "年报年度",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer year;


    @Schema(description = "序号")
    private Integer serialNumber;


    @Schema(description = "对外投资企业id")
    private Long investCompanyId;

	@Schema(description = "对外投资企业名称")
	private String investCompanyName;

	@Schema(description = "对外投资企业统一社会信用代码")
	private String investCompanyUscc;


    @Schema(description = "对外投资比例")
    private BigDecimal investPercent;


    @Schema(description = "对外投资金额（万元）")
    private BigDecimal investAmount;


    @Schema(description = "对外投资金额币种")
    private Long investAmountCurrencyDictId;

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyAnnualReportForeignInvestDataMd5(investCompanyName, investCompanyUscc);
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
                && Objects.isNull(investCompanyId)
                && StrUtil.isEmpty(investCompanyName)
                && StrUtil.isEmpty(investCompanyUscc)
                && Objects.isNull(investPercent)
                && Objects.isNull(investAmount)
                && Objects.isNull(investAmountCurrencyDictId);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyAnnualReportForeignInvestExWarehouseVO exWarehouseVO) {
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
        if (Objects.equals(investCompanyId, exWarehouseVO.getInvestCompanyId())) {
            this.investCompanyId = null;
        }
        if (Objects.equals(investCompanyName, exWarehouseVO.getInvestCompanyName())) {
            this.investCompanyName = null;
        }
        if (Objects.equals(investCompanyUscc, exWarehouseVO.getInvestCompanyUscc())) {
            this.investCompanyUscc = null;
        }
        if (NumberUtil.equals(investPercent, exWarehouseVO.getInvestPercent())) {
            this.investPercent = null;
        }
        if (NumberUtil.equals(investAmount, exWarehouseVO.getInvestAmount())) {
            this.investAmount = null;
        }
        if (Objects.equals(investAmountCurrencyDictId, exWarehouseVO.getInvestAmountCurrencyDictId())) {
            this.investAmountCurrencyDictId = null;
        }

    }

    public static DataCompanyAnnualReportForeignInvestWarehouseCommand createByDataCompanyAnnualReportForeignInvestExWarehouseVO(DataCompanyAnnualReportForeignInvestExWarehouseVO exWarehouseVO) {
        DataCompanyAnnualReportForeignInvestWarehouseCommand command = new DataCompanyAnnualReportForeignInvestWarehouseCommand();
        command.companyId = exWarehouseVO.getCompanyId();
        command.companyAnnualReportId = exWarehouseVO.getCompanyAnnualReportId();
        command.year = exWarehouseVO.getYear();
        command.serialNumber = exWarehouseVO.getSerialNumber();
        command.investCompanyId = exWarehouseVO.getInvestCompanyId();
        command.investCompanyName = exWarehouseVO.getInvestCompanyName();
        command.investCompanyUscc = exWarehouseVO.getInvestCompanyUscc();
        command.investPercent = exWarehouseVO.getInvestPercent();
        command.investAmount = exWarehouseVO.getInvestAmount();
        command.investAmountCurrencyDictId = exWarehouseVO.getInvestAmountCurrencyDictId();

        return command;

    }
}
