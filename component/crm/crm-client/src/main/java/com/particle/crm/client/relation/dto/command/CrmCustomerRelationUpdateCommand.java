package com.particle.crm.client.relation.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 客户与客户关系 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Data
@Schema
public class CrmCustomerRelationUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "客户id 不能为空")
        @Schema(description = "客户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long crmCustomerId;


    @NotNull(message = "另一个客户id 不能为空")
        @Schema(description = "另一个客户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long anotherCrmCustomerId;


    @NotNull(message = "关系id 不能为空")
        @Schema(description = "关系id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long crmCustomerRelationDefineId;


    @Schema(description = "关系详情描述")
    private String relationDetail;



}
