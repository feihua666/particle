package com.particle.data.client.company.dto.command.warehouse;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentStatisticExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业知识产权专利统计入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprPatentStatisticWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权专利表id 不能为空")
    @Schema(description = "企业知识产权专利表id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprPatentId;


    @Schema(description = "同族专利数量")
    private Integer familyNum;


    @Schema(description = "扩展同族专利数量")
    private Integer extFamilyNum;


    @Schema(description = "被引证数量")
    private Integer citedNum;


    @Schema(description = "引证专利数量")
    private Integer quoteNum;


    @Schema(description = "权利要求数量")
    private Integer claimNum;


    @Schema(description = "独权数")
    private Integer independentClaimNum;


    @Schema(description = "从权数")
    private Integer dependentClaimNum;


    @Schema(description = "转让次数")
    private Integer transferNum;


    @Schema(description = "许可次数")
    private Integer licenseNum;


    @Schema(description = "质押次数")
    private Integer pledgeNum;


    @Schema(description = "无效次数")
    private Integer invalidNum;


    @Schema(description = "诉讼次数")
    private Integer litigationNum;


    @Schema(description = "IPC分类数量")
    private Integer ipcCategoryNum;


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprPatentId)
                && Objects.isNull(familyNum)
                && Objects.isNull(extFamilyNum)
                && Objects.isNull(citedNum)
                && Objects.isNull(quoteNum)
                && Objects.isNull(claimNum)
                && Objects.isNull(independentClaimNum)
                && Objects.isNull(dependentClaimNum)
                && Objects.isNull(transferNum)
                && Objects.isNull(licenseNum)
                && Objects.isNull(pledgeNum)
                && Objects.isNull(invalidNum)
                && Objects.isNull(litigationNum)
                && Objects.isNull(ipcCategoryNum);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprPatentStatisticExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyIprPatentId, exWarehouseVO.getCompanyIprPatentId())) {
            this.companyIprPatentId = null;
        }
        if (Objects.equals(familyNum, exWarehouseVO.getFamilyNum())) {
            this.familyNum = null;
        }
        if (Objects.equals(extFamilyNum, exWarehouseVO.getExtFamilyNum())) {
            this.extFamilyNum = null;
        }
        if (Objects.equals(citedNum, exWarehouseVO.getCitedNum())) {
            this.citedNum = null;
        }
        if (Objects.equals(quoteNum, exWarehouseVO.getQuoteNum())) {
            this.quoteNum = null;
        }
        if (Objects.equals(claimNum, exWarehouseVO.getClaimNum())) {
            this.claimNum = null;
        }
        if (Objects.equals(independentClaimNum, exWarehouseVO.getIndependentClaimNum())) {
            this.independentClaimNum = null;
        }
        if (Objects.equals(dependentClaimNum, exWarehouseVO.getDependentClaimNum())) {
            this.dependentClaimNum = null;
        }
        if (Objects.equals(transferNum, exWarehouseVO.getTransferNum())) {
            this.transferNum = null;
        }
        if (Objects.equals(licenseNum, exWarehouseVO.getLicenseNum())) {
            this.licenseNum = null;
        }
        if (Objects.equals(pledgeNum, exWarehouseVO.getPledgeNum())) {
            this.pledgeNum = null;
        }
        if (Objects.equals(invalidNum, exWarehouseVO.getInvalidNum())) {
            this.invalidNum = null;
        }
        if (Objects.equals(litigationNum, exWarehouseVO.getLitigationNum())) {
            this.litigationNum = null;
        }
        if (Objects.equals(ipcCategoryNum, exWarehouseVO.getIpcCategoryNum())) {
            this.ipcCategoryNum = null;
        }

    }

    public static DataCompanyIprPatentStatisticWarehouseCommand createByDataCompanyIprPatentStatisticExWarehouseVO(DataCompanyIprPatentStatisticExWarehouseVO exWarehouseVO) {
        DataCompanyIprPatentStatisticWarehouseCommand command = new DataCompanyIprPatentStatisticWarehouseCommand();
        command.companyIprPatentId = exWarehouseVO.getCompanyIprPatentId();
        command.familyNum = exWarehouseVO.getFamilyNum();
        command.extFamilyNum = exWarehouseVO.getExtFamilyNum();
        command.citedNum = exWarehouseVO.getCitedNum();
        command.quoteNum = exWarehouseVO.getQuoteNum();
        command.claimNum = exWarehouseVO.getClaimNum();
        command.independentClaimNum = exWarehouseVO.getIndependentClaimNum();
        command.dependentClaimNum = exWarehouseVO.getDependentClaimNum();
        command.transferNum = exWarehouseVO.getTransferNum();
        command.licenseNum = exWarehouseVO.getLicenseNum();
        command.pledgeNum = exWarehouseVO.getPledgeNum();
        command.invalidNum = exWarehouseVO.getInvalidNum();
        command.litigationNum = exWarehouseVO.getLitigationNum();
        command.ipcCategoryNum = exWarehouseVO.getIpcCategoryNum();


        return command;
    }
}
