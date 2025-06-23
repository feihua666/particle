package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkLicenseWarehouseCommand;

/**
 * <p>
 * 企业知识产权商标许可信息 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:10
 */
@Data
@Schema
public class DataCompanyIprTrademarkLicenseUpdateCommand extends AbstractBaseUpdateCommand {


    @Schema(description = "企业知识产权商标id")
    private Long companyIprTrademarkId;


    @Schema(description = "许可期号")
    private String licenseIssueNo;


    @Schema(description = "许可页码")
    private String licensePageNo;


    @Schema(description = "许可备案号")
    private String licenseFilingNo;


    @Schema(description = "许可备案公告日期")
    private LocalDate licenseFilingPublicDate;


    @Schema(description = "许可期限")
    private String licenseTerm;


    @Schema(description = "许可使用的商品服务")
    private String licenseGoodService;


    @Schema(description = "数据md5")
    private String dataMd5;


    public static DataCompanyIprTrademarkLicenseUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprTrademarkLicenseWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprTrademarkLicenseUpdateCommand command = new DataCompanyIprTrademarkLicenseUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.companyIprTrademarkId = dataCompanyBasicWarehouseCommand.getCompanyIprTrademarkId();
        command.licenseIssueNo = dataCompanyBasicWarehouseCommand.getLicenseIssueNo();
        command.licensePageNo = dataCompanyBasicWarehouseCommand.getLicensePageNo();
        command.licenseFilingNo = dataCompanyBasicWarehouseCommand.getLicenseFilingNo();
        command.licenseFilingPublicDate = dataCompanyBasicWarehouseCommand.getLicenseFilingPublicDate();
        command.licenseTerm = dataCompanyBasicWarehouseCommand.getLicenseTerm();
        command.licenseGoodService = dataCompanyBasicWarehouseCommand.getLicenseGoodService();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
