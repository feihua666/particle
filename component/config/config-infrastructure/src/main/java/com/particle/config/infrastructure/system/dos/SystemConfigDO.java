package com.particle.config.infrastructure.system.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
/**
 * <p>
 * 系统参数配置表
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Data
@TableName("component_system_config")
public class SystemConfigDO extends BaseDO {

    /**
    * 参数配置编码，唯一
    */
    private String code;

    /**
    * 参数配置名称
    */
    private String name;

    /**
    * 参数配置值
    */
    private String value;

    /**
    * 是否内置，一般内置的不可删除，否则可能引起错误
    */
    private Boolean isBuiltIn;

    /**
    * 是否禁用
    */
    private Boolean isDisabled;

    /**
    * 禁用原因
    */
    private String disabledReason;

    /**
    * 标签，一个标签，可用于标识一类数据
    */
    private String tag;

    /**
    * 描述
    */
    private String remark;


}
