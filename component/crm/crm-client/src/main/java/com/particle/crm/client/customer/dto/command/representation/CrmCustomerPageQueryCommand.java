package com.particle.crm.client.customer.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
/**
 * <p>
 * 客户 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Data
@Schema
public class CrmCustomerPageQueryCommand extends AbstractBasePageQueryCommand {



    @Like
        @Schema(description = "客户编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "客户名称,左前缀匹配")
    private String name;


    @Like
        @Schema(description = "客户称呼,左前缀匹配")
    private String appellation;


    @Schema(description = "客户类型")
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


    @Schema(description = "是否为黑名单")
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










}
