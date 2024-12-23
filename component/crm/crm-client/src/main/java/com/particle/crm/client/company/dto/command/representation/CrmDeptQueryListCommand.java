package com.particle.crm.client.company.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 客户公司部门 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Data
@Schema
public class CrmDeptQueryListCommand extends AbstractBaseTreeQueryCommand {



    @Schema(description = "部门编码")
    private String code;


    @Schema(description = "部门名称")
    private String name;


    @Schema(description = "客户公司id")
    private Long crmCompanyId;


    @Schema(description = "备注")
    private String remark;


    @Schema(description = "排序")
    private Integer seq;





















}
