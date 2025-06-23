package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportWebsiteWarehouseCommand;

/**
 * <p>
 * 企业年报网站网店 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Data
@Schema
public class DataCompanyAnnualReportWebsiteUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "企业表ID")
    private Long companyId;



    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;



    @Schema(description = "年报年度")
    private Integer year;


    @Schema(description = "类型")
    private Long typeDictId;

	@Schema(description = "类型名称，如：网站、网店")
	private String typeName;


    @Schema(description = "名称")
    private String name;


    @NotEmpty(message = "网址 不能为空")
    @Schema(description = "网址")
    private String url;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;
    public static DataCompanyAnnualReportWebsiteUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyAnnualReportWebsiteWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyAnnualReportWebsiteUpdateCommand command = new DataCompanyAnnualReportWebsiteUpdateCommand();
        command.setId(id);
        command.setVersion(version);
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
