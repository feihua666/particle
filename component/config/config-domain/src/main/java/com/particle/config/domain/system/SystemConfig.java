package com.particle.config.domain.system;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 系统参数配置 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Data
@Entity
public class SystemConfig extends AggreateRoot {

    private SystemConfigId id;

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



    /**
     * 创建系统参数配置领域模型对象
     * @return 系统参数配置领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static SystemConfig create(){
        return DomainFactory.create(SystemConfig.class);
    }
}
