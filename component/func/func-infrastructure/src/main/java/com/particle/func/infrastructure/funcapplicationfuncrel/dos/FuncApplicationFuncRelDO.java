package com.particle.func.infrastructure.funcapplicationfuncrel.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 功能应用功能关系表
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Data
@Accessors(chain = true)
@TableName("component_func_application_func_rel")
public class FuncApplicationFuncRelDO extends BaseDO {

    /**
    * 功能应用id
    */
    private Long funcApplicationId;

    /**
    * 功能id
    */
    private Long funcId;


}
