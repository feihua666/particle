package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkLicensePersonWarehouseCommand;

/**
 * <p>
 * 企业知识产权商标许可人 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Data
@Schema
public class DataCompanyIprTrademarkLicensePersonCreateCommand extends AbstractBaseCommand {



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


    @NotEmpty(message = "数据md5 不能为空")
        @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;



    public static DataCompanyIprTrademarkLicensePersonCreateCommand createByWarehouseCommand(DataCompanyIprTrademarkLicensePersonWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprTrademarkLicensePersonCreateCommand command = new DataCompanyIprTrademarkLicensePersonCreateCommand();
                command.companyIprTrademarkLicenseId = dataCompanyBasicWarehouseCommand.getCompanyIprTrademarkLicenseId();
        command.licenseName = dataCompanyBasicWarehouseCommand.getLicenseName();
        command.licenseNameCn = dataCompanyBasicWarehouseCommand.getLicenseNameCn();
        command.licenseNameEn = dataCompanyBasicWarehouseCommand.getLicenseNameEn();
        command.isLicensed = dataCompanyBasicWarehouseCommand.getIsLicensed();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
