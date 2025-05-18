package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyHonorQualificationWarehouseCommand;

/**
 * <p>
 * 企业荣誉资质 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Data
@Schema
public class DataCompanyHonorQualificationUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "企业表ID 不能为空")
        @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "等级")
    private String level;


    @Schema(description = "发布日期")
    private LocalDate publishDate;


    @Schema(description = "发布日期")
    private LocalDate startDate;


    @Schema(description = "发布日期")
    private LocalDate endDate;


    @Schema(description = "证书编号")
    private String certificateNo;


    @Schema(description = "发布单位")
    private String publishOffice;


    @Schema(description = "发布标题")
    private String publishTitle;

    public static DataCompanyHonorQualificationUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyHonorQualificationWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyHonorQualificationUpdateCommand command = new DataCompanyHonorQualificationUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.name = dataCompanyBasicWarehouseCommand.getName();
        command.level = dataCompanyBasicWarehouseCommand.getLevel();
        command.publishDate = dataCompanyBasicWarehouseCommand.getPublishDate();
        command.startDate = dataCompanyBasicWarehouseCommand.getStartDate();
        command.endDate = dataCompanyBasicWarehouseCommand.getEndDate();
        command.certificateNo = dataCompanyBasicWarehouseCommand.getCertificateNo();
        command.publishOffice = dataCompanyBasicWarehouseCommand.getPublishOffice();
        command.publishTitle = dataCompanyBasicWarehouseCommand.getPublishTitle();

        return command;
    }
}
