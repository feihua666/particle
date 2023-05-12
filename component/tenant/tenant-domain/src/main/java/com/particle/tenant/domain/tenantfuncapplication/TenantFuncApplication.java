package com.particle.tenant.domain.tenantfuncapplication;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 租户功能应用 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Data
@Entity
public class TenantFuncApplication extends AggreateRoot {

    private TenantFuncApplicationId id;

    /**
    * 功能应用id
    */
    private Long funcApplicationId;

    /**
    * 名称
    */
    private String name;

    /**
    * 应用主题，一个代表客户端主题样式的字符串
    */
    private String applicationTheme;

    /**
    * 应用默认的页面路由
    */
    private String applicationDefaultRoute;


    /**
     * 应用logo地址
     */
    private String applicationLogoUrl;
    /**
     * 应用图标地址
     */
    private String applicationIconUrl;
    /**
    * 额外配置json
    */
    private String configJson;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 简单填充属性，用于简单添加
     * 当前添加该方法主要是租户申请审批通过后添加租户应用，所以不需要复杂的属性
     * @param funcApplicationId
     * @param tenantId
     */
    public void simpleFill(Long funcApplicationId, Long tenantId) {
        this.funcApplicationId = funcApplicationId;
        this.tenantId = tenantId;
    }

    /**
     * 创建租户功能应用领域模型对象
     * @return 租户功能应用领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static TenantFuncApplication create(){
        return DomainFactory.create(TenantFuncApplication.class);
    }
}
