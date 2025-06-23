package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkTransferWarehouseCommand;

/**
 * <p>
 * 企业知识产权商标转让信息 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
@Data
@Schema
public class DataCompanyIprTrademarkTransferCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权商标id 不能为空")
        @Schema(description = "企业知识产权商标id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprTrademarkId;


    @Schema(description = "转让期号")
    private String transferIssueNo;


    @Schema(description = "转让页码")
    private String transferPageNo;


    @Schema(description = "转让公告日期")
    private LocalDate transferPublicDate;


    @NotEmpty(message = "数据md5 不能为空")
        @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;


    public static DataCompanyIprTrademarkTransferCreateCommand createByWarehouseCommand(DataCompanyIprTrademarkTransferWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprTrademarkTransferCreateCommand command = new DataCompanyIprTrademarkTransferCreateCommand();
                command.companyIprTrademarkId = dataCompanyBasicWarehouseCommand.getCompanyIprTrademarkId();
        command.transferIssueNo = dataCompanyBasicWarehouseCommand.getTransferIssueNo();
        command.transferPageNo = dataCompanyBasicWarehouseCommand.getTransferPageNo();
        command.transferPublicDate = dataCompanyBasicWarehouseCommand.getTransferPublicDate();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
