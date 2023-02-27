package com.particle.global.mybatis.plus.crud;

import com.particle.global.mybatis.plus.dto.BaseDO;

/**
 * 服务帮助类
 * @author yangwei
 * @since 2023/02/17
 */
public class ServiceHelperTool {


    /**
     * 清空 BaseDO对应的所有字段，一般用于复制实体时使用
     * @param po
     */
    public static <Po extends BaseDO> void emptyBaseDOFields(Po po){

        po.setId(null);
        po.setCreateAt(null);
        po.setCreateBy(null);

        po.setUpdateAt(null);
        po.setUpdateBy(null);

        po.setVersion(null);

        po.setTenantId(null);

        po.setAddControl(null);
        po.setUpdateControl(null);
        po.setQueryControl(null);
        po.setDeleteControl(null);
    }
}
