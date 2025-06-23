package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprIntegratedCircuitWarehouseCommand;

/**
 * <p>
 * 企业知识产权集成电路 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
@Data
@Schema
public class DataCompanyIprIntegratedCircuitUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "公告号")
    private String publicNo;


    @Schema(description = "公告日期")
    private LocalDate publicDate;


    @Schema(description = "布图设计名称")
    private String name;


    @Schema(description = "布图设计登记号")
    private String regNo;


    @Schema(description = "布图设计申请日")
    private LocalDate applyDate;


    @Schema(description = "布图设计权利人名称")
    private String rightHolder;


    @Schema(description = "是否权利人为自然人")
    private Boolean isRightHolderNaturalPerson;


    @Schema(description = "权利人公司id")
    private Long rightHolderCompanyId;


    @Schema(description = "权利人个人id")
    private Long rightHolderCompanyPersonId;


    @Schema(description = "布图设计权利人国籍")
    private String rightHolderCountry;


    @Schema(description = "布图设计权利人地址")
    private String rightHolderAddress;


    @Schema(description = "布图设计创作人")
    private String designCreator;


    @Schema(description = "布图设计创作完成日")
    private LocalDate completeDate;


    @Schema(description = "布图设计类别")
    private String typeName;


    @Schema(description = "首次商业利用日期 ")
    private LocalDate firstBusinessDate;


    public static DataCompanyIprIntegratedCircuitUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprIntegratedCircuitWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprIntegratedCircuitUpdateCommand command = new DataCompanyIprIntegratedCircuitUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.publicNo = dataCompanyBasicWarehouseCommand.getPublicNo();
        command.publicDate = dataCompanyBasicWarehouseCommand.getPublicDate();
        command.name = dataCompanyBasicWarehouseCommand.getName();
        command.regNo = dataCompanyBasicWarehouseCommand.getRegNo();
        command.applyDate = dataCompanyBasicWarehouseCommand.getApplyDate();
        command.rightHolder = dataCompanyBasicWarehouseCommand.getRightHolder();
        command.isRightHolderNaturalPerson = dataCompanyBasicWarehouseCommand.getIsRightHolderNaturalPerson();
        command.rightHolderCompanyId = dataCompanyBasicWarehouseCommand.getRightHolderCompanyId();
        command.rightHolderCompanyPersonId = dataCompanyBasicWarehouseCommand.getRightHolderCompanyPersonId();
        command.rightHolderCountry = dataCompanyBasicWarehouseCommand.getRightHolderCountry();
        command.rightHolderAddress = dataCompanyBasicWarehouseCommand.getRightHolderAddress();
        command.designCreator = dataCompanyBasicWarehouseCommand.getDesignCreator();
        command.completeDate = dataCompanyBasicWarehouseCommand.getCompleteDate();
        command.typeName = dataCompanyBasicWarehouseCommand.getTypeName();
        command.firstBusinessDate = dataCompanyBasicWarehouseCommand.getFirstBusinessDate();


        return command;
    }
}
