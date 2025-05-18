package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPersonWarehouseCommand;

/**
 * <p>
 * 企业个人 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Data
@Schema
public class DataCompanyPersonUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "姓名 不能为空")
    @Schema(description = "姓名",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "证号")
    private String idNo;

	@Schema(description = "脱敏证号")
	private String idNoDesensitized;


    @Schema(description = "手机号")
    private String mobile;


    @Schema(description = "手机号运营商")
    private Long mobileOperatorDictId;


    @Schema(description = "手机号1")
    private String mobile1;


    @Schema(description = "手机号1运营商")
    private Long mobile1OperatorDictId;


    @Schema(description = "手机号2")
    private String mobile2;


    @Schema(description = "手机号2运营商")
    private Long mobile2OperatorDictId;

	@Schema(description = "处理备注，用来标识一些处理备注")
	private String handleRemark;



    public static DataCompanyPersonUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyPersonWarehouseCommand dataCompanyPersonWarehouseCommand){
        DataCompanyPersonUpdateCommand command = new DataCompanyPersonUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.name = dataCompanyPersonWarehouseCommand.getName();
        command.idNo = dataCompanyPersonWarehouseCommand.getIdNo();
        command.idNoDesensitized = dataCompanyPersonWarehouseCommand.getIdNoDesensitized();
        command.mobile = dataCompanyPersonWarehouseCommand.getMobile();
        command.mobileOperatorDictId = dataCompanyPersonWarehouseCommand.getMobileOperatorDictId();
        command.mobile1 = dataCompanyPersonWarehouseCommand.getMobile1();
        command.mobile1OperatorDictId = dataCompanyPersonWarehouseCommand.getMobile1OperatorDictId();
        command.mobile2 = dataCompanyPersonWarehouseCommand.getMobile2();
        command.mobile2OperatorDictId = dataCompanyPersonWarehouseCommand.getMobile2OperatorDictId();
        command.handleRemark = dataCompanyPersonWarehouseCommand.getHandleRemark();


        return command;
    }
}
