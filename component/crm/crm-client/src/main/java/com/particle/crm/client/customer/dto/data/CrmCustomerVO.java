package com.particle.crm.client.customer.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
/**
 * <p>
 * 客户 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Data
@Schema
public class CrmCustomerVO extends AbstractBaseIdVO {

    @Schema(description = "客户编码")
    private String code;
    
    @Schema(description = "客户名称")
    private String name;
    
    @Schema(description = "客户称呼")
    private String appellation;
    
    @Schema(description = "客户类型")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "客户类型对应字典名称")
    private String typeDictName;
        
    @Schema(description = "客户性别")
    private Long genderDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "genderDictId",mapValueField = "name")
    @Schema(description = "客户性别对应字典名称")
    private String genderDictName;
        
    @Schema(description = "客户年龄")
    private Integer age;
    
    @Schema(description = "客户生日")
    private LocalDate birthDay;
        
    @Schema(description = "客户公司id")
    private Long crmCompanyId;


    @TransBy(tableName = TransTableNameConstants.component_crm_company, byFieldName = "crmCompanyId", mapValueField = "name")
    @Schema(description = "客户公司名称")
    private String crmCompanyName;
    
    @Schema(description = "客户公司部门id")
    private Long crmDeptId;


    @TransBy(tableName = TransTableNameConstants.component_crm_dept, byFieldName = "crmDeptId", mapValueField = "name")
    @Schema(description = "客户公司部门名称")
    private String crmDeptName;

    @Schema(description = "是否为黑名单")
    private Boolean isBlack;
    
    @Schema(description = "黑名单原因")
    private String blackReason;
    
    @Schema(description = "客户分类")
    private Long categoryDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "categoryDictId",mapValueField = "name")
    @Schema(description = "客户分类对应字典名称")
    private String categoryDictName;
        
    @Schema(description = "归属用户id")
    private Long belongUserId;

    @TransBy(type = TransConstants.TRANS_USER_BY_ID,byFieldName = "belongUserId",mapValueField = "nickname")
    @Schema(description = "归属用户昵称")
    private String belongUserNickname;
    
    @Schema(description = "归属用户的公司id")
    private Long belongCompId;

    @TransBy(type = TransConstants.TRANS_DEPT_BY_ID,byFieldName = "belongCompId",mapValueField = "name")
    @Schema(description = "归属用户的公司名称")
    private String belongCompName;
    
    @Schema(description = "归属用户的部门id")
    private Long belongDeptId;

    @TransBy(type = TransConstants.TRANS_DEPT_BY_ID,byFieldName = "belongDeptId",mapValueField = "name")
    @Schema(description = "归属用户的部门名称")
    private String belongDeptName;
    
    @Schema(description = "唯一id")
    private Long unionId;


    @TransBy(tableName = TransTableNameConstants.component_crm_customer, byFieldName = "id", mapValueField = "name")
    @Schema(description = "唯一客户名称")
    private String unionCustomerName;

    @Schema(description = "备注")
    private String remark;
    


}
