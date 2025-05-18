package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业知识产权专利通知书信息 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyIprPatentNoticeExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @NotNull(message = "企业知识产权专利id 不能为空")
    @Schema(description = "企业知识产权专利id")
    private Long companyIprPatentId;

    public static DataCompanyIprPatentNoticeExWarehouseQueryCommand create(Long companyIprPatentId) {
        DataCompanyIprPatentNoticeExWarehouseQueryCommand command = new DataCompanyIprPatentNoticeExWarehouseQueryCommand();
        command.companyIprPatentId = companyIprPatentId;
        return command;
    }
}
