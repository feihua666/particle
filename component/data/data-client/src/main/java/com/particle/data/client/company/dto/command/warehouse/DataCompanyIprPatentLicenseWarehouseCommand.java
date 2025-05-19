package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLicenseExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权专利许可信息入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprPatentLicenseWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权专利表id 不能为空")
    @Schema(description = "企业知识产权专利表id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprPatentId;


    @Schema(description = "专利权许可类型")
    private String licenseType;


    @Schema(description = "专利备案合同号编码")
    private String filingContractNo;


    @Schema(description = "合同备案日期")
    private LocalDate filingContractDate;


    @Schema(description = "让与人")
    private String assignor;


    @Schema(description = "合同变更日期")
    private LocalDate contractChangeDate;


    @Schema(description = "受让人")
    private String assignee;


    @Schema(description = "合同解除日期")
    private LocalDate contractRescissionDate;

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyIprPatentLicenseDataMd5(licenseType, filingContractNo, filingContractDate, assignor, assignee);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprPatentId)
                && StrUtil.isEmpty(licenseType)
                && StrUtil.isEmpty(filingContractNo)
                && Objects.isNull(filingContractDate)
                && StrUtil.isEmpty(assignor)
                && Objects.isNull(contractChangeDate)
                && StrUtil.isEmpty(assignee)
                && Objects.isNull(contractRescissionDate);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprPatentLicenseExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyIprPatentId, exWarehouseVO.getCompanyIprPatentId())) {
            this.companyIprPatentId = null;
        }
        if (Objects.equals(licenseType, exWarehouseVO.getLicenseType())) {
            this.licenseType = null;
        }
        if (Objects.equals(filingContractNo, exWarehouseVO.getFilingContractNo())) {
            this.filingContractNo = null;
        }
        if (Objects.equals(filingContractDate, exWarehouseVO.getFilingContractDate())) {
            this.filingContractDate = null;
        }
        if (Objects.equals(assignor, exWarehouseVO.getAssignor())) {
            this.assignor = null;
        }
        if (Objects.equals(contractChangeDate, exWarehouseVO.getContractChangeDate())) {
            this.contractChangeDate = null;
        }
        if (Objects.equals(assignee, exWarehouseVO.getAssignee())) {
            this.assignee = null;
        }
        if (Objects.equals(contractRescissionDate, exWarehouseVO.getContractRescissionDate())) {
            this.contractRescissionDate = null;
        }

    }
    public static DataCompanyIprPatentLicenseWarehouseCommand createByDataCompanyIprPatentLicenseExWarehouseVO(DataCompanyIprPatentLicenseExWarehouseVO exWarehouseVO) {
        DataCompanyIprPatentLicenseWarehouseCommand command = new DataCompanyIprPatentLicenseWarehouseCommand();
        command.companyIprPatentId = exWarehouseVO.getCompanyIprPatentId();
        command.licenseType = exWarehouseVO.getLicenseType();
        command.filingContractNo = exWarehouseVO.getFilingContractNo();
        command.filingContractDate = exWarehouseVO.getFilingContractDate();
        command.assignor = exWarehouseVO.getAssignor();
        command.contractChangeDate = exWarehouseVO.getContractChangeDate();
        command.assignee = exWarehouseVO.getAssignee();
        command.contractRescissionDate = exWarehouseVO.getContractRescissionDate();

        return command;
    }
}
