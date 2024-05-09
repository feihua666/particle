package com.particle.crm.domain.customer;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDate;
/**
 * <p>
 * 客户 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Data
@Entity
public class CrmCustomer extends AggreateRoot {

    private CrmCustomerId id;

    /**
    * 客户编码
    */
    private String code;

    /**
    * 客户名称
    */
    private String name;

    /**
    * 客户称呼
    */
    private String appellation;

    /**
    * 客户类型，字典id，如：个人、企业
    */
    private Long typeDictId;

    /**
    * 客户性别，字典id
    */
    private Long genderDictId;

    /**
    * 客户年龄
    */
    private Integer age;

    /**
    * 客户生日
    */
    private LocalDate birthDay;
    
    /**
    * 客户公司id
    */
    private Long crmCompanyId;

    /**
    * 客户公司部门id
    */
    private Long crmDeptId;

    /**
    * 是否为黑名单
    */
    private Boolean isBlack;

    /**
    * 黑名单原因
    */
    private String blackReason;

    /**
    * 客户分类，字典id,如：新客户、老客户
    */
    private Long categoryDictId;

    /**
    * 归属用户id
    */
    private Long belongUserId;

    /**
    * 归属用户的公司id
    */
    private Long belongCompId;

    /**
    * 归属用户的部门id
    */
    private Long belongDeptId;

    /**
    * 唯一id，有可能不同的客户数据其实是同一个客户，这里唯一标识，默认为主键id
    */
    private Long unionId;

    /**
    * 备注
    */
    private String remark;



    /**
     * 创建客户领域模型对象
     * @return 客户领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CrmCustomer create(){
        return DomainFactory.create(CrmCustomer.class);
    }
}
