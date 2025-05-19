package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyRestrictHighConsumeWarehouseCommand;

/**
 * <p>
 * 企业限制高消费 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Data
@Schema
public class DataCompanyRestrictHighConsumeCreateCommand extends AbstractBaseCommand {


    @Schema(description = "案号")
    private String caseNo;


    @Schema(description = "文件链接")
    private String attachUrl;


    @Schema(description = "文件快照链接")
    private String attachSnapshotUrl;


    @Schema(description = "立案日期")
    private LocalDate fileCaseDate;


    @Schema(description = "发布日期")
    private LocalDate publishDate;


    @Schema(description = "执行法院公司id")
    private Long executeCourtCompanyId;


    @Schema(description = "执行法院名称")
    private String executeCourtName;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;

    public static DataCompanyRestrictHighConsumeCreateCommand createByWarehouseCommand(DataCompanyRestrictHighConsumeWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyRestrictHighConsumeCreateCommand command = new DataCompanyRestrictHighConsumeCreateCommand();
        command.caseNo = dataCompanyBasicWarehouseCommand.getCaseNo();
        command.attachUrl = dataCompanyBasicWarehouseCommand.getAttachUrl();
        command.attachSnapshotUrl = dataCompanyBasicWarehouseCommand.getAttachSnapshotUrl();
        command.fileCaseDate = dataCompanyBasicWarehouseCommand.getFileCaseDate();
        command.publishDate = dataCompanyBasicWarehouseCommand.getPublishDate();
        command.executeCourtCompanyId = dataCompanyBasicWarehouseCommand.getExecuteCourtCompanyId();
        command.executeCourtName = dataCompanyBasicWarehouseCommand.getExecuteCourtName();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();


        return command;
    }
}
