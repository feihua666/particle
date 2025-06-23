package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportChangeExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业年报变更入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyAnnualReportChangeWarehouseCommand extends AbstractBaseCommand {


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


    @Schema(description = "变更事项")
    private Long changeItemDictId;


    @NotEmpty(message = "变更事项 不能为空")
    @Schema(description = "变更事项",requiredMode = Schema.RequiredMode.REQUIRED)
    private String changeItemName;


    @Schema(description = "变更前内容")
    private String contentBefore;


    @Schema(description = "变更后内容")
    private String contentAfter;


    @Schema(description = "变更日期")
    private LocalDate changeDate;

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyAnnualReportChangeDataMd5(changeItemName, contentBefore, contentAfter, changeDate);
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
                && Objects.isNull(changeItemDictId)
                && StrUtil.isEmpty(changeItemName)
                && StrUtil.isEmpty(contentBefore)
                && StrUtil.isEmpty(contentAfter)
                && Objects.isNull(changeDate);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyAnnualReportChangeExWarehouseVO exWarehouseVO) {
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
        if (Objects.equals(changeItemDictId, exWarehouseVO.getChangeItemDictId())) {
            this.changeItemDictId = null;
        }
        if (Objects.equals(changeItemName, exWarehouseVO.getChangeItemName())) {
            this.changeItemName = null;
        }
        if (Objects.equals(contentBefore, exWarehouseVO.getContentBefore())) {
            this.contentBefore = null;
        }
        if (Objects.equals(contentAfter, exWarehouseVO.getContentAfter())) {
            this.contentAfter = null;
        }
        if (Objects.equals(changeDate, exWarehouseVO.getChangeDate())) {
            this.changeDate = null;
        }
    }

    public static DataCompanyAnnualReportChangeWarehouseCommand createByDataCompanyAnnualReportChangeExWarehouseVO(DataCompanyAnnualReportChangeExWarehouseVO exWarehouseVO) {
        DataCompanyAnnualReportChangeWarehouseCommand command = new DataCompanyAnnualReportChangeWarehouseCommand();
        command.companyId = exWarehouseVO.getCompanyId();
        command.companyAnnualReportId = exWarehouseVO.getCompanyAnnualReportId();
        command.year = exWarehouseVO.getYear();
        command.serialNumber = exWarehouseVO.getSerialNumber();
        command.changeItemDictId = exWarehouseVO.getChangeItemDictId();
        command.changeItemName = exWarehouseVO.getChangeItemName();
        command.contentBefore = exWarehouseVO.getContentBefore();
        command.contentAfter = exWarehouseVO.getContentAfter();
        command.changeDate = exWarehouseVO.getChangeDate();


        return command;
    }
}
