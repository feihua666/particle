package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkWarehouseCommand;

/**
 * <p>
 * 企业知识产权商标 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@Data
@Schema
public class DataCompanyIprTrademarkUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "商标名称")
    private String name;


    @Schema(description = "商标名称")
    private String nameCn;


    @Schema(description = "商标名称")
    private String nameEn;


    @Schema(description = "注册号")
    private String regNo;


    @Schema(description = "申请号")
    private String applyNo;


    @Schema(description = "尼斯分类号")
    private String niceCategoryNo;


    @Schema(description = "当前权利状态名称")
    private String rightStatusName;


    @Schema(description = "商标类型名称")
    private String typeName;


    @Schema(description = "商标图片地址")
    private String trademarkImageUrl;


    @Schema(description = "注册日期")
    private LocalDate regDate;


    @Schema(description = "申请日期")
    private LocalDate applyDate;


    @Schema(description = "是否共享")
    private Boolean isShare;


    @Schema(description = "是否驰名商标")
    private Boolean isWellKnown;


    @Schema(description = "是否指定颜色")
    private Boolean isSpecifyColor;


    @Schema(description = "优先权日期")
    private LocalDate priorityDate;


    @Schema(description = "专用权期限开始日期")
    private LocalDate specialStartDate;


    @Schema(description = "专用权期限截止日期")
    private LocalDate specialEndDate;


    @Schema(description = "初审公告日期")
    private LocalDate firstTrialPublicDate;


    @Schema(description = "初审公告号")
    private String firstTrialPublicNo;


    @Schema(description = "后期指定日期")
    private LocalDate lateSpecifyDate;


    @Schema(description = "异议截止日期")
    private LocalDate dissentEndDate;


    @Schema(description = "原始语种")
    private String originLang;


    @Schema(description = "注册公告期号")
    private String regPublicIssueNo;


    @Schema(description = "国际注册日期")
    private LocalDate internationalRegDate;


    public static DataCompanyIprTrademarkUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprTrademarkWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprTrademarkUpdateCommand command = new DataCompanyIprTrademarkUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.name = dataCompanyBasicWarehouseCommand.getName();
        command.nameCn = dataCompanyBasicWarehouseCommand.getNameCn();
        command.nameEn = dataCompanyBasicWarehouseCommand.getNameEn();
        command.regNo = dataCompanyBasicWarehouseCommand.getRegNo();
        command.applyNo = dataCompanyBasicWarehouseCommand.getApplyNo();
        command.niceCategoryNo = dataCompanyBasicWarehouseCommand.getNiceCategoryNo();
        command.rightStatusName = dataCompanyBasicWarehouseCommand.getRightStatusName();
        command.typeName = dataCompanyBasicWarehouseCommand.getTypeName();
        command.trademarkImageUrl = dataCompanyBasicWarehouseCommand.getTrademarkImageUrl();
        command.regDate = dataCompanyBasicWarehouseCommand.getRegDate();
        command.applyDate = dataCompanyBasicWarehouseCommand.getApplyDate();
        command.isShare = dataCompanyBasicWarehouseCommand.getIsShare();
        command.isWellKnown = dataCompanyBasicWarehouseCommand.getIsWellKnown();
        command.isSpecifyColor = dataCompanyBasicWarehouseCommand.getIsSpecifyColor();
        command.priorityDate = dataCompanyBasicWarehouseCommand.getPriorityDate();
        command.specialStartDate = dataCompanyBasicWarehouseCommand.getSpecialStartDate();
        command.specialEndDate = dataCompanyBasicWarehouseCommand.getSpecialEndDate();
        command.firstTrialPublicDate = dataCompanyBasicWarehouseCommand.getFirstTrialPublicDate();
        command.firstTrialPublicNo = dataCompanyBasicWarehouseCommand.getFirstTrialPublicNo();
        command.lateSpecifyDate = dataCompanyBasicWarehouseCommand.getLateSpecifyDate();
        command.dissentEndDate = dataCompanyBasicWarehouseCommand.getDissentEndDate();
        command.originLang = dataCompanyBasicWarehouseCommand.getOriginLang();
        command.regPublicIssueNo = dataCompanyBasicWarehouseCommand.getRegPublicIssueNo();
        command.internationalRegDate = dataCompanyBasicWarehouseCommand.getInternationalRegDate();


        return command;
    }
}
