package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权植物新品种变更信息入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprPlantVarietyChangeWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权植物新品种id 不能为空")
        @Schema(description = "企业知识产权植物新品种id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprPlantVarietyId;


    @Schema(description = "变更日期")
    private LocalDate changeDate;


    @Schema(description = "变更前")
    private String changeBefore;


    @Schema(description = "变更后")
    private String changeAfter;


    @Schema(description = "原因")
    private String changeReason;


    @Schema(description = "是否名称变更")
    private Boolean isName;


    @Schema(description = "是否转让变更")
    private Boolean isTransfer;


    @Schema(description = "是否培育人变更")
    private Boolean isCultivate;


    @Schema(description = "是否申请人变更")
    private Boolean isApplicant;


    @Schema(description = "是否代理人变更")
    private Boolean isAgent;


    @Schema(description = "是否代理机构变更")
    private Boolean isAgency;


    @Schema(description = "数据md5")
    private String dataMd5;
    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyIprPlantVarietyChangeDataMd5(changeDate,changeBefore,changeAfter,changeReason,
                    isName, isTransfer, isCultivate, isApplicant, isAgent, isAgency);
        }
        return dataMd5;
    }
    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprPlantVarietyId)
                && Objects.isNull(changeDate)
                && StrUtil.isEmpty(changeBefore)
                && StrUtil.isEmpty(changeAfter)
                && StrUtil.isEmpty(changeReason)
                && Objects.isNull(isName)
                && Objects.isNull(isTransfer)
                && Objects.isNull(isCultivate)
                && Objects.isNull(isApplicant)
                && Objects.isNull(isAgent)
                && Objects.isNull(isAgency)
                && StrUtil.isEmpty(dataMd5)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprPlantVarietyChangeExWarehouseVO exWarehouseVO) {
                if (Objects.equals(companyIprPlantVarietyId, exWarehouseVO.getCompanyIprPlantVarietyId())) {
            this.companyIprPlantVarietyId = null;
        }
        if (Objects.equals(changeDate, exWarehouseVO.getChangeDate())) {
            this.changeDate = null;
        }
        if (Objects.equals(changeBefore, exWarehouseVO.getChangeBefore())) {
            this.changeBefore = null;
        }
        if (Objects.equals(changeAfter, exWarehouseVO.getChangeAfter())) {
            this.changeAfter = null;
        }
        if (Objects.equals(changeReason, exWarehouseVO.getChangeReason())) {
            this.changeReason = null;
        }
        if (Objects.equals(isName, exWarehouseVO.getIsName())) {
            this.isName = null;
        }
        if (Objects.equals(isTransfer, exWarehouseVO.getIsTransfer())) {
            this.isTransfer = null;
        }
        if (Objects.equals(isCultivate, exWarehouseVO.getIsCultivate())) {
            this.isCultivate = null;
        }
        if (Objects.equals(isApplicant, exWarehouseVO.getIsApplicant())) {
            this.isApplicant = null;
        }
        if (Objects.equals(isAgent, exWarehouseVO.getIsAgent())) {
            this.isAgent = null;
        }
        if (Objects.equals(isAgency, exWarehouseVO.getIsAgency())) {
            this.isAgency = null;
        }
        if (Objects.equals(dataMd5, exWarehouseVO.getDataMd5())) {
            this.dataMd5 = null;
        }

    }

    public static DataCompanyIprPlantVarietyChangeWarehouseCommand createByDataCompanyIprPlantVarietyChangeExWarehouseVO(DataCompanyIprPlantVarietyChangeExWarehouseVO exWarehouseVO) {
        DataCompanyIprPlantVarietyChangeWarehouseCommand command = new DataCompanyIprPlantVarietyChangeWarehouseCommand();
        command.companyIprPlantVarietyId = exWarehouseVO.getCompanyIprPlantVarietyId();
        command.changeDate = exWarehouseVO.getChangeDate();
        command.changeBefore = exWarehouseVO.getChangeBefore();
        command.changeAfter = exWarehouseVO.getChangeAfter();
        command.changeReason = exWarehouseVO.getChangeReason();
        command.isName = exWarehouseVO.getIsName();
        command.isTransfer = exWarehouseVO.getIsTransfer();
        command.isCultivate = exWarehouseVO.getIsCultivate();
        command.isApplicant = exWarehouseVO.getIsApplicant();
        command.isAgent = exWarehouseVO.getIsAgent();
        command.isAgency = exWarehouseVO.getIsAgency();

        return command;
    }

}
