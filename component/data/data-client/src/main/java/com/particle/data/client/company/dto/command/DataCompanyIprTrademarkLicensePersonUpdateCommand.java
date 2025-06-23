package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkLicensePersonWarehouseCommand;

/**
 * <p>
 * 企业知识产权商标许可人 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Data
@Schema
public class DataCompanyIprTrademarkLicensePersonUpdateCommand extends AbstractBaseUpdateCommand {

    @Schema(description = "企业知识产权商标许可id")
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




    public static DataCompanyIprTrademarkLicensePersonUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprTrademarkLicensePersonWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprTrademarkLicensePersonUpdateCommand command = new DataCompanyIprTrademarkLicensePersonUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.companyIprTrademarkLicenseId = dataCompanyBasicWarehouseCommand.getCompanyIprTrademarkLicenseId();
        command.licenseName = dataCompanyBasicWarehouseCommand.getLicenseName();
        command.licenseNameCn = dataCompanyBasicWarehouseCommand.getLicenseNameCn();
        command.licenseNameEn = dataCompanyBasicWarehouseCommand.getLicenseNameEn();
        command.isLicensed = dataCompanyBasicWarehouseCommand.getIsLicensed();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
