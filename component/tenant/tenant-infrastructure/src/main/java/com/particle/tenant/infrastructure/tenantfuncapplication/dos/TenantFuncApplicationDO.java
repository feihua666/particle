package com.particle.tenant.infrastructure.tenantfuncapplication.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 租户功能应用表
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Data
@TableName("component_tenant_func_application")
public class TenantFuncApplicationDO extends BaseDO {

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


}
