package com.particle.func.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 功能组表
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@TableName("component_func_group")
public class FuncGroupDO extends BaseTreeDO {
    /**
    * 编码，模糊查询
    */
    private String code;
    /**
    * 名称，模糊查询
    */
    private String name;
    /**
    * 描述
    */
    private String remark;

}
