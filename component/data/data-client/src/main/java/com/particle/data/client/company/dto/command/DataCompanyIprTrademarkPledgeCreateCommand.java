package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkPledgeWarehouseCommand;

/**
 * <p>
 * 企业知识产权商标质押信息 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Data
@Schema
public class DataCompanyIprTrademarkPledgeCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权商标id 不能为空")
        @Schema(description = "企业知识产权商标id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprTrademarkId;


    @Schema(description = "出质人")
    private String pledgor;


    @Schema(description = "质权人")
    private String pledgee;


    @Schema(description = "质权登记起始日期")
    private LocalDate pledgeRegStartDate;


    @Schema(description = "质权登记截止日期")
    private LocalDate pledgeRegEndDate;


    @Schema(description = "质权公告页号")
    private String pledgePublicPageNo;


    @NotEmpty(message = "数据md5 不能为空")
        @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;


    public static DataCompanyIprTrademarkPledgeCreateCommand createByWarehouseCommand(DataCompanyIprTrademarkPledgeWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprTrademarkPledgeCreateCommand command = new DataCompanyIprTrademarkPledgeCreateCommand();
        command.companyIprTrademarkId = dataCompanyBasicWarehouseCommand.getCompanyIprTrademarkId();
        command.pledgor = dataCompanyBasicWarehouseCommand.getPledgor();
        command.pledgee = dataCompanyBasicWarehouseCommand.getPledgee();
        command.pledgeRegStartDate = dataCompanyBasicWarehouseCommand.getPledgeRegStartDate();
        command.pledgeRegEndDate = dataCompanyBasicWarehouseCommand.getPledgeRegEndDate();
        command.pledgePublicPageNo = dataCompanyBasicWarehouseCommand.getPledgePublicPageNo();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
