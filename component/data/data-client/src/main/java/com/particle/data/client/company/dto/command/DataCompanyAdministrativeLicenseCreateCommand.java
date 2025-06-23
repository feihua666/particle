package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAdministrativeLicenseWarehouseCommand;

/**
 * <p>
 * 企业行政许可 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@Data
@Schema
public class DataCompanyAdministrativeLicenseCreateCommand extends AbstractBaseCommand {



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


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;










    public static DataCompanyAdministrativeLicenseCreateCommand createByWarehouseCommand(DataCompanyAdministrativeLicenseWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyAdministrativeLicenseCreateCommand command = new DataCompanyAdministrativeLicenseCreateCommand();
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
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();


        return command;
    }
}
