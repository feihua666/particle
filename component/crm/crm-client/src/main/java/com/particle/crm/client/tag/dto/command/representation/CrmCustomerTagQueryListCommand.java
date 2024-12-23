package com.particle.crm.client.tag.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 客户标签 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Data
@Schema
public class CrmCustomerTagQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "标签编码")
    private String code;


    @Schema(description = "标签名称")
    private String name;


    @Schema(description = "备注")
    private String remark;









}
