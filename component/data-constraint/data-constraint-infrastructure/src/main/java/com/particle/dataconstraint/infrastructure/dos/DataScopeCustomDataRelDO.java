package com.particle.dataconstraint.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据范围自定义数据关系表
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Accessors(chain = true)
@Data
@TableName("component_data_scope_custom_data_rel")
public class DataScopeCustomDataRelDO extends BaseDO {

    /**
    * 数据范围id
    */
    private Long dataScopeId;

    /**
    * 自定义数据id
    */
    private Long dataId;


}
