package com.particle.dataconstraint.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 数据范围自定义数据关系 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Data
@Entity
public class DataScopeCustomDataRel extends AggreateRoot {

    private DataScopeCustomDataRelId id;

    /**
    * 数据范围id
    */
    private Long dataScopeId;

    /**
    * 自定义数据id
    */
    private Long dataId;



    /**
     * 创建数据范围自定义数据关系领域模型对象
     * @return 数据范围自定义数据关系领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataScopeCustomDataRel create(){
        return DomainFactory.create(DataScopeCustomDataRel.class);
    }
}
