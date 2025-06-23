package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicensePersonExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业知识产权商标许可人入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprTrademarkLicensePersonWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权商标许可id 不能为空")
        @Schema(description = "企业知识产权商标许可id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprTrademarkLicenseId;


    @Schema(description = "原始许可人名称")
    private String licenseName;


    @Schema(description = "中文许可人名称")
    private String licenseNameCn;


    @Schema(description = "英文许可人名称")
    private String licenseNameEn;


    @Schema(description = "是否为被许可人")
    private Boolean isLicensed;


    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyIprTrademarkLicensePersonDataMd5(licenseName,isLicensed);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprTrademarkLicenseId)
                && StrUtil.isEmpty(licenseName)
                && StrUtil.isEmpty(licenseNameCn)
                && StrUtil.isEmpty(licenseNameEn)
                && Objects.isNull(isLicensed)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprTrademarkLicensePersonExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyIprTrademarkLicenseId, exWarehouseVO.getCompanyIprTrademarkLicenseId())) {
            this.companyIprTrademarkLicenseId = null;
        }
        if (Objects.equals(licenseName, exWarehouseVO.getLicenseName())) {
            this.licenseName = null;
        }
        if (Objects.equals(licenseNameCn, exWarehouseVO.getLicenseNameCn())) {
            this.licenseNameCn = null;
        }
        if (Objects.equals(licenseNameEn, exWarehouseVO.getLicenseNameEn())) {
            this.licenseNameEn = null;
        }
        if (Objects.equals(isLicensed, exWarehouseVO.getIsLicensed())) {
            this.isLicensed = null;
        }
    }

    public static DataCompanyIprTrademarkLicensePersonWarehouseCommand createByDataCompanyIprTrademarkLicensePersonExWarehouseVO(DataCompanyIprTrademarkLicensePersonExWarehouseVO exWarehouseVO) {
        DataCompanyIprTrademarkLicensePersonWarehouseCommand command = new DataCompanyIprTrademarkLicensePersonWarehouseCommand();
        command.companyIprTrademarkLicenseId = exWarehouseVO.getCompanyIprTrademarkLicenseId();
        command.licenseName = exWarehouseVO.getLicenseName();
        command.licenseNameCn = exWarehouseVO.getLicenseNameCn();
        command.licenseNameEn = exWarehouseVO.getLicenseNameEn();
        command.isLicensed = exWarehouseVO.getIsLicensed();

        return command;
    }
}
