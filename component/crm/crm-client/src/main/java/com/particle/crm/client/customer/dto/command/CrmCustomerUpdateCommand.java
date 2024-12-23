package com.particle.crm.client.customer.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * <p>
 * 客户 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Data
@Schema
public class CrmCustomerUpdateCommand extends AbstractBaseUpdateCommand {



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


    @SetNullWhenNull
    @Schema(description = "客户性别")
    private Long genderDictId;


    @SetNullWhenNull
    @Schema(description = "客户年龄")
    private Integer age;

    @SetNullWhenNull
    @Schema(description = "客户生日")
    private LocalDate birthDay;

    @SetNullWhenNull
    @Schema(description = "客户公司id")
    private Long crmCompanyId;

    @SetNullWhenNull
    @Schema(description = "客户公司部门id")
    private Long crmDeptId;


    @NotNull(message = "是否为黑名单 不能为空")
    @Schema(description = "是否为黑名单",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isBlack;

    @SetNullWhenNull
    @Schema(description = "黑名单原因")
    private String blackReason;

    @SetNullWhenNull
    @Schema(description = "客户分类")
    private Long categoryDictId;

    @SetNullWhenNull
    @Schema(description = "归属用户id")
    private Long belongUserId;

    @SetNullWhenNull
    @Schema(description = "归属用户的公司id")
    private Long belongCompId;

    @SetNullWhenNull
    @Schema(description = "归属用户的部门id")
    private Long belongDeptId;

    @SetNullWhenNull
    @Schema(description = "唯一id")
    private Long unionId;


    @Schema(description = "备注")
    private String remark;









}
