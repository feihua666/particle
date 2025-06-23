package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Objects;
import cn.hutool.core.util.NumberUtil;

/**
 * <p>
 * 企业行政许可入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyAdministrativeLicenseWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "许可编号 ")
    private String licenceNo;


    @Schema(description = "许可证书名称")
    private String licenceName;


    @Schema(description = "行政许可决定文书号")
    private String decisionDocumentNo;


    @Schema(description = "许可证决定文书名称")
    private String decisionDocumentName;


    @Schema(description = "许可决定日期")
    private LocalDate decisionDate;


    @Schema(description = "许可开始日期")
    private LocalDate fromDate;


    @Schema(description = "许可截止日期")
    private LocalDate endDate;


    @Schema(description = "许可内容")
    private String licenceContent;


    @Schema(description = "许可机关")
    private String institute;


    @Schema(description = "许可机关统一社会信用代码 ")
    private String instituteUscc;


    @Schema(description = "许可机关公司id")
    private Long instituteCompanyId;


    @Schema(description = "数据来源单位 ")
    private String dataFrom;


    @Schema(description = "数据来源单位统一社会信用代码")
    private String dataFromUscc;


    @Schema(description = "数据来源单位公司id")
    private Long dataFromCompanyId;


    @Schema(description = "许可类型")
    private String licenceType;


    @Schema(description = "许可备注")
    private String licenceRemark;


    @Schema(description = "是否数据标识为工商公示")
    private Boolean isDataFlagGs;


    @Schema(description = "是否数据标识为信用中国")
    private Boolean isDataFlagXyzg;


    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyAdministrativeLicenseDataMd5(decisionDocumentNo, decisionDate, licenceContent, licenceType, isDataFlagGs, isDataFlagXyzg);
        }
        return dataMd5;
    }
    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && StrUtil.isEmpty(licenceNo)
                && StrUtil.isEmpty(licenceName)
                && StrUtil.isEmpty(decisionDocumentNo)
                && StrUtil.isEmpty(decisionDocumentName)
                && Objects.isNull(decisionDate)
                && Objects.isNull(fromDate)
                && Objects.isNull(endDate)
                && StrUtil.isEmpty(licenceContent)
                && StrUtil.isEmpty(institute)
                && StrUtil.isEmpty(instituteUscc)
                && Objects.isNull(instituteCompanyId)
                && StrUtil.isEmpty(dataFrom)
                && StrUtil.isEmpty(dataFromUscc)
                && Objects.isNull(dataFromCompanyId)
                && StrUtil.isEmpty(licenceType)
                && StrUtil.isEmpty(licenceRemark)
                && Objects.isNull(isDataFlagGs)
                && Objects.isNull(isDataFlagXyzg)
                && StrUtil.isEmpty(dataMd5)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyAdministrativeLicenseExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(licenceNo, exWarehouseVO.getLicenceNo())) {
            this.licenceNo = null;
        }
        if (Objects.equals(licenceName, exWarehouseVO.getLicenceName())) {
            this.licenceName = null;
        }
        if (Objects.equals(decisionDocumentNo, exWarehouseVO.getDecisionDocumentNo())) {
            this.decisionDocumentNo = null;
        }
        if (Objects.equals(decisionDocumentName, exWarehouseVO.getDecisionDocumentName())) {
            this.decisionDocumentName = null;
        }
        if (Objects.equals(decisionDate, exWarehouseVO.getDecisionDate())) {
            this.decisionDate = null;
        }
        if (Objects.equals(fromDate, exWarehouseVO.getFromDate())) {
            this.fromDate = null;
        }
        if (Objects.equals(endDate, exWarehouseVO.getEndDate())) {
            this.endDate = null;
        }
        if (Objects.equals(licenceContent, exWarehouseVO.getLicenceContent())) {
            this.licenceContent = null;
        }
        if (Objects.equals(institute, exWarehouseVO.getInstitute())) {
            this.institute = null;
        }
        if (Objects.equals(instituteUscc, exWarehouseVO.getInstituteUscc())) {
            this.instituteUscc = null;
        }
        if (Objects.equals(instituteCompanyId, exWarehouseVO.getInstituteCompanyId())) {
            this.instituteCompanyId = null;
        }
        if (Objects.equals(dataFrom, exWarehouseVO.getDataFrom())) {
            this.dataFrom = null;
        }
        if (Objects.equals(dataFromUscc, exWarehouseVO.getDataFromUscc())) {
            this.dataFromUscc = null;
        }
        if (Objects.equals(dataFromCompanyId, exWarehouseVO.getDataFromCompanyId())) {
            this.dataFromCompanyId = null;
        }
        if (Objects.equals(licenceType, exWarehouseVO.getLicenceType())) {
            this.licenceType = null;
        }
        if (Objects.equals(licenceRemark, exWarehouseVO.getLicenceRemark())) {
            this.licenceRemark = null;
        }
        if (Objects.equals(isDataFlagGs, exWarehouseVO.getIsDataFlagGs())) {
            this.isDataFlagGs = null;
        }
        if (Objects.equals(isDataFlagXyzg, exWarehouseVO.getIsDataFlagXyzg())) {
            this.isDataFlagXyzg = null;
        }

    }

    public static DataCompanyAdministrativeLicenseWarehouseCommand createByDataCompanyAdministrativeLicenseExWarehouseVO(DataCompanyAdministrativeLicenseExWarehouseVO dataCompanyBasicWarehouseCommand){
        DataCompanyAdministrativeLicenseWarehouseCommand command = new DataCompanyAdministrativeLicenseWarehouseCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.licenceNo = dataCompanyBasicWarehouseCommand.getLicenceNo();
        command.licenceName = dataCompanyBasicWarehouseCommand.getLicenceName();
        command.decisionDocumentNo = dataCompanyBasicWarehouseCommand.getDecisionDocumentNo();
        command.decisionDocumentName = dataCompanyBasicWarehouseCommand.getDecisionDocumentName();
        command.decisionDate = dataCompanyBasicWarehouseCommand.getDecisionDate();
        command.fromDate = dataCompanyBasicWarehouseCommand.getFromDate();
        command.endDate = dataCompanyBasicWarehouseCommand.getEndDate();
        command.licenceContent = dataCompanyBasicWarehouseCommand.getLicenceContent();
        command.institute = dataCompanyBasicWarehouseCommand.getInstitute();
        command.instituteUscc = dataCompanyBasicWarehouseCommand.getInstituteUscc();
        command.instituteCompanyId = dataCompanyBasicWarehouseCommand.getInstituteCompanyId();
        command.dataFrom = dataCompanyBasicWarehouseCommand.getDataFrom();
        command.dataFromUscc = dataCompanyBasicWarehouseCommand.getDataFromUscc();
        command.dataFromCompanyId = dataCompanyBasicWarehouseCommand.getDataFromCompanyId();
        command.licenceType = dataCompanyBasicWarehouseCommand.getLicenceType();
        command.licenceRemark = dataCompanyBasicWarehouseCommand.getLicenceRemark();
        command.isDataFlagGs = dataCompanyBasicWarehouseCommand.getIsDataFlagGs();
        command.isDataFlagXyzg = dataCompanyBasicWarehouseCommand.getIsDataFlagXyzg();


        return command;
    }
}
