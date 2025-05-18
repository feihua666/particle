package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentContentWarehouseCommand;

/**
 * <p>
 * 企业裁判文书内容 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:53
 */
@Data
@Schema
public class DataCompanyJudgmentDocumentContentCreateCommand extends AbstractBaseCommand {



    @Schema(description = "裁判文书表id")
    private Long companyJudgmentDocumentId;


    @Schema(description = "裁判内容")
    private String content;



    public static DataCompanyJudgmentDocumentContentCreateCommand createByWarehouseCommand(DataCompanyJudgmentDocumentContentWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyJudgmentDocumentContentCreateCommand command = new DataCompanyJudgmentDocumentContentCreateCommand();
                command.companyJudgmentDocumentId = dataCompanyBasicWarehouseCommand.getCompanyJudgmentDocumentId();
        command.content = dataCompanyBasicWarehouseCommand.getContent();

        return command;
    }
}
