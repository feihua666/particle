package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentContentWarehouseCommand;

/**
 * <p>
 * 企业裁判文书内容 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Data
@Schema
public class DataCompanyJudgmentDocumentContentUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "裁判文书表id")
    private Long companyJudgmentDocumentId;


    @Schema(description = "裁判内容")
    private String content;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;
    public static DataCompanyJudgmentDocumentContentUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyJudgmentDocumentContentWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyJudgmentDocumentContentUpdateCommand command = new DataCompanyJudgmentDocumentContentUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.companyJudgmentDocumentId = dataCompanyBasicWarehouseCommand.getCompanyJudgmentDocumentId();
        command.content = dataCompanyBasicWarehouseCommand.getContent();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
