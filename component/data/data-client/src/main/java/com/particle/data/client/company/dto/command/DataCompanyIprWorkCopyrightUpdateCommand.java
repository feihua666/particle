package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprWorkCopyrightWarehouseCommand;

/**
 * <p>
 * 企业知识产权作品著作 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:45
 */
@Data
@Schema
public class DataCompanyIprWorkCopyrightUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "注册号")
    private String regNo;


    @Schema(description = "作品名称")
    private String name;


    @Schema(description = "作品类别")
    private String typeName;


    @Schema(description = "著作权人")
    private String copyrightOwner;


    @Schema(description = "是否著作权人为自然人")
    private Boolean isCopyrightOwnerNaturalPerson;


    @Schema(description = "著作权人公司id")
    private Long copyrightOwnerCompanyId;


    @Schema(description = "著作权人个人id")
    private Long copyrightOwnerCompanyPersonId;


    @Schema(description = "国家")
    private String country;


    @Schema(description = "省")
    private String province;


    @Schema(description = "市")
    private String city;


    @Schema(description = "作者")
    private String author;


    @Schema(description = "创作完成日期")
    private LocalDate completeDate;


    @Schema(description = "首次发表日期")
    private LocalDate firstPublicDate;


    @Schema(description = "登记日期")
    private LocalDate regDate;


    @Schema(description = "发布日期")
    private LocalDate publicDate;



    public static DataCompanyIprWorkCopyrightUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprWorkCopyrightWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprWorkCopyrightUpdateCommand command = new DataCompanyIprWorkCopyrightUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.regNo = dataCompanyBasicWarehouseCommand.getRegNo();
        command.name = dataCompanyBasicWarehouseCommand.getName();
        command.typeName = dataCompanyBasicWarehouseCommand.getTypeName();
        command.copyrightOwner = dataCompanyBasicWarehouseCommand.getCopyrightOwner();
        command.isCopyrightOwnerNaturalPerson = dataCompanyBasicWarehouseCommand.getIsCopyrightOwnerNaturalPerson();
        command.copyrightOwnerCompanyId = dataCompanyBasicWarehouseCommand.getCopyrightOwnerCompanyId();
        command.copyrightOwnerCompanyPersonId = dataCompanyBasicWarehouseCommand.getCopyrightOwnerCompanyPersonId();
        command.country = dataCompanyBasicWarehouseCommand.getCountry();
        command.province = dataCompanyBasicWarehouseCommand.getProvince();
        command.city = dataCompanyBasicWarehouseCommand.getCity();
        command.author = dataCompanyBasicWarehouseCommand.getAuthor();
        command.completeDate = dataCompanyBasicWarehouseCommand.getCompleteDate();
        command.firstPublicDate = dataCompanyBasicWarehouseCommand.getFirstPublicDate();
        command.regDate = dataCompanyBasicWarehouseCommand.getRegDate();
        command.publicDate = dataCompanyBasicWarehouseCommand.getPublicDate();


        return command;
    }
}
