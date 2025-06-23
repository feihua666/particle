package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentLegalStatusWarehouseCommand;

/**
 * <p>
 * 企业知识产权专利法律状态 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Data
@Schema
public class DataCompanyIprPatentLegalStatusUpdateCommand extends AbstractBaseUpdateCommand {




    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;

    @Schema(description = "法律状态")
    private Long legalStatusDictId;

	@Schema(description = "法律状态代码")
	private String legalStatusCode;

	@Schema(description = "原始法律状态名称")
	private String legalStatusName;

	@Schema(description = "英文法律状态名称")
	private String legalStatusNameEn;

	@Schema(description = "中文法律状态名称")
	private String legalStatusNameCn;


    @Schema(description = "原始法律状态详情")
    private String legalStatusDetail;

	@Schema(description = "英文法律状态详情")
	private String legalStatusDetailEn;

	@Schema(description = "中文法律状态详情")
	private String legalStatusDetailCn;

    @Schema(description = "法律状态日期")
    private LocalDate legalStatusDate;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;
    public static DataCompanyIprPatentLegalStatusUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprPatentLegalStatusWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprPatentLegalStatusUpdateCommand command = new DataCompanyIprPatentLegalStatusUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.companyIprPatentId = dataCompanyBasicWarehouseCommand.getCompanyIprPatentId();
        command.legalStatusDictId = dataCompanyBasicWarehouseCommand.getLegalStatusDictId();
        command.legalStatusCode = dataCompanyBasicWarehouseCommand.getLegalStatusCode();
        command.legalStatusName = dataCompanyBasicWarehouseCommand.getLegalStatusName();
        command.legalStatusNameEn = dataCompanyBasicWarehouseCommand.getLegalStatusNameEn();
        command.legalStatusNameCn = dataCompanyBasicWarehouseCommand.getLegalStatusNameCn();
        command.legalStatusDetail = dataCompanyBasicWarehouseCommand.getLegalStatusDetail();
        command.legalStatusDetailEn = dataCompanyBasicWarehouseCommand.getLegalStatusDetailEn();
        command.legalStatusDetailCn = dataCompanyBasicWarehouseCommand.getLegalStatusDetailCn();
        command.legalStatusDate = dataCompanyBasicWarehouseCommand.getLegalStatusDate();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
