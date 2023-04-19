package com.particle.tenant.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 租户 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Data
@Entity
public class Tenant extends AggreateRoot {

    private TenantId id;

    /**
    * 租户编码,模糊查询
    */
    private String code;

    /**
    * 租户名称,模糊查询
    */
    private String name;

    /**
    * 是否禁用
    */
    private Boolean isDisabled;

    /**
    * 禁用原因
    */
    private String disabledReason;

    /**
    * 联系人姓名
    */
    private String contactUserName;

    /**
    * 联系人邮箱
    */
    private String contactUserEmail;

    /**
    * 联系人电话
    */
    private String contactUserPhone;

    /**
    * 租户域名，可根据域名识别租户如：example.com
    */
    private String tenantDomain;

    /**
    * 租户主题，一个代表客户端主题样式的字符串
    */
    private String tenantTheme;

    /**
    * 租户默认的页面路由
    */
    private String tenantDefaultRoute;
    /**
     * 租户logo地址
     */
    private String tenantLogoUrl;
    /**
    * 额外配置json
    */
    private String configJson;

    /**
    * 描述
    */
    private String remark;



    /**
     * 创建租户领域模型对象
     * @return 租户领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static Tenant create(){
        return DomainFactory.create(Tenant.class);
    }
}
