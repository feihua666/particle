package com.particle.tenant.infrastructure.tenantfunc.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 租户功能菜单表
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Data
@Accessors(chain = true)
@TableName("component_tenant_func")
public class TenantFuncDO extends BaseDO {

    /**
    * 用户id
    */
    private Long funcId;

    /**
    * 名称
    */
    private String name;


}
