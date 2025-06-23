package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权植物新品种入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprPlantVarietyWarehouseCommand extends AbstractBaseCommand {



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


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(applyNo)
                && Objects.isNull(applyDate)
                && StrUtil.isEmpty(publicNo)
                && Objects.isNull(publicDate)
                && StrUtil.isEmpty(name)
                && StrUtil.isEmpty(applyPublicNo)
                && Objects.isNull(applyPublicDate)
                && StrUtil.isEmpty(publicTypeName)
                && StrUtil.isEmpty(plantKindName)
                && StrUtil.isEmpty(varietyRightType)
                && StrUtil.isEmpty(applicantName)
                && Objects.isNull(isApplicantNameNaturalPerson)
                && Objects.isNull(applicantNameCompanyId)
                && Objects.isNull(applicantNameCompanyPersonId)
                && StrUtil.isEmpty(applicantAddress)
                && StrUtil.isEmpty(cultivateName)
                && StrUtil.isEmpty(coVarietyRightName)
                && StrUtil.isEmpty(agency)
                && StrUtil.isEmpty(agencyAddress)
                && StrUtil.isEmpty(agent)
                && StrUtil.isEmpty(priorityNo)
                && StrUtil.isEmpty(varietySourceFrom)
                && StrUtil.isEmpty(batchNo)
                && StrUtil.isEmpty(description)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprPlantVarietyExWarehouseVO exWarehouseVO) {
                if (Objects.equals(applyNo, exWarehouseVO.getApplyNo())) {
            this.applyNo = null;
        }
        if (Objects.equals(applyDate, exWarehouseVO.getApplyDate())) {
            this.applyDate = null;
        }
        if (Objects.equals(publicNo, exWarehouseVO.getPublicNo())) {
            this.publicNo = null;
        }
        if (Objects.equals(publicDate, exWarehouseVO.getPublicDate())) {
            this.publicDate = null;
        }
        if (Objects.equals(name, exWarehouseVO.getName())) {
            this.name = null;
        }
        if (Objects.equals(applyPublicNo, exWarehouseVO.getApplyPublicNo())) {
            this.applyPublicNo = null;
        }
        if (Objects.equals(applyPublicDate, exWarehouseVO.getApplyPublicDate())) {
            this.applyPublicDate = null;
        }
        if (Objects.equals(publicTypeName, exWarehouseVO.getPublicTypeName())) {
            this.publicTypeName = null;
        }
        if (Objects.equals(plantKindName, exWarehouseVO.getPlantKindName())) {
            this.plantKindName = null;
        }
        if (Objects.equals(varietyRightType, exWarehouseVO.getVarietyRightType())) {
            this.varietyRightType = null;
        }
        if (Objects.equals(applicantName, exWarehouseVO.getApplicantName())) {
            this.applicantName = null;
        }
        if (Objects.equals(isApplicantNameNaturalPerson, exWarehouseVO.getIsApplicantNameNaturalPerson())) {
            this.isApplicantNameNaturalPerson = null;
        }
        if (Objects.equals(applicantNameCompanyId, exWarehouseVO.getApplicantNameCompanyId())) {
            this.applicantNameCompanyId = null;
        }
        if (Objects.equals(applicantNameCompanyPersonId, exWarehouseVO.getApplicantNameCompanyPersonId())) {
            this.applicantNameCompanyPersonId = null;
        }
        if (Objects.equals(applicantAddress, exWarehouseVO.getApplicantAddress())) {
            this.applicantAddress = null;
        }
        if (Objects.equals(cultivateName, exWarehouseVO.getCultivateName())) {
            this.cultivateName = null;
        }
        if (Objects.equals(coVarietyRightName, exWarehouseVO.getCoVarietyRightName())) {
            this.coVarietyRightName = null;
        }
        if (Objects.equals(agency, exWarehouseVO.getAgency())) {
            this.agency = null;
        }
        if (Objects.equals(agencyAddress, exWarehouseVO.getAgencyAddress())) {
            this.agencyAddress = null;
        }
        if (Objects.equals(agent, exWarehouseVO.getAgent())) {
            this.agent = null;
        }
        if (Objects.equals(priorityNo, exWarehouseVO.getPriorityNo())) {
            this.priorityNo = null;
        }
        if (Objects.equals(varietySourceFrom, exWarehouseVO.getVarietySourceFrom())) {
            this.varietySourceFrom = null;
        }
        if (Objects.equals(batchNo, exWarehouseVO.getBatchNo())) {
            this.batchNo = null;
        }
        if (Objects.equals(description, exWarehouseVO.getDescription())) {
            this.description = null;
        }
    }

    public static DataCompanyIprPlantVarietyWarehouseCommand createByDataCompanyIprPlantVarietyExWarehouseVO(DataCompanyIprPlantVarietyExWarehouseVO exWarehouseVO) {
        DataCompanyIprPlantVarietyWarehouseCommand command = new DataCompanyIprPlantVarietyWarehouseCommand();
        command.applyNo = exWarehouseVO.getApplyNo();
        command.applyDate = exWarehouseVO.getApplyDate();
        command.publicNo = exWarehouseVO.getPublicNo();
        command.publicDate = exWarehouseVO.getPublicDate();
        command.name = exWarehouseVO.getName();
        command.applyPublicNo = exWarehouseVO.getApplyPublicNo();
        command.applyPublicDate = exWarehouseVO.getApplyPublicDate();
        command.publicTypeName = exWarehouseVO.getPublicTypeName();
        command.plantKindName = exWarehouseVO.getPlantKindName();
        command.varietyRightType = exWarehouseVO.getVarietyRightType();
        command.applicantName = exWarehouseVO.getApplicantName();
        command.isApplicantNameNaturalPerson = exWarehouseVO.getIsApplicantNameNaturalPerson();
        command.applicantNameCompanyId = exWarehouseVO.getApplicantNameCompanyId();
        command.applicantNameCompanyPersonId = exWarehouseVO.getApplicantNameCompanyPersonId();
        command.applicantAddress = exWarehouseVO.getApplicantAddress();
        command.cultivateName = exWarehouseVO.getCultivateName();
        command.coVarietyRightName = exWarehouseVO.getCoVarietyRightName();
        command.agency = exWarehouseVO.getAgency();
        command.agencyAddress = exWarehouseVO.getAgencyAddress();
        command.agent = exWarehouseVO.getAgent();
        command.priorityNo = exWarehouseVO.getPriorityNo();
        command.varietySourceFrom = exWarehouseVO.getVarietySourceFrom();
        command.batchNo = exWarehouseVO.getBatchNo();
        command.description = exWarehouseVO.getDescription();

        return command;
    }

}
