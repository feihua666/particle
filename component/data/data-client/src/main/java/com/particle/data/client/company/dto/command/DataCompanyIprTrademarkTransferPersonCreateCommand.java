package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkTransferPersonWarehouseCommand;

/**
 * <p>
 * 企业知识产权商标转让人 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Data
@Schema
public class DataCompanyIprTrademarkTransferPersonCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权商标转让id 不能为空")
        @Schema(description = "企业知识产权商标转让id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprTrademarkTransferId;


    @Schema(description = "原始转让人名称")
    private String transferName;


    @Schema(description = "中文转让人名称")
    private String transferNameCn;


    @Schema(description = "英文转让人名称")
    private String transferNameEn;


    @Schema(description = "是否被转让人")
    private Boolean isTransferred;


    @NotEmpty(message = "数据md5 不能为空")
        @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;


    public static DataCompanyIprTrademarkTransferPersonCreateCommand createByWarehouseCommand(DataCompanyIprTrademarkTransferPersonWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprTrademarkTransferPersonCreateCommand command = new DataCompanyIprTrademarkTransferPersonCreateCommand();
                command.companyIprTrademarkTransferId = dataCompanyBasicWarehouseCommand.getCompanyIprTrademarkTransferId();
        command.transferName = dataCompanyBasicWarehouseCommand.getTransferName();
        command.transferNameCn = dataCompanyBasicWarehouseCommand.getTransferNameCn();
        command.transferNameEn = dataCompanyBasicWarehouseCommand.getTransferNameEn();
        command.isTransferred = dataCompanyBasicWarehouseCommand.getIsTransferred();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
