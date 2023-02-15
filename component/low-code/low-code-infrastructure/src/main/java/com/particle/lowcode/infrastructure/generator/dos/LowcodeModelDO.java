package com.particle.lowcode.infrastructure.generator.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 低代码模型表
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Data
@TableName("component_lowcode_model")
public class LowcodeModelDO extends BaseDO {
    /**
    * 名称
    */
    private String name;
    /**
     * 名称
     */
    private String nameEn;
    /**
    * 表名称
    */
    private String tableName;
    /**
    * 模型表类型字典id，rel,tree,normal
    */
    private Long tableTypeDictId;
    /**
    * 描述,注意事项等
    */
    private String remark;

}
