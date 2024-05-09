package com.particle.crm.client.customer.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * <p>
 * 客户 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Data
@Schema
public class CrmCustomerCreateCommand extends AbstractBaseCommand {



    @Schema(description = "客户编码")
    private String code;

    @NotEmpty(message = "客户名称 不能为空")
    @Schema(description = "客户名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


        @Schema(description = "客户称呼")
    private String appellation;

    @NotNull(message = "客户类型 不能为空")
    @Schema(description = "客户类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long typeDictId;


    @Schema(description = "客户性别")
    private Long genderDictId;


    @Schema(description = "客户年龄")
    private Integer age;


    @Schema(description = "客户生日")
    private LocalDate birthDay;
    

        @Schema(description = "客户公司id")
    private Long crmCompanyId;


        @Schema(description = "客户公司部门id")
    private Long crmDeptId;


    @NotNull(message = "是否为黑名单 不能为空")
        @Schema(description = "是否为黑名单",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isBlack;


    @Schema(description = "黑名单原因")
    private String blackReason;


    @Schema(description = "客户分类")
    private Long categoryDictId;


    @Schema(description = "归属用户id")
    private Long belongUserId;


    @Schema(description = "归属用户的公司id")
    private Long belongCompId;


    @Schema(description = "归属用户的部门id")
    private Long belongDeptId;


    @Schema(description = "唯一id")
    private Long unionId;


    @Schema(description = "备注")
    private String remark;









}
