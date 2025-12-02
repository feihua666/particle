package com.particle.data.domain.dynamicdata;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 动态数据指标 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
@Data
@Entity
public class DynamicDataIndicator extends AggreateRoot {

    private DynamicDataIndicatorId id;

    /**
    * 动态数据分类id
    */
    private Long dynamicDataCategoryId;

    /**
    * 动态数据指标分类id
    */
    private Long dynamicDataIndicatorCategoryId;

    /**
    * 指标名称
    */
    private String name;

    /**
    * 是否禁用，1=是，0=否
    */
    private Boolean isDisabled;

    /**
    * 备注信息
    */
    private String remark;



    /**
     * 创建动态数据指标领域模型对象
     * @return 动态数据指标领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DynamicDataIndicator create(){
        return DomainFactory.create(DynamicDataIndicator.class);
    }
}
