package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPlantVarietyWarehouseCommand;

/**
 * <p>
 * 企业知识产权植物新品种 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:40
 */
@Data
@Schema
public class DataCompanyIprPlantVarietyUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "申请号")
    private String applyNo;


    @Schema(description = "申请日期")
    private LocalDate applyDate;


    @Schema(description = "公告号")
    private String publicNo;


    @Schema(description = "公告日期")
    private LocalDate publicDate;


    @Schema(description = "品种名称")
    private String name;


    @Schema(description = "申请公告号")
    private String applyPublicNo;


    @Schema(description = "申请公告日期")
    private LocalDate applyPublicDate;


    @Schema(description = "公告类型")
    private String publicTypeName;


    @Schema(description = "植物种类")
    private String plantKindName;


    @Schema(description = "品种权事务分类")
    private String varietyRightType;


    @Schema(description = "申请人名称")
    private String applicantName;


    @Schema(description = "是否申请人为自然人")
    private Boolean isApplicantNameNaturalPerson;


    @Schema(description = "申请人公司id")
    private Long applicantNameCompanyId;


    @Schema(description = "申请人个人id")
    private Long applicantNameCompanyPersonId;


    @Schema(description = "申请人地址")
    private String applicantAddress;


    @Schema(description = "培育人")
    private String cultivateName;


    @Schema(description = "共同品种权人")
    private String coVarietyRightName;


    @Schema(description = "代理机构")
    private String agency;


    @Schema(description = "代理机构地址")
    private String agencyAddress;


    @Schema(description = "代理人")
    private String agent;


    @Schema(description = "优先权号")
    private String priorityNo;


    @Schema(description = "品种来源")
    private String varietySourceFrom;


    @Schema(description = "批次号")
    private String batchNo;


    @Schema(description = "说明")
    private String description;



    public static DataCompanyIprPlantVarietyUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprPlantVarietyWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprPlantVarietyUpdateCommand command = new DataCompanyIprPlantVarietyUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.applyNo = dataCompanyBasicWarehouseCommand.getApplyNo();
        command.applyDate = dataCompanyBasicWarehouseCommand.getApplyDate();
        command.publicNo = dataCompanyBasicWarehouseCommand.getPublicNo();
        command.publicDate = dataCompanyBasicWarehouseCommand.getPublicDate();
        command.name = dataCompanyBasicWarehouseCommand.getName();
        command.applyPublicNo = dataCompanyBasicWarehouseCommand.getApplyPublicNo();
        command.applyPublicDate = dataCompanyBasicWarehouseCommand.getApplyPublicDate();
        command.publicTypeName = dataCompanyBasicWarehouseCommand.getPublicTypeName();
        command.plantKindName = dataCompanyBasicWarehouseCommand.getPlantKindName();
        command.varietyRightType = dataCompanyBasicWarehouseCommand.getVarietyRightType();
        command.applicantName = dataCompanyBasicWarehouseCommand.getApplicantName();
        command.isApplicantNameNaturalPerson = dataCompanyBasicWarehouseCommand.getIsApplicantNameNaturalPerson();
        command.applicantNameCompanyId = dataCompanyBasicWarehouseCommand.getApplicantNameCompanyId();
        command.applicantNameCompanyPersonId = dataCompanyBasicWarehouseCommand.getApplicantNameCompanyPersonId();
        command.applicantAddress = dataCompanyBasicWarehouseCommand.getApplicantAddress();
        command.cultivateName = dataCompanyBasicWarehouseCommand.getCultivateName();
        command.coVarietyRightName = dataCompanyBasicWarehouseCommand.getCoVarietyRightName();
        command.agency = dataCompanyBasicWarehouseCommand.getAgency();
        command.agencyAddress = dataCompanyBasicWarehouseCommand.getAgencyAddress();
        command.agent = dataCompanyBasicWarehouseCommand.getAgent();
        command.priorityNo = dataCompanyBasicWarehouseCommand.getPriorityNo();
        command.varietySourceFrom = dataCompanyBasicWarehouseCommand.getVarietySourceFrom();
        command.batchNo = dataCompanyBasicWarehouseCommand.getBatchNo();
        command.description = dataCompanyBasicWarehouseCommand.getDescription();


        return command;
    }
}
