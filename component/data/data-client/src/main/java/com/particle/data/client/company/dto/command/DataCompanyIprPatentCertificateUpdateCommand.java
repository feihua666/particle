package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentCertificateWarehouseCommand;

/**
 * <p>
 * 企业知识产权专利证书信息 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:02
 */
@Data
@Schema
public class DataCompanyIprPatentCertificateUpdateCommand extends AbstractBaseUpdateCommand {




    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;


    @Schema(description = "专利证书发文日")
    private LocalDate publicDate;


    @Schema(description = "专利证书挂号")
    private String mailNo;


    @Schema(description = "专利证书收件人姓名")
    private String receiverName;


    @Schema(description = "专利证书收件人地址")
    private String receiverAddress;

    public static DataCompanyIprPatentCertificateUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprPatentCertificateWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprPatentCertificateUpdateCommand command = new DataCompanyIprPatentCertificateUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.companyIprPatentId = dataCompanyBasicWarehouseCommand.getCompanyIprPatentId();
        command.publicDate = dataCompanyBasicWarehouseCommand.getPublicDate();
        command.mailNo = dataCompanyBasicWarehouseCommand.getMailNo();
        command.receiverName = dataCompanyBasicWarehouseCommand.getReceiverName();
        command.receiverAddress = dataCompanyBasicWarehouseCommand.getReceiverAddress();

        return command;
    }
}
