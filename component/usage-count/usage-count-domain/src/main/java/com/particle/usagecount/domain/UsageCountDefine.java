package com.particle.usagecount.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 使用次数定义 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Data
@Entity
public class UsageCountDefine extends AggreateRoot {

    private UsageCountDefineId id;

    /**
    * 编码，唯一,模糊查询
    */
    private String code;

    /**
    * 名称,模糊查询
    */
    private String name;

    /**
    * 匹配的url地址，填写且能匹配后端限制
    */
    private String urlPattern;

    /**
    * 是否为分组，1=分组，0=配置
    */
    private Boolean isGroup;

    /**
    * 备注
    */
    private String remark;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

    /**
    * 父级
    */
    private Long parentId;



    /**
     * 创建使用次数定义领域模型对象
     * @return 使用次数定义领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static UsageCountDefine create(){
        return DomainFactory.create(UsageCountDefine.class);
    }
}
