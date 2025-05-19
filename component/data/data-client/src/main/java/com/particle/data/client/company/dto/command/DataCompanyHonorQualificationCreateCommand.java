package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyHonorQualificationWarehouseCommand;

/**
 * <p>
 * 企业荣誉资质 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Data
@Schema
public class DataCompanyHonorQualificationCreateCommand extends AbstractBaseCommand {



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

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;

    public static DataCompanyHonorQualificationCreateCommand createByWarehouseCommand(DataCompanyHonorQualificationWarehouseCommand dataCompanyHonorQualificationWarehouseCommand){
        DataCompanyHonorQualificationCreateCommand command = new DataCompanyHonorQualificationCreateCommand();
        command.companyId = dataCompanyHonorQualificationWarehouseCommand.getCompanyId();
        command.name = dataCompanyHonorQualificationWarehouseCommand.getName();
        command.level = dataCompanyHonorQualificationWarehouseCommand.getLevel();
        command.publishDate = dataCompanyHonorQualificationWarehouseCommand.getPublishDate();
        command.startDate = dataCompanyHonorQualificationWarehouseCommand.getStartDate();
        command.endDate = dataCompanyHonorQualificationWarehouseCommand.getEndDate();
        command.certificateNo = dataCompanyHonorQualificationWarehouseCommand.getCertificateNo();
        command.publishOffice = dataCompanyHonorQualificationWarehouseCommand.getPublishOffice();
        command.publishTitle = dataCompanyHonorQualificationWarehouseCommand.getPublishTitle();
        command.dataMd5 = dataCompanyHonorQualificationWarehouseCommand.obtainDataMd5();


        return command;
    }
}
