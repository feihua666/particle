package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportWebsiteWarehouseCommand;

/**
 * <p>
 * 企业年报网站网店 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Data
@Schema
public class DataCompanyAnnualReportWebsiteCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业id 不能为空")
    @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @NotNull(message = "企业年报id 不能为空")
    @Schema(description = "企业年报id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyAnnualReportId;


    @NotNull(message = "年报年度 不能为空")
    @Schema(description = "年报年度",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer year;


    @NotNull(message = "类型 不能为空")
    @Schema(description = "类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long typeDictId;

	@Schema(description = "类型名称，如：网站、网店")
	private String typeName;


    @Schema(description = "名称")
    private String name;


    @NotEmpty(message = "网址 不能为空")
    @Schema(description = "网址",requiredMode = Schema.RequiredMode.REQUIRED)
    private String url;


    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;

    public static DataCompanyAnnualReportWebsiteCreateCommand createByWarehouseCommand(DataCompanyAnnualReportWebsiteWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyAnnualReportWebsiteCreateCommand command = new DataCompanyAnnualReportWebsiteCreateCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.companyAnnualReportId = dataCompanyBasicWarehouseCommand.getCompanyAnnualReportId();
        command.year = dataCompanyBasicWarehouseCommand.getYear();
        command.typeDictId = dataCompanyBasicWarehouseCommand.getTypeDictId();
        command.typeName = dataCompanyBasicWarehouseCommand.getTypeName();
        command.name = dataCompanyBasicWarehouseCommand.getName();
        command.url = dataCompanyBasicWarehouseCommand.getUrl();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
