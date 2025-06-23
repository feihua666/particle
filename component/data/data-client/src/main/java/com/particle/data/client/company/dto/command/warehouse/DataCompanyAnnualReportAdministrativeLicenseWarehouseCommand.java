package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业年报行政许可入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyAnnualReportAdministrativeLicenseWarehouseCommand extends AbstractBaseCommand {



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

    @NotEmpty(message = "许可文件名称 不能为空")
    @Schema(description = "许可文件名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String fileName;


    @Schema(description = "许可文件到期日期")
    private LocalDate validToDate;

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyAnnualReportAdministrativeLicensetDataMd5(fileName);
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
                && StrUtil.isEmpty(fileName)
                && Objects.isNull(validToDate);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO exWarehouseVO) {
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
        if (Objects.equals(fileName, exWarehouseVO.getFileName())) {
            this.fileName = null;
        }
        if (Objects.equals(validToDate, exWarehouseVO.getValidToDate())) {
            this.validToDate = null;
        }

    }

    public static DataCompanyAnnualReportAdministrativeLicenseWarehouseCommand createByDataCompanyAnnualReportAdministrativeLicenseExWarehouseVO(DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO exWarehouseVO) {
        DataCompanyAnnualReportAdministrativeLicenseWarehouseCommand command = new DataCompanyAnnualReportAdministrativeLicenseWarehouseCommand();
        command.companyId = exWarehouseVO.getCompanyId();
        command.companyAnnualReportId = exWarehouseVO.getCompanyAnnualReportId();
        command.year = exWarehouseVO.getYear();
        command.serialNumber = exWarehouseVO.getSerialNumber();
        command.fileName = exWarehouseVO.getFileName();
        command.validToDate = exWarehouseVO.getValidToDate();


        return command;
    }
}
