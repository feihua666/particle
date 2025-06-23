package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprSoftwareCopyrightWarehouseCommand;

/**
 * <p>
 * 企业知识产权软件著作 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
@Data
@Schema
public class DataCompanyIprSoftwareCopyrightCreateCommand extends AbstractBaseCommand {

    @Schema(description = "注册号")
    private String regNo;


    @Schema(description = "分类号")
    private String categoryNo;


    @Schema(description = "软件全称")
    private String name;


    @Schema(description = "软件简称")
    private String nameSimple;


    @Schema(description = "版本号")
    private String versionNo;


    @Schema(description = "著作权人")
    private String copyrightOwner;


    @Schema(description = "是否著作权人为自然人")
    private Boolean isCopyrightOwnerNaturalPerson;


    @Schema(description = "著作权人公司id")
    private Long copyrightOwnerCompanyId;


    @Schema(description = "著作权人个人id")
    private Long copyrightOwnerCompanyPersonId;


    @Schema(description = "首次发表日期")
    private LocalDate firstPublicDate;


    @Schema(description = "登记日期")
    private LocalDate regDate;


    @Schema(description = "国家")
    private String country;

    public static DataCompanyIprSoftwareCopyrightCreateCommand createByWarehouseCommand(DataCompanyIprSoftwareCopyrightWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprSoftwareCopyrightCreateCommand command = new DataCompanyIprSoftwareCopyrightCreateCommand();
        command.regNo = dataCompanyBasicWarehouseCommand.getRegNo();
        command.categoryNo = dataCompanyBasicWarehouseCommand.getCategoryNo();
        command.name = dataCompanyBasicWarehouseCommand.getName();
        command.nameSimple = dataCompanyBasicWarehouseCommand.getNameSimple();
        command.versionNo = dataCompanyBasicWarehouseCommand.getVersionNo();
        command.copyrightOwner = dataCompanyBasicWarehouseCommand.getCopyrightOwner();
        command.isCopyrightOwnerNaturalPerson = dataCompanyBasicWarehouseCommand.getIsCopyrightOwnerNaturalPerson();
        command.copyrightOwnerCompanyId = dataCompanyBasicWarehouseCommand.getCopyrightOwnerCompanyId();
        command.copyrightOwnerCompanyPersonId = dataCompanyBasicWarehouseCommand.getCopyrightOwnerCompanyPersonId();
        command.firstPublicDate = dataCompanyBasicWarehouseCommand.getFirstPublicDate();
        command.regDate = dataCompanyBasicWarehouseCommand.getRegDate();
        command.country = dataCompanyBasicWarehouseCommand.getCountry();


        return command;
    }
}
