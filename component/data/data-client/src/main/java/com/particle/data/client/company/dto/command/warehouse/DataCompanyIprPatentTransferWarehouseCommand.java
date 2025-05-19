package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentTransferExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权专利转让信息入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprPatentTransferWarehouseCommand extends AbstractBaseCommand {

    @NotNull(message = "企业知识产权专利表id 不能为空")
    @Schema(description = "企业知识产权专利表id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprPatentId;


    @Schema(description = "转移类型")
    private String transferType;


    @Schema(description = "部门")
    private String dept;


    @Schema(description = "变更前权利人")
    private String changeBeforeRightHolder;


    @Schema(description = "变更前地址")
    private String changeBeforeAddress;


    @Schema(description = "变更后权利人")
    private String changeAfterRightHolder;


    @Schema(description = "变更后地址")
    private String changeAfterAddress;


    @Schema(description = "当前权利人")
    private String currentRightHolder;


    @Schema(description = "当前地址")
    private String currentAddress;


    @Schema(description = "变更生效日期")
    private LocalDate changeEffectiveDate;

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyIprPatentTransferDataMd5(transferType, dept, changeBeforeRightHolder, changeAfterRightHolder, currentRightHolder);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprPatentId)
                && StrUtil.isEmpty(transferType)
                && StrUtil.isEmpty(dept)
                && StrUtil.isEmpty(changeBeforeRightHolder)
                && StrUtil.isEmpty(changeBeforeAddress)
                && StrUtil.isEmpty(changeAfterRightHolder)
                && StrUtil.isEmpty(changeAfterAddress)
                && StrUtil.isEmpty(currentRightHolder)
                && StrUtil.isEmpty(currentAddress)
                && Objects.isNull(changeEffectiveDate);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprPatentTransferExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyIprPatentId, exWarehouseVO.getCompanyIprPatentId())) {
            this.companyIprPatentId = null;
        }
        if (Objects.equals(transferType, exWarehouseVO.getTransferType())) {
            this.transferType = null;
        }
        if (Objects.equals(dept, exWarehouseVO.getDept())) {
            this.dept = null;
        }
        if (Objects.equals(changeBeforeRightHolder, exWarehouseVO.getChangeBeforeRightHolder())) {
            this.changeBeforeRightHolder = null;
        }
        if (Objects.equals(changeBeforeAddress, exWarehouseVO.getChangeBeforeAddress())) {
            this.changeBeforeAddress = null;
        }
        if (Objects.equals(changeAfterRightHolder, exWarehouseVO.getChangeAfterRightHolder())) {
            this.changeAfterRightHolder = null;
        }
        if (Objects.equals(changeAfterAddress, exWarehouseVO.getChangeAfterAddress())) {
            this.changeAfterAddress = null;
        }
        if (Objects.equals(currentRightHolder, exWarehouseVO.getCurrentRightHolder())) {
            this.currentRightHolder = null;
        }
        if (Objects.equals(currentAddress, exWarehouseVO.getCurrentAddress())) {
            this.currentAddress = null;
        }
        if (Objects.equals(changeEffectiveDate, exWarehouseVO.getChangeEffectiveDate())) {
            this.changeEffectiveDate = null;
        }

    }
    public static DataCompanyIprPatentTransferWarehouseCommand createByDataCompanyIprPatentTransferExWarehouseVO(DataCompanyIprPatentTransferExWarehouseVO exWarehouseVO) {
        DataCompanyIprPatentTransferWarehouseCommand command = new DataCompanyIprPatentTransferWarehouseCommand();
        command.companyIprPatentId = exWarehouseVO.getCompanyIprPatentId();
        command.transferType = exWarehouseVO.getTransferType();
        command.dept = exWarehouseVO.getDept();
        command.changeBeforeRightHolder = exWarehouseVO.getChangeBeforeRightHolder();
        command.changeBeforeAddress = exWarehouseVO.getChangeBeforeAddress();
        command.changeAfterRightHolder = exWarehouseVO.getChangeAfterRightHolder();
        command.changeAfterAddress = exWarehouseVO.getChangeAfterAddress();
        command.currentRightHolder = exWarehouseVO.getCurrentRightHolder();
        command.currentAddress = exWarehouseVO.getCurrentAddress();
        command.changeEffectiveDate = exWarehouseVO.getChangeEffectiveDate();

        return command;
    }
}
